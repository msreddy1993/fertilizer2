package com.sss.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.sss.pojo.User;

/** 
 * @author Santhosh
 * 
 * <code>SessionManager</code> stores the session, Sets the informations of user
 * as {@link User} representations in to a ThreadLocal variable, which is
 * retrieved to get the informations of the current user.
 * <p>
 * This class includes the methods to get {@link User}, set {@link User}
 * from {@link HttpServletRequest}
 * </p>
 * 
 * 
 */
public class SessionManager
{
    /**
     * Represents name of the session attribute
     */
    public static final String AUTH_SESSION_COOKIE_NAME = "User";

    // It has to be static and final for ThreadLocal to work properly
    public static final ThreadLocal<User> threadLocal = new ThreadLocal<User>();

    /**
     * Sets {@link User} to threadloacl object
     * 
     * @param user
     *            {@link User}
     */
    public static void set(User user)
    {
	threadLocal.set(user);
    }

    /**
     * Removes value from the {@link ThreadLocal} variable
     */
    public static void unset()
    {
	threadLocal.remove();
    }

    /**
     * Gets the {@link User} from the current {@link ThreadLocal} object
     * 
     * @return
     */
    public static User get()
    {
	return threadLocal.get();
    }
    
    /**
     * Gets the {@link User} from the current {@link ThreadLocal} object
     * 
     * @return
     */
    public static User getFromRequest(HttpServletRequest request)
    {
    	 return (User) request.getSession().getAttribute(
    				AUTH_SESSION_COOKIE_NAME);
    }

    /**
     * Sets User from request in to {@link SessionManager}'s
     * {@link ThreadLocal}. {@link User} is fetched from session cookie
     * which is set during login, registration
     * 
     * @param request
     * @throws ServletException
     */
    public static void set(HttpServletRequest request) throws ServletException
    {
	// Fetch User from the session attribute
	User user = (User) request.getSession().getAttribute(
		AUTH_SESSION_COOKIE_NAME);
	if (user == null)
	    throw new ServletException("Request null");

	// If User is not null, then User is set to ThreadLocal
	set(user);
    }
}