package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import ServiceImp.CommodityServiceImp;
import entity.Cate;
import entity.Comm;
import entity.Commodity;
import entity.Page;
import utils.DealData;
import utils.UploadUtil;

/**
 * Servlet implementation class CommodityServlet
 */
@WebServlet("/commodityServlet")
public class CommodityServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	CommodityServiceImp service = new CommodityServiceImp();

	public CommodityServlet() {
       super();
    }

	public void findCommodityList(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String state = request.getParameter("state");
		String begin = request.getParameter("start_time");
		String end = request.getParameter("end_time");
	
		Comm commodity = new Comm(name,state,begin,end);
		//第几页
		String str = request.getParameter("current_page");
		//得到该页数据
		Page<Commodity> page = service.findCommodityList(str,name,state,begin,end);
		//放入request域
		if(page != null) {
			request.setAttribute("page", page);
		}else {
			System.out.println("commodity list is null");
		}
		
		request.setAttribute("commodity", commodity);
		try {
			request.getRequestDispatcher("admin/commodity_list.jsp").forward(request, response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public String findCategroyList(HttpServletRequest request, HttpServletResponse response) {
		
		List<Cate> categoryList = service.findCategroyList();
		
		if(categoryList != null) {
			
			request.setAttribute("categoryList", categoryList);
			
			return "admin/commodity_add";
		}
		return "commodity_list";
	}
	
	public void saveCommodity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Commodity co = new Commodity();
		//商品信息接收，，含文件
		String path = "E:\\XiaoMi_project\\xm_static\\img";
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		List<FileItem> ft = UploadUtil.parseFileItem(request);
		
		if(ft != null) {
			
			for(FileItem item : ft) {
				if(item.isFormField()) {
					UploadUtil.dealFormField2(item, co);
				}else {
					String pic = UploadUtil.dealImgField(item, f);
					co.setPic(pic);
				}
			}
			
			int row = service.saveCommodity(co);
			if(row > 0) {
				response.sendRedirect("commodityServlet?mark=findCommodityList");//添加成功，返回商品页
			}
		}
			System.out.println("商品添加信息为空");
			response.sendRedirect("commodityServlet?mark=findCategroyList");//添加失败，返回添加页
		
		
	}
	
	
	//跳到该用户修改页面,,,需要查到类别表 和 该商品信息
	public String findCommodityById(HttpServletRequest request, HttpServletResponse response) {
			String str = request.getParameter("cid");
			
			//页面携带的模糊查询下数据
			Map<String , String[]> chaxun = request.getParameterMap();

			Map<String , String> paramMap = new HashMap<String, String>();

			if( chaxun !=null) {
				
				for(Entry<String , String[]> e : chaxun.entrySet()) {
					paramMap.put(e.getKey(), e.getValue()[0]);
				}
				request.getSession().setAttribute("paramMap", paramMap);
			}
	
			
			//1.得到商品类别，显示在update页面
			List<Cate> categoryList = service.findCategroyList();
			
			request.setAttribute("categoryList", categoryList);
			//2.得到该商品全部信息，显示在update页面
			Commodity comm = service .findCommodityById(str);
			
			request.setAttribute("commodity", comm);
			
			return "admin/commodity_update";
		}
		

	public void deleteCommodity(HttpServletRequest request, HttpServletResponse response) throws IOException {

            //页面携带的模糊查询下数据
			Map<String , String[]> chaxun = request.getParameterMap();

		
			String cid = request.getParameter("cid");
			String pic = request.getParameter("pic");
			
			int row = service.deleteCommodity(cid,pic);
			
			if(row > 0) {
				System.out.println("删除商品成功");
			}
			

			//添加模糊查询参数，，回到 删除时的状态
	
			if(chaxun != null ) {
				String str = DealData.getParam1(chaxun);
				System.out.println("修改文件时 的查询数据，，，"+str);
				response.sendRedirect("commodityServlet?mark=findCommodityList"+str);
				
			}else {
				response.sendRedirect("commodityServlet?mark=findCommodityList");
			}
	}
	
	public void batchDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		
		int row = service.batchDelete(ids);
		
		if (row>0) {
			try {
				System.out.println("删除"+row+"条数据");
				response.sendRedirect("commodityServlet?mark=findCommodityList");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}
