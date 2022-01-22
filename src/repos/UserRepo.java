package repositories;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import beans.User;
import db.Crypt;
import db.MysqlConnect;
import exceptions.DbConnectionException;
import exceptions.UserExistException;

public class UserRepo {
	
    private static Connection sql = null;
    private static UserRepo instance;
    
    private UserRepo() throws DbConnectionException{
        try {
            sql = MysqlConnect.getConnection();
        } catch (DbConnectionException e) {
            throw e;
        }
    }
    
    public static UserRepo getUserRepoInstance() throws DbConnectionException {
        if(sql != null) {
        	return instance;
        }else {
        	try {
        		instance = new UserRepo();
        		return instance;
        	}catch (DbConnectionException e) {
        		throw e;        	
			}
        }
    }
    
    public void save(User user) throws DbConnectionException,UserExistException {
        User userExist = findByMail(user.getMail());
        if (userExist == null) {
            try {
                PreparedStatement query = sql.prepareStatement("INSERT INTO Users(mail,firstName,lastName,phoneNumber,roleId,password) VALUES(?,?,?,?,2,?)");
                query.setString(1, user.getMail());
                query.setString(2, user.getFirstName());
                query.setString(3, user.getLastName());
                query.setString(4, user.getPhoneNumber());
                query.setString(5, user.getPassword());
                query.executeUpdate();
            } catch (SQLException e) {
                throw new DbConnectionException("An error occured while adding a new user ! : " + e.getMessage());
            }
        } else {
            throw new UserExistException("User already exist !");
        }   
    }
       public void saveWithRole(User user) throws DbConnectionException,UserExistException {
        User userExist = findByMail(user.getMail());
        if (userExist == null) {
            try {
                PreparedStatement query = sql.prepareStatement("INSERT INTO Users(mail,firstName,lastName,phoneNumber,roleId,password) VALUES(?,?,?,?,?,?)");
                query.setString(1, user.getMail());
                query.setString(2, user.getFirstName());
                query.setString(3, user.getLastName());
                query.setString(4, user.getPhoneNumber());
                query.setInt(5, user.getRole());
                query.setString(6, user.getPassword());
                query.executeUpdate();
            } catch (SQLException e) {
                throw new DbConnectionException("An error occured while adding a new user ! : " + e.getMessage());
            }
        } else {
            throw new UserExistException("User already exist !");
        }   
    }
    
    public ArrayList<User> findAll() throws DbConnectionException {
        ArrayList<User> users = new ArrayList<User>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users");
            ResultSet result = query.executeQuery();
            while (result.next()) {
                users.add(new User(result.getInt("userId"),result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"),result.getString("phoneNumber"), result.getString("password"), result.getInt("roleId"), result.getDate("dateCreated"),result.getDate("dateModified")));
            }
            return users;
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while fetching users ! : " + e.getMessage());
        }
    }
    
    


    public User findById(int id) throws DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users WHERE userId = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                User user = new User(result.getInt("userId"),result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"),result.getString("phoneNumber"), result.getString("password"), result.getInt("roleId"));
                return user;
            } else {
                throw new DbConnectionException("User with id " + id + " Not Found !");
            }
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while fetching user with id " + id + " ! : " + e.getMessage());
        }
    }
    public User findByMail(String mail) {
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users WHERE mail = ?");
            query.setString(1, mail);
            ResultSet result = query.executeQuery();
            if (result.next()) {
                User user = new User(result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"),result.getString("phoneNumber"), result.getString("password"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
    
    public void update(User user) throws DbConnectionException,UserExistException{
	     User currentUserState = findById(user.getUserId());
	     User userExist = findByMail(user.getMail());
         if (userExist == null || userExist.getMail().equals(currentUserState.getMail())) {
	        try {
	            PreparedStatement query = sql.prepareStatement(
	                    "UPDATE Users SET firstName=?,lastName=?,mail=?,phoneNumber=?,password=?,roleId=?,dateModified=NOW() WHERE userId=?");
	            query.setString(1, user.getFirstName());
	            query.setString(2, user.getLastName());
	            query.setString(3, user.getMail());
	            query.setString(4, user.getPhoneNumber());
	            query.setString(5, user.getPassword());
	            query.setInt(6, user.getRole());
                    query.setInt(7, user.getUserId());
	            query.executeUpdate();
	        } catch (SQLException e) {
	            throw new DbConnectionException("An error Occured while updating user ! : " + e.getMessage());
	        } 
        } else {
            throw new UserExistException("User already exist !");
        }   
    }
        public void updateProfile(User user) throws DbConnectionException,UserExistException{
	     User currentUserState = findById(user.getUserId());
	     User userExist = findByMail(user.getMail());
         if (userExist == null || userExist.getMail().equals(currentUserState.getMail())) {
	        try {
	            PreparedStatement query = sql.prepareStatement(
	                    "UPDATE Users SET firstName=?,lastName=?,mail=?,phoneNumber=?,password=?,dateModified=NOW() WHERE userId=?");
	            query.setString(1, user.getFirstName());
	            query.setString(2, user.getLastName());
	            query.setString(3, user.getMail());
	            query.setString(4, user.getPhoneNumber());
	            query.setString(5, user.getPassword());
                    query.setInt(6, user.getUserId());
	            query.executeUpdate();
	        } catch (SQLException e) {
	            throw new DbConnectionException("An error Occured while updating user ! : " + e.getMessage());
	        } 
        } else {
            throw new UserExistException("User already exist !");
        }   
    }
    
    public void deleteById(int id) throws DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("DELETE FROM users where userId=?");
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while deleting user ! : " + e.getMessage());
        }
    }
    public User findOne(String login) throws DbConnectionException {
        User user = null;
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Users WHERE mail = ?");
            query.setString(1, login);
            ResultSet result = query.executeQuery();
            if (result.next()) {
            	user = new User(result.getInt("userId"),result.getString("mail"), result.getString("firstName"),
                        result.getString("lastName"),result.getString("phoneNumber"), result.getString("password"), result.getInt("roleId"), result.getDate("dateCreated"),result.getDate("dateModified"));
            }
            return user;
        } catch (SQLException e) {
            throw new DbConnectionException(
                    "An error occured while fetching user with email " + login + " ! : " + e.getMessage());
        }
    }
    public User login(String login,String password) throws DbConnectionException {
        try {
            User user = findOne(login);
            if(user == null) return null;
            if (user.getPassword().equalsIgnoreCase(Crypt.encrypt(password))) {
                return user;
            }
            else
                return null;
        } catch (DbConnectionException e) {
            throw e;
        }
    }
    
	
}
