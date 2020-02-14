package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101300674173";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCoN3cf2xq9Exq+mvW80QAN6EtH3t3KCOF8KZxDiEY0J/KE0P94QWTVhmtQ8"
			+ "xOv227zT6l56JUdeq1OuQP3NIlKe+eB/6g37XKL5kRzyXYVBpJpVN7+Bbp6wkUExsQLDR+W3VTuq8A4cVeDWZfPRV4hmlH+Yl6dt5A67CqGAwE"
			+ "eHfjj0vI5lZAZ/oiy6yn6EXzzX81AS2DAdEGiQOx1SvMAb5igmDQLNZBY8ztKntqlwOPCI0QMLKWuyhIlL5Z03UEQwv+jTNc189fLjSYAYeD9Y"
			+ "6hIZ/NOHxWw0Quowdiu2TawBsq1ffr3bRu8UNAD9mSUc6hgt6Gg+zgsPugTvnxvAgMBAAECggEAC4ehtqFB9+uzTZU5erOomUtuvYJdGVf6fJ"
			+ "QtjcNtbG3AhbH9kLSKwMfx2x6OHuOfezXem+iBMAAUmOy9hNgpAN31REfNs+emjkg+MOGCInbM2kNi9W5XFcrzCBzHmypPG/wXsjQ7fKLoKKy"
			+ "fZymOBieZryH28kB+tksxrvXNwc6XJbxnnwWKiZXP0OUueuSPC88+AMpq5dogH4SnkXmwI/R8+iTb/Sy9EgQNrLz3ww4OYtnYVKoitKr8MqvZ"
			+ "MERQSrisecqq4lJF/FdF0MQlpl5LCjWuu6OEKZMU44P8JkxGyTl9Ucn44yW/0q8lyqgLSWGMxHkGLhNQrInN8X38gQKBgQD7B/+up6+j2jki"
			+ "sx4YxuKQqDoaBn9yq/xdGqdWyzbk0/fBnUOZUb496n4geO0r0NaTBqVykgVJ0mgPh/N+BrjnfbXh580GymWZEofndrfY1IA9hEYd1xND0Hr/i"
			+ "5WhZEEzT9+hllftONB/3YG+xLu7ZShwo1umt0ay7l1paX6C0QKBgQCri9YmbHJCAx0VX/GTlttg/Q3jjTTPSjVxizP3VpK1oZqqLsQvAtP5Xq"
			+ "oCYde5UQziHqQpjhLg1IkrjYC4OWW+KP6SiSszwTP0fIoFxfZC1IvMwrklZ2qpsWtV00N8sHfPFkcoepnu5/NK62fGJSMR9yrz64iCX3nHIX"
			+ "jyglxbPwKBgQCFtuwFv4J6V/pJNnnYns/+V+2jAUUP8vaZLWJnhBV4bEQzmK5yU61vU6HdcCx9Q1+Yr1ifn5I7kV0msLcNsUz62eZxL8TxUgw"
			+ "uLkXAEGe7ku3AVt32dSLo43sw9cw9TKbCS1CGxQuO68173qSG1kpddnPFE+707fk1xGxzguMFkQKBgQCTau0SQfmCTuisWLHiMi6+T1ijJtKuH"
			+ "EiHFagHsmcLHSArhVrkR1rgHoCc4XYf883Nd6V9bHE+soZaXhSyu+h6HQJ/7M6qgCpkvK1jvrYcwkiNIv02boPT5fqmW5HQUlnzJWLrH0ivbLa"
			+ "/uEKKnZlgs/Y+fTbnSqL9A//K70nswwKBgCXs5t+h5tFz9cruj9QHroYib1C5FEDCcBduTlDNRZtlKSlz3mhQnpMD3GBRkERd6ao8exyGanRqk"
			+ "KWfZ+OjNRFNTmZQI3oC8Uz54N3H3+11GKwIKilVX1WGMttf/LNEWc5vT0774qh5FWdh4QUuOWr7WkFE4Ngn0i/7m3btMTEa";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "YZrUPMTr9tu80+peeiVHXqtTrkD9zSJSnvngf+oN+1yi+ZEc8l2FQaSaVTe/gW6esJFBMbECw0flt1U7qvAOHFXg1mXz0VeIZpR/mJenbe"
			+ "QOuwqhgMBHh3449LyOZWQGf6Isusp+hF881/NQEtgwHRBokDsdUrzAG+YoJg0CzWQWPM7Sp7apcDjwiNEDCylrsoSJS+WdN1BEML/o0zXNfP"
			+ "Xy40mAGHg/WOoSGfzTh8VsNELqMHYrtk2sAbKtX36920bvFDQA/ZklHOoYLehoPs4LD7oE758bwIDAQAB";
    
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "UTF-8";
	
	//  
	public static String format = "json" ;
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


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

