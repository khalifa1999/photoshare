/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import beans.Album;
import exceptions.DbConnectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;

/**
 *
 * @author bemax
 */
public class EditAlbumForm extends AlbumForm{

    private int id;
	
    public EditAlbumForm(HttpServletRequest req) {
        super(req);
    }
    
    public boolean editAlbum(Part part) {
        validateInputs(TITLE,DESC);
        String title = getParameter(TITLE);
        String desc = getParameter(DESC);
        int privacy = Integer.parseInt(getParameter("privacy"));
        try {
            InputStream imageBlob = part.getInputStream();

            album = new Album(privacy,title,desc,imageBlob);
            album.setAlbumId(id);
            album.setImageOut(albumRepo.findById(id).getImageOut());
            if(errors.isEmpty()) {
                if(albumRepo != null) {
                    try {
                        if(part.getSize()<=0) {
                            albumRepo.editOmitImage(album);
                        }else {
                            albumRepo.edit(album);  
                        }
                        statusMessage = "Album Updated Successfully !";	
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
                statusMessage = "Error While Updating Album !";
                return false;
            }
        } catch (Exception e) {
            statusMessage = "Error While Updating Album !";
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
