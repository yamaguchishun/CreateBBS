package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Branch;
import beans.Division;
import beans.User;
import service.BranchService;
import service.DivisionService;
import service.UserService;

@WebServlet(urlPatterns = { "/management" })
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User sessionUser = (User) request.getSession().getAttribute("user") ;
		List<User> users = new UserService().getUser();
		List<Branch> branches = new BranchService().getBranch();
		List<Division> divisions = new DivisionService().getDivision();

		request.setAttribute("sessionUser", sessionUser);
		request.setAttribute("users", users);
		request.setAttribute("branches", branches);
		request.setAttribute("divisions", divisions);
		request.getRequestDispatcher("/management.jsp").forward(request, response);
		return;
	}
}