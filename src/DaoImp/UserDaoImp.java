package DaoImp;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import Dao.UserDao;
import entity.User;
import utils.jdbcUtils;
public class UserDaoImp implements UserDao {
	private static QueryRunner qr;
	static {
		try {
			
			qr=jdbcUtils.getQr();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public Object checkPhone(String phone) {
		String sql = "select count(id) from user where phone_number=?";
		
		try {
			return  qr.query(sql, new ScalarHandler(),phone);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;

	}

	public User verifyUser(String phone) {
		String sql = "select * from user where phone_number=?";
		
		try {
			return  qr.query(sql, new BeanHandler<User>(User.class),phone);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public Object checkUsername(String username) {
		String sql = "select count(id) from user where username=?";
		
		try {
			return  qr.query(sql, new ScalarHandler(),username);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public int register(User user) {
		String sql = " insert into user values(null , ? ,? ,?, ? , 1 , ? , ? ,? , ?)";
		
		try {
			return qr.update(sql , user.getName(), user.getSex(), user.getPhone_number(), user.getArea()
					,user.getUsername(),user.getPassword(), user.getPhoto() , 
					new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date()));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return 0;
	}

}
