package com.zjh.dao;

import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.zjh.db.C3P0;
import com.zjh.vo.User;

public class UserDAOImpl implements IUserDAO {
	private static Connection conn=C3P0.getConnection();
	public User login(User user) {
		String sql="select * from user where username=? and password=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		User user1=null;

		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()){
				user1=new User();
				user1.setId(rs.getInt("id"));
				user1.setUsername(rs.getString("username"));
				user1.setPassword(rs.getString("password"));
				user1.setPagenum(rs.getInt("pagenum"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user1;
	}

	public byte[] queryById(int id) {
		String sql="select pic from user where id=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		byte[] buffer=null;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				Blob blob=rs.getBlob("pic");
				buffer=blob.getBytes(1, (int)blob.length());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return buffer;
	}
	public boolean register(User user){
		String sql="insert into user(username,password,pic,pagenum) values(?,?,?,5)";
		PreparedStatement pstmt=null;
		boolean flag=false;
		
		try{
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2,user.getPassword());
			//blob
			//blob pstmt.setString(3,user.getUsername());
			FileInputStream fis=new FileInputStream(user.getPicpath());
			pstmt.setBinaryStream(3, fis, fis.available());
			flag=pstmt.executeUpdate()>0?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return flag;
	}

}
