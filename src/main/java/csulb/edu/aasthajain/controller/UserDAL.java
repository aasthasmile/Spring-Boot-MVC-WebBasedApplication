package csulb.edu.aasthajain.controller;

import java.util.List;

import csulb.edu.aasthajain.model.User;

public interface UserDAL {

	public List<User> getAllUser();
	
	//public User findUserById(String Id);
	//public User findUserByName(String name);
	
	public int CreateUser(User user);
	public int updateUser(User user);
	public int deleteUser(String id);
	
	public void insertBatch();
	
}
