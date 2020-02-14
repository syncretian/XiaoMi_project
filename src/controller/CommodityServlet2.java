package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import ServiceImp.CommodityServiceImp;
import entity.Cate;
import entity.Commodity;
import entity.Trolley;
import entity.User;
import utils.DealData;
import utils.UploadUtil;

@WebServlet("/commodityServlet2")
public class CommodityServlet2 extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CommodityServiceImp service = new CommodityServiceImp();

	public void updateCommodity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Commodity co = new Commodity();
		//商品信息接收，，含文件
		String path = "E:\\XiaoMi_project\\xm_static\\img";
		File f = new File(path);
		if(!f.exists()) {
			f.mkdirs();
		}
		List<FileItem> ft = UploadUtil.parseFileItem(request);
		
		FileItem picItem = null;
		if(ft != null) {
				//循环读取表单数据
				for(FileItem item : ft) {
						if(item.isFormField()) {
							UploadUtil.dealFormField2(item, co);
						}else {
							picItem = item ;
						}		
				}
		
				/////////////////////////////////////
				//文件项-----检查图片是否修改
				if(picItem != null) {
					
					String pic = co.getPicc();   //表单picc的数据存储原文件名
					String pic2 = picItem.getName();   //上传的文件名
					System.out.println("pic:   "+pic+"\n pic2:  "+pic2);
					// 上传文件相同  或者 没有选择文件
					if(pic2.equals(pic) || pic2.trim().isEmpty()) {
						co.setPic(pic);     //不需要重新上传文件
					}else {
						//重新上传文件
						pic2 = UploadUtil.dealImgField(picItem, f);
						co.setPic(pic2);
						//删除服务器上原来的文件
						UploadUtil.deleteFile(path+"/"+pic);
					}
				}
				
				//////////////////////////////////
				System.out.println("修改co :"+co.toString());
			    //将修改后的商品信息存入数据库
				int row = service.updateCommodity(co);
				System.out.println(row);
				//修改成功或失败，都返回商品页
				
		}
		
		//表单数据为空，，返回商品页
		
		
		@SuppressWarnings("unchecked")
		Map<String , String> chaxun = (Map<String, String>) request.getSession().getAttribute("paramMap");
		
		if(chaxun != null ) {
			String str = DealData.getParam(chaxun);
			System.out.println("修改文件时 的查询数据，，，");
			response.sendRedirect("commodityServlet?mark=findCommodityList"+str);
			
		}else {
			response.sendRedirect("commodityServlet?mark=findCommodityList");
		}
		
	}
	
	public String findIndexCommodityInfo(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		
		
		
		//得到商品分类表
		List<Cate> catelist = service.findCategroyList1(10);
		
//		//	     				<option value="5">=== 请选择 ===</option>
//			state == 0 }">selected</c:if>>普通</option>
//			state == 1 }">selected</c:if>>热门产品</option>
//			.state == 2 }">selected</c:if>>为你推荐</option>
//			${commodity.state == 3 }">selected</c:if>>新品</option>
//			{commodity.state == 4 }">selected</c:if>>小米明星单品</option>
		
		//1.小米明星单品 5
		List<Commodity> star_product=service.getCommodityState(null,4,5);
		
		//2.家电 8
		//从类别表得到  gid 
		Cate jiadian = service.findCategroyListName("%家电%");
		List<Commodity> home_appliance=null;
		if(jiadian != null) {
			home_appliance=service.getCommodityState(jiadian.getGid(),null,8);  //gid,state,limit
		}
		//3.智能
		Cate zhineng = service.findCategroyListName("%智能%");
		List<Commodity> intel_Product=null;
		if(jiadian != null) {
			intel_Product=service.getCommodityState(zhineng.getGid(),null,5);  //gid,state,limit
		}
		//4.为你推荐
		List<Commodity> recommend=service.getCommodityState(null,2,5);
		//5.热门
		List<Commodity> hot=service.getCommodityState(null,1,4);
		
		
		
		
		request.setAttribute("catelist", catelist);
	
		request.setAttribute("star_product", star_product);
		request.setAttribute("home_appliance", home_appliance);
		request.setAttribute("intel_Product", intel_Product);
		request.setAttribute("recommend", recommend);
		request.setAttribute("hot", hot);
	
		
		
		return "index";
	}
	
	public String findGoodsDetail(HttpServletRequest request ,HttpServletResponse response) {
		String cid = request.getParameter("cid");
		
		Commodity comm = service.findCommodityById(cid);
		
		
		request.setAttribute("commodity", comm);
		
		return "detail";
	}
	

	

}