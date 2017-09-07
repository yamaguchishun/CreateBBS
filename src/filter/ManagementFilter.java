package filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

@WebFilter("/management")
public class ManagementFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try{
			User sessionUser = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
			HttpSession session = ((HttpServletRequest) request).getSession();
			List<String> messages = new ArrayList<String>();

			if(sessionUser.getDivisionId()==1){
				chain.doFilter(request, response);
			}else{
				messages.add("アクセスエラー");
				session.setAttribute("errorMessages", messages);
				((HttpServletResponse) response).sendRedirect("./");
			}

		}catch (ServletException se){
		}catch (IOException e){
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException{

	}

	public void destroy(){

	}
}