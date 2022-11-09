package com.springmvc.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springmvc.manager.LoginManager;
import com.springmvc.manager.UsersManager;
import com.springmvc.model.Admin;
import com.springmvc.model.LoginBean;
import com.springmvc.model.Users;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginBean login = new LoginBean();
		
		login.setUsername(username);
		login.setPassword(password);

		LoginManager lgmanage = new LoginManager();
		String result = lgmanage.doLogin(login);
		
		if("login success users".equalsIgnoreCase(result)) {
			UsersManager usermanage = new UsersManager();
			Users user = usermanage.doUsersDetail(login.getUsername());
			session.setAttribute("user", user);
			session.setAttribute("username", login.getUsername());
			return "listroomuser";
		}
		
		else if("login success admin".equalsIgnoreCase(result)) {
			UsersManager usermanage = new UsersManager();
			Admin admin = usermanage.doAdminDetail(login.getUsername());
			session.setAttribute("admin", admin);
			session.setAttribute("username", login.getUsername());
			return "listroomadmin";
		}
		
		else {
			model.addAttribute("errorlogin", "?????????? ???? ???????? ?????????? ????????????");
			UsersManager usermanage = new UsersManager();
			Users user = usermanage.doUsersDetail((String)session.getAttribute("username"));
			return "login";
		}
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String doUser(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		return "listroomuser";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String doAdmin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		UsersManager usermanage = new UsersManager();
		Admin admin = usermanage.doAdminDetail((String)session.getAttribute("username"));
		session.setAttribute("admin", admin);
		return "listroomadmin";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String doLogout(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		session.invalidate();
		return "login";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addRoomBillAdmin(HttpServletRequest request, Model model, HttpSession session) throws SQLException{
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fullname = request.getParameter("fullname");
			String roomid = request.getParameter("roomid");
			
			LoginBean login = new LoginBean(username, password);
			
			UsersManager manage = new UsersManager();
			
			Users user = new Users();
			user.setFullname(fullname);
			
			manage.addUser(login, user, roomid);

			UsersManager usermanage = new UsersManager();
			Admin admin = usermanage.doAdminDetail((String)session.getAttribute("username"));
			session.setAttribute("admin", admin);
			
			return "listroomadmin";
		} catch(Exception e) {
			System.out.println("Error : "+e.getMessage());
			return "error";
		}
	}
}