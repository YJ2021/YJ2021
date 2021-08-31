package com.insurance.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.insurance.config.AlipayConfig;
import com.insurance.pojo.Insurance_Pay;
import com.insurance.service.Insurance_PayService;
import com.insurance.util.EmptyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Api("支付业务Controller")
@RestController
@RequestMapping(value = "/pay")
@Slf4j
public class Insurance_PayController {

    @Autowired
    Insurance_PayService payService;

    @ApiOperation("确认支付订单")
    @RequestMapping(value = "/prepay/{orderNo}")
    public ModelAndView loadInsPayOrder(HttpServletRequest request, HttpServletResponse response, @PathVariable String orderNo){
        System.out.println("根据订单编号查询订单对象"+orderNo);
            Insurance_Pay proOrder=payService.searchProDetails(orderNo);
            HttpSession session=request.getSession();
            if(EmptyUtils.isNotEmpty(proOrder)){
                session.setAttribute("orderNo",proOrder.getOrderNo());
                session.setAttribute("proName",proOrder.getProName());
                session.setAttribute("payAmount",proOrder.getPayAmount());
                session.setAttribute("endDate",proOrder.getEndDate());
                session.setAttribute("proBName",proOrder.getProBName());
                session.setAttribute("proRName",proOrder.getProRName());
                RedirectView redirectView=new RedirectView("/pay.jsp");
                ModelAndView mv=new ModelAndView();
                mv.setView(redirectView);
                return mv;
            }
        return null;
    }

    /**
     * 向支付宝提交支付请求
     *
     * @param WIDout_trade_no
     *            商户订单号，商户网站订单系统中唯一订单号，必填
     * @param WIDsubject
     *            订单名称，必填
     * @param WIDtotal_amount
     *            付款金额，必填
     */
	/*@ApiOperation(value = "订单支付", httpMethod = "POST",
			protocols = "HTTP", produces = "application/xml", consumes="application/x-www-form-urlencoded",
			response =  String.class,
			WIDout_trade_no:订单编号
			WIDsubject:订单名称
			WIDtotal_amount:订单金额
			notes = "客户端提交订单支付请求，对该API的返回结果不用处理，浏览器将自动跳转至支付宝。<br><b>请使用普通表单提交，不能使用ajax异步提交。</b>")	*/
    @RequestMapping(value = "/testPay")
    public void pay(String WIDout_trade_no,String WIDsubject,String WIDtotal_amount, HttpServletResponse response) {
        System.out.println("准备支付宝支付...");
        System.out.println("订单号："+WIDout_trade_no);
        System.out.println("订单名称："+WIDsubject);
        System.out.println("支付金额："+WIDtotal_amount);
        //获得初始化的AlipayClient
        /***
         * serverUrl 支付宝网关
         * privatekey 私钥
         * alipaypublickey 支付宝公钥
         * */
        //AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","2016101600702705","MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCu2UMZX9IEXn+H2ptW9scAHoG+J0SSbmTz2EZb+44j1cM0WqGgPat7Edi3HVp0y4yj5qCpZxfKy1Dm2jwUAyWbBM/PyrxqeA0K9/kUDYIpF6PgT41EdLkwL2o4gg/bM+0CCdNbkghRxHbV7VC+z71ebEMiKDRo4Zg0s9LJZzt5jywkdAsAXeSIj49nXDAURduhik2K18xHKmPeXyVculQg1IQ/fBTIZWZXw1ViFa3q2NOg/LBkiguduIJHX9yhoTe69RjiiHYg9gDgl6XvDyWFV1jmIc/5pht3AOOJU57RAS+qTbF6Y+D5EzpSr4WSKwwqYQbKcofOrEaMl97HmOzBAgMBAAECggEARV/Y2rGFL8yaxzl6lwe1L5vrGJqV/4+jqIiwagCmhhtjp1sqc9zkNCGBni4cXOkCdWmlZ3GLJCCFigMfAUW6XwaKucST+56EdkyrXER713gUwoRt5bJ2Y+L8P02DoxK4QC3L2bJYcLEGAZ12gy8D0PodrOjM2qxf6tFKmjTGiVUWEbW+oi9fwZmnT1bcdW3ESaxUf5GoNE/yoiNBnFSq6qOTQ3Vwegxi7LDFxkPXI4vOQvw5z2/q8MQ8NRfs7IhNMKdJjkLtkwwoh36nPUnlLhOdyMf6HQ20RCw3WIY7Cyfz3AOpo1/KFZTHiJkxdLbBYmQPFM1SN1aA4jEzTh8CvQKBgQD57mJRgRiZnIOIg+kM2SNN5dhQ+377qIZ6/Qf/xdUlgCoM9S+b/0nL4LM2H2gfn0OZr0Zv09hz1surPg1hUmcoUO3cZ/9HrRAfQtumAWCtfXhREaqxvieYSjt6HxsihCgJEHQMuWbOPOLuQsiKcGdo+xTW0ytUMKi1JEjGPhdAwwKBgQCzGCbtaR6LEov7xgV0zdBNIryqrg/WBmAvtFcFdZi+NE6Ivr+eqhZnzUdeJmqstmlodsW7B9xJeQ8mbulgR8ggZbOcUIM3jL1Vd0ShqbhUNwBWcEFV9vmDTdh8f0N8l3u9uOp43UntwOpld+hg4Y2MRiI5JxdnZUKafNC4ZboEKwKBgQDIVObj87l3L3hTDYDZNpdQ0kIwr1Yae/vHS1iFENsHoxKRrlpKDTfmvqaHZGc+qZcy8cZgzoq6V1qLWUK6VqWvMCdousdpeXPpytpq1sHabi7ptGKA9C2iqSXBfntukEXS9ig/JsEb4Lv5RPif1vdcs50BkOQzKImiIIJgvNZApwKBgGbMrDuGJUQKx1MjnSoooTJFiCooc2qUik2XpIO7tosnFxUi+HaohufaSubeAklVAzg1RNZQcr+xv2J+M3NSgKsn9Wr6Q/d0z5DpPvnUo7ujPoxfLwGbHCmkW2lK23/+q8aBCAWMb80K+QB5TWee0FL+RtKrf6GX3B01G9FcguO1AoGBALim7j3BR1FHcJQujV5ZHGaOH+J7ZWXMcN4LSxwKwiu6DeG790VPu0con0EtkhsmTuZeDhER8SS0KMzdhaGN2utcDxV+D9xFt40jCEmQRY7QFUL3z7cHmohEAtK3zb4FQKH4OYGMQV4xiqFoBevN5rLLFhSl/+3Mlu6ZKNWxGLTu","JSON","utf-8","MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAp1nsIlDtEyPfoqy4lnk0LcHbjjzd6bM2Do6rGLThmK3J3Rcl/2sDGcCsmynB+fpSwEjOjwo35rFZQ3Eft7EsGVmGNGatja6A68CUfU6WxZtaEPKobkQZKDDOqjeX6AUqygJIxL+9WtMY81UhhXAoKxDYrl2xe98qZPHEx2DCTbe5CkApBMCRR+rEAvMEfPYhK9j31xQfKGMx5xZwhXPzVlQIphXVAAgPHcU+woVQ4tEH+YsbpEbEkFwzbIoSkx3sT3kC6GCfsHjEifefnyx5JEsq6yoHRaA8Jtqpvk8SkFrmPzdH0RFesRPMpIgLwoXsmDjVg1Z5ZtYKGbqLuZD6SwIDAQAB","RSA2");
        //支付配置文件中获取相关信息
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.APP_ID,AlipayConfig.APP_PRIVATE_KEY,"JSON",AlipayConfig.CHARSET,AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 设置同步地址
        System.out.println("设置同步地址...");
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        System.out.println("返回执行成功...");
        // 设置异步通知地址
        //alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        // 封装请求支付信息
        //商户订单号，商户网站订单系统中唯一订单号，必填

        String out_trade_no =WIDout_trade_no;
        //付款金额，必填

        String total_amount =WIDtotal_amount;
        //订单名称，必填

        String subject="保险购买";
        //商品描述，可空
        String body=WIDsubject;
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        try {
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 支付宝页面跳转同步通知页面
     */
    @RequestMapping(value = "/return")
    public ModelAndView callback(String WIDout_trade_no,String WIDsubject,String WIDtotal_amount, HttpServletResponse response) {
        System.out.println("支付成功...");
        System.out.println("截取前订单编号：" + WIDout_trade_no);
        String str=WIDout_trade_no.substring(0,WIDout_trade_no.indexOf(","));
        String orderNo=WIDout_trade_no.substring(str.length()+1,WIDout_trade_no.length());
        System.out.println("截取后订单编号："+orderNo);
        //根据订单编号修改订单状态
        Map<String,Object> param=new HashMap<>();
        param.put("orderNo",orderNo);
        param.put("statusType",5);
        try {
            //获取支付的订单将istype=4改为5
            Integer flag =payService.update_ProPayStatus(param);
            if(flag>0){
                //调用定时器
                timer(orderNo);
            }
            //准备主页面数据
            Insurance_Pay order=payService.searchProDetails(orderNo);

            RedirectView redirectView = new RedirectView("http://127.0.0.1:8087/home.html?us="+ URLEncoder.encode(order.getUserName(),"utf-8") +"&uscode="+order.getUserCode());
            ModelAndView mv = new ModelAndView();
            mv.setView(redirectView);
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
          * 这个方法是调度一个task，经过2000(ms)后开始进行调度，仅仅调度一次。
          */
    public void timer(String orderId){
        Timer nTimer= new Timer();
        nTimer.schedule(new TimerTask() {
            public void run() {
                System.out.println("----设定要指定任务-----");
                //获取支付的订单将statusType=5改为6
                //根据订单编号修改订单状态
                Map<String,Object> param=new HashMap<>();
                param.put("orderNo",orderId);
                param.put("statusType",6);
                //生效时间
                param.put("effectDate",new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
                try {
                    Integer rows = payService.update_ProEffectDate(param);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },20000);
    }
    /**
          * 设置18：00执行任务(第一次调度的时间)，每过一天执行一次
          * java.util.Timer.scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
          */
 /*   public void timer4(){
Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,18);
        calendar.set(calendar.MINUTE,0);
        calendar.set(calendar.SECOND,0);
   Date time = calendar.getTime();
Timer timer = new Timer();
timer.scheduleAtFixedRate(new TimerTask() {
public void run(){
System.out.println("-------设定要指定任务--------");
}
},time,1000*60*60*24);// 这里设定将延时每天固定执行
}*/
}
