package ServiceImp;

import DaoImp.UserDaoImp;
import Service.UserService;
import entity.User;

public class UserServiceImp implements UserService {

	UserDaoImp dao = new UserDaoImp();
	@Override
	public boolean checkPhone(String phone){
		long num =0;
		
		num = (long) dao.checkPhone(phone);
		//数据库已存在该用户
		if(num>0) {
			return true;
		}
		//没有数据
		return false;
	}


	public User verifyUser(String phone) {
		return dao.verifyUser(phone);
		
	}


	public boolean checkUsername(String username) {
long num =0;
		
		num = (long) dao.checkUsername(username);
		//数据库已存在该用户
		if(num>0) {
			return true;
		}
		//没有数据
		return false;
	}


	public boolean register(User user) {
		if(0<(long)dao.checkPhone(user.getPhone_number())) {
			return false;
		}
		if(0<(long)dao.checkUsername(user.getUsername())) {
			return false;
		}
		
		int a =  dao.register(user);
		
		return a>0;
	}
	
}
