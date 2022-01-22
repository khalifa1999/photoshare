/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import repositories.ImageRepo;

/**
 *
 * @author bemax
 */
@WebServlet(name = "Gallery", urlPatterns = {"/gallery"})
public class Gallery extends HttpServlet {

    private static final String GALLERY_PAGE = "/WEB-INF/gallery.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User u = (User)req.getSession().getAttribute("currentUser");
            int id = u.getUserId();
            req.setAttribute("images", ImageRepo.getImageRepoInstance().getImagesByUserId(id));    
        } catch (Exception e) {
        }
        getServletContext().getRequestDispatcher(GALLERY_PAGE).forward(req, resp);
    }
}
