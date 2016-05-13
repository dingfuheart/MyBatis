package com.mybatis.inter;

import java.util.List;

import com.mybatis.domain.User;

public interface IUserOperation {
	
	public User selectUserByID(int id);
	
	public List<User> selectUsers(String userName);  
	
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public void deleteUser(int id);
	
	public User getUserArticles(int id);
	
}
