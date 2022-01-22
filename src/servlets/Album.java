/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.User;
import exceptions.DbConnectionException;
import forms.AlbumForm;
import forms.EditAlbumForm;
import forms.ImageForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import repositories.AlbumRepo;
import repositories.ImageRepo;

/**
 *
 * @author bemax
 */
@WebServlet(name = "Album", urlPatterns = {"/albums","/album-details","/albums/delete","/album-edit","/albums/delete-image"})
@MultipartConfig
public class Album extends HttpServlet {
    private static final String ALBUMS_PAGE = "/WEB-INF/albums.jsp";
    private static final String ALBUMS_DETAILS_PAGE = "/WEB-INF/album-details.jsp";
    private static final String ALBUMS_EDITING_PAGE = "/WEB-INF/album-editing.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        switch (req.getServletPath()) {
            case "/albums": default:
                AlbumForm albumform = (AlbumForm)req.getSession().getAttribute("albumForm");
                if(albumform != null && albumform.getStatus()) {
                    req.getSession().removeAttribute("albumForm");
                }
                User u = (User)req.getSession().getAttribute("currentUser");
                int id = u.getUserId();
                try {
                    req.setAttribute("albums", AlbumRepo.getAlbumRepoInstance().getAlbumsByUserId(id));
                    req.setAttribute("dbOut", false);
                } catch (DbConnectionException ex) {
                    req.setAttribute("dbOut", true);
                }
                getServletContext().getRequestDispatcher(ALBUMS_PAGE).forward(req, resp);
                break;
            case "/album-details":
                ImageForm form = (ImageForm) req.getSession().getAttribute("imgForm");
		if(form != null && form.getStatus()) {
                    req.getSession().removeAttribute("imgForm");
		}
                String albumid = req.getParameter("id");
		if(albumid != null && albumid.matches("[0-9]+")) { 
                    try {
                        AlbumRepo albumRepo = AlbumRepo.getAlbumRepoInstance();   
                        req.setAttribute("album", albumRepo.findById(Integer.parseInt(albumid)));
                        req.setAttribute("images", albumRepo.getAlbumImages(Integer.parseInt(albumid)));
                        req.getSession().setAttribute("albumId", Integer.parseInt(albumid));
                        getServletContext().getRequestDispatcher(ALBUMS_DETAILS_PAGE).forward(req, resp);
                        req.setAttribute("dbOut", false);
                    } catch (DbConnectionException e) {
                        req.setAttribute("dbOut", true);
                        getServletContext().getRequestDispatcher(ALBUMS_PAGE).forward(req, resp);
                    }	
		}
                break;       
            case "/albums/delete":
                String delalbumid = req.getParameter("id");
		if(delalbumid != null && delalbumid.matches("[0-9]+")) { 
                    try {
                        AlbumRepo.getAlbumRepoInstance().deleteAlbumById(Integer.parseInt(delalbumid));
                        resp.sendRedirect(req.getContextPath()+"/albums?deleted=true");				
                    } catch (DbConnectionException e) {
                        resp.sendRedirect(req.getContextPath()+"/albums?deleted=false");					
                    }
                }
                break;
            case "/albums/delete-image":
                String delimageid = req.getParameter("id");
		if(delimageid != null && delimageid.matches("[0-9]+")) { 
                    try {
                        ImageRepo.getImageRepoInstance().deleteImageById(Integer.parseInt(delimageid));
                        resp.sendRedirect(req.getContextPath()+"/album-details?id=" +req.getSession().getAttribute("albumId"));				
                    } catch (DbConnectionException e) {
                        resp.sendRedirect(req.getContextPath()+"/album-details?id="+req.getSession().getAttribute("albumId"));					
                    }
                }
                break;
            case "/album-edit":        
                String editalbumid = req.getParameter("id");
		if(editalbumid != null && editalbumid.matches("[0-9]+")) {
                    try {
                        beans.Album album = AlbumRepo.getAlbumRepoInstance().findById(Integer.parseInt(editalbumid));
                        AlbumForm editAlbumForm = new AlbumForm(req);
                        editAlbumForm.setAlbum(album);
                        req.getSession().setAttribute("editAlbum", editAlbumForm);
                        req.getSession().setAttribute("albumId",editalbumid);                        
                    } catch (Exception e) {}
		} 
                getServletContext().getRequestDispatcher(ALBUMS_EDITING_PAGE).forward(req, resp);
                break;
                
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/albums": default:
                Part partA = req.getPart("input-file");
                if(partA != null){
                    AlbumForm albumForm = new AlbumForm(req);
                    req.getSession().setAttribute("albumForm", albumForm);
                    if(albumForm.addAlbum(partA)) {
                        resp.sendRedirect(req.getContextPath() + "/albums?added=true");
                    }else {
                        resp.sendRedirect(req.getContextPath() + "/albums");
                    } 
                }
                break;
            case "/album-details":
                int result = 0;
                Part part = req.getPart("input-file");
                if(part != null){
                    ImageForm imgForm = new ImageForm(req);
                    req.getSession().setAttribute("imgForm", imgForm);
                    if(imgForm.addImage(part)) {
                        resp.sendRedirect(req.getContextPath() + "/album-details?id=" + req.getSession().getAttribute("albumId") + "&added=true");  
                    }else {
                        resp.sendRedirect(req.getContextPath() + "/album-details?id=" + req.getSession().getAttribute("albumId"));
                    }
                }
                break;
            case "/album-edit":    
                Part partB = req.getPart("input-file");
                EditAlbumForm editAlbumForm = new EditAlbumForm(req);
                String id = (String)req.getSession().getAttribute("albumId");
                if(id != null && id.matches("[0-9]+")) {
                    editAlbumForm.setId(Integer.parseInt(id));
                }
                req.getSession().setAttribute("editAlbum", editAlbumForm);
                if(editAlbumForm.editAlbum(partB)) {
                    resp.sendRedirect(req.getContextPath() + "/albums?updated=true");
                }else {
                    resp.sendRedirect(req.getContextPath() + "/album-edit");
                }	
                break;
        }
    }
}
