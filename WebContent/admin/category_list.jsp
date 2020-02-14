<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品分类</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>

<script type="text/javascript">

// old write 
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});
});


$(function(){
	$("#add_category").click(function(){
		//直接跳转到添加页面
		window.location = "admin/category_add.jsp";
	});
});

function del(gid){
	if(!confirm("你确定删除么？")){
		return;
	}
	window.location = "<%=basePath%>categoryServlet?mark=deleteCategory&gid="+gid;
}

</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">分类管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        
        <%-- <li class="click"><span><img src="<%=basePath %>admin/images/t02.png" /></span>修改</li>
        <li><span><img src="<%=basePath %>admin/images/t04.png" /></span>统计</li> --%>
        <li style="cursor: pointer;" id="add_category"><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加类别</li>
        <li style="cursor: pointer;" id=""><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    
    <table class="tablelist">
    	<thead>
    	<tr>
	        <th><input name="" type="checkbox" value="" /></th>
	        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
	        <th>类别名称</th>
	        <th>启用状态</th>
	        <th>排序序号</th>
	        <th>创建时间</th>
	        <th>描述</th>
	        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list }" var="cate" varStatus="i">
	        <tr>
		        <td><input name="" type="checkbox" value="" /></td>
		        <td>${i.count }</td>
		        <td>${cate.name }</td>
		        <td>
		        	<c:if test="${cate.state == 1 }">启用</c:if>
		        	<c:if test="${cate.state == 0 }">未启用</c:if>
		        </td>
		        <td>${cate.order_number }</td>
		        <td>${cate.create_time }</td>
		        <td>${cate.description }</td>
		        <td>
		        	<a href="<%=basePath %>categoryServlet?mark=findCategoryById&gid=${cate.gid}">修改</a>
		        	&nbsp;
		        	<a href="javascript:void(0)" onclick="del(${cate.gid})" >删除</a>
		        </td>
	        </tr>
        
        </c:forEach>
        
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${page.count_rows }</i>条记录，当前显示第&nbsp;<i class="blue">${page.current_page } &nbsp;</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="<%=basePath %>categoryServlet?mark=findCategoryList&current_page=1">首页</a></li>
	         <li class="paginItem"><a href="<%=basePath %>categoryServlet?mark=findCategoryList&current_page=${page.pre_page}">上一页</a></li>
	         <li class="paginItem"><a href="<%=basePath %>categoryServlet?mark=findCategoryList&current_page=${page.next_page}">下一页</a></li>
	         <li class="paginItem"><a href="<%=basePath %>categoryServlet?mark=findCategoryList&current_page=${page.count_page}">尾页</a></li>
	         
	        
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
