package com.zjh.dao;

import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.zjh.db.C3P0;
import com.zjh.vo.Article;
import com.zjh.vo.PageBean;
import com.zjh.vo.User;

public class ArticleDAOImpl implements IArticleDAO {
	private static Connection conn=C3P0.getConnection();
	@Override
	public List<Article> queryArticles() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from article a,user u where a.userid=u.id and a.rootid=0 order by a.id desc";
		List<Article> list=new ArrayList<Article>();

		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Article a=new Article();
				a.setId(rs.getInt("a.id"));
				a.setRootid(rs.getInt("a.rootid"));
				a.setTitle(rs.getString("a.title"));
				a.setContent(rs.getString("a.content"));
				a.setDatetime(rs.getString("a.datetime"));

				User u=new User();
				u.setId(rs.getInt("u.id"));
				u.setPagenum(rs.getInt("u.pagenum"));
				a.setUser(u);
				
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return list;
	}
	@Override
	public PageBean queryAByP(int curPage, int userid) {
		// TODO Auto-generated method stub
		CallableStatement cs=null;
		ResultSet rs=null;
		String sql="{call p_1(?,?,?,?,?)}";
		ArrayList<Article> list=new ArrayList<Article>();
		PageBean pb=new PageBean();

		try {
			cs=conn.prepareCall(sql);
			cs.setString(1, userid+"");
			cs.setInt(2, curPage);
			cs.registerOutParameter(3, java.sql.Types.INTEGER);
			cs.registerOutParameter(4, java.sql.Types.INTEGER);
			cs.registerOutParameter(5, java.sql.Types.INTEGER);
			
			boolean flag=cs.execute();
			while(flag){
				rs=cs.getResultSet();
				pb.setMaxPage(cs.getInt(3));
				pb.setMaxRowCount(cs.getInt(4));
				pb.setRowsPerPage(cs.getInt(5));
				while(rs.next()){
					Article a=new Article();
					a.setId(rs.getInt("a.id"));
					a.setRootid(rs.getInt("a.rootid"));
					a.setTitle(rs.getString("a.title"));
					a.setContent(rs.getString("a.content"));
					a.setDatetime(rs.getString("a.datetime"));

					User u=new User();
					u.setId(rs.getInt("u.id"));
					u.setUsername(rs.getString("u.username"));
					u.setPagenum(rs.getInt("u.pagenum"));
					a.setUser(u);

					list.add(a);
				}
				pb.setData(list);
				pb.setCurPage(curPage);
				
				flag=cs.getMoreResults();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				cs.close();
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return pb;
	}
	@Override
	public boolean delArticle(int id) {
		PreparedStatement pstmt=null;
		boolean flag=false;
		
		try {
			pstmt=conn.prepareStatement("delete from article where id=? or rootid=?");
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			flag=pstmt.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}
	@Override
	public boolean addArticle(Article a) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt=null;
		boolean flag=false;
		
		try {
			pstmt=conn.prepareStatement("insert into article(rootid,title,content,userid,datetime) values(?,?,?,?,now())");
			pstmt.setInt(1, a.getRootid());
			pstmt.setString(2, a.getTitle());
			//写blob方法
			StringReader sr=new StringReader(a.getContent());
			pstmt.setCharacterStream(3, sr, a.getContent().length());
			
			pstmt.setInt(4, a.getUser().getId());
			flag=pstmt.executeUpdate()>0?true:false;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}

}
