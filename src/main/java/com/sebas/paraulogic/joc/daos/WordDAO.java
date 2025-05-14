/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebas.paraulogic.joc.daos;
import com.sebas.paraulogic.joc.model.Word;
import com.sebas.paraulogic.joc.utils.DBAccess;
import java.sql.*;
import java.util.*;

/**
 *
 * @author sebastianr
 */
public class WordDAO {

    public List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();

        try (Connection conn = DBAccess.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT text FROM dictionary")) {

            while (rs.next()) {
                Word word = new Word(rs.getString("text"), true, 0);
                words.add(word);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return words;
    }

    public boolean isValid(String text) {
        String sql = "SELECT COUNT(*) FROM dictionary WHERE text = ?";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, text.toLowerCase());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1) > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
