package DaoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import Dao.AdminDao;
import entity.User;
import utils.jdbcUtils;

public class AdminDaoImp implements AdminDao {
	@Override
	public User getUserByNamePsd(String username, String psd) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select * from user where username=? and password=?";
		try {
			
			return qr.query(sql, new BeanHandler<User>(User.class),username,psd);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public List<User> findUserList() {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select * from user";
		
		try {
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public int getCount() {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select count(*) from user";
		long a = 0;
		try {
			
			a = (long) qr.query(sql, new ScalarHandler());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return (int)a;
	}

	public List<User> findUserList(int begin, int size) {
		QueryRunner qr = jdbcUtils.getQr();
		
		String sql = "select * from user  ORDER BY create_time DESC limit ?,?";
		try {
			return qr.query(sql,  new BeanListHandler<User>(User.class),begin , size);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public int updateRole(String id, int i) {
		QueryRunner qr = jdbcUtils.getQr();
		
		System.out.println("dao ...");
		String sql = "update user set manager=? where id=?";
		try {
			return qr.update(sql,i,id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

	public int batchDelete(String id) {
		QueryRunner qr = jdbcUtils.getQr();
		
		System.out.println("dao ...");
		String sql = "delete from user where id=?";
		try {
			return qr.update(sql,id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

}
