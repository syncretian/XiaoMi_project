<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户信息</title>
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

//就绪函数
$(function(){
	//全选全不选功能
	$("#checkbox_main").click(function(){
		$("[name='ids']").prop("checked",this.checked);
	});
	
	//获取选中的数据的ID  格式 in（1,2,4,5）
	var ids ="";
	$("#batchDelete").click(function(){
		
		if(!confirm("你确定删除么?")){
			return;
		}
		
		$("[name='ids']:checked").each(function(){
			ids += ","+$(this).val();
		});
		//截取字符串去掉第一个,
		ids = ids.substring(1);
		
		if(ids == ""){
			alert("请选择数据后在执行删除！");
			return;
		}
		
		//ids2 = ids.substring(1,ids.length);
		window.location = "admin?mark=batchDelete&ids="+ids;
		
	});
	
});


</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">商品管理</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        
        <!-- 
        
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
         -->
        <li id="batchDelete" style="cursor: pointer;"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th><input id="checkbox_main" type="checkbox" /></th>
		        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
		        <th>姓名</th>
		        <th>性别</th>
		        <th>电话号码</th>
		        <th>所在地区</th>
		        <th>权限</th>
		        <th>账号</th>
		       	<th>头像</th>
		       	<th>注册时间</th>
		       	<th>操作</th>
	        </tr>
	    </thead>
        <tbody>
       
        	<c:forEach items="${page.users }" var="u" varStatus="i">
        		<tr>
			        <td><input name="ids" type="checkbox" value="${u.id }"/></td>
			        <td>${(page.current_page-1)*page.size+i.count }</td>
			        <td>${u.name }</td>
			        <td>
			        	<c:if test="${u.sex ==1 }">男</c:if>
			        	<c:if test="${u.sex ==0 }">女</c:if>
			        </td>
			        <td>${u.phone_number }</td>
			        <td>${u.area }</td>
			        <td>
			        	<c:if test="${u.manager==1 }">用户</c:if>
			        	<c:if test="${u.manager==0 }"> <font style="font-weight: bold;color: red">管理员</font></c:if>
			        </td>
			        <td>${u.username }</td>
			       	<td style="text-align: center;">
			       		<img src="http://localhost:8080/img_pro/${u.photo }" width="80" height="50" alt="" />
			       	</td>
			       	<td>${u.create_time }</td>
			       	<td>
			       		<c:if test="${u.manager==1 }">
			       			<a href="<%=basePath %>admin?mark=updateRole&id=${u.id}&manager=0&current_page=${page.current_page}">指定管理员</a>
			       		</c:if>
			       		<c:if test="${u.manager==0 }">
			       			<a style="color: red" href="<%=basePath %>admin?mark=updateRole&id=${u.id}&manager=1&current_page=${page.current_page}">撤销管理员</a>
			       		</c:if>
			       	</td>
		        </tr>
        	</c:forEach>
        	
        </tbody>
    </table>
   
    <div class="pagin">
    	<div class="message">共<i class="blue">${page.sumNum }</i>条记录，当前显示第&nbsp;<i class="blue">${page.current_page }&nbsp;</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="<%=basePath%>admin?mark=findUserList&current_page=1">首页</a></li>
	         <li class="paginItem"><a href="<%=basePath%>admin?mark=findUserList&current_page=${page.current_page-1}">上一页</a></li>
	         <li class="paginItem"><a href="<%=basePath%>admin?mark=findUserList&current_page=${page.current_page+1}">下一页</a></li>
	         <li class="paginItem"><a href="<%=basePath%>admin?mark=findUserList&current_page=${page.pageNum }">尾页</a></li>
	         <li class="paginItem"><a href="#">共${page.pageNum }页</a></li>
	         
	        
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="<%=basePath %>admin/images/ticon.png" /></span>
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
