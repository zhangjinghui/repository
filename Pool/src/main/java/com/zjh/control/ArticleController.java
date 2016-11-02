package com.zjh.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zjh.service.ArticleServiceImpl;
import com.zjh.service.IArticleService;
import com.zjh.vo.Article;
import com.zjh.vo.PageBean;
import com.zjh.vo.User;

@WebServlet(name="ArticleControl",urlPatterns={"/article"})
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IArticleService service=new ArticleServiceImpl();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getParameter("action");
		RequestDispatcher dispatcher=null;
		
		switch (path) {
		case "query":
			int curPage=Integer.parseInt(request.getParameter("curpage"));
			int userid=999;
			if(request.getSession().getAttribute("user")!=null){
				userid=((User)(request.getSession().getAttribute("user"))).getId();
			}
			PageBean pb=service.queryAByP(curPage, userid);
//			List<Article> list=service.queryArticles();
			request.setAttribute("pb", pb);
			dispatcher=request.getRequestDispatcher("/show.jsp");
			break;
		case "delz":
			String id=request.getParameter("id");
			if(service.delArticle(Integer.parseInt(id))){
				dispatcher=request.getRequestDispatcher("article?action=query&curpage=1");
			}
			break;
		case "addz":
			String rootid=request.getParameter("rootid");
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			
			Article a=new Article();
			a.setRootid(Integer.parseInt(rootid));
			a.setTitle(title);
			a.setContent(content);
			
			User u=new User();
			u.setId(((User)(request.getSession().getAttribute("user"))).getId());
			a.setUser(u);
			if(service.addArticle(a)){
				dispatcher=request.getRequestDispatcher("article?action=query&curpage=1");
			}
			break;
		default:
			break;
		}
		dispatcher.forward(request, response);
	}

}
