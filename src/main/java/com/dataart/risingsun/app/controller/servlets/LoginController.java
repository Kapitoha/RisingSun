package com.dataart.risingsun.app.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dataart.risingsun.app.Constants;
import com.dataart.risingsun.app.controller.service.InstanceServiceManager;
import com.dataart.risingsun.app.model.instances.User;

/**
 * 
 * @author kapitoha
 *
 */
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException
    {
	String inputLogin = req.getParameter("input_login");
	String inputPassword = req.getParameter("input_password");
	boolean logout = req.getParameter("logout") != null;
	HttpSession session = req.getSession(true);
	if (logout)
	{
	    session.invalidate();
	    resp.sendRedirect(Constants.HOME);
	}
	else
	{
	    User user = new User();
	    user.setLogin(inputLogin);
	    user.setPassword(inputPassword.toCharArray());
	    if (!InstanceServiceManager.validateUser(user))
	    {
		req.setAttribute("errorMessage", "Incorrect login or password");
	    req.getRequestDispatcher("/index.jsp").forward(req, resp);
	    }
	    else if (inputLogin != null)
	    {
		session.setAttribute("auth", inputLogin);
		resp.sendRedirect(Constants.HOME);
	    }
	}
    }
    
    

}
