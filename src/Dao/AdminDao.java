package Dao;

import java.util.List;

import entity.User;

public interface AdminDao {
	 /*QueryRunner qr;*/
	 User getUserByNamePsd(String name, String psd);
	 
	 public List<User> findUserList();
	 
	 public int getCount();
	 
	 public List<User> findUserList(int page, int size);
	 
	 public int updateRole(String id, int i);
	 
	 public int batchDelete(String ids);
}
