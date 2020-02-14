package utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class YanzhengMa {
	public static String code =null;
	/*
	pom.xml
	<dependency>
	  <groupId>com.aliyun</groupId>
	  <artifactId>aliyun-java-sdk-core</artifactId>
	  <version>4.0.3</version>
	</dependency>
	*/
	public static String sendCode(String phone) {
		//随机生成验证码
		int code1 = (int)(Math.random()*1000000);		

		code=code1+"";
		
		return YanzhengMa.test(phone,code+"");
	}
	
	    public static String test(String phone,String code) {
	        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FkGWB4gzmbcrYQKvhCD", "0XtQBudk0wBjoCP4EZrwCLlx0H1OMP");
	        IAcsClient client = new DefaultAcsClient(profile);

	        CommonRequest request = new CommonRequest();
	        request.setMethod(MethodType.POST);
	        request.setDomain("dysmsapi.aliyuncs.com");
	        request.setVersion("2017-05-25");
	        request.setAction("SendSms");
	        request.putQueryParameter("RegionId", phone);
	        request.putQueryParameter("PhoneNumbers", "18973852776");
	        request.putQueryParameter("SignName", "syncretian");
	        request.putQueryParameter("TemplateCode", "SMS_174690105");
	        //"{\"code\":\"1111\"}"
	        System.out.println("{\"code\":\""+code+"\"}");
	        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
	        try {
	            CommonResponse response = client.getCommonResponse(request);
	            
	            String msg = response.getData().split(",")[0].split("\"")[3];
	            System.out.println(msg);
	            System.out.println(response.getData());
	            
	            return msg;
	        } catch (ServerException e) {
	            e.printStackTrace();
	        } catch (ClientException e) {
	            e.printStackTrace();
	        }
	        
	        
			return "验证码发送错误，请重新发送";
	    }
}
