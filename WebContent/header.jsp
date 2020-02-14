<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="box">
      <div class="inner whiteGL">
          <div class="left">
              <a class="mix" href="">仿小米商城-学习专用</a>
          </div>
          <div class="right">
          <c:choose>
          <c:when test="${user==null }">
              <a class="mix" href="login.jsp">登录</a>
              <a href="register.jsp">注册</a>         
          </c:when>
          <c:otherwise>
              <a href="#">${user.name }</a>
              <a href="user?mark=loginOut">登出</a>   
          </c:otherwise>
          </c:choose>

              <a class="max"  href="">消息通知</a>
              <a href="troll?mark=trolleyJsp"><img src="img/cart.jpg" alt=""><span style="color:red;" id="trolley"></span></a>
          </div>
      </div>
  </div>
  <div class="logo">
      <div class="logo_left">
          <div>
              <a href="default.jsp" title="小米官网"><img class="xiaomi" src="img/logo.jpg"></a>
          </div>
      </div>
      <ul class="logo_center orangeGL">
          <a href="">小米手机</a>
          <a href="">红米</a>
          <a href="">笔记本</a>
          <a href="">电视</a>
          <a href="">盒子</a>
          <a href="">新品</a>
          <a href="">路由器</a>
          <a href="">智能硬件</a>
          <a href="">服务</a>
          <a href="">社区</a>
      </ul>
      <formv class="logo_right">
         <div class="logo_input"><input type="text">
             <div class="logo_input_div">
                 <a class="logo_input_a" href="">小米5 新品</a>
                 <a class="logo_input_a" href="">小米Note 3</a>
             </div>


         </div>
          <a class="logo_right_a"><img src="img/find.jpg"></a>
          <!--<a href="">红米5新品</a>-->
          <!--<a href="">小米Noto 3</a>-->
      </formv>
  </div>
</body>
</html>