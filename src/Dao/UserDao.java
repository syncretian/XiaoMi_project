package Dao;

import entity.User;

public interface UserDao {
	
	public Object checkPhone(String phone);
	
	public User verifyUser(String phone);
}
