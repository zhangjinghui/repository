package com.zjh.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class PageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int curPage;//当前第几页
	private int maxPage;//一共多少页
	private int maxRowCount;//一共多少行
	private int rowsPerPage;//每页多少行
	private ArrayList<Article> data;//本页中要显示的资料
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getMaxRowCount() {
		return maxRowCount;
	}
	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public ArrayList<Article> getData() {
		return data;
	}
	public void setData(ArrayList<Article> data) {
		this.data = data;
	}
	
}
