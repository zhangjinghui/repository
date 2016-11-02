package com.zjh.dao;

import java.util.List;

import com.zjh.vo.Article;
import com.zjh.vo.PageBean;

public interface IArticleDAO {
	List<Article> queryArticles();
	PageBean queryAByP(int curPage, int userid);
	boolean delArticle(int id);
	boolean addArticle(Article a);
}
