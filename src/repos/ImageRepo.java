/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import beans.Image;
import db.MysqlConnect;
import exceptions.DbConnectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bemax
 */
public class ImageRepo {
        private static Connection sql = null;
    private static ImageRepo instance;
    
    private ImageRepo() throws DbConnectionException{
        try {
            sql = MysqlConnect.getConnection();
        } catch (DbConnectionException e) {
            throw e;
        }
    }
    
    public static ImageRepo getImageRepoInstance() throws DbConnectionException {
        if(sql != null) {
        	return instance;
        }else {
            try {
                instance = new ImageRepo();
                return instance;
            }catch (DbConnectionException e) {
                throw e;        	
            }
        }
    }
    
    public void save(Image image) throws DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("INSERT INTO Images(titre,description,albumId,image) VALUES(?,?,?,?)");
            query.setString(1, image.getTitle());
            query.setString(2, image.getDesc());
            query.setInt(3, image.getAlbumId());
            query.setBlob(4, image.getImage());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while adding a new Album ! : " + e.getMessage());
        } 
    }
    
    public ArrayList<Image> getPublicImages() throws DbConnectionException{
        ArrayList<Image> images = new ArrayList<Image>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT i.imageId,i.titre,i.description,i.albumId,i.image FROM Images i join Albums j on i.albumId=j.albumId where j.isPrivate=2");
            ResultSet result = query.executeQuery();
            while(result.next()) {
                images.add(new Image(result.getInt("imageId"),result.getString("titre"),result.getString("description"),AlbumRepo.getImage(result.getBlob("image").getBinaryStream()),result.getInt("albumId")));
            }
            return images;
        }catch(Exception e) {
            throw new DbConnectionException("An error occured while fetching Images :" + e.getMessage());
        }
    }
    
    public ArrayList<Image> getImagesByUserId(int userId) throws DbConnectionException {
        ArrayList<Image> images = new ArrayList<Image>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT i.imageId,i.titre,i.description,i.albumId,i.image FROM Images i join Albums j on i.albumId=j.albumId where j.userId=?");
            query.setInt(1, userId);
            ResultSet result = query.executeQuery();
            while(result.next()) {
                images.add(new Image(result.getInt("imageId"),result.getString("titre"),result.getString("description"),AlbumRepo.getImage(result.getBlob("image").getBinaryStream()),result.getInt("albumId")));
            }
            return images;
        } catch (Exception e) {
            throw new DbConnectionException("An error occured while fetching Images :" + e.getMessage());
        }
    }
    
     public void deleteImageById(int id) throws DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("DELETE FROM Images where imageId=?");
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while deleting Image ! : " + e.getMessage());
        }
    }
    
}
