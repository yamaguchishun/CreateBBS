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

import beans.Branch;
import beans.Division;
import beans.User;
import service.BranchService;
import service.DivisionService;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		List<Branch> branches = new BranchService().getBranch();
		List<Division> divisions = new DivisionService().getDivision();
		User sessionUser = (User) request.getSession().getAttribute("user") ;

		if(request.getParameter("userid") == " "){
			messages.add("存在しないユーザです");
			session.setAttribute("errorMessages",messages);
			response.sendRedirect("management");
		}

		List<User> users = new UserService().getUser(Integer.parseInt(request.getParameter("userid")));

		if(users.size()==0){
			messages.add("存在しないユーザです");
			session.setAttribute("errorMessages",messages);
			response.sendRedirect("management");
		}else{
			request.setAttribute("branches", branches);
			request.setAttribute("divisions", divisions);
			request.setAttribute("users", users);
			request.setAttribute("sessionUser",sessionUser);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		List<Branch> branches = new BranchService().getBranch();
		List<Division> divisions = new DivisionService().getDivision();
		List<User> users = new UserService().getUser(Integer.parseInt(request.getParameter("userid")));

		User user = new User();
		user.setId(Integer.parseInt(request.getParameter("userid")));
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch")));
		user.setDivisionId(Integer.parseInt(request.getParameter("division")));
		user.setPassword(request.getParameter("password"));

		if (isValid(request, messages) == true) {
			new UserService().update(user);
			response.sendRedirect("management");
		} else {
			request.setAttribute("branches", branches);
			request.setAttribute("divisions", divisions);
			request.setAttribute("errorMessages", messages);
			request.setAttribute("users",users);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account").trim();
		String password = request.getParameter("password").trim();
		String confirmation = request.getParameter("confirmation").trim();
		String name = request.getParameter("name").trim();

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力して下さい");
		}

		if (name == "") {
			messages.add("名前を入力して下さい");
		}

		if(10 < name.length()){
			messages.add("名前は10文字以内で入力して下さい");
		}

		if (StringUtils.isEmpty(account) == true) {
			messages.add("ログインIDを入力して下さい");
		}

		if(account.matches("^[0-9a-zA-Z]{6,20}+$")!=true){
			messages.add("ログインIDは半角英数字6～20文字以内で入力して下さい");
		}

		if(StringUtils.isEmpty(password) == false){
			if(password.matches("[ -~｡-ﾟ]{6,20}+")!=true){
				messages.add("パスワードは半角文字6～20文字以内で入力して下さい");
			}
		}

		if(password.equals(confirmation)==false){
			messages.add("パスワードと確認用パスワードが一致していません");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}