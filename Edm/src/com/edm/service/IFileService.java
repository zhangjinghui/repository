package com.edm.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.edm.vo.Files;

public interface IFileService {
	boolean uploadFile(HttpServletRequest request,String username);
	List<Files> showFile(String username);
}
