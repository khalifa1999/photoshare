/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import beans.User;
import exceptions.DbConnectionException;
import forms.AddUserForm;
import forms.EditUserForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import repositories.UserRepo;

/**
 *
 * @author bemax
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin","/admin/add","/admin/edit","/admin/delete"})
public class Admin extends HttpServlet {
    	private static int justLoggedIn = 0;
        	
       private static final String ADMIN_PAGE = "/WEB-INF/admin/admin.jsp";
       private static final String ADD_PAGE = "/WEB-INF/admin/add.jsp";
       private static final String EDIT_PAGE = "/WEB-INF/admin/edit.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(justLoggedIn++ >=1) {
			req.getSession().removeAttribute("logForm");
		}
        boolean status = false;
		Object form = req.getSession().getAttribute("addForm");
		if(form != null) {
			status = true;
			req.getSession().removeAttribute("addForm");
		}
		req.setAttribute("form", status);
		req.setAttribute("activeHome", "active");
        switch (req.getServletPath()) {
            case "/admin":default:
                try {
                    req.setAttribute("users", UserRepo.getUserRepoInstance().findAll());            
                } catch (DbConnectionException e) {
                    req.setAttribute("dbOut", true);
                }   
                getServletContext().getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
            break;
            case "/admin/add":
                getServletContext().getRequestDispatcher(ADD_PAGE).forward(req, resp);
            break;
            case "/admin/edit":
                String id = req.getParameter("id");
		if(id != null && id.matches("[0-9]+")) {
                    try {
                            User u = UserRepo.getUserRepoInstance().findById(Integer.parseInt(id));
                            if(u != null) {
                                System.out.println(u.getFirstName());
                                EditUserForm editUserForm = new EditUserForm(req);
                                u.setDecryptedPassword(u.getPassword());
                                editUserForm.setUser(u);
                                editUserForm.setPasswordConf(u.getPassword());
                                req.getSession().setAttribute("editForm", editUserForm);
                                req.getSession().setAttribute("id",id);
                            }
                            else {
                                System.out.println("Null User Here!");
                            }
                    } catch (Exception e) {}
		} 
                getServletContext().getRequestDispatcher(EDIT_PAGE).forward(req, resp);
            break;
            case "/admin/delete":
                String userid = req.getParameter("id");
		if(userid != null && userid.matches("[0-9]+")) { 
			try {
				UserRepo userRepo = UserRepo.getUserRepoInstance();    
				userRepo.deleteById(Integer.parseInt(userid));
				resp.sendRedirect(req.getContextPath()+"/admin?deleted=true");				
			} catch (DbConnectionException e) {
				resp.sendRedirect(req.getContextPath()+"/admin?deleted=false");					
				
			}
			
		}
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         switch (req.getServletPath()) {
            case "/admin":default:
                try {
                    req.setAttribute("users", UserRepo.getUserRepoInstance().findAll());            
                } catch (DbConnectionException e) {
                    req.setAttribute("dbOut", true);
                }   
                getServletContext().getRequestDispatcher(ADMIN_PAGE).forward(req, resp);
            break;
            case "/admin/add":
                	AddUserForm addUserForm = new AddUserForm(req);
		req.getSession().setAttribute("addForm", addUserForm);
		if(addUserForm.addUser()) {
			resp.sendRedirect(req.getContextPath() + "/admin?added=true");
		}else {
			resp.sendRedirect(req.getContextPath() + "/admin/add");
		}		
            break;
            case "/admin/edit":
                String id = (String)req.getSession().getAttribute("id");
		EditUserForm editUserForm = new EditUserForm(req);
		if(id != null && id.matches("[0-9]+")) {
                    editUserForm.setId(Integer.parseInt(id));
		}
		req.getSession().setAttribute("editForm", editUserForm);
		if(editUserForm.editUser()) {
                    resp.sendRedirect(req.getContextPath() + "/admin?updated=true");
		}else {
                    resp.sendRedirect(req.getContextPath() + "/admin/edit");
		}		
            break;
//            case "/admin/delete":
//                String id = req.getParameter("id");
//		if(id != null && id.matches("[0-9]+")) { 
//			try {
//				UserRepo userRepo = UserRepo.getUserRepoInstance();    
//				userRepo.deleteById(Integer.parseInt(id));
//				resp.sendRedirect(req.getContextPath()+"/admin?deleted=true");				
//			} catch (DbConnectionException e) {
//				resp.sendRedirect(req.getContextPath()+"/admin?deleted=false");					
//				
//			}
//			
//		}
        }

    }
    
    
}
