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

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Branch> branches = new BranchService().getBranch();
		List<Division> divisions = new DivisionService().getDivision();

		request.setAttribute("branches", branches);
		request.setAttribute("divisions", divisions);
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		User user = new User();
		User sessionUser = (User) request.getSession().getAttribute("user") ;

		boolean checkUser = new UserService().checkUser(request.getParameter("account"),sessionUser);

		user.setPassword(request.getParameter("password"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch")));
		user.setDivisionId(Integer.parseInt(request.getParameter("division")));
		user.setName(request.getParameter("name"));
		user.setAccount(request.getParameter("account"));

		if(checkUser == false){
			messages.add("入力したログインIDは既に使用されています");
		}

		if (isValid(request, messages) == true) {
			new UserService().register(user);
			response.sendRedirect("management");
			return;
		} else {
			session.setAttribute("errorMessages", messages);
			session.setAttribute("newUser",user);
			response.sendRedirect("signup");
			return;
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = TrimUtil.trimWhitespace(request.getParameter("account"));
		String password = TrimUtil.trimWhitespace(request.getParameter("password"));
		String confirmation = TrimUtil.trimWhitespace(request.getParameter("confirmation"));
		String name = TrimUtil.trimWhitespace(request.getParameter("name"));

		if (StringUtils.isEmpty(account) == true) {
			messages.add("ログインIDを入力して下さい");
		}

		if(account.matches("^[0-9a-zA-Z]{6,20}+$")!=true){
			messages.add("【ログインID】入力エラー");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力して下さい");
		}

		if(password.matches("[ -~｡-ﾟ]{6,20}+")!=true){
			messages.add("【パスワード】入力エラー");
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
}