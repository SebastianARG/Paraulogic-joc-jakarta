package com.sebas.paraulogic.joc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.sebas.paraulogic.joc.daos.DAOFactory;
import com.sebas.paraulogic.joc.utils.LanguageConfig;
import com.sebas.paraulogic.joc.utils.LetterGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String lang = (String) request.getSession().getAttribute("lang");
        Locale locale = lang != null ? new Locale(lang) : request.getLocale();

        // Generar letras válidas y guardarlas en la base de datos
        List<Character> letras = LetterGenerator.generateValidLetters();
        DAOFactory.getLetterDAO().saveLetters(letras);

        // Texto traducido
        request.setAttribute("scoreText", LanguageConfig.get(locale, "score"));
        request.setAttribute("foundWordsTitle", LanguageConfig.get(locale, "foundWords"));
        request.setAttribute("foundWordsPlaceholder", LanguageConfig.get(locale, "foundWords.placeholder"));
        request.setAttribute("wordInputPlaceholder", LanguageConfig.get(locale, "wordInput.placeholder"));
        request.setAttribute("wordInputButton", LanguageConfig.get(locale, "wordInput.button"));
        request.setAttribute("startText", LanguageConfig.get(locale, "start"));

        // Letras organizadas en 3 filas para el frontend
        request.setAttribute("fila1", List.of(letras.get(0)));
        request.setAttribute("fila2", letras.subList(1, 4));
        request.setAttribute("fila3", letras.subList(4, 7));

        // Datos falsos de momento
        request.setAttribute("foundWords", List.of("amor", "roma", "rama"));
        request.setAttribute("score", 23);

        request.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet que prepara y muestra el juego";
    }
}