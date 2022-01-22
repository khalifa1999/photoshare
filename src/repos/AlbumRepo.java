/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import beans.Album;
import beans.Image;
import db.MysqlConnect;
import exceptions.DbConnectionException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author bemax
 */
public class AlbumRepo {
    private static Connection sql = null;
    private static AlbumRepo instance;
    
    private AlbumRepo() throws DbConnectionException{
        try {
            sql = MysqlConnect.getConnection();
        } catch (DbConnectionException e) {
            throw e;
        }
    }
    
    public static AlbumRepo getAlbumRepoInstance() throws DbConnectionException {
        if(sql != null) {
            return instance;
        }else {
            try {
                instance = new AlbumRepo();
                return instance;
            }catch (DbConnectionException e) {
                throw e;        	
            }
        }
    }
    
    public void save(Album album) throws DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("INSERT INTO Albums(title,description,userId,isPrivate,albumCover) VALUES(?,?,?,?,?)");
            query.setString(1, album.getTitle());
            query.setString(2, album.getDesc());
            query.setInt(3, album.getUserId());
            query.setInt(4, album.getIsPrivate());
            query.setBlob(5, album.getImage());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while adding a new Album ! : " + e.getMessage());
        } 
    }
    
    public void edit(Album album) throws  DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("UPDATE Albums SET title=?,description=?,isPrivate=?,albumCover=? WHERE albumId=?");
            query.setString(1, album.getTitle());
            query.setString(2, album.getDesc());
            query.setInt(3, album.getIsPrivate());
            query.setBlob(4, album.getImage());
            query.setInt(5, album.getAlbumId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while Updating Album ! : " + e.getMessage());
        }
    }
        public void editOmitImage(Album album) throws  DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("UPDATE Albums SET title=?,description=?,isPrivate=? WHERE albumId=?");
            query.setString(1, album.getTitle());
            query.setString(2, album.getDesc());
            query.setInt(3, album.getIsPrivate());
            query.setInt(4, album.getAlbumId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while Updating Album ! : " + e.getMessage());
        }
    }
    
    public ArrayList<Album> getAlbumsByUserId(int id) throws DbConnectionException  {
        ArrayList<Album> albums = new ArrayList<Album>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Albums WHERE userId = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            while (result.next()) {
                albums.add(new Album(result.getInt("albumId"),result.getInt("isPrivate"),result.getInt("userId"),result.getString("title"),result.getString("description"),getImage(result.getBlob("albumCover").getBinaryStream())));
            }
            return albums;
        } catch (Exception e) {
            throw new DbConnectionException("An error occured while Fetching Albums ! : " + e.getMessage());
        }
    }
    
    public Album findById(int id)  throws DbConnectionException{
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Albums WHERE albumId = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();
            if (result.next()) {
               Album album = new Album(result.getInt("albumId"),result.getInt("isPrivate"),result.getInt("userId"),result.getString("title"),result.getString("description"),getImage(result.getBlob("albumCover").getBinaryStream()));
                return album;
            } else {
                throw new DbConnectionException("Album with id " + id + " Not Found !");
            }
        } catch (Exception e) {
            throw new DbConnectionException("An error occured while fetching Album with id " + id + " ! : " + e.getMessage());
        }
    }
    
    public ArrayList<Image> getAlbumImages(int albumId) throws DbConnectionException{
        ArrayList<Image> images = new ArrayList<Image>();
        try {
            PreparedStatement query = sql.prepareStatement("SELECT * FROM Images WHERE albumId = ?");
            query.setInt(1, albumId);
            ResultSet result = query.executeQuery();
            while(result.next()) {
                images.add(new Image(result.getInt("imageId"),result.getString("titre"),result.getString("description"),getImage(result.getBlob("image").getBinaryStream()),result.getInt("albumId")));
            }
            return images;
        }catch(Exception e) {
            throw new DbConnectionException("An error occured while fetching Images with album id " + albumId + " ! : " + e.getMessage());
        }
    }
    
    public void deleteAlbumById(int id) throws DbConnectionException {
        try {
            PreparedStatement query = sql.prepareStatement("DELETE FROM albums where albumId=?");
            query.setInt(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DbConnectionException("An error occured while deleting Album ! : " + e.getMessage());
        }
    }
    
    public static String getImage(InputStream inputStream) throws IOException {
       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
       byte[] buffer = new byte[4096];
       int bytesRead = -1;

       while ((bytesRead = inputStream.read(buffer)) != -1) {
           outputStream.write(buffer, 0, bytesRead);
       }

       byte[] imageBytes = outputStream.toByteArray();
       String base64Image = Base64.getEncoder().encodeToString(imageBytes);

       inputStream.close();
       outputStream.close();
       return base64Image;
    }
}
