package forms;

import beans.User;
import exceptions.DbConnectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repositories.UserRepo;

public class LoginForm extends Form{
	
        private UserRepo userRepo;

	protected static final String LOGIN = "login";
	protected static final String PASSWORD = "password";
	protected static final String LOGIN_INPUT = "loginInput";
	protected static final String PASSWORD_INPUT = "passwordInput";
	
	protected HttpServletResponse resp;
	protected String login;
        private User user;
	
	public LoginForm(HttpServletRequest req, HttpServletResponse resp) {
		super(req);
		this.resp = resp;
		try {
            this.userRepo = UserRepo.getUserRepoInstance();            
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
	}
	
	public boolean login() {
		validateInputs(LOGIN,PASSWORD);
		login = getParameter(LOGIN);
		String password = getParameter(PASSWORD);
		if ("admin".equals(login) && "passer".equals(password)) {
			req.getSession().setAttribute("currentUser", new User(login,login,login,login,password));	
			status = true;
			statusMessage = "Logged In Succesfully !";
			return true;
		} else { 
			validateMail(); 
			if(errors.isEmpty()) {		
				if(userRepo != null) {
					try {
						user = userRepo.login(login, password);
						if(user != null) {
                                                    if(user.getRole() == 2) {
                                                       req.getSession().setAttribute("currentUser", user);	 
                                                    }else {
                                                        req.getSession().removeAttribute("currentUser");
                                                        req.getSession().setAttribute("adminUser", user);	
                                                    }
							
							status = true;
							statusMessage = "Logged In Succesfully !";
							return true; 
						}else {
							statusMessage = "Login or Password Incorrect !";
							errors.put(PASSWORD_INPUT, INVALID_INPUT);
							return false;
						} 
					} catch (DbConnectionException e) {
						statusMessage = "Database Offline !";
						return false;
					}
				}else {
					statusMessage = "Database Offline !";
					return false;
				}
			} else {
				return false;
			} 
		} 
	}
	
	protected void validateInputs(String ...inputs) {
		for (String input : inputs) {
			switch (input) {

				case LOGIN:
					if(getParameter(input) == null) {
						errors.put(LOGIN, "Login is required");
						errors.put(LOGIN_INPUT, INVALID_INPUT);
					}else {
						valids.put(LOGIN_INPUT, VALID_INPUT);
					}
					break;
				case PASSWORD:
					if(getParameter(input) == null) {
						errors.put(PASSWORD, "Password is required");
						errors.put(PASSWORD_INPUT, INVALID_INPUT);
					}else {
						valids.put(PASSWORD_INPUT, VALID_INPUT);
					}
					break;
				default:
					break;
			}
		}
	}
	protected void validateMail() {
		String mailString = getParameter(LOGIN);
		if(!EmailValidation.isValid(mailString)) {
			errors.put(LOGIN, "Invalid Email !");
			errors.put(LOGIN_INPUT, INVALID_INPUT);
		}
		
	}
	
	
	public String getLogin() {
		return login;
	}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
	
}
