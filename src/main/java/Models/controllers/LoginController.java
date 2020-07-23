package Models.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.User;
import Models.loginDTO;
import Models.services.LoginService;

public class LoginController {
private static final LoginService ls = new LoginService();
private static final ObjectMapper ow = new ObjectMapper();
public User getcurrentuser(){
	return ls.getcurrentuser();
}
public void login(HttpServletRequest req, HttpServletResponse res)throws IOException{
	if(req.getMethod().equals("POST")) {
		BufferedReader reader = req.getReader();
		
		
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		
		while (line != null) {
			s.append(line);
			line=reader.readLine();
		}
		
		String body = new String (s);		
		loginDTO l = ow.readValue(body,loginDTO.class);
		
		if(ls.login(l)) {
			HttpSession ses = req.getSession();
			ses.setAttribute("user", l);
			ses.setAttribute("loggedin", true);
			res.setStatus(200);
		}else {
			HttpSession ses = req.getSession(false);
			if(ses!= null) {
				res.getWriter().println("You couldn't login.");
				ses.invalidate();
				}
			res.setStatus(401);
		}
	}
		}
public User loginfinduser(HttpServletRequest req, HttpServletResponse res)throws IOException{
	if(req.getMethod().equals("POST")) {
		BufferedReader reader = req.getReader();
		
		
		StringBuilder s = new StringBuilder();
		String line = reader.readLine();
		
		while (line != null) {
			s.append(line);
			line=reader.readLine();
		}
		
		String body = new String (s);
		System.out.println(body);
		
		loginDTO l = ow.readValue(body,loginDTO.class);
		if(l != null) {
		User a = ls.loginfinduser(l);
		return a;
		}
		else {
			return null;
		}
		}else {
			HttpSession ses = req.getSession(false);
			if(ses!= null) {
				ses.invalidate();
			}
			res.setStatus(401);
			res.getWriter().println("cant find user");
			return null;
		}
	}
		
	public void logout(HttpServletRequest req2, HttpServletResponse res2)  throws IOException {
		HttpSession ses = req2.getSession(false);
		
		if(ses!=null) {
			loginDTO l = (loginDTO) ses.getAttribute("user");
			ses.invalidate();
			res2.setStatus(200);
			res2.getWriter().println(l.username+" you logged out.");
		} else {
			res2.setStatus(400);
			res2.getWriter().println("You must be logged in to log out.");
		}
	
		
	
	//may want to remove
	//else if (req.getMethod().equals("GET")&&req.getParameterMap().containsKey("username")&&(req.getParameter("password"))) {
		
	//}

	


}
}
