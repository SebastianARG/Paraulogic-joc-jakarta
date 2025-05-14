package com.sebas.paraulogic.joc.servlet;

import com.sebas.paraulogic.joc.utils.LanguageConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lang = (String) request.getSession().getAttribute("lang");
        Locale locale = lang != null ? new Locale(lang) : request.getLocale();

        String title = LanguageConfig.get(locale, "title");
        request.setAttribute("title", title);
        request.setAttribute("game", LanguageConfig.get(locale, "game"));
        request.setAttribute("hits", LanguageConfig.get(locale, "hits"));
        request.setAttribute("modes", LanguageConfig.get(locale, "modes"));
        request.setAttribute("rules", LanguageConfig.get(locale, "rules"));
        request.setAttribute("score", LanguageConfig.get(locale, "score"));
        request.setAttribute("statistics", LanguageConfig.get(locale, "stats"));
        request.setAttribute("hola", LanguageConfig.get(locale, "hola"));

        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}