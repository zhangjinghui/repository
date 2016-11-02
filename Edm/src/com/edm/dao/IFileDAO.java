package com.edm.dao;

import java.util.List;

import com.edm.vo.Files;

public interface IFileDAO {
	boolean uploadFile(Files file);
	List<Files> showFile(String username);
}
