package controller;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BaseServlet() {
        super();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			Class<? extends BaseServlet> base = this.getClass();
			System.out.println("========"+base.getName());
			
			String method = request.getParameter("mark");
			
			System.out.println("method: "+method);
			
			if(method == null || method.trim().isEmpty()) {
				if("controller.CommodityServlet".equals(base.getName())) {
					method = "saveCommodity";
					System.out.println(method);
					
					dealMethod(method,base,request,response);
				}else if("controller.CommodityServlet2".equals(base.getName())) {
					method = "updateCommodity";
					System.out.println(method);
					
					dealMethod(method,base,request,response);
				}else if("controller.UserServlet".equals(base.getName())) {
					method = "register";
					System.out.println(method);
					
					dealMethod(method,base,request,response);
				}else {

					throw new RuntimeException("请求的方法参数为null ,或者不存在");
				}
				
			}else {
				dealMethod(method,base,request,response);
				
			}
			
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void dealMethod(String method, Class<? extends BaseServlet> base,HttpServletRequest request, HttpServletResponse response) {
		
		Method mm = null;
		
		try {
			
	
				mm = base.getMethod(method,HttpServletRequest.class,HttpServletResponse.class);
				
				Object ob = mm.invoke(this, request, response);
				
				if(ob!=null) {
					String page = (String)ob;
					
//					//回到首页，，需要重定向，，否则数据没有了
//					if("index".equals(page)) {
//						response.sendRedirect("commodityServlet2?mark=findIndexCommodityInfo");
//					}else {
						request.getRequestDispatcher(page + ".jsp").forward(request, response);
//					}
					
					
				}
				
			
		
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
