package Service;

import entity.User;

public interface UserService {
	boolean checkPhone(String phone);

	public User verifyUser(String phone);
}
