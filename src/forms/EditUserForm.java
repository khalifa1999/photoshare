package forms;

import beans.User;
import exceptions.DbConnectionException;
import exceptions.UserExistException;
import javax.servlet.http.HttpServletRequest;
import repositories.UserRepo;

public class EditUserForm extends AddUserForm{
	
	protected String passwordConf;
	protected int id;
	
	public EditUserForm(HttpServletRequest req) {
            super(req);
            try {
                this.userRepo = UserRepo.getUserRepoInstance();
            } catch (Exception e) {
            }
                
	}
	
	public boolean editUser() {
            validateInputs(FIRSTNAME,LASTNAME,MAIL,LOGIN,PASSWORD,PASSWORD_CONF);
            validatePasswords();
            validateMail();
            String firstName = getParameter("firstname");
            String lastName = getParameter("lastname");
            String mail = getParameter("mail");
            int role = Integer.parseInt(getParameter("role"));
            String phone = getParameter("phoneNumber");
            String password = getParameter("password"); 
            passwordConf = getParameter("passwordConf");
            if(errors.isEmpty()) {
                if(userRepo != null) {
                    try {
                        user = new User(mail,firstName,lastName,phone,password,role);
                        user.setUserId(id);
                        userRepo.update(user);	         		        
                        statusMessage = "User Updated Successfully !";
                        status = true;
                        return true;
                    } catch (UserExistException e) {
                        user = new User(id,mail, firstName, lastName, phone, password,role);
                        statusMessage = "User with the same Email Already Exist !";
                        errors.put(MAIL_INPUT, INVALID_INPUT);
                        return false;	        
                    }catch (DbConnectionException e) {
                        statusMessage = "Database Offline Try Again Later !";
                        System.out.println(e.getMessage());
                        return false;
                    } 
                }else {
                    statusMessage = "Database Offline !";
                    return false;
                }
            }
            else {
                user = new User(id,mail, firstName, lastName, phone, password,role);
                statusMessage = "Error While Updating User !";
                return false;
            }
	}
	
	public void setUser(User u) {
		this.user = u;
	}
	
	public String getPasswordConf() {
		return passwordConf;
	}
	
	public void setPasswordConf(String s) {
		passwordConf = s;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}


