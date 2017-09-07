package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;


@WebServlet(urlPatterns = { "/status" })
public class AccountStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int status = Integer.parseInt(request.getParameter("isworking"));
		if(status == 0){
			status = 1;
		}else if(status ==1){
			status = 0;
		}

		new UserService().status(status,Integer.parseInt(request.getParameter("userid")));
		response.sendRedirect("management");
	}
}
