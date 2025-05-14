package com.sebas.paraulogic.joc.servlet;

import com.sebas.paraulogic.joc.daos.DAOFactory;
import com.sebas.paraulogic.joc.model.Game;
import com.sebas.paraulogic.joc.utils.LanguageConfig;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "StatsServlet", urlPatterns = {"/StatsServlet"})
public class StatsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Locale locale = (String) request.getSession().getAttribute("lang") != null
                ? new Locale((String) request.getSession().getAttribute("lang"))
                : request.getLocale();

        // Obtener datos de estadísticas desde GameDAO
        List<Game> scores = DAOFactory.getGameDAO().getAllGames();
        int total = scores.stream().mapToInt(g -> g.getScore().getTotalPoints()).sum();
        int count = scores.size();
        double average = count > 0 ? (double) total / count : 0;

        // Setear en el request
        request.setAttribute("scoreList", scores);
        request.setAttribute("totalGames", count);
        request.setAttribute("averageScore", average);
        request.setAttribute("statsTitle", LanguageConfig.get(locale, "stats.title"));
        request.setAttribute("back", LanguageConfig.get(locale, "back"));
        request.setAttribute("totalGamesLabel", LanguageConfig.get(locale, "stats.total"));
        request.setAttribute("averageScoreLabel", LanguageConfig.get(locale, "stats.average"));
        request.setAttribute("scoreListTitle", LanguageConfig.get(locale, "stats.scoreListTitle"));
        request.setAttribute("pointsSuffix", LanguageConfig.get(locale, "points.suffix"));
        request.getRequestDispatcher("/WEB-INF/views/stats.jsp").forward(request, response);
    }
}