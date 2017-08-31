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

import beans.User;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		System.out.println(request.getParameter("userid"));
		List<User> users = new UserService().getUser(Integer.parseInt(request.getParameter("userid")));
		request.setAttribute("users", users);
		request.getRequestDispatcher("/edit.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));
		user.setPassword(request.getParameter("password"));
		user.setBranchID(Integer.parseInt(request.getParameter("branch")));
		user.setDivisionID(Integer.parseInt(request.getParameter("division")));
		System.out.println(user.getName());
		System.out.println(user.getAccount());
		System.out.println(user.getPassword());
		System.out.println(user.getBranchID());
		System.out.println(user.getDivisionID());

//		new UserService().update(user,Integer.parseInt(request.getParameter("userid")));
//		response.sendRedirect("management");

		if (isValid(request, messages) == true) {
			new UserService().update(user,Integer.parseInt(request.getParameter("userid")));
			response.sendRedirect("management");
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("User",user);
			response.sendRedirect("management");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}