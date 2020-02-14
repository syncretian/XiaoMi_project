package controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;

import ServiceImp.CommodityServiceImp;
import entity.Commodity;
import entity.Order;
import entity.Trolley;
import entity.User;
import utils.PayUtil;


@WebServlet("/troll")
public class TrolleyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	CommodityServiceImp service = new CommodityServiceImp();
	
	public void addProductToTrolley(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
		//0. 处理商品ID
		String cid = request.getParameter("cid");
		Integer id = null;
		if(cid != null && !cid.trim().isEmpty()) {
			try {
				id = new Integer(cid);
			}catch(NumberFormatException e) {
				System.out.println("商品编号解析错误");
			}
		}
		
		//1.判断是否登录
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			//判断购物车里是否有该商品，，有则数量加1，没有则创建该商品信息，创建随机订单编号
			
			Trolley tt = new Trolley(user.getId(),id);
			boolean bo = service.addProductToTrolley(tt);
			System.out.println(bo);
			if(bo) {
				response.getWriter().write("商品添加成功");
			}else {
				response.getWriter().write("商品添加失败");
			}
		
		}else {
			//没有登录，，跳转到登录页面
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
		
	}
	
	public String trolleyJsp(HttpServletRequest request ,HttpServletResponse response) {
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user != null) {
			//从trolley表中得到该用户所有商品信息
			List<Commodity> trolley = service.getTrolleyList(user.getId());
			
			if(trolley!= null) {
				request.setAttribute("trolley", trolley);
				
			}
			return "trolley";
		}else {
			return "login";
		}
		
	}
	
	public void deleteTrolleyCommodity(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		String str = request.getParameter("cid");
		if(user != null ) {
			//用户ID
			Integer uid =user.getId();
			//商品ID
			Integer cid = null;
			
			try {
				cid = new Integer(str);
			}catch(NumberFormatException e) {
				System.out.println("待删除的商品ID解析错误");
			}
			
			Boolean bo = service.deleteTrolleyCommodity(uid,cid);
			
			if(bo) {
				response.getWriter().write("商品删除成功");
			}else {
				response.getWriter().write("商品删除失败");
			}
		}
	}
	
	public void payCommodity(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException, AlipayApiException {
		String ids = request.getParameter("ids");
		
		System.out.println(ids);
		
		//1. 支付，，，提供支付信息，，订单编号 ，商品列表信息
		User user = (User) request.getSession().getAttribute("user");
		Integer uid= user.getId();
		
		Order order = service.payCommodity(uid,ids);
		
		System.out.println(order.toString());
		//跳到支付页面
		
//		Order order = new Order("12d213d2-3675-4e3b-a71a-3fd1dd0e7de2",269.0,"小米小爱音箱 数量：X1 计￥269.0");
	
//		Order order = new Order();
//		order.setOid(UUID.randomUUID().toString());
//		order.setSum_price(1267.0);
//		order.setDescription("小米手环5 数量：X1 计￥998.0 小米小爱音箱 数量：X1 计￥269.0");
		
		if( order != null) {
			PayUtil.PayDemo(request,response,order.getOid(), order.getSum_price(), order.getDescription().substring(0, 6), order.getDescription());
			
		}
		
		

	}
	
	

	
	
}
