package com.insurance.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.insurance.pojo.Insurance_User;
import cz.mallat.uasparser.UserAgentInfo;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Slf4j
public class TokenUtil {
    private static String tokenPrefix = "token:";//统一加入 token前缀标识
    private static Jedis jedis = new Jedis("127.0.0.1", 6379);
    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }
    /***
     * @param agent Http头中的user-agent信息
     * @param us 用户信息
     * @return Token格式<br/>
     * 	PC：“前缀PC-USERCODE-USERID-CREATIONDATE-RONDEM[6位]”
     *  <br/>
     *  Android：“前缀ANDROID-USERCODE-USERID-CREATIONDATE-RONDEM[6位]”
     *  生成token
     */
    public static String getTokenGenerator(String agent, Insurance_User us) {
        try {
            UserAgentInfo userAgentInfo = UserAgentUtil.getUasParser().parse(agent);
            System.out.println("userAgentInfo:"+userAgentInfo.toString());
            StringBuilder sb = new StringBuilder();
            sb.append(tokenPrefix);//统一前缀
            if (userAgentInfo.getDeviceType().equals(UserAgentInfo.UNKNOWN)) {
                log.debug("【TokenUtil】设备类型未知");
                if (UserAgentUtil.CheckAgent(agent)) {
                    sb.append("MOBILE-");
                } else {
                    sb.append("PC-");
                }
            } else if (userAgentInfo.getDeviceType()
                    .equals("Personal computer")) {

                sb.append("PC-");
            } else {
                sb.append("MOBILE-");
            }
                sb.append(MD5Util.getMd5(us.getUserCode(), 32) + "-");//加密用户名称
                sb.append(us.getUserPassword() + "-");//用户名
                sb.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ "-");//当前系统时间
                sb.append(MD5Util.getMd5(agent, 6));// 识别客户端的简化实现——6位MD5码
                log.info("生成的token："+sb);
            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

       return  null;
    }
    /*token时间验证*/
    /*public static boolean validate(String agent, String token) {

        if (jedis.get(token)==null) {// token不存在
            return false;
        }
        try {
            Date TokenGenTime;// token生成时间
            String agentMD5;
            String[] tokenDetails = token.split("-");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            TokenGenTime = formatter.parse(tokenDetails[3]);
            long passed = Calendar.getInstance().getTimeInMillis()
                    - TokenGenTime.getTime();
            if(passed>60*60*1000)
                return false;
            agentMD5 = tokenDetails[4];
            if(MD5Util.getMd5(agent, 6).equals(agentMD5))
                return true;
        } catch (ParseException e) {
            return false;
        }
        return false;
    }*/
   /*删除token*/
    public static void delete(HttpServletRequest request, HttpServletResponse response, String token, String usersCoe) {
        if (jedis.get(token)!=null) {
            Cookie usernameCookie = new Cookie("token",null);//cookie名字要相同
            usernameCookie.setMaxAge(0); //
            usernameCookie.setPath(request.getContextPath());  // 相同路径
            response.addCookie(usernameCookie);
            jedis.del(token);
            jedis.del(usersCoe);

        }
    }
    /*置换token*/
    public static String replaceToken(String agent, String token,HttpServletResponse response )
            throws TokenValidationFailedException {
        // 验证旧token是否有效
        if (jedis.get(token)==null) {// token不存在
            throw new TokenValidationFailedException("未知的token或 token已过期");// 终止置换
        }
        Date TokenGenTime;// token生成时间
        try {
            String[] tokenDetails = jedis.get(token).split("-");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            TokenGenTime = formatter.parse(tokenDetails[3]);
            log.info("【TokenUtil】TokenGenTime:"+TokenGenTime);
        } catch (ParseException e) {
            throw new TokenValidationFailedException("token格式错误:" + token);
        }
        // token已产生时间
        long passed = Calendar.getInstance().getTimeInMillis() - TokenGenTime.getTime();
        if (passed <  30000) {// 置换保护期内
            throw new TokenValidationFailedException("token处于置换保护期内，剩余"
                    + (30000 - passed) / 1000
                    + "(s),禁止置换");
        }
        // 置换token
        String newToken = "";
        Insurance_User listuser = JSONArray.parseObject(jedis.get(jedis.get(token)).toString(), Insurance_User.class);
        long ttl = jedis.ttl(token);// token有效期（剩余秒数 ）
        if (ttl > 0 || ttl == -1) {// 兼容手机与PC端的token在有效期,判断是否在有效期
            //删除旧的token的用户
            jedis.del(jedis.get(token));
            jedis.del(token);
            newToken = getTokenGenerator(agent, listuser);
            //this.save(newToken, user);// 缓存新token
            //将token存入redis(因为将来取值时需要返回对象所以转成json存进去)
            String strJson = JSON.toJSONString(listuser);
            jedis.setex(newToken,7200,strJson);
            // 2分钟后旧token过期，注意手机端由永久有效变为2分钟（REPLACEMENT_DELAY默认值）后失效
            jedis.setex(listuser.getUserCode(), 7200, newToken);
            //后台存储到浏览器cookie
            Cookie userrnameCookie = new Cookie("token", newToken);
            userrnameCookie.setMaxAge(60 * 60 * 2);
            response.addCookie(userrnameCookie);
        } else {// 其它未考虑情况，不予置换
            throw new TokenValidationFailedException("当前token的过期时间异常,禁止置换");
        }
        return newToken;
    }
}
