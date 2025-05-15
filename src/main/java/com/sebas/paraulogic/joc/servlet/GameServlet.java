package com.sebas.paraulogic.joc.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.sebas.paraulogic.joc.daos.DAOFactory;
import com.sebas.paraulogic.joc.utils.DisplayContext;
import com.sebas.paraulogic.joc.utils.LanguageConfig;
import com.sebas.paraulogic.joc.utils.LetterGenerator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "GameServlet", urlPatterns = {"/game"})
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
        request.setAttribute("displayContext", DisplayContext.class);

        // Letras organizadas en 3 filas para el frontend
        request.setAttribute("fila1", List.of(letras.get(0)));
        request.setAttribute("fila2", letras.subList(1, 4));
        request.setAttribute("fila3", letras.subList(4, 7));

        List<String> foundWords = (List<String>) request.getSession().getAttribute("foundWords");
        if (foundWords == null) foundWords = new ArrayList<>();

        int score = request.getSession().getAttribute("score") != null
                ? (int) request.getSession().getAttribute("score") : 0;

        request.setAttribute("foundWords", foundWords);
        request.setAttribute("score", score);

        request.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String palabra = request.getParameter("palabra").toLowerCase();

        List<String> acertadas = (List<String>) session.getAttribute("foundWords");
        if (acertadas == null) acertadas = new ArrayList<>();

        int puntuacion = session.getAttribute("score") != null
                ? (int) session.getAttribute("score") : 0;

        if (DAOFactory.getWordDAO().exists(palabra)) {
            if (!acertadas.contains(palabra)) {
                acertadas.add(palabra);
                puntuacion += calcularPuntos(palabra);
            }
        }

        session.setAttribute("foundWords", acertadas);
        session.setAttribute("score", puntuacion);

        response.sendRedirect("game");
    }

    private int calcularPuntos(String palabra) {
        int len = palabra.length();
        switch (len) {
            case 3: return 1;
            case 4: return 2;
            case 5: return 3;
            case 6: return 4;
            case 7: return 5;
            default: return 0;
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet que prepara y muestra el juego";
    }
}