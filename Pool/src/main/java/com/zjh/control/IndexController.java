package com.zjh.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="/IndexController",urlPatterns={"/index"},initParams={
	@WebInitParam(name="index",value="article?action=query&curpage=1")
})
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String,String> map=new HashMap<String,String>();
	
    public void init(ServletConfig config) throws ServletException {
    	map.put("index",config.getInitParameter("index"));
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher(map.get("index"));
		dispatcher.forward(request, response);
	}

}
