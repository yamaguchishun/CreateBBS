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
import service.CommentService;
import util.TrimUtil;

@WebServlet(urlPatterns = { "/comment" })
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {
			Comment comment = new Comment();
			comment.setText(TrimUtil.trimWhitespace(request.getParameter("comment")));
			comment.setPostId(Integer.parseInt(request.getParameter("postid")));
			comment.setDivisionId(Integer.parseInt(request.getParameter("divisionid")));
			comment.setBranchId(Integer.parseInt(request.getParameter("branchid")));
			comment.setUserId(Integer.parseInt(request.getParameter("userid")));
			new CommentService().register(comment,comment.getUserId());
			response.sendRedirect("./");
			return;

		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("sessionPostid", request.getParameter("postid"));
			session.setAttribute("sessionComment", request.getParameter("comment"));
			response.sendRedirect("./");
			return;
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {

		String comment = TrimUtil.trimWhitespace(request.getParameter("comment"));

		if (StringUtils.isEmpty(comment) == true) {
			messages.add("コメントを入力してください");
		}
		if (500 < comment.length()) {
			messages.add("コメント500文字以下で入力してください");
		}
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
