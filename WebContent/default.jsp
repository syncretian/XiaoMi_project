<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<!-- <a href="PayServlet">点击测试1</a> -->
<!--  <a href="troll?mark=payCommodity">点击测试2</a>  --> 
  <jsp:forward page="commodityServlet2?mark=findIndexCommodityInfo"></jsp:forward>  
<%-- <jsp:forward page="admin?mark=tologin"></jsp:forward>  --%>
</body>
</html>