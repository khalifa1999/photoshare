/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;
import repositories.UserRepo;
import beans.User;
import exceptions.DbConnectionException;
import exceptions.UserExistException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bemax
 */
public class SignUpForm extends Form{
    
        
        private UserRepo userRepo;

        protected static final String FIRSTNAME = "firstname";
	protected static final String LASTNAME = "lastname";
	protected static final String MAIL = "mail";
	protected static final String PHONE = "phoneNumber";
	protected static final String PASSWORD = "password";
	protected static final String PASSWORD_CONF = "confPass";
	protected static final String FIRSTNAME_INPUT = "firstnameInput";
	protected static final String LASTNAME_INPUT = "lastnameInput";
	protected static final String MAIL_INPUT = "mailInput";
	protected static final String PHONE_INPUT = "phoneInput";
	protected static final String PASSWORD_INPUT = "passwordInput";
	protected static final String PASSWORD_CONF_INPUT = "passwordConfInput";


	protected User user;
	
	public SignUpForm(HttpServletRequest req) {
            super(req);
            try {
                this.userRepo = UserRepo.getUserRepoInstance();            
            } catch (DbConnectionException e) {
                System.out.println(e.getMessage());
            }
	}
	
	public boolean addUser() {
            validateInputs(FIRSTNAME,LASTNAME,MAIL,PHONE,PASSWORD,PASSWORD_CONF);
            validatePasswords();
            validateMail();
            String firstName = getParameter("firstname");
            String lastName = getParameter("lastname");
            String mail = getParameter("mail");
            String phone = getParameter("phoneNumber");
            String password = getParameter("password"); 

            user = new User(mail,firstName,lastName,phone,password);
            if(errors.isEmpty()) {
                if(userRepo != null) {
                    try {
                        userRepo.save(user);
                        status = true;
                        statusMessage = "User added Successfully !";		            
                        return true;
                    }catch (UserExistException e) {
                        statusMessage = "User with the same Email Already Exist !";
                        errors.put(MAIL, "Mail Already Exist !");
                        errors.put(MAIL_INPUT, INVALID_INPUT);
                        return false;
                    }catch(DbConnectionException e) {
                        statusMessage = "Database Offline Try Again Later !";
                                    System.out.println(e.getMessage());
                                    return false;
                    }
                }
                else {
                    statusMessage = "Database Offline!";
                    return false;
                }
            }else {
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
				case PHONE:
					if(getParameter(input) == null) {
						errors.put(PHONE, "Phone Number is required");
						errors.put(PHONE_INPUT, INVALID_INPUT);
					}else {
						valids.put(PHONE_INPUT, VALID_INPUT);
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

