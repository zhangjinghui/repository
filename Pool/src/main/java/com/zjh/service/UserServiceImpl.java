package com.zjh.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.zjh.dao.IUserDAO;
import com.zjh.dao.UserDAOImpl;
import com.zjh.vo.User;

public class UserServiceImpl implements IUserService {
	private IUserDAO dao=new UserDAOImpl();
	private Map<String,String> types=new HashMap<String,String>();
	private File tmpDir=null;
	private File saveDir=null;
	
	{
		types.put("image/jpeg",".jpg");
		types.put("image/gif",".gif");
		types.put("image/x-ms-bmp",".bmp");
		types.put("image/png",".png");
		String tpath=System.getProperty("file.separator")+"tmp";//得到不同系统（windows、linux）路径分隔符
		String spath=System.getProperty("file.separator")+"upload";
		tmpDir=new File(tpath);
		saveDir=new File(spath);
		if(!tmpDir.exists()){
			tmpDir.mkdir();
		}
		if(!saveDir.exists()){
			saveDir.mkdir();
		}
		
	}
	public User login(User user) {
		return dao.login(user);
	}
	public byte[] queryById(int id){
		return dao.queryById(id);
	}
	public boolean register(User user){
		return dao.register(user);
	}
	public User upload(HttpServletRequest request){
		String path=request.getServletContext().getRealPath("/");
		User user=null;
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory factory=new DiskFileItemFactory();
			factory.setRepository(tmpDir);//设置临时目录
			factory.setSizeThreshold(1024*1024*100);
			
			ServletFileUpload sf=new ServletFileUpload(factory);
			sf.setFileSizeMax(1024*1024*10);//设置单文件大小限制
			sf.setSizeMax(1024*1024*50);//设置所有文件域大小总和限制
			
			FileItemIterator fi=null;
			user=new User();
			try{
				fi=sf.getItemIterator(request);
				while(fi.hasNext()){
					FileItemStream fis=fi.next();
					
					String name=fis.getFieldName();
					String type=fis.getContentType();

					InputStream is=fis.openStream();
					
					if(!fis.isFormField()&&fis.getName().length()!=0){
						if(!types.containsKey(type)){
							request.setAttribute("error", "头像类型不匹配");
							break;
						}
						BufferedInputStream bis=new BufferedInputStream(is);
						String filename=path+"upload\\"+UUID.randomUUID()+types.get(type);
						BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(new File(filename)));
						
						Streams.copy(bis, bos, true);//输入流拷贝到输出流
						
						user.setPicpath(filename);
						bis.close();
						bos.flush();
						bos.close();
					}else{
						switch(name){
						case "reusername":
							user.setUsername(Streams.asString(is, "utf-8"));
							break;
						case "repassword":
							user.setPassword(Streams.asString(is, "utf-8"));
						default:
							break;
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return user;
	}
}
