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
import service.UserService;

@WebFilter("/*")
public class LoginFilter implements Filter{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
		try{
			HttpSession session = ((HttpServletRequest) request).getSession();
			String path = ((HttpServletRequest)request).getServletPath();
			List<String> messages = new ArrayList<String>();
			User sessionUser = (User) ((HttpServletRequest) request).getSession().getAttribute("user");

			if (path.equals("/login") || path.equals("style.css")
					|| path.equals("backgrounds.css") || path.equals("buttons.css")
					|| path.equals("forms.css") || path.equals("breadcrumbs.css") || path.equals("tables.css")
					|| path.equals("responsive.css") || path.equals("responsive.css") || path.equals("workless.css")
					|| path.equals("plugins.css") || path.equals("helpers.css") || path.equals("alerts.css")
					|| path.equals("pagination.css") || path.equals("font.css") || path.equals("scaffolding.css")){
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