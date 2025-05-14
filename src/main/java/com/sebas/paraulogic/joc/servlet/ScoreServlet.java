package com.sebas.paraulogic.joc.servlet;

import com.sebas.paraulogic.joc.utils.LanguageConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "ScoreServlet", urlPatterns = {"/ScoreServlet"})
public class ScoreServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	Locale locale = (Locale) request.getSession().getAttribute("locale");
    	if (locale == null) {
    	    String langCode = (String) request.getSession().getAttribute("lang");
    	    if (langCode != null) {
    	        locale = new Locale(langCode);
    	    } else {
    	        locale = request.getLocale(); // fallback
    	    }
    	}

        // Traducciones
        request.setAttribute("scoreTitle", LanguageConfig.get(locale, "score.title"));
        request.setAttribute("lvl3", LanguageConfig.get(locale, "score.3"));
        request.setAttribute("lvl4", LanguageConfig.get(locale, "score.4"));
        request.setAttribute("lvl5", LanguageConfig.get(locale, "score.5"));
        request.setAttribute("lvl6", LanguageConfig.get(locale, "score.6"));
        request.setAttribute("lvl7", LanguageConfig.get(locale, "score.7"));
        request.setAttribute("bonus", LanguageConfig.get(locale, "score.bonus"));
        request.setAttribute("back", LanguageConfig.get(locale, "score.back"));

        request.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Sistema de puntuación traducible";
    }
}