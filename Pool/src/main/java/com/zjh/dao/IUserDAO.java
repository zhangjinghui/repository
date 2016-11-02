package com.zjh.dao;

import com.zjh.vo.User;

public interface IUserDAO {
	public User login(User user);
	public byte[] queryById(int id);
	public boolean register(User user);
}
