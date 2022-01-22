/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import beans.Image;
import exceptions.DbConnectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.InputStream;
import repositories.ImageRepo;

/**
 *
 * @author bemax
 */
public class ImageForm extends Form{
    
    protected static final String IMG = "img";
    protected static final String TITLE = "title";
    protected static final String DESC = "desc";
    protected static final String TITLE_INPUT = "titleInput";
    protected static final String DESC_INPUT = "descInput";
    
    protected Image image;
    protected ImageRepo imageRepo;

    
    public ImageForm(HttpServletRequest req) {
        super(req);
                try {
            this.imageRepo = ImageRepo.getImageRepoInstance();
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean addImage(Part part) {
        validateInputs(TITLE,DESC);
        String title = getParameter(TITLE);
        String desc = getParameter(DESC);
        int albumId = (int) req.getSession().getAttribute("albumId");
        try {
            InputStream imageBlob = part.getInputStream();
            if(part.getSize()<=0) {
                errors.put(IMG, "Image is required");
            }
            image = new Image(title,desc,imageBlob,albumId);
               if(errors.isEmpty()) {
                if(imageRepo != null) {
                    try {
                        imageRepo.save(image);
                        statusMessage = "Image added Successfully !";	
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
                statusMessage = "Error While Adding Image !";
                return false;
            }
        } catch (Exception e) {
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
      
    
}
