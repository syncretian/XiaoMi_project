<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品信息</title>
<link href="<%=basePath %>admin/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="<%=basePath %>admin/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

// old write 
$(function(){
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
  
  	//跳转添加商品事件
  	$("#addCommodity").click(function(){
  		window.location = "<%=basePath%>commodityServlet?mark=findCategroyList";
  	});

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
		window.location = "<%=basePath %>commodityServlet?mark=batchDelete&ids="+ids;
		
	});


  	 fy=function(current_page){
  		//赋值给表单中的隐藏域
  		$("#current_page").val(current_page);
  		$("#f5").submit();
  	}

  	upCommodity=function(cid,current_page){
  		$("#current_page").val(current_page);
  		
  		var str = $("#f5").serialize();
  		console.log("form:  "+str)
  		var index=str.indexOf('&');
  		var str2 = str.substring(index);
  		console.log("for:  "+str2);
  		
  		console.log(cid);
  		window.location ="<%=basePath %>commodityServlet?mark=findCommodityById&cid="+cid+str2;
  	}
  	 ttt=function(){
  		alert("ttttt");
  	}
})
  
delCommodity=function(cid,current_page,pic){
		console.log("test");
		if(!confirm("确认删除？")){
			return;
		}
		
		$("#current_page").val(current_page);
		
		var str = $("#f5").serialize();
		console.log("form:  "+str)
		var index=str.indexOf('&');
		var str2 = str.substring(index);
		console.log("for:  "+str2);
		
		console.log(cid);
		window.location = "<%=basePath%>commodityServlet?mark=deleteCommodity&cid="+cid+"&pic="+pic+str;
	}
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
        <li style="cursor: pointer;" id="addCommodity" ><span><img src="<%=basePath %>admin/images/t01.png"  /></span>添加商品</li>
        <li style="cursor: pointer;" id="batchDelete"><span><img src="<%=basePath %>admin/images/t03.png" /></span>批量删除</li>
        </ul>

    </div>
  
    <form id="f5" action="commodityServlet" method="post">
    	<input type="hidden" name="mark" value="findCommodityList" />
    	
    	<!-- 隐藏一个current_page参数 -->
    	<input name="current_page" value="1" id="current_page" />
    	
    	
	    <div style="width: 100%;height: 30px; text-align: center;">
	     	商品名称：<input name="name" value="${commodity.name }" style="height: 25px;border:1px solid #ccc;" type="text"/> &nbsp;&nbsp;
	     	<!-- 0 正常,1热门产品，2为你推荐，3，新品 4，小米明星单品 -->
	     	
	     	是否热推：<select name="state" style="height: 28px;border:1px solid #ccc;">
	     				<option value="5">=== 请选择 ===</option>
	     				<option value="0" <c:if test="${commodity.state == 0 }">selected</c:if>>普通</option>
	     				<option value="1" <c:if test="${commodity.state == 1 }">selected</c:if>>热门产品</option>
	     				<option value="2" <c:if test="${commodity.state == 2 }">selected</c:if>>为你推荐</option>
	     				<option value="3" <c:if test="${commodity.state == 3 }">selected</c:if>>新品</option>
	     				<option value="4" <c:if test="${commodity.state == 4 }">selected</c:if>>小米明星单品</option>
	     	 	   </select> &nbsp;&nbsp;
	     	 时间：<input class="Wdate" name="start_time" value="${commodity.begin }" type="text" style="height: 25px;border:1px solid #ccc;" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> ~
	     	 <input class="Wdate" name="end_time" value="${commodity.end }" type="text" style="height: 25px;border:1px solid #ccc;" onclick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd HH:mm:ss'})" />&nbsp;&nbsp;
	         <input type="submit" value="查询" style="width: 60px;height: 30px;"  />
	    </div><br/>
    </form>
    <table class="tablelist">
    	<thead>
	    	<tr>
		        <th><input id="checkbox_main"type="checkbox" /></th>
		        <th>序号<i class="sort"><img src="<%=basePath %>admin/images/px.gif" /></i></th>
		        <th>商品类别</th>
		        <th>商品名称</th>
		        <th>商品颜色</th>
		         <th>规格</th>
		        <th>商品价格</th>
		        <th width="10%">简介</th>
		        <th width="20%">详情</th>
		       	<th>商品展示图</th>
		       	<th>是否热推</th>
		       	<th>型号</th>
		       	<th>生产日期</th>
		       	<th>操作</th>
	        </tr>
        </thead>
        <tbody>
        	<c:forEach items="${page.users }" var="comm" varStatus="i">
        		<tr>
			        <td><input name="ids" type="checkbox" value="${comm.cid}"/></td>
			        <td>${(page.current_page-1)*page.size+i.count }</td>
			        <td>${comm.category_name }</td>
			        <td>${comm.name }</td>
			        <td>${comm.color }</td>
			        <td>${comm.size }</td>
			        <td>${comm.price }</td>
			        <td>${comm.description }</td>
			        <td>${comm.full_description }</td>
			       	<td>
			       		<img src="http://localhost:8080/img/${comm.pic }" alt="" width="80" />
			       	</td>
			       	<td>
			       		<!-- 0 正常,1热门产品，2为你推荐，3，新品 4，小米明星单品 -->
			       		<c:if test="${comm.state == 0}">正常</c:if>
			       		<c:if test="${comm.state == 1}">热门产品</c:if>
			       		<c:if test="${comm.state == 2}">为你推荐</c:if>
			       		<c:if test="${comm.state == 3}">新品</c:if>
			       		<c:if test="${comm.state == 4}">小米明星单品</c:if>
			       	</td>
			       	<td>${comm.version }</td>
			       	<td>${comm.product_date }</td>
			       	<td>
			       		<a href="javascript:void(0)" onclick="delCommodity(${comm.cid},${page.current_page},'${comm.pic}')">删除</a>
			       		<a href="javascript:void(0)" onclick="upCommodity(${comm.cid},${page.current_page})">修改</a>  <%-- upCommodity(${comm.cid}) --%>
			       	</td>
		        </tr>
        	</c:forEach>
        </tbody>
    </table>
    
  
    <div class="pagin">
    	<div class="message">共<i class="blue">${page.sumNum }</i>条记录，当前显示第&nbsp;<i class="blue">${page.current_page }&nbsp;</i>页</div>
        <ul class="paginList">
	        
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fy(1)">首页</a></li>
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fy(${page.current_page-1})">上一页</a></li>
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fy(${page.current_page+1})">下一页</a></li>
	         <li class="paginItem"><a href="javascript:void(0)" onclick="fy(${page.pageNum})">尾页</a></li>
        	  <li class="paginItem"><a href="javascript:void(0)">共${page.pageNum }页</a></li>
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
