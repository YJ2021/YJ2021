package com.insurance.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000118609503";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGS1pBf++G+/+QICWqBtplLBmeTIbvTBuHeQ/n4ZzxtgEnqVrm7tiYqjQzhzK7xem9PBUMMMPNRPweF82VzRdMo2nOEHn2/rD+o7/Ehq7Q/ZtzBdQkvt1wxWBp/Bh/kxOu8pNi/3/It8nNNgUsvbCUCg7iEoWv3Fm0Hp4zk1z6xcYPRNjBmt/QuU8+Dl61GMXbMxn1NfmfQYhVdl5BFzoZy6eTTSH75ZSNzv5DEwJqcmZa4P+PM3vIXV0vfP4yN0n/mTTstfyW7P4DYIgTvdu1Ds66EzUqM+1ftJBOJ5T4PFJfIj81ipyRARQbdkfdYzd9vMS5TJCPE5GYwEQeK3KlAgMBAAECggEAEWBurUsAk1bIGr4OIZIBcFXkercsPop6hW0EwdxPkncabydau8br/9UGLCSyS9mwBOxhTNKCg20JslsiHuAKw7/hUOiQ34TVIh3OEn/a3u/xzVBTwu1vecgQUaZtBRTi1yBMMeMdhbtCQWtcAA4LaxxiEJ6U7cx1TvknP0UyIsCEfsEd+/E9gshrbB0UMBJ3GuQ2FCddrkKlhrLktqltE+vcfCz9EqnpHI3JFcz+i6p5RcDyAWdydZhzMZ2FS/CMfsjJFOfbuCLEig5GnWheIo9h62DkyB66WoUvWOiPBQbh1knDlGr4BcL2LbBGfL/sxGzO0SktlIrgZ50zS68ZgQKBgQC/GWrJlcmXV+nCxDWeb1UOY6ryof4xSUEEuQu3epxqAgKlyrfirR31negbY8WHtryEDmXV2fejf6KCwuh6kjm+Z4HxNYq1qqYPBoczIvzrqqvswKAC82A5ZSsF4rYugIimDPoaGyJFPoCRFZ4OpczUPB2Ga9G61l9myUXsJJKAdwKBgQCz5zEZ4uezGP50q9LyHAYoFxHI4kTEyPNbuKkZcgHx+/u2MKELJvYeQko3xnwzb9DIsR+jW5cEz6VVxerEEFt6tWVGru3qLvq3iVKMnjAqo5QD8aMCmzBeNINyU4lP1q2rcLhFCYu1fSi5lZbj0Acgd+oDb4hlut0TET2jDscowwKBgQCAyJpEu+MdUSvCDASufwztI5Hi7fSmNl3Q1vvkbIbYkf5CyLjf1Bbqnb38J2LJMQceX62VTs0qrCtSwBN39u9fN9ZEPfb6ssQyQA4BEkUsdNpkNPQgyO9B/sHdt4lbEVgFv50U1tBjpdly7QSVjnrZVKH5ItQ2e53TYTT0DTmFxQKBgECinZeJWGmWX0WXvjBSNTwOHcuoz8BL2nNnwwlrVpuURvlMO3AATYjNlFC5tUHduIuCZ7WPYJDP8b0BYLmUjullZr+gdcYfnuB2K/xtkSygnLtWA5UaIKSYBWn77WeL39Wqp+nV5iYMk6cpwY6T3RujOaaGWeEC5A7HsPd2hcN1AoGASmRAO68RjIgcWZQ7FGUZZ/zQYru3nfKgCQO/tvNplJL2WPj/wp7Mf1iJgLQF80NUSAiyU4CNVzU2VNmuMNCY/ML7TFw0ZD9RdX8ypGKsdqLNw/Fhl0YROsxClEi/ULAHFA4WudfoaZn47T6SEhXo5O01B0iVOaGrd1QG7BnxyGs=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAw2sCHf/5XDjHoIoTeIqOc6Ku8ylTrf1P3mEHNDDqCTlzcQZVbUO/Q4V3Z6pVZ5KT+LsPBD0qW9iM7kBfpNJwoG7V20Dwxf0Vx8I6bHyTn6kZJOGkErMxylV8I8NiMBNQB3InMb3cSOuKyPyHTLRCSBUhM3KYJ9s+KwtE3AiM7PMqtqkQGp/MjUnXmhgbgu/n31SuiPffviMdGzG/3zNUR+w8pWgP9lFZqAbKcvyXnCxg2tbZDc65isGIfgJYFrtc8eSV2my1QL7qVkRoywnUCeixLDmU1Fky2cyBgXpUF5wUh8gSv72B9x3XoPOjOq92ws7y0dmlqjMNl+yBIh116wIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8090/alipay.trade.page.pay-JAVA-UTF-8/notify_url.html";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://localhost:8090/return_url.html";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志路径
    public static String log_path = "E:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
