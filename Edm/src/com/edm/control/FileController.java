package com.edm.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.edm.service.FileServiceImpl;
import com.edm.service.IFileService;
import com.edm.vo.Files;
import com.edm.vo.User;
@WebServlet(name="FileController",urlPatterns={"/file"})
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IFileService service=new FileServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		RequestDispatcher dispatcher=null;
		String username=null;
		
		switch (action) {
		case "uploadfile":
			username=((User)(request.getSession().getAttribute("user"))).getUsername();
			response.setContentType("text/html");
			if(service.uploadFile(request,username)){
				response.getWriter().print("<h2>�ϴ��ɹ���</h2>");
			}else{
				response.getWriter().print("<h2>�ϴ�ʧ�ܣ�</h2>");
			}
			break;
		case "showfile":
			String from = request.getParameter("from");
			username=((User)(request.getSession().getAttribute("user"))).getUsername();
			List<Files> list=service.showFile(username);
			request.setAttribute("list", list);
			if(from!=null){
				dispatcher=request.getRequestDispatcher("/data/newData.jsp");
			}else{
				dispatcher=request.getRequestDispatcher("/files/existingFiles.jsp");
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
