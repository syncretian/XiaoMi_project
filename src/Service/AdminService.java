package Service;

import java.util.List;

import entity.Page;
import entity.User;

public interface AdminService {
	
	User getUserByNamePsd(String name, String psd);
	
	public Page<User> findUserList(String str);
	
	public List<User> findUserList(int page, int size);
	
	public int getCount();
	
	public int updateRole(String id, String manager);
	
	public int batchDelete(String ids);
}
