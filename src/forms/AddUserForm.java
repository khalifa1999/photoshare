package forms;


import beans.User;
import exceptions.DbConnectionException;
import exceptions.UserExistException;
import javax.servlet.http.HttpServletRequest;
import repositories.UserRepo;

public class AddUserForm extends Form{
	
	protected static final String FIRSTNAME = "firstname";
	protected static final String LASTNAME = "lastname";
	protected static final String MAIL = "mail";
	protected static final String LOGIN = "phoneNumber";
	protected static final String PASSWORD = "password";
	protected static final String PASSWORD_CONF = "passwordConf";
	protected static final String FIRSTNAME_INPUT = "firstnameInput";
	protected static final String LASTNAME_INPUT = "lastnameInput";
	protected static final String MAIL_INPUT = "mailInput";
	protected static final String LOGIN_INPUT = "loginInput";
	protected static final String PASSWORD_INPUT = "passwordInput";
	protected static final String PASSWORD_CONF_INPUT = "passwordConfInput";

	protected User user;
        protected UserRepo userRepo;
	
	public AddUserForm(HttpServletRequest req) {
            super(req);
            try {
                this.userRepo = UserRepo.getUserRepoInstance();            
            } catch (DbConnectionException e) {
                System.out.println(e.getMessage());
            }
	}
	
	public boolean addUser() {
            validateInputs(FIRSTNAME,LASTNAME,MAIL,LOGIN,PASSWORD,PASSWORD_CONF);
            validatePasswords();
            validateMail();
            String firstName = getParameter("firstname");
            String lastName = getParameter("lastname");
            String mail = getParameter("mail");
            int role = Integer.parseInt(getParameter("role"));
            String phone = getParameter("phoneNumber");
            String password = getParameter("password"); 

            user = new User(mail,firstName,lastName,phone,password,role);
            if(errors.isEmpty()) {
                    if(userRepo != null) {
                        try {
                            userRepo.saveWithRole(user);
                            statusMessage = "User added Successfully !";
                            status = true;
                            return true;
                        }catch (UserExistException e) {
                                        statusMessage = "User with the same Email Already Exist !";
                                        errors.put(MAIL, "Mail Already Exist !");
                                        errors.put(MAIL_INPUT, INVALID_INPUT);
                                        return false;
                        } catch (DbConnectionException e) {
                                statusMessage = "Database Offline Try Again Later !";
                                System.out.println(e.getMessage());
                                return false;
                        }
                    }else {
                            statusMessage = "Database Offline!";
                            return false;
                    }
            }
            else {
                statusMessage = "Error While Adding User !";
                return false;
            }
	}
	
	protected void validateInputs(String ...inputs) {
		for (String input : inputs) {
			switch (input) {
				case FIRSTNAME:
					if(getParameter(input) == null) {
						errors.put(FIRSTNAME, "Firstname is required");
						errors.put(FIRSTNAME_INPUT, INVALID_INPUT);
					}else {
						valids.put(FIRSTNAME_INPUT, VALID_INPUT);
					}
					break;
				case LASTNAME:
					if(getParameter(input) == null) {
						errors.put(LASTNAME, "Lastname is required");
						errors.put(LASTNAME_INPUT, INVALID_INPUT);
					}else {
						valids.put(LASTNAME_INPUT, VALID_INPUT);
					}
					break;
				case MAIL:
					if(getParameter(input) == null) {
						errors.put(MAIL, "Mail is required");
						errors.put(MAIL_INPUT, INVALID_INPUT);
					}else {
						valids.put(MAIL_INPUT, VALID_INPUT);
					}
					break;
				case LOGIN:
					if(getParameter(input) == null) {
						errors.put(LOGIN, "Phone Number is required");
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
				case PASSWORD_CONF:
					if(getParameter(input) == null) {
						errors.put(PASSWORD_CONF, "Password Confirmation is required");
						errors.put(PASSWORD_CONF_INPUT, INVALID_INPUT);
					}else {
						valids.put(PASSWORD_CONF_INPUT, VALID_INPUT);
					}
					break;
				default:
					break;
			}
		}
	}
	
	protected void validatePasswords() {
		String password = getParameter(PASSWORD);
		String passwordConf = getParameter(PASSWORD_CONF);
		
		if(password == null || passwordConf == null || !password.equals(passwordConf)) {
			errors.put(PASSWORD_CONF, "Passwords Doesn't Match !");
			errors.put(PASSWORD, "Passwords Doesn't Match !");
			errors.put(PASSWORD_INPUT, INVALID_INPUT);
			errors.put(PASSWORD_CONF_INPUT, INVALID_INPUT);
		}
	}
	
	protected void validateMail() {
		String mailString = getParameter(MAIL);
		if(!EmailValidation.isValid(mailString)) {
			errors.put(MAIL, "Invalid Email !");
			errors.put(MAIL_INPUT, INVALID_INPUT);
		}
		
	}
	
	public User getUser() {
		return user;
	}
	
}
