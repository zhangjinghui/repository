package com.zjh.service;

import java.util.List;

import com.zjh.dao.ArticleDAOImpl;
import com.zjh.dao.IArticleDAO;
import com.zjh.vo.Article;
import com.zjh.vo.PageBean;

public class ArticleServiceImpl implements IArticleService {
	private IArticleDAO dao=new ArticleDAOImpl();
	@Override
	public List<Article> queryArticles() {
		// TODO Auto-generated method stub
		return dao.queryArticles();
	}
	@Override
	public PageBean queryAByP(int curPage, int userid) {
		// TODO Auto-generated method stub
		return dao.queryAByP(curPage, userid);
	}
	@Override
	public boolean delArticle(int id) {
		// TODO Auto-generated method stub
		return dao.delArticle(id);
	}
	@Override
	public boolean addArticle(Article a) {
		// TODO Auto-generated method stub
		return dao.addArticle(a);
	}

}
