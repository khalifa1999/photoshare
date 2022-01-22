/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import beans.User;
import exceptions.DbConnectionException;
import exceptions.UserExistException;
import static forms.AddUserForm.FIRSTNAME;
import static forms.AddUserForm.LASTNAME;
import static forms.AddUserForm.LOGIN;
import static forms.AddUserForm.MAIL;
import static forms.AddUserForm.MAIL_INPUT;
import static forms.AddUserForm.PASSWORD;
import static forms.AddUserForm.PASSWORD_CONF;
import static forms.Form.INVALID_INPUT;
import javax.servlet.http.HttpServletRequest;
import repositories.UserRepo;

/**
 *
 * @author bemax
 */
public class EditProfileForm extends EditUserForm{
    
    public EditProfileForm(HttpServletRequest req) {
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
            String phone = getParameter("phoneNumber");
            String password = getParameter("password"); 
            passwordConf = getParameter("passwordConf");
            if(errors.isEmpty()) {
                if(userRepo != null) {
                    try {
                        user = new User(mail,firstName,lastName,phone,password);
                        user.setUserId(id);
                        userRepo.updateProfile(user);	         		        
                        statusMessage = "User Updated Successfully !";
                        req.getSession().setAttribute("currentUser", user);
                        status = true;
                        return true;
                    } catch (UserExistException e) {
                        user = new User(id,mail, firstName, lastName, phone, password);
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
                user = new User(id,mail, firstName, lastName, phone, password);
                statusMessage = "Error While Updating User !";
                return false;
            }
	}
	
	
    
}
