/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.User;
import forms.EditProfileForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author bemax
 */
@WebServlet(name = "Profile", urlPatterns = {"/profile"})
public class Profile extends HttpServlet {
   
    private static final String PROFILE_PAGE = "/WEB-INF/edit-profile.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       User u = (User) req.getSession().getAttribute("currentUser");
       if(req.getSession().getAttribute("editForm") == null) {
            try {
                if(u != null) {
                    EditProfileForm editProfileForm = new EditProfileForm(req);
                    u.setDecryptedPassword(u.getPassword());
                    editProfileForm.setUser(u);
                    editProfileForm.setPasswordConf(u.getPassword());
                    req.getSession().setAttribute("editForm", editProfileForm);
                    req.getSession().setAttribute("id",u.getUserId());
                }
                else {
                    System.out.println("Null User Here!");
                }
            } catch (Exception e) {}
       }
       getServletContext().getRequestDispatcher(PROFILE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditProfileForm editProfileForm = new EditProfileForm(req);
        int id = (int)req.getSession().getAttribute("id");
        editProfileForm.setId(id);
        req.getSession().setAttribute("editForm", editProfileForm);
        if(editProfileForm.editUser()) {
            req.getSession().removeAttribute("editForm");
            resp.sendRedirect(req.getContextPath() + "/albums");
        }else {
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
}
