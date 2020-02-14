package controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import ServiceImp.UserServiceImp;
import entity.User;
import utils.ImgCode;
import utils.UploadUtil;
import utils.YanzhengMa;

@WebServlet("/user")
public class UserServlet extends BaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	UserServiceImp service = new UserServiceImp();
	
	public  String loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		
		return "default";
	}
	public void verifyUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String phone = request.getParameter("phone_number");
		String code = request.getParameter("code");
		
		if(YanzhengMa.code.equals(code)) {
			//验证成功
			User u = service.verifyUser(phone); 
			
			if(u != null) {
				request.getSession().setAttribute("user", u);
				
				response.sendRedirect("commodityServlet2?mark=findIndexCommodityInfo");
			}else {
				request.setAttribute("msg", "没有该用户");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		
		
	}
	
	public void sendCode(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phone");
		
		String msg = YanzhengMa.sendCode(phone);
		
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public void checkPhone(HttpServletRequest request, HttpServletResponse response) {
		
		String phone = request.getParameter("phone");
		System.out.println("调用 检验电话函数"+phone);
		
		boolean isE =service.checkPhone(phone);
		
		try {
			
			response.getWriter().write(""+isE);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) {
		
		String username = request.getParameter("username");
		System.out.println("检验账号"+username);
		
		boolean isE =service.checkUsername(username);
		
		try {
			
			response.getWriter().write(""+isE);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		
	}


	/*public void to(HttpServletRequest request, HttpServletResponse response,String page) {
		System.out.println("to"+page+"()被调用");
		try {
			
			request.getRequestDispatcher("WEB-INF/"+page+".jsp").forward(request, response);
			
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	*/
	
	public void verifyUser2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String phone = request.getParameter("phone_number");
		String password = request.getParameter("psd_number");
		String code = request.getParameter("code");
		System.out.println(phone+"---"+password+"---"+code);
		
		System.out.println(code.equalsIgnoreCase(ImgCode.code));
		//验证码正确
		if(code.equalsIgnoreCase(ImgCode.code)) {
	
			User uu = service.verifyUser(phone); 
			//手机号存在  ， 密码正确
			
			if( uu != null && uu.getPassword().equals(password) ) {
				request.getSession().setAttribute("user", uu);
				
				response.sendRedirect("commodityServlet2?mark=findIndexCommodityInfo");
			}else {
				
				System.out.println("密码错误");
				response.sendRedirect("user?mark=tologin2");
			}
		}else {
			System.out.println("验证码错误");
			System.out.println(code+"----"+ImgCode.code);
			response.sendRedirect("user?mark=tologin2");		
			}
		
	}
	
	public String tologin2(HttpServletRequest request, HttpServletResponse response) {
		String picCode =  ImgCode.getImg();
		
		System.out.println("login请求验证码："+ImgCode.code);
		request.setAttribute("picCode", picCode);
		
		return "login2";
	}
	
	public void getImgCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String picCode =  ImgCode.getImg();
		
		System.out.println("login请求验证码："+ImgCode.code);
		response.getWriter().write(picCode);
	}
	
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		User user = new User();
		List<FileItem> items = UploadUtil.parseFileItem(request);
		if(items == null) {
			System.out.println("表单数据为空");
		}else {
			for(FileItem item : items ) {
				if(item.isFormField()) {
					String name = item.getFieldName();
					String  str= item.getString();
					if(str!= null) {
						String value = new String(str.getBytes("ISO-8859-1"),"UTF-8");
						
						switch(name) {
							case"name": user.setName(value);break;
							case"sex" :
								Integer sex = null;
								try {
									sex = new Integer(value);
								}catch(NumberFormatException e) {
									System.out.println("注册Sex信息解析错误");
								}
								System.out.println("性别："+value);
								user.setSex(sex);
								break;
							case"phone_number":user.setPhone_number(value);break;
							case"area":user.setArea(value);break;
							case"username":user.setUsername(value);break;
							case"password":user.setPassword(value);break;
							
						}
					}
					
				}else {
					String path = "E:\\XiaoMi_project\\xm_static\\img";
					
					String inputname = item.getFieldName();  //表单项name
					String fileName =item.getName();//上传的文件名
					System.out.println("inputname:  "+inputname+" fileName: "+fileName);
					
					//没有选择头像
					if(fileName.isEmpty()) {
						System.out.println("文件名为空");
						continue;
					}
					
					if("photo".equals(inputname)) {
						String imgName = UploadUtil.getImgName(fileName);
						FileOutputStream fo = new FileOutputStream(path+"/"+imgName);
						
						InputStream in = item.getInputStream();
						
						byte[] buf = new byte[1024];
						int len = 0;
						while((len = in.read(buf))!=-1) {
							fo.write(buf);
						}
						
						user.setPhoto(imgName);
					}
					
				}
			}
			
			System.out.println("注册信息："+user.toString());
			boolean bo = service.register(user);
			
			if(bo) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			
		}
	
	}
}
