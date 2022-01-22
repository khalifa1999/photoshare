/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import java.io.InputStream;

public class Album implements Serializable {

    private Integer albumId;
    private int isPrivate;
    private List<Image> imagesList;
    private int userId;
    private String title;
    private String desc;
    private InputStream image;
    private String imageOut;

    public Album() {
    }

    public Album(int isPrivate, String title, String desc, InputStream image) {
        this.isPrivate = isPrivate;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }
    
    public Album(int isPrivate, int userId, String title, String desc, InputStream image) {
        this.isPrivate = isPrivate;
        this.userId = userId;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public Album(Integer albumId, int isPrivate, int userId, String title, String desc,String imageOut) {
        this.albumId = albumId;
        this.isPrivate = isPrivate;
        this.userId = userId;
        this.title = title;
        this.desc = desc;
        this.imageOut = imageOut;
    }

    public Album(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public int getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(int isPrivate) {
        this.isPrivate = isPrivate;
    }

    @XmlTransient
    public List<Image> getImagesList() {
        return imagesList;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getImageOut() {
        return imageOut;
    }

    public void setImageOut(String imageOut) {
        this.imageOut = imageOut;
    }
    
    public void setImagesList(List<Image> imagesList) {
        this.imagesList = imagesList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (albumId != null ? albumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.albumId == null && other.albumId != null) || (this.albumId != null && !this.albumId.equals(other.albumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Albums[ albumId=" + albumId + " ]";
    }
    
}
