package com.zjh.service;

import java.util.List;

import com.zjh.vo.Article;
import com.zjh.vo.PageBean;

public interface IArticleService {
	List<Article> queryArticles();
	PageBean queryAByP(int curPage, int userid);
	boolean delArticle(int id);
	boolean addArticle(Article a);
}
