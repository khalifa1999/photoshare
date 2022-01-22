/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import forms.SignUpForm;
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
@WebServlet(name = "SignUp", urlPatterns = {"/signup"})
public class SignUp extends HttpServlet {
    private static final String SIGNUP_PAGE = "/WEB-INF/signup.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm form = (SignUpForm) req.getSession().getAttribute("signUp");
        if(form != null && form.getStatus()) {
            req.getSession().removeAttribute("signUp");
        }
        getServletContext().getRequestDispatcher(SIGNUP_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpForm signUpForm = new SignUpForm(req);
        req.getSession().setAttribute("signUp", signUpForm);
        if(signUpForm.addUser()) {
            resp.sendRedirect(req.getContextPath() + "/albums");
        }else {
            resp.sendRedirect(req.getContextPath() + "/signup");
        }	
    }
}
