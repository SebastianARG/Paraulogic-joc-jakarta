/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebas.paraulogic.joc.daos;


import com.sebas.paraulogic.joc.model.Game;
import com.sebas.paraulogic.joc.model.Score;
import com.sebas.paraulogic.joc.utils.DBAccess;
import java.sql.*;
import java.util.*;

/**
 *
 * @author sebastianr
 */
public class GameDAO {

    public void saveGame(Game game) {
        String sql = "INSERT INTO games(score) VALUES(?)";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, game.getScore().getTotalPoints());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> loadAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT id, score FROM games";

        try (Connection conn = DBAccess.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Game g = new Game();
                g.setId(rs.getInt("id"));
                g.getScore().setTotalPoints(rs.getInt("score"));
                games.add(g);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }


    public List<Character> getLettersForNewGame() {
        List<Character> letras = new ArrayList<>();
        String sql = "SELECT text FROM dictionary WHERE CHAR_LENGTH(text) >= 7";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<String> candidatas = new ArrayList<>();

            while (rs.next()) {
                String palabra = rs.getString("text").toUpperCase();
                Set<Character> letrasUnicas = new HashSet<>();

                for (char c : palabra.toCharArray()) {
                    if (Character.isLetter(c)) {
                        letrasUnicas.add(c);
                    }
                }

                // Solo usamos palabras con exactamente 7 letras diferentes
                if (letrasUnicas.size() == 7) {
                    candidatas.add(palabra);
                }
            }

            if (!candidatas.isEmpty()) {
                Random random = new Random();
                String seleccionada = candidatas.get(random.nextInt(candidatas.size()));

                // Imprimir por consola para depuración
                System.out.println("Palabra base seleccionada: " + seleccionada);

                Set<Character> letrasSet = new LinkedHashSet<>();
                for (char c : seleccionada.toCharArray()) {
                    if (Character.isLetter(c)) {
                        letrasSet.add(c);
                        if (letrasSet.size() == 7) break;
                    }
                }

                letras.addAll(letrasSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return letras;
    }
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String query = "SELECT id, score FROM games";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Score score = new Score();
                score.setTotalPoints(rs.getInt("score"));
                Game game = new Game();
                game.setScore(score);
                game.setId(rs.getInt("id"));
                games.add(game);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}

