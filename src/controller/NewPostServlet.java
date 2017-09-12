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

import beans.Post;
import beans.User;
import service.PostService;
import util.TrimUtil;

@WebServlet(urlPatterns = { "/newpost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		request.setAttribute("user",user);
		request.getRequestDispatcher("/newpost.jsp").forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {
			Post post = new Post();
			post.setText(request.getParameter("text"));
			post.setSubject(request.getParameter("subject"));
			post.setUserId(Integer.parseInt(request.getParameter("userid")));
			post.setDivisionId(Integer.parseInt(request.getParameter("divisionid")));
			post.setBranchId(Integer.parseInt(request.getParameter("branchid")));
			post.setCategory(request.getParameter("category"));
			new PostService().register(post);
			response.sendRedirect("./");
			return;
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("sessionText",request.getParameter("text"));
			session.setAttribute("sessionSubject",request.getParameter("subject"));
			session.setAttribute("sessionCategory",request.getParameter("category"));
			response.sendRedirect("newpost");
			return;
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String category =TrimUtil.trimWhitespace(request.getParameter("category").trim());
		String post = TrimUtil.trimWhitespace(request.getParameter("text").trim());
		String subject = TrimUtil.trimWhitespace(request.getParameter("subject").trim());

		if (StringUtils.isEmpty(category) == true) {
			messages.add("カテゴリーを入力して下さい");
		}

		if (10 < category.length()) {
			messages.add("【カテゴリー】入力エラー");
		}

		if(StringUtils.isEmpty(subject) == true){
			messages.add("件名を入力して下さい");
		}

		if(30 < subject.length()){
			messages.add("【件名】入力エラー");
		}

		if (StringUtils.isEmpty(post) == true) {
			messages.add("本文を入力して下さい");
		}

		if (1000 < post.length()) {
			messages.add("【本文】入力エラー");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}


}
