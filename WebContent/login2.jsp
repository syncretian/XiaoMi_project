<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <link rel="stylesheet" href="css/index.css">
 <!--   <script src="js/jquery.1.11.1.min.js"></script>  -->
    <script type="text/javascript" src=" js/jquery-3.3.1.js"></script>
   
    <style>

    </style>
<script type="text/javascript">


 $(function(){

     $("#yanzhengma").click(function(){
    	 console.log("su");
    	 $.get("user?mark=getImgCode",function(data){
    		 console.log("success");
    		 $("#yanzhengma").prop("src","http://localhost:8080/codeImg/"+data);
    	 },"text")
     });
	
	function checkP(){
		var phone = $("#phone_number").val();
		
		var reg = /^1[3-9]\d{9}$/;
		console.log(phone)
		//手机格式正确
		if(reg.test(phone)){	
/* 			$("#msg").html("手机号码格式正确");
			 */
			return true;
		}else{
			$("#msg").html("手机号码格式错误");
			
			return false;
		}
	}
	
	 $("#psd_number").blur(function(){
		var phone = $("#phone_number").val();
		
		 if(checkP()){
			$.get("user",{"mark": "checkPhone","phone":phone},function(data){
				console.log("ajax 返回数据："+data);
				if(data=="true"){
					$("#msg").html("该号码已注册，可登陆");
				}else{
					$("#msg").html("该号码未注册");
					phone_flag=true;
				}
			},"text");
			
		} 
	}) 
		
	$("#sub").click(function(){
		$("form").submit();
	})
	
	$("#phone_number").focus(function(){
		$("#errorInfo").html("");
		$("#msg").html("");
	})
})
</script>
</head>
<body>
<div class="register_head_on">

</div>
<div class="register_head">
    <a href="default.jsp"><img src="img/logo.jpg" alt=""></a>
    <div class="register_head_right">
        <p class="register_head_right_p1">小 米 商 城</p>
        <p class="register_head_right_p2">让每个人都享受科技乐趣</p>
    </div>

</div>

<div class="register">
    <div class="register_boby">
        <div class="register_boby_min">
            <div class="register_boby_no1">
                <div class="register_boby_no1_in">
                    <span style="color: #ff6700">账号密码登录 </span>
                </div>
            </div>
            <form id="f3" action="user" method="post">
            <input type="hidden" name="mark" value="verifyUser2">
            <!-- fs区分的方法 -->
            
            <div class="register_boby_no22">
            	<span id="msg" style="color: red;font-size: 12px;margin-left: 20px;"></span>
            	<span id="errorInfo" style="color: red;font-size: 12px;margin-left: 20px;">${msg }</span>
                <input id="phone_number" name="phone_number" type="text" placeholder="手机号码">
                
                <input name="psd_number" type="password" placeholder="密码" >
                <input name="code" type="text" placeholder="验证码" style="width: 180px;height:20px; float: left; margin-left: 30px;margin-top: 4px;">
                <!-- 新增加 -->
                <img id="yanzhengma" src="http://localhost:8080/codeImg/${picCode }" alt="" style="width: 134px;float: left;height: 48px;margin-left: 5px;margin-top: 4px;">
                 
                
                <div style="clear: both;">
               	
                 <div class="register_boby_no22_div">
                    <span id="sub">登录</span>
                </div> 
                
            </div>
            </div>
            </form>
            
            <div class="register_boby_no33">
                <a href="login.jsp" style="color: #ff6700">验证码登录</a>
                <sapn class="register_boby_no33_span">
                    <a href="register.jsp">立即注册</a>
                    <span>|</span>
                    <a href="avascript:void(0);">忘记密码?</a>
                </sapn>
        
            </div>
            <div class="register_boby_no4">
                <img src="img/register02.jpg" alt="">
            </div>

        </div>
    </div>
</div>

<div class="register_foot">
    <img  src="img/register03.jpg" alt="">
</div>


</body>
</html>