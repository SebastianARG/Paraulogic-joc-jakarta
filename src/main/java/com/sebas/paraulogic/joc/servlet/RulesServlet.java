package com.sebas.paraulogic.joc.servlet;

import com.sebas.paraulogic.joc.utils.LanguageConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "RulesServlet", urlPatterns = {"/RulesServlet"})
public class RulesServlet extends HttpServlet {

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
        request.setAttribute("rulesTitle", LanguageConfig.get(locale, "rules.title"));
        request.setAttribute("rule1", LanguageConfig.get(locale, "rules.1"));
        request.setAttribute("rule2", LanguageConfig.get(locale, "rules.2"));
        request.setAttribute("rule3", LanguageConfig.get(locale, "rules.3"));
        request.setAttribute("rule4", LanguageConfig.get(locale, "rules.4"));
        request.setAttribute("rule5", LanguageConfig.get(locale, "rules.5"));
        request.setAttribute("back", LanguageConfig.get(locale, "score.back")); // reutilizado

        request.getRequestDispatcher("/WEB-INF/views/rules.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Página de reglas del juego traducible";
    }
}