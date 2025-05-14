package com.sebas.paraulogic.joc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "LanguageServlet", urlPatterns = {"/LanguageServlet"})
public class LanguageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String lang = request.getParameter("lang");
        if (lang != null && (lang.equals("es") || lang.equals("en"))) {
            request.getSession().setAttribute("lang", lang);
        }

        String referer = request.getHeader("referer");
        response.sendRedirect(referer != null ? referer : request.getContextPath() + "/home");
    }
}