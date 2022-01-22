/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import beans.Album;
import beans.User;
import exceptions.DbConnectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import repositories.AlbumRepo;

/**
 *
 * @author bemax
 */
public class AlbumForm extends Form{

    protected static final String IMG = "img";
    protected static final String TITLE = "title";
    protected static final String DESC = "desc";
    protected static final String TITLE_INPUT = "titleInput";
    protected static final String DESC_INPUT = "descInput";

    protected Album album;
    protected AlbumRepo albumRepo;

    public AlbumForm(HttpServletRequest req) {
        super(req);
        try {
            this.albumRepo = AlbumRepo.getAlbumRepoInstance();
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean addAlbum(Part part) {
        validateInputs(TITLE,DESC);
        String title = getParameter(TITLE);
        String desc = getParameter(DESC);
        User user = (User) req.getSession().getAttribute("currentUser");
        int userId = user.getUserId();
        int privacy = Integer.parseInt(getParameter("privacy"));
        try {
            InputStream imageBlob = part.getInputStream();
            if(part.getSize()<=0) {
                errors.put(IMG, "Image is required");
            }
            album = new Album(privacy,userId,title,desc,imageBlob);
            if(errors.isEmpty()) {
                if(albumRepo != null) {
                       try {
                           albumRepo.save(album);
                           statusMessage = "Album added Successfully !";	
                           status = true;
                           return true;
                       } catch (DbConnectionException e) {
                            statusMessage = "Database Offline Try Again Later !";
                            System.out.println(e.getMessage());
                            return false;
                       }
                   }else {
                       statusMessage = "Database Offline!";
                       return false;
                }
            }else {
                statusMessage = "Error While Adding Album !";
                return false;
            }
        } catch (Exception e) {
            statusMessage = "Error While Adding Album !";
            return false;
        }
      
    }
    
    protected void validateInputs(String ...inputs) {
        for (String input : inputs) {
            switch (input) {
                case TITLE:
                    if(getParameter(input) == null) {
                            errors.put(TITLE, "Title is required");
                            errors.put(TITLE_INPUT, INVALID_INPUT);
                    }else {
                            valids.put(TITLE_INPUT, VALID_INPUT);
                    }
                    break;
                case DESC:
                    if(getParameter(input) == null) {
                            errors.put(DESC, "Description is required");
                            errors.put(DESC_INPUT, INVALID_INPUT);
                    }else {
                            valids.put(DESC_INPUT, VALID_INPUT);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
}
