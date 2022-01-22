/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import forms.LoginForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bemax
 */
@WebServlet({"/login","/logout"})
public class Auth extends HttpServlet {
    private static final String LOGIN_VUE = "/WEB-INF/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
        switch (req.getServletPath()) {
            case "/login": 
                getServletContext().getRequestDispatcher(LOGIN_VUE).forward(req, resp);
                break;
            default:
//                    Home.resetJustLoggedIn();
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath());
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case"": case "/login":
                LoginForm loginForm = new LoginForm(req, resp);
                req.getSession().setAttribute("logForm", loginForm);
                if(loginForm.login()) {
                    if(loginForm.getUser().getRole() == 2)
                        resp.sendRedirect(req.getContextPath() + "/albums");
                    else
                        resp.sendRedirect(req.getContextPath() + "/admin");
                }else {
                    resp.sendRedirect(req.getContextPath() + "/login");
                }
                break;
            default:
                break;
        }
    }
}
