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
import util.TrimUtil;

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

		if(request.getParameter("userid") != null){
			if(request.getParameter("userid") == ""){
				messages.add("無効な値です");
				session.setAttribute("errorMessages",messages);
				response.sendRedirect("management");
				return;
			}else{
				if(request.getParameter("userid").matches("^[0-9]+$") != true  ){
					messages.add("無効な値です");
					session.setAttribute("errorMessages",messages);
					response.sendRedirect("management");
					return;
				}else if(isNumber(request.getParameter("userid")) == false){
					messages.add("無効な値です");
					session.setAttribute("errorMessages",messages);
					response.sendRedirect("management");
					return;
				}
			}
		}else{
			messages.add("無効な値です");
			session.setAttribute("errorMessages",messages);
			response.sendRedirect("management");
			return;
		}

		List<User> users = new UserService().getUser(Integer.parseInt(request.getParameter("userid")));
		User user = new UserService().getEditUser(Integer.parseInt(request.getParameter("userid")));

		if(users.size()==0){
			messages.add("無効な値です");
			session.setAttribute("errorMessages",messages);
			response.sendRedirect("management");
			return;
		}else{
			request.setAttribute("branches", branches);
			request.setAttribute("divisions", divisions);
			request.setAttribute("sessionUser", sessionUser);
			request.setAttribute("user", user);
			request.setAttribute("sessionUser",sessionUser);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		List<Branch> branches = new BranchService().getBranch();
		List<Division> divisions = new DivisionService().getDivision();
		List<User> users = new UserService().getUser(Integer.parseInt(request.getParameter("userid")));
		boolean checkLoginId = new UserService().checkLoginId(request.getParameter("account"),users);
		User sessionUser = (User) request.getSession().getAttribute("user") ;
		User user = new User();

		if(Integer.parseInt(request.getParameter("userid")) == sessionUser.getId()){
			user.setId(sessionUser.getId());
			user.setName(request.getParameter("name"));
			user.setAccount(request.getParameter("account"));
			user.setBranchId(sessionUser.getBranchId());
			user.setDivisionId(sessionUser.getDivisionId());
			user.setPassword(request.getParameter("password"));

		}else{
			user.setId(Integer.parseInt(request.getParameter("userid")));
			user.setName(request.getParameter("name"));
			user.setAccount(request.getParameter("account"));
			user.setBranchId(Integer.parseInt(request.getParameter("branch")));
			user.setDivisionId(Integer.parseInt(request.getParameter("division")));
			user.setPassword(request.getParameter("password"));
		}

		if(checkLoginId == false){
			messages.add("入力したログインIDは既に使用されています");
			request.setAttribute("branches", branches);
			request.setAttribute("divisions", divisions);
			request.setAttribute("errorMessages", messages);
			request.setAttribute("user",user);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
			return;
		}

		if (isValid(request, messages) == true) {
			new UserService().update(user);
			response.sendRedirect("management");
			return;
		} else {
			request.setAttribute("branches", branches);
			request.setAttribute("divisions", divisions);
			request.setAttribute("errorMessages", messages);
			request.setAttribute("user",user);
			request.getRequestDispatcher("/edit.jsp").forward(request, response);
			return;
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = TrimUtil.trimWhitespace(request.getParameter("account"));
		String password = TrimUtil.trimWhitespace(request.getParameter("password"));
		String confirmation = TrimUtil.trimWhitespace(request.getParameter("confirmation"));
		String name = TrimUtil.trimWhitespace(request.getParameter("name").trim());

		if (StringUtils.isEmpty(account) == true) {
			messages.add("ログインIDを入力して下さい");
		}

		if(account.matches("^[0-9a-zA-Z]{6,20}+$")!=true){
			messages.add("【ログインID】入力エラー");
		}

		if(StringUtils.isEmpty(password) == false){
			if(password.matches("[ -~｡-ﾟ]{6,20}+")!=true){
				messages.add("【パスワード】入力エラー");
			}
		}

		if(password.equals(confirmation)==false){
			messages.add("パスワードと確認用パスワードが一致していません");
		}

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力して下さい");
		}

		if(10 < name.length()){
			messages.add("【名前】入力エラー");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNumber(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}
}