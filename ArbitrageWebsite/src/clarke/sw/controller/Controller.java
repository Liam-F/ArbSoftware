package clarke.sw.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import clarke.sw.account.Account;
import clarke.sw.beans.User;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, String> actionMap;
	private HttpSession session;
	private DataSource dsSite;

	public Controller() {
		super();
		// Build map of action param to page
		actionMap = new HashMap<>();
		actionMap.put("home", "/home.jsp");
		actionMap.put("about", "/about.jsp");
		actionMap.put("login", "/login.jsp");
		actionMap.put("signup", "/signup.jsp");
		actionMap.put("logout", "/home.jsp");
		actionMap.put("hiddenArbPage", "/hiddenArbPage.jsp");
	}

	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initContext = new InitialContext();

			Context env = (Context) initContext.lookup("java:comp/env");

			dsSite = (DataSource) env.lookup("jdbc/ArbitrageTradingSite");
			
		} catch (NamingException e) {
			throw new ServletException();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("validationMessage", "");
		request.setAttribute("creationMessage", "");

		// Get the action parameter
		String action = request.getParameter("action");

		if (action == null || !actionMap.containsKey(action))
			action = "home";
		else if (action.equals("logout")) {
			// If action is logout, clear session
			session.setAttribute("email", "");
			session.setAttribute("password", "");
		}
		// Forward to page
		request.getRequestDispatcher(actionMap.get(action)).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher(actionMap.get("/home.jsp")).forward(request, response);
			return;
		}

		Connection conn = null;

		try {
			conn = dsSite.getConnection();
		} catch (SQLException e) {
			throw new ServletException();
		}

		Account account = new Account(conn);

		if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			try {
				if (account.login(email, password)) {
					session = request.getSession();
					session.setAttribute("email", email);
					session.setAttribute("password", "");
					request.getRequestDispatcher("/hiddenArbPage.jsp").forward(request, response);
				} else {
					request.setAttribute("validationMessage", "email address or password not recognised.");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				request.setAttribute("validationMessage", "Database error: please try again later.");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} else if (action.equals("createaccount")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");

			session = request.getSession();
			session.setAttribute("email", "");
			session.setAttribute("password", "");
			session.setAttribute("confirmPassword", "");
			request.setAttribute("creationMessage", "");

			if (!password.equals(confirmPassword)) {
				// Passwords don't match.
				request.setAttribute("creationMessage", "Passwords do not match.");
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
			} else {
				User user = new User(email, password);

				if (!user.validate()) {
					// Password or email address has wrong format.
					request.setAttribute("creationMessage", user.getMessage());
					request.getRequestDispatcher("/signup.jsp").forward(request, response);
				} else {
					try {
						if (account.exists(email)) {
							// This email address already exists in the user
							// database.
							request.setAttribute("creationMessage",
									"An account with this email address already exists.");
							request.getRequestDispatcher("/signup.jsp").forward(request, response);
						} else {
							// We create create the account.
							account.create(email, password);
							session.setAttribute("email", email);
							request.getRequestDispatcher("/home.jsp").forward(request, response);
						}
					} catch (SQLException e) {
						request.setAttribute("creationMessage", "Database error try again later.");
						request.getRequestDispatcher("/signup.jsp").forward(request, response);
					}
				}

			}

			try {
				conn.close();
			} catch (SQLException e) {
				throw new ServletException();
			}

		}

	}

}
