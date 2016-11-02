package com.zjh.control;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zjh.service.IUserService;
import com.zjh.service.UserServiceImpl;
import com.zjh.vo.User;
@WebServlet(name="UserControl",urlPatterns={"/user"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService service=new UserServiceImpl();
	
    public UserController() {
        super();
    }
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=null;
		String action=request.getParameter("action");
		switch (action) {
		case "login":
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			User user=new User();
			user.setUsername(username);
			user.setPassword(password);
			User user1=service.login(user);
			dispatcher=request.getRequestDispatcher("article?action=query&curpage=1");
			if(user1!=null){
				request.getSession().setAttribute("user",user1);
				request.setAttribute("error","登录成功！欢迎"+user.getUsername());
			}else{
				request.setAttribute("error","登录失败！");
			}
			break;
		case "pic":
			String id=request.getParameter("id");
			byte[] buffer=service.queryById(Integer.parseInt(id));
			response.setContentType("image/jpeg");
			ServletOutputStream so=response.getOutputStream();
			so.write(buffer);
			so.flush();
			so.close();
			break;
		case "reg":
			User user2=service.upload(request);
			if(service.register(user2)){
				request.setAttribute("error", "注册成功！");
				dispatcher=request.getRequestDispatcher("/show.jsp");
			}
			break;
		default:
			break;
		}
		if(dispatcher!=null){
			dispatcher.forward(request, response);
		}
	}

}
