package de.fhws.app.presentation.showcase.filter;

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

@WebFilter(urlPatterns={"/*"})
public class FirefoxBlockFilter implements Filter {
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//System.out.println("im Filter vor Servlet");
		
		if (((HttpServletRequest)request).getHeader("User-Agent").toLowerCase().contains("firefox")) {
			//Firefox browser
			((HttpServletResponse)response).getWriter().println("FireFox Browser - mögen wir nicht!");
			return;
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		
		//System.out.println("im Filter nach Servlet");
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
