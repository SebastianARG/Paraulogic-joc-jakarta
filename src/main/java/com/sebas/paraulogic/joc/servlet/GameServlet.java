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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "GameServlet", urlPatterns={"game"})
public class GameServlet extends HttpServlet {
	private final Logger _log = LoggerFactory.getLogger(GameServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		_log.info("inicio del doGet");

		HttpSession session = request.getSession();
		String lang = (String) session.getAttribute("lang");
		if (lang == null) {
			lang = "es"; // Idioma por defecto
			session.setAttribute("lang", lang);
		}
		Locale locale = new Locale(lang);

		// Si se solicita reset (nueva partida)
		if ("true".equals(request.getParameter("reset"))) {
			session.removeAttribute("foundWords");
			session.removeAttribute("score");
			session.removeAttribute("new");
			session.removeAttribute("letras");
		}

// Si es una partida nueva o se reinició
		if (session.getAttribute("new") == null) {
			List<Character> letras = LetterGenerator.generateValidLetters();
			session.setAttribute("letras", letras);
			session.setAttribute("new", "no");
		}

		List<Character> letras = (List<Character>) session.getAttribute("letras");
		if (letras != null && letras.size() >= 7) {
			request.setAttribute("fila1", List.of(letras.get(0)));
			request.setAttribute("fila2", letras.subList(1, 4));
			request.setAttribute("fila3", letras.subList(4, 7));
		}

		// Traducciones
		request.setAttribute("scoreText", LanguageConfig.get(locale, "score"));
		request.setAttribute("foundWordsTitle", LanguageConfig.get(locale, "foundWords"));
		request.setAttribute("foundWordsPlaceholder", LanguageConfig.get(locale, "foundWords.placeholder"));
		request.setAttribute("wordInputPlaceholder", LanguageConfig.get(locale, "wordInput.placeholder"));
		request.setAttribute("wordInputButton", LanguageConfig.get(locale, "wordInput.button"));
		request.setAttribute("startText", LanguageConfig.get(locale, "start"));
		request.setAttribute("displayContext", DisplayContext.class);

		// Estado actual del juego
		List<String> foundWords = (List<String>) session.getAttribute("foundWords");
		if (foundWords == null) foundWords = new ArrayList<>();
		int score = session.getAttribute("score") != null ? (int) session.getAttribute("score") : 0;

		request.setAttribute("foundWords", foundWords);
		request.setAttribute("score", score);

		_log.info("Final del doGet");
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
		session.setAttribute("new", "no");

		// 🔁 Redirige con GET para renderizar todo `game.jsp`
		response.sendRedirect(request.getContextPath() + "/GameServlet");
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

}