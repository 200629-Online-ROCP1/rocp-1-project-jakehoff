package Models.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import Models.Account;
import Models.AccountStatus;
import Models.ChangeStatus;
import Models.ChangeString;
import Models.User;
import Models.controllers.AccountController;
import Models.controllers.LoginController;
import Models.controllers.RoleController;
import Models.controllers.UserController;
import Models.Deposit;
import Models.LookUser;
import Models.Withdraw;
import Models.hashing;
import Models.intrest;
import Models.Transfer;
public class MasterServlet extends HttpServlet {
	public static final ObjectMapper om = new ObjectMapper();
	private static final AccountController ac = new AccountController();
	private static final LoginController lc = new LoginController();
	private static final UserController uc = new UserController();
	private static final RoleController rc = new RoleController();
	private User CurrentUser;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);

		final String URI = req.getRequestURI().replace("/rocp-project/", "");
		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));
		System.out.println(req.getRequestURI());
		try {
			System.out.println(portions[0]);
			switch (portions[0]) {

			case "account":

				HttpSession ses = req.getSession(false);
				// checks if logged in
				// impossible to have session and not logged in
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
						// for adding stuff
						if (req.getMethod().equals("POST")) {
							BufferedReader reader = req.getReader();

							StringBuilder s = new StringBuilder();

							String line = reader.readLine();

							while (line != null) {
								s.append(line);
								line = reader.readLine();
							}

							String body = new String(s);
							Account a = om.readValue(body, Account.class);
							if (ac.addAccount(a)) {
								res.setStatus(201);
								res.getWriter().println("Account was created");// remove for final
							} else {
								List<Account> all = ac.findAll();
								res.setStatus(200);
								res.getWriter().println(om.writeValueAsString(all));
							}
						}

					}
				} else {
					res.setStatus(401);
					res.getWriter().println("you must be logged in to access this feature");
				}
				break;
			case "user":
				//done
				if (portions.length == 2) {
					// System.out.println("here");
					int id = Integer.parseInt(portions[1]);
					User u = uc.findById(id);
					res.setStatus(200);
					res.getWriter().println(om.writeValueAsString(u));
				} else {
					// for adding stuff
					// System.out.println("there");
					if (req.getMethod().equals("POST")) {
						BufferedReader reader = req.getReader();

						StringBuilder s = new StringBuilder();

						String line = reader.readLine();

						while (line != null) {
							s.append(line);
							line = reader.readLine();
						}

						String body = new String(s);
						User u = om.readValue(body, User.class);
						if (uc.addUser(u)) {

							res.setStatus(201);
							res.getWriter().println("Account was created");// remove for final
						} else {

							List<User> all = uc.findAll();
							res.setStatus(200);
							res.getWriter().println(om.writeValueAsString(all));
						}
					}

				}

				break;
			case "rolestatus":
				ses = req.getSession(false);
				if(CurrentUser.getRole().getRole().equals("admin")) {
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
				if (req.getMethod().equals("POST")) {
					if(CurrentUser.getRole().getRole().equals("admin")) {

					BufferedReader reader = req.getReader();
					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					ChangeStatus i = om.readValue(body, ChangeStatus.class);
					//int x = om.readValue(body, int.class);
					float z = i.getu();
					System.out.println("aaa");
					String st = i.getS();
					int a = i.getu();
					AccountStatus az = new AccountStatus(a,st);
					az.setStatus(st);
					az.setStatusId(a);
					System.out.println(st);
					rc.ChangeStatus(az,st);
					System.out.println(a);
						res.setStatus(201);
						res.getWriter().println("Account was created");// remove for final
					}
			}
				}
				}}
				
				break;
			case "change":
				ses = req.getSession(false);

				ses = req.getSession(false);
				// checks if logged in
				// impossible to have session and not logged in
				if(CurrentUser.getRole().getRole().equals("admin")) {
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
					if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
						if (portions.length == 2) {
							int id = Integer.parseInt(portions[1]);
							Account a = ac.findById(id);
							res.setStatus(200);
							res.getWriter().println(om.writeValueAsString(a));
						}
				else {
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();
					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					ChangeString i = om.readValue(body, ChangeString.class);					
					String sa = i.getS();
					String st = i.getSt();
					User a = uc.findById(i.getu());
					if(CurrentUser.getUserId()==a.getUserId()) {
					rc.ChangeInfo(a,sa,st);

						res.setStatus(201);
						res.getWriter().println("Your changes where succesful");// remove for final
					
				}
				}else {
					res.getWriter().println("You don't ahve permision to change that.");// remove for final

				}
					
						
				}}}}}
				break;
			case "changeother":
				ses = req.getSession(false);
				// checks if logged in
				// impossible to have session and not logged in

				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					res.getWriter().println("3");
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
						System.out.println("there");
					}
			else {
				if(CurrentUser.getRole().getRole().equals("admin")) {
				if (req.getMethod().equals("POST")) {
					if(CurrentUser.getRole().getRole().equals("admin")) {

					BufferedReader reader = req.getReader();
					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					ChangeString i = om.readValue(body, ChangeString.class);					
					String sa = i.getS();
					String st = i.getSt();
					User a = uc.findById(i.getu());
					
					rc.ChangeInfo(a,sa,st);

						res.setStatus(201);
						res.getWriter().println("Your changes where succesful");// remove for final
					
				}
				}}}}
					
				break;
			case "destroy":
				ses = req.getSession(false);
				// checks if logged in
				// impossible to have session and not logged in
				if(CurrentUser.getRole().getRole().equals("admin")) {
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					res.getWriter().println("3");
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
						System.out.println("there");
					}
			else {
				if (req.getMethod().equals("POST")) {
					
					if(CurrentUser.getRole().getRole().equals("admin")) {
						BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					ChangeString a = om.readValue(body, ChangeString.class);
					System.out.println(a.getu());
					rc.DestroyUser(a.getS(),a.getSt(), a.getu());
					//String st = u.toString();
					//find doesnt print to postman
					//System.out.println(st);
				}
				}}}}
				break;
			case "destroyself":
				if (req.getMethod().equals("POST")) {
					String table = "users";
	 				String field = "users_id";
	 				ChangeString s= new ChangeString(table,field,CurrentUser.getUserId());
	 				System.out.println(CurrentUser);
					rc.DestroyUser(s.getS(),s.getSt(), s.getu());
					lc.logout(req, res);
				}
				break;
			case "passtime":
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					intrest a = om.readValue(body, intrest.class);
					float f = a.calcintrest(a.getP(), a.getR(), a.getT());
					System.out.println(f);

				}
				break;
			case"hash":
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					hashing h = om.readValue(body, hashing.class);
					String hashed = h.hash(h.getPasswordToHash());
					res.getWriter().println(hashed);

				}
				break;
			case "viewuser":
				ses = req.getSession(false);				
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
				if (req.getMethod().equals("POST")) {
					if(CurrentUser.getRole().getRole().equals("admin")||CurrentUser.getRole().getRole().equals("employee")) {
					BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					LookUser a = om.readValue(body, LookUser.class);
					User u = uc.findById(a.getId());
					String st = u.toString();
					//find doesnt print to postman
					res.getWriter().println(st);				}	
			}}}
				break;
			case "viewalluser":
				ses = req.getSession(false);				
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
				if (req.getMethod().equals("POST")) {
					if((CurrentUser.getRole().getRole().equals("admin"))||(CurrentUser.getRole().getRole().equals("employee"))) {
					BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}
					//LookUser a = om.readValue(body, LookUser.class);
					List<User> u = uc.findAll();
					
					int i=0;
					while(i <= u.size()-1) {
						String st = u.get(i).toString();
						res.getWriter().println(st);						i++;
					}
				}}}}
				
				break;
			case "viewmyaccount":

				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					LookUser a = om.readValue(body, LookUser.class);
					User u = uc.findById(a.getId());
					String st = u.toString();
					//find doesnt print to postman
					System.out.println(st);
				}
				break;
			case "viewaccount":
				
				ses = req.getSession(false);				
				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
				if(CurrentUser.getRole().getRole().equals("admin")) {

				if(CurrentUser.getRole().getRole().equals("admin")) {
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();

					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					LookUser a = om.readValue(body, LookUser.class);
					User u = uc.findById(a.getId());
					String st = u.toString();
					System.out.println(st);
				}}}}}
				break;
			case "withdraw":
				//if(CurrentUser.getRole().getRole().equals("")) {
				ses = req.getSession(false);

				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();
					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					Withdraw i = om.readValue(body, Withdraw.class);
					//int x = om.readValue(body, int.class);
					float z = i.getAmount();
					Account a = ac.findById(i.getId());
					if (ac.Withdraw(z, a) >= 0) {
						res.setStatus(201);
						res.getWriter().println("The Withdraw was succesfull");// remove for final
					}
				}}}
				break;
			case "deposit":
				ses = req.getSession(false);

				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
					}
			else {
				
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();
					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}

					String body = new String(s);
					Deposit i = om.readValue(body, Deposit.class);
					float z = i.getAmount();

					Account a = ac.findById(i.getId());
					
					if (ac.Deposit(z, a)>=0) {

						res.setStatus(201);
						res.getWriter().println("The deposit was succesful");// remove for final
					}
				}}}
				break;
			case "transfer":
				ses = req.getSession(false);

				if (ses != null && ((boolean) ses.getAttribute("loggedin"))) {
					res.getWriter().println("3");
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
						System.out.println("there");
					}
			else {
				if (req.getMethod().equals("POST")) {
					BufferedReader reader = req.getReader();
					StringBuilder s = new StringBuilder();

					String line = reader.readLine();

					while (line != null) {
						s.append(line);
						line = reader.readLine();
					}
					String body = new String(s);
					Transfer i = om.readValue(body, Transfer.class);
					float z = i.getAmount();

					Account a = ac.findById(i.getId1());
					Account b = ac.findById(i.getId2());
					if (ac.Withdraw(z, a)>=0) {
						res.getWriter().println("6");
						ac.Deposit(z, b);
						res.setStatus(201);
						res.getWriter().println("The transfer was success");// remove for final
					}
				}}}
				break;
			// add alternative user function that allows sign up, set defult for role
			case "login":
				ses = req.getSession(false);

				if (!(ses != null && ((boolean) ses.getAttribute("loggedin")))) {
					res.getWriter().println("3");
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						Account a = ac.findById(id);
						res.setStatus(200);
						res.getWriter().println(om.writeValueAsString(a));
						System.out.println("there");
					}
			else {
				lc.login(req, res);
						
				CurrentUser = lc.getcurrentuser();
				if (CurrentUser != null)
				{
				res.getWriter().println("You have succesfully logged in.");
				res.getWriter().println(CurrentUser);
				}
				else {
					res.getWriter().println("You have failed to logged in.");
				}
			}}
				break;
			case "logout":
				lc.logout(req, res);
			
			break;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			res.getWriter().println("The id you provided is not an integer");
			res.setStatus(400);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
