/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import db.Crypt;
import java.util.Date;

public class User {

    private Integer userId;
    private String mail;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Boolean isBlocked;
    private Date dateCreated;
    private Date dateModified;
    private int role;
//    private List<Album> albumsList;

    public User() {
    }

    public User(String mail, String firstname, String lastname, String phoneNumber, String password) {
        super();
        setFirstName(firstname);
        setLastName(lastname);
        setMail(mail);
        setPhoneNumber(phoneNumber);
        setPassword(password);
    }
       public User(String mail, String firstname, String lastname, String phoneNumber, String password,int role) {
        super();
        setFirstName(firstname);
        setLastName(lastname);
        setMail(mail);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setRole(role);
    }

    public User(Integer userId, String mail, String firstName, String lastName, String phoneNumber, String password,int role) {
        this.userId = userId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }

    public User(Integer userId, String mail, String firstName, String lastName, String phoneNumber, String password) {
        this.userId = userId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
       
       
    public User(Integer userId, String mail, String firstName, String lastName, String phoneNumber, String password, int roleId, Date dateCreated, Date dateModified) {
        this.userId = userId;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = roleId;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }
     
   
    

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

      public void setPassword(String password) {
        this.password = Crypt.encrypt(password);
    }
    public void setDecryptedPassword(String password) {
    	this.password = Crypt.decrypt(password);
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    

    @Override
    public String toString() {
        return "entities.Users[ userId=" + userId + " ]";
    }

    
}
