package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.Comment;
import beans.Post;
import service.CommentService;
import service.PostService;

@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		//boolean isShowMessageForm = true;
		List<Post> messages = new PostService().getMesaage();
		List<Comment> comments = new CommentService().getComment();

		request.setAttribute("messages", messages);
		request.setAttribute("comments", comments);
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		int postid = Integer.parseInt(request.getParameter("postid"));
		System.out.println(request.getParameter("postid"));

		if (isValid(request, messages) == true) {
//			User user = (User) session.getAttribute("loginUser");
			Comment comment = new Comment();
			comment.setText(request.getParameter("comment"));
			comment.setPostID(postid);
			new CommentService().register(comment);
			response.sendRedirect("home");

		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String post = request.getParameter("text");

		if (StringUtils.isEmpty(post) == true) {
			messages.add("コメントを入力してください");
		}
		if (500 < post.length()) {
			messages.add("500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}
