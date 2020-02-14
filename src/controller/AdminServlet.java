package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ServiceImp.AdminServiceImp;
import entity.Page;
import entity.User;

@WebServlet("/admin")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	AdminServiceImp service = new AdminServiceImp();
	
	public void updateRole(HttpServletRequest request, HttpServletResponse response) {
		String id =  request.getParameter("id");
		String manager = request.getParameter("manager");
		String current_page = request.getParameter("current_page");
		
		int row = service.updateRole(id,manager);
		
		if (row>0) {
			try {
				//
				response.sendRedirect("admin?mark=findUserList&current_page="+current_page);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		
		int row = service.batchDelete(ids);
		
		if (row>0) {
			try {
				//
				response.sendRedirect("admin?mark=findUserList");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
	public void findUserList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String str = request.getParameter("current_page");
			System.out.println("current_page str: "+str);
			
			Page<User> page = service.findUserList(str);
			
			
			if(page!= null) {
				request.setAttribute("page", page);
			
				request.getRequestDispatcher("admin/user_list.jsp").forward(request, response);
				
			}else {
				System.out.println("userlist is null");
			}
		
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void adminLogout(HttpServletRequest request, HttpServletResponse response) {
		request.removeAttribute("mydata");
		
		try {
			response.sendRedirect("admin/login.jsp");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void adminLogin(HttpServletRequest request, HttpServletResponse response) {		
		try {
			System.out.println("URL: "+request.getRequestURI());
			String username = request.getParameter("username");
			String psd = request.getParameter("psd");
			System.out.println(username+"---"+psd);
			
			User u = service.getUserByNamePsd(username,psd);			
			
			//登录成功，将该用户信息放入session中
			if(u != null) {
				
				String rempsd = request.getParameter("rempsd");
				Cookie c1 = new Cookie("rempsd","checked");
				Cookie c2 = new Cookie("name",username);
				Cookie c3 = new Cookie("psd",psd);
				
				if(rempsd == null) {
					c1.setMaxAge(0);
					c3.setMaxAge(0);
				}
				
				response.addCookie(c1);
				response.addCookie(c2);
				response.addCookie(c3);
				request.getSession().setAttribute("mydata", u);
				
				//去首页
				request.getRequestDispatcher("admin/main.jsp").forward(request, response);
				
				
			}else {
				
				Cookie c2 = new Cookie("name",username);
				response.addCookie(c2);
				
				request.setAttribute("msg", "账号或密码错误");
				//回到登录页面
				request.getRequestDispatcher("admin/login.jsp").forward(request, response);
				
			}
		
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}

	public void toLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			request.getRequestDispatcher("admin/login.jsp").forward(request, response);
			
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
