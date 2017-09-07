package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Comment;
import beans.Post;
import beans.User;
import service.CommentService;
import service.PostService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String startdate = "20000101";
		Date todaydate = new Date();
		String enddate = todaydate.toString();
		String category = request.getParameter("category");

		List<Post> posts = new PostService().getMesaage(startdate,enddate);
		List<Comment> comments = new CommentService().getComment();
		List<Post> categorys = new PostService().getCategory();
		User sessionUser = (User) request.getSession().getAttribute("user") ;

		if(request.getParameter("startdate")!=null){
			if(request.getParameter("startdate")!=""){
				startdate = request.getParameter("startdate");
			}
		}

		if(request.getParameter("enddate")!= null){
			if(request.getParameter("enddate")!=""){
				enddate = request.getParameter("enddate");
			}
		}

		posts = new PostService().getMesaage(startdate,enddate,category);

		request.setAttribute("user", sessionUser);
		request.setAttribute("posts", posts);
		request.setAttribute("comments", comments);
		request.setAttribute("categorys",categorys);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}

