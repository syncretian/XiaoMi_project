package ServiceImp;

import java.util.List;

import DaoImp.AdminDaoImp;
import Service.AdminService;
import entity.Page;
import entity.User;

public class AdminServiceImp implements AdminService {

	private AdminDaoImp dao = new AdminDaoImp();
	@Override
	public User getUserByNamePsd(String username, String psd) {
		
		return dao.getUserByNamePsd(username,psd);
	}

	public Page<User> findUserList(String str) {
		int current_page ;
		//进入用户管理，无该参数，默认为首页
		if(str != null) {
			try{
				current_page = Integer.parseInt(str);
			}catch(NumberFormatException e) {
				current_page = 1 ;  //如果解析错误，也默认到首页
			}
			
		} 
		else {
			current_page = 1 ;
		}
		
		int size = 4;
		int sum = dao.getCount();

		Page<User> page = new Page<User>(size,sum,current_page);

		System.out.println("current_page:  "+page.getCurrent_page());
	
		List<User> list = null;
		if(sum>0) {
			list =  dao.findUserList((page.getCurrent_page()-1)*size,size);
		}
		
		page.setUsers(list);
		System.out.println("page s data:"+page.toString());
		return page;
	}

	public int getCount() {
		
		return dao.getCount();
	}

	public List<User> findUserList(int page, int size) {
		
		return dao.findUserList(page,size);
	}

	public int updateRole(String id, String manager) {
		//指定管理员
		if("0".equals(manager)) {
			return dao.updateRole(id, 0);
		}else if("1".equals(manager)) {
			return dao.updateRole(id, 1);
		}else{
			System.out.println("无操作r: "+manager);
		}
		return 0;
	}

	public int batchDelete(String ids) {
		String[] uids = ids.split(",");
		int count =0 ;
		for(String id : uids) {
			if(dao.batchDelete(id)>0) {
				count++;
			}
		}
		return count;
	}

}
