<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/img/";
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="css/index.css">
<script src="<%=basePath %>js/jquery-3.4.1.js"></script>
<!-- <script>
$(function(){

});
</script> -->
</head>
<body>
    <div class="order_head">
        <div class="head_background">
            <div class="head_box">
                <a href="default.jsp" class="head_left_a"><img src="img/logo.jpg" alt="" class="head_left_p"></a>
                <h1 class="head_h1">我的购物车</h1>
                <div class="head_right">
                    <span class="head_right_in"> ${user.name }/${user.username } </span>
                    <span class="head_right_in">|</span>
                    <a href="" class="head_right_in">我的订单</a>
                </div>

            </div>
        </div>
    </div>
    <div class="trolley_background">
        <div class="trolley_background_in">
            <div class="tro_tab_h">
                <div class="col tro_tab_check" >
                     <h3 class="tro_tab_check_p"  class="tro_tab_check_p">
                    <input type="checkbox"  id="check_main" >
                    </h3> 
                    <span class="tro_tab_check_sp">全选</span>

                </div>
                <div class="col tro_tab_img">

                </div>
                <p class="col tro_tab_name">商品名称</p>
                <div class="col tro_tab_price">单价</div>
                <div class="col tro_tab_num">数量</div>
                <div class="col tro_tab_total">小计</div>
                <div class="col tro_tab_action">操作</div>

            </div>
            
         <c:set var="sum" value="0"></c:set>
        <c:set var="num" value="0"></c:set>
        <c:forEach items="${trolley }" var="comm" varStatus="i">
         <c:set var="num" value="${num +comm.number }"></c:set>
        <c:set var="sum" value="${sum+comm.number*comm.price }"></c:set>
        
        
          <div class="tro_tab_h1" id="a${i.count}">
                <div class="col tro_tab_check">
                    <h1 class="tro_tab_check_p" style="background-color: #fff">
						<input type="checkbox" name="ids" value="${comm.cid }">
					</h1>
                    <span class="tro_tab_check_sp"></span>

                </div>
                <div class="col tro_tab_img">
                    <img src="<%=imgPath %>${comm.pic}" alt="">

                </div>
                <div class="col tro_tab_name">
                <!--<h2 tro_tab_name_h2>小米电视4A 32英寸 黑色 32英寸</h2>-->
               <li class="tro_tab_name_li1" style="font-size: 16px;">${comm.name }&nbsp;${comm.color }</li>
                </div>
                <div class="col tro_tab_price">
                    <span  id="price">
                    
                    <fmt:formatNumber type="number" pattern=".##"
            value="${comm.price}" />
                  
                    </span><span>元</span>
                </div>
                <div class="col tro_tab_num">
                    <a class="tro_tab_num_p1" id="subtract" href="javascript:void(0)" onclick="addOrDeleteNumber()">-</a>
                    <input type="text" value="${comm.number }" id="num" readonly>
                    
                   
                    
                    <a class="tro_tab_num_p2" id="plus" href="javascript:void(0)" onclick="addOrDeleteNumber()">+</a>
                </div>
                <div class="col tro_tab_total "><span class="tro_tab_total_value" id="prices" >总价 
                
                <fmt:formatNumber type="number" pattern=".##"
            value="${comm.number*comm.price }" />
           
            </span>元

                </div>
                <div class="col tro_tab_action" style="cursor: pointer;width: 40px;height: 40px;" onclick="delGoods(${comm.cid},${comm.price},${comm.number },'a${i.count}')">删除</div>
            </div>
	         
            </c:forEach>  
      
     <!--  ni -->
        <div class="tro_close_bot ">
            <!--<p class="tro_bot_ppp">+</p>-->
            <p class="tro_close_p "> <a href="default.jsp">继续购物 </a>  | 共<span id='product_number'>${num }</span>件商品</p>
           
            <p class="tro_close_p_c">合计:<span id="close">
           <fmt:formatNumber type="number" pattern=".##"
            value=" ${sum }" />
            </span>元</p>
            
            <p class="tro_close_p_r" style="cursor: pointer;" onclick="pay()">去结算</p>
        </div>

    </div>


   <jsp:include page="footer.jsp"></jsp:include>
<script>

//删除该商品
delGoods =function(cid,price,number,ddd){
	var id = "#"+ddd;
	console.log(ddd);
	console.log($(id));
	
	
	
	 $.get("troll",{"mark":"deleteTrolleyCommodity","cid":cid},function(data){
		 window.confirm(data);
		 /* $(ddd).html(""); */
		 
		$(id).remove();
		 
		//改变商品数量
		var num = $("#product_number").html();
		 $("#product_number").html(num-number+"");
		 //改变总价
		 var sum = $("#close").html();
		 $("#close").html(sum-number+"");
		 
	 },"text");
}

//全选全不选
$("#check_main").click(function(){
		$("[name='ids']").prop("checked",this.checked);
		
		console.log(this.checked);
})


  pay=function(){
	
	var ids ="";
	
	$("[name='ids']:checked").each(function(){
		ids += ","+$(this).val();
	});
	//截取字符串去掉第一个,
	ids = ids.substring(1);
	
	if(ids == ""){
		alert("请选择商品后再结算！");
		return;
	}
	
	//ids2 = ids.substring(1,ids.length);
	window.location = "troll?mark=payCommodity&ids="+ids;
}
</script>
   
</body>
</html>