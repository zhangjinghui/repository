package com.zjh.service;

import javax.servlet.http.HttpServletRequest;

import com.zjh.vo.User;

public interface IUserService {
	public User login(User user);
	public byte[] queryById(int id);
	public boolean register(User user);
	public User upload(HttpServletRequest request);
}
