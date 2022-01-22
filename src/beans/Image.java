/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import java.io.InputStream;

public class Image {

    private Integer imageId;
    private String title;
    private String description;
    private Integer height;
    private Integer width;
    private Date dateCreated;
    private Date dateModified;
    private InputStream image;
    private String imageOut;
    private List<Type> typesList;
    private int albumId;

    public Image() {
    }

    public Image(String title, String description, InputStream image, int albumId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.albumId = albumId;
    }

    public Image(int imageId,String title, String description, String imageOut, int albumId) {
        this.imageId = imageId;
        this.title = title;
        this.description = description;
        this.imageOut = imageOut;
        this.albumId = albumId;
    }
    

    public Image(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageOut() {
        return imageOut;
    }

    public void setImageOut(String imageOut) {
        this.imageOut = imageOut;
    }
    
    
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
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

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    @XmlTransient
    public List<Type> getTypesList() {
        return typesList;
    }

    public void setTypesList(List<Type> typesList) {
        this.typesList = typesList;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (imageId != null ? imageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.imageId == null && other.imageId != null) || (this.imageId != null && !this.imageId.equals(other.imageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Images[ imageId=" + imageId + " ]";
    }
    
}
