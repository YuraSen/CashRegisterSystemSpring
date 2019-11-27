package com.spring.controller.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class ReqFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();
		String path = ((HttpServletRequest)request).getServletPath();
 		if (session == null || session.getAttribute("user") == null) {
 			if (path.equals("") || path.equals("/") || path.equals("/login") 
 					|| path.equals("/logout") || path.equals("/registration") || path.startsWith("/css") || path.startsWith("/js")) {
 				chain.doFilter(request, response);
 			} else {
 				((HttpServletResponse)response).sendRedirect("/");
 			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
