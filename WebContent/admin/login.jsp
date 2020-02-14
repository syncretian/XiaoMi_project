<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录小米后台管理系统</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=basePath %>admin/js/jquery-3.3.1.js"></script>
<script src="<%=basePath %>admin/js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

<script>
$(function(){
	function ssbumit(){
		$("form").submit();
	}
})

</script>
</head>

<body style="background-color:#1c77ac; 
background-image:url(images/light.png); 
background-repeat:no-repeat; 
background-position:center top; 
overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="admin?method=toindex">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    
    <form action="admin" method="post">
    	<input name="mark" type="hidden" value="adminLogin" />
	    <ul>
	    <li>
	    <font style="color: red">${msg }</font><br>
	    <input name="username" type="text" class="loginuser" value="${cookie.name.value}"/></li>
	    <li><input name="psd" type="password" class="loginpwd" value="${cookie.psd.value}"/></li>
	    <li><input type="submit" class="loginbtn" value="登录"  onclick="ssbumit()"/><label>
	    <input name="rempsd" type="checkbox" ${cookie.rempsd.value}/>记住密码</label>
	    <label><a href="admin/error.jsp">忘记密码？</a></label></li>
	    </ul>
    </form>
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2013  .com 仅供学习交流，勿用于任何商业用途</div>
</body>
</html>
