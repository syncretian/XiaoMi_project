<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#phone_number").change(function(){
    		var phone = this.value;
    		
    		if(checkP()){
    			$.get("user",{"mark": "checkPhone","phone":phone},function(data){
        			console.log("ajax 返回数据："+data);
        			if(data=="true"){
        				$("#phone_info").html("该号码已注册");
        			}else{
        				$("#phone_info").html("该号码可注册");
        			}
        			
        		},"text");
    		}else{
    			$("#phone_info").html("手机号格式错误");
    		}
    		
    	});
	
    	function checkP(){
    		var phone = $("#phone_number").val();
    		
    		var reg = /^1[3-9]\d{9}$/;

    		if(reg.test(phone)){	
    			return true;
    		}else{
    			return false;
    		}
    	}
    	
    	
    	$("#username").change(function(){
    		var username = this.value;
	
    			$.get("user",{"mark": "checkUsername","username":username},function(data){
        			console.log("ajax 返回数据："+data);
        			if(data=="true"){
        				$("#username_info").html("该账号已注册");
        			}else{
        				$("#username_info").html("");
        			}
        			
        		},"text");
    		
    		
    	});
    	
    	$("#btn").click(function(){
    		$("form").submit();
    	})
    	
    })
    </script>
</head>
<body>
<body>
<div class="sign_background">
    <div class="sign_background_in">
        <div class="sign_background_no1">
            <a href="main?method=toindex"><img src="img/logo.jpg" alt=""></a>
        </div>
        <div class="sign_background_no2">注册小米帐号</div>
        <div class="sign_background_no3">
               
            <div class="sign_background_no5">
             	
             	<form action="user" method="post" enctype="multipart/form-data">
             	<input type="hidden" name="mark" value="register">
             		<table style="width: 500px;" border="0" cellspacing="0">
             			<tr>
             				<td width="25%" class="_left">姓名：</td>
             				<td><input type="text" name="name"></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">性别：</td>
             				<td>
             					男<input type="radio" value="1" name="sex">
             				 	女<input type="radio" value="0" name="sex">
							</td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">*电话号码：</td>
             				<td><input type="text" name="phone_number" id="phone_number"><span id="phone_info"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">所在地区：</td>
             				<td><input type="text" name="area"></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">*账号：</td>
             				<td><input type="text" name="username" id="username"><span id="username_info"></span></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">*密码：</td>
             				<td><input type="text" name="password"></td>
             			</tr>
             			<tr>
             				<td width="25%" class="_left">上传头像：</td>
             				<td><input type="file" name="photo"></td>
             			</tr>
             		</table>
             		<div class="sign_background_no6" id="btn" >立即注册</div>
             	</form>
             	 
            </div>
        </div>
        <div class="sign_background_no7" > <span > 注册帐号即表示您同意并愿意遵守小米<a href="#" >用户协议</a>和<a href="#">隐私政策</a></span> </div>
    </div>
    <div class="sign_background_no8"><img src="img/sign01.jpg" alt=""></div>

</div>
</body>
</html>