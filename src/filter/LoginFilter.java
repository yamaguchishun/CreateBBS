package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import service.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try{
			String path = ((HttpServletRequest)request).getServletPath();
			User sessionUser = (User) ((HttpServletRequest) request).getSession().getAttribute("user");

			if(path.equals("/login") || path.matches("/css/.*.css$")) {
				chain.doFilter(request, response);
			}else{
				if(sessionUser != null){
					User user = new UserService().checkStatus(sessionUser.getId());
					if(user.getIsWorking()==0){
						chain.doFilter(request, response);
					}else{
						((HttpServletResponse)response).sendRedirect("login");
						return;
					}
				}else{
					((HttpServletResponse)response).sendRedirect("login");
					return;
				}
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