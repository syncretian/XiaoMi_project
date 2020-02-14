package DaoImp;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import Dao.CommodityDao;
import entity.Cate;
import entity.Commodity;
import entity.Trolley;
import entity.User;
import utils.jdbcUtils;

public class CommodityDaoImp implements CommodityDao {

	public int getCount(String where_str) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select count(*) from commodity c "+where_str;
		System.out.println("sql: "+sql);
		long a = 0;
		try {
			
			a = (long) qr.query(sql, new ScalarHandler());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return (int)a;
	}

	
	public List<Commodity> findCommodityList(String str) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "SELECT cc.name AS category_name ,c.* FROM commodity c, category cc WHERE c.gid=cc.gid "+str;
		System.out.println("sql: "+sql);
		try {
			return qr.query(sql,  new BeanListHandler<Commodity>(Commodity.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public List<Cate> findCategroyList() {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select * from category  order by create_time desc ";		
		try {
			
			return qr.query(sql, new BeanListHandler<Cate>(Cate.class));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public int saveCommodity(Commodity co) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "insert into commodity(gid,name,color,size,price," + 
				"description,full_description,pic,state,version,product_date)"
				+ " value (?,?,?,?,?,?,?,?,?,?,?)";		
		try {
			
			return qr.update(sql,co.getGid(),co.getName(),
					co.getColor(),co.getSize(),co.getPrice(),co.getDescription(),
					co.getFull_description(),co.getPic(),co.getState(),co.getVersion(),
					co.getProduct_date());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}


	public Commodity findCommodityById(Integer cid) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select *  from commodity where cid =?";		
		
		try {
			return qr.query(sql, new BeanHandler<Commodity>(Commodity.class),cid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return null;
	}


	public int updateCommodity(Commodity co) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "update commodity set gid=?,name=?,color=?,size=?,price=?,"
				+ "description=?,full_description=?,pic=?,state=?,version=?,product_date=?where cid =?";		
		
		try {
			return qr.update(sql,co.getGid(),co.getName(),
					co.getColor(),co.getSize(),co.getPrice(),co.getDescription(),
					co.getFull_description(),co.getPic(),co.getState(),co.getVersion(),
					co.getProduct_date(),co.getCid());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
		
	}


	public int deleteCommodity(String cid) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql="delete from commodity where cid =?";
		
		try {
			return qr.update(sql,cid);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return 0;
	}


	public List<Cate> findCategroyList1(int i) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select * from category  order by create_time desc  limit ?";		
		try {
			
			return qr.query(sql, new BeanListHandler<Cate>(Cate.class),i);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	public Cate findCategroyListName(String str) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select * from category where name like ? ";		
		try {
			
			return qr.query(sql, new BeanHandler<Cate>(Cate.class),str);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	public List<Commodity> getCommodityState(Integer gid, Object ob, Integer n) {
		QueryRunner qr = jdbcUtils.getQr();
		String sql = null;
		List<Commodity> co = null;
		try {
			if(gid == null ) {
				sql = "select * from commodity where state = ? order by product_date desc limit ?";
				co = qr.query(sql, new BeanListHandler<Commodity>(Commodity.class), (int)ob,n);
			}else {
				sql = "select * from commodity where gid = ? order by product_date desc limit ?";
				co = qr.query(sql, new BeanListHandler<Commodity>(Commodity.class), gid,n);
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return co;
	}


	public Trolley getTrolleyBy(Integer uid, Integer cid) {
		Trolley tt = null;
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = " SELECT * FROM trolley WHERE uid=? AND cid=?";
		
		try {
			tt= (Trolley) qr.query(sql,  new BeanHandler<Trolley>(Trolley.class), uid,cid );
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	
		return tt ;
	}


	public int addNumber(Integer tid) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "UPDATE trolley SET number=number+1 WHERE tid=?";
		
		try {
			return qr.update(sql,tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	public int addTrolly(Trolley tro) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "insert into trolley value(null,?,?,?)";
		
		try {
			return qr.update(sql,tro.getUid(),tro.getCid(),1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
		
	}


	public List<Commodity> getTrolleyList(int id) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "SELECT DISTINCT c.*,t.number as number FROM commodity c,trolley t WHERE t.uid=? AND t.cid=c.cid";
		
		try {
			return qr.query(sql, new BeanListHandler<Commodity>(Commodity.class),id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}


	public Boolean deleteTrolleyCommodity(Integer uid, Integer cid) {
		QueryRunner qr = jdbcUtils.getQr();
		
		int a = 0;
		String sql = "DELETE FROM trolley WHERE uid = ? AND cid =?";
		
		try {
			a = qr.update(sql,uid,cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a>0;
	}


	public boolean addOrderInfo(String orders_number, Trolley tro, Commodity co) {
		QueryRunner qr = jdbcUtils.getQr();
		
		int a = 0;
		String sql = "insert into orders values(null, ? ,? ,? ,?,?,?)";
		
		try {
			a = qr.update(sql,"'"+orders_number+"'", tro.getUid(), co.getPrice()*tro.getNumber(), 0 ,
					new SimpleDateFormat ("yyyy-MM-dd HH:MM:ss").format(new Date()) , tro.getNumber());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a>0;
		
	}


	

}
