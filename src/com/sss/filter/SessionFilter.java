package com.sss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sss.pojo.User;
import com.sss.session.SessionCache;
import com.sss.session.SessionManager;

/**
 * @author Santhosh
 *
 */
public class SessionFilter implements Filter{
	
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		// Reset the thread local
		SessionManager.set((User) null);
		
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) res;
		
		// If no sessions are there, redirect to login page
		if (isDevelopmentEnvironment(httpRequest) && httpRequest.getSession(false) == null)
		{
		    redirectToLogin(httpRequest, httpResponse);
		    return;
		}
		
		// Check if UserInfo is already there
		User user = (User) httpRequest.getSession().getAttribute(SessionManager.AUTH_SESSION_COOKIE_NAME);
		if (user == null)
		{
		    redirectToLogin(httpRequest, httpResponse);
		    return;
		}
		
		chain.doFilter(httpRequest, httpResponse);
	    SessionCache.unsetSession();
		return;
	}

	public void init(FilterConfig config) throws ServletException {
		//Nothing to do
	}
	
	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
	// Gets the reqeust uri
	String uri = request.getRequestURI();

	// If uri doesn't contain "core" in it, then uri is set in session for
	// redirection
	if (uri.contains("/core"))
	{
	    // Sends error response, so that user is notified about session
	    // expiry
	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You are not logged in.");
	    return;
	}
	
	response.sendRedirect("/login");
    }
	
	public boolean isDevelopmentEnvironment(HttpServletRequest httpRequest){
		if(httpRequest.getRequestURI() != null && httpRequest.getRequestURI().contains("localhost"))
			return true;
		return false;
	}
}
