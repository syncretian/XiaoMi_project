package utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;

public class PayUtil {


	public static void PayDemo(HttpServletRequest request ,HttpServletResponse response ,String id, double total_price, String subject, String body) {
		
		 //获得初始化的AlipayClient

		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2016101300674173",
				"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCoN3cf2xq9Exq+mvW80QAN6EtH3t3KCOF8KZxDiEY0J/KE0P94QWTVhmtQ8"
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
				+ "KWfZ+OjNRFNTmZQI3oC8Uz54N3H3+11GKwIKilVX1WGMttf/LNEWc5vT0774qh5FWdh4QUuOWr7WkFE4Ngn0i/7m3btMTEa",
				"json", "UTF-8", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqDd3H9savRMavpr1vNEADehLR97dygjhfCmcQ4hGNCfyhND/eEFk1"
						+ "YZrUPMTr9tu80+peeiVHXqtTrkD9zSJSnvngf+oN+1yi+ZEc8l2FQaSaVTe/gW6esJFBMbECw0flt1U7qvAOHFXg1mXz0VeIZpR/mJenbe"
						+ "QOuwqhgMBHh3449LyOZWQGf6Isusp+hF881/NQEtgwHRBokDsdUrzAG+YoJg0CzWQWPM7Sp7apcDjwiNEDCylrsoSJS+WdN1BEML/o0zXNfP"
						+ "Xy40mAGHg/WOoSGfzTh8VsNELqMHYrtk2sAbKtX36920bvFDQA/ZklHOoYLehoPs4LD7oE758bwIDAQAB"
						, "RSA2"); //获得初始化的AlipayClient
		
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
		alipayRequest.setReturnUrl("/apply/return_url.jsp");
		alipayRequest.setNotifyUrl("/apply/notify_url.jsp");//在公共参数中设置回跳和通知地址
		
		
		alipayRequest.setBizContent("{" +
		"    \"out_trade_no\":\""+id+"\"," +
		"    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
		"    \"total_amount\":"+total_price+"," +
		"    \"subject\":\"" + subject + "\"," +
		"    \"body\":\""+ body + "\"," +
		"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
		"    \"extend_params\":{" +
		"    \"sys_service_provider_id\":\"2088511833207846\"" +
		"    }"+
		"  }");//填充业务参数
		



		String form="";
		try {
		form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		} catch (AlipayApiException e) {
		e.printStackTrace();
		}
		
		try {
			response.setContentType("text/html;charset=" + "UTF-8");
			response.getWriter().write(form);//直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void tt() {
		System.out.println(new SimpleDateFormat ("yyyy-MM-dd HH:MM:ss").format(new Date()));
		
	}
}
