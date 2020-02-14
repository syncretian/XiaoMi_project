package ServiceImp;

import java.io.File;
import java.util.List;
import java.util.UUID;

import DaoImp.CommodityDaoImp;
import Service.CommodityService;
import entity.Cate;
import entity.Commodity;
import entity.Order;
import entity.Page;
import entity.Trolley;
import utils.UploadUtil;

public class CommodityServiceImp implements CommodityService {

	private CommodityDaoImp dao = new CommodityDaoImp();
	
	public Page<Commodity> findCommodityList(String str, String name, String state, String begin, String end) {
		//可能有where条件 and...，也可能没有
		String where_str = getWhere(name,state,begin,end);
		
		int current_page ;
		System.out.println("current_page str:  "+str);
		//进入用户管理，无该参数，默认为首页
		if(str != null ) {
			try{
				current_page = Integer.parseInt(str);
			}catch(NumberFormatException e) {
				current_page = 1 ;   //参数错误时默认当前页为1
			}			
		} 
		else {
			current_page = 1 ;  //无该参数时默认当前页为1
		}
		
		int size = 5;
		int sum = dao.getCount("where 1=1 "+where_str);
		
		//初始化页面对象的数据总量，页面数，当前页码
		//当数据量为0时，，页面数和当前页码会处理为0,List为空
		Page<Commodity> page = new Page<Commodity>(size,sum,current_page);

		                                 System.out.println("current_page:  "+page.getCurrent_page());
	
		List<Commodity> list = null;
		if(sum>0) {
			list =  dao.findCommodityList(where_str+" ORDER BY product_date DESC "
					+ " limit "+(page.getCurrent_page()-1)*size+", "+size);
		}
	    
		page.setUsers(list);
		return page;
	}


	public  String getWhere(String name, String state, String begin, String end) {
		StringBuilder where = new StringBuilder();
		
		if(state != null && !state.trim().isEmpty()) {
			if(!"5".equals(state)) {
				where.append(" and c.state="+state);			
			}
		}
		//可能为null 或者为空
		//点用户管理时都为Null
		//点查询时为空，state为5
		//将参数都处理成where语句
		if(name != null && !name.trim().isEmpty()) {
			where.append(" and c.name like '%"+name+"%' ");
		}
		
		
		if(begin != null && !begin.trim().isEmpty()) {
			where.append(" and c.product_date>'"+begin+"'");
		}
		
		if(end != null && !end.trim().isEmpty()) {
			where.append("and c.product_date<'"+end+"'");
		}
//		this.state = state;
//		this.begin = begin;
//		this.end = end;
		return where.toString();
	}


	public List<Cate> findCategroyList() {
		return dao.findCategroyList();
	}


	public int saveCommodity(Commodity co) {
		
		return dao.saveCommodity(co);
		
	}


	public Commodity findCommodityById(String str) {
		Integer cid = null;
		if(str != null && !str.trim().isEmpty()) {
			try {
				cid = Integer.parseInt(str);
			}catch(NumberFormatException e) {
				System.out.println("修改的商品ID 解析错误");
			}
			
		}else {
			System.out.println("商品ID 为空");
		}
		
		
		return dao.findCommodityById(cid);
	}


	public int updateCommodity(Commodity co) {
		return dao.updateCommodity(co);
		
	}


	public int deleteCommodity(String cid, String pic) {
		//删除商品图片
		UploadUtil.deleteFile("E:\\XiaoMi_project\\xm_static\\img"+"/"+pic);
		
		return dao.deleteCommodity(cid);
	}


	public int batchDelete(String ids) {
		String[] uids = ids.split(",");
		int count =0 ;
		for(String id : uids) {
			if(dao.deleteCommodity(id)>0) {
				count++;
			}
		}
		return count;
	}


	public List<Cate> findCategroyList1(int i) {
		return dao.findCategroyList1(i);
	}


	public Cate findCategroyListName(String str) {
		return dao.findCategroyListName(str);
	}


	public List<Commodity> getCommodityState(Integer gid, Object ob, Integer i) {
		return dao.getCommodityState(gid,ob,i);
		
		
	}


	public boolean addProductToTrolley(Trolley tro) {
		Trolley tid = dao.getTrolleyBy(tro.getUid(),tro.getCid());
		
		
		int row = 0;
		if(tid != null) {
			System.out.println(tid.toString());
			row = dao.addNumber(tid.getTid());
		}else {
			
			row = dao.addTrolly(tro);
		}
		
		System.out.println("添加购物车row : "+row);
		return row>0 ;
	}


	public List<Commodity> getTrolleyList(int id) {
		return dao.getTrolleyList(id) ;
	}


	public Boolean deleteTrolleyCommodity(Integer uid, Integer cid) {
		return dao.deleteTrolleyCommodity(uid,cid);
	}


	public Order payCommodity(Integer uid, String ids) {
		String[] ss = ids.split(",");
		
		String orders_number = UUID.randomUUID().toString();
		
		
		Double sum = 0.0;	
		String str="";
		
		for(String id : ss) {
			Integer cid =null;
			try {
				cid = new Integer(id);
			}catch(NumberFormatException e) {
				System.out.println("支付商品ID解析错误");
			}
			//sql ..null is ok getTrolleyBy
			Commodity co = dao.findCommodityById(cid);
			
			Trolley tro = dao.getTrolleyBy(uid, cid);
			
			
			if(dao.addOrderInfo(orders_number,tro,co)) {
				dao.deleteTrolleyCommodity(uid , cid);
			}
			
					
			sum+=tro.getNumber()*co.getPrice();	
			
			str=str+co.getName()+" 数量:"+tro.getNumber() +" ￥"+tro.getNumber()*co.getPrice()+" ";
			
		}
		
		
		Order order = new Order(orders_number,sum,str);
		
		return order;
	}
	

}
