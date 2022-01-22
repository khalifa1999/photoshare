/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repositories.ImageRepo;

/**
 *
 * @author bemax
 */
@WebServlet(name = "Home", urlPatterns = {"","/home"})
public class Home extends HttpServlet {
    private static final String PROFILE_PAGE = "/WEB-INF/index.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("images", ImageRepo.getImageRepoInstance().getPublicImages());    
        } catch (Exception e) {
        }
        getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(req, resp);
        
    }

    
}
