package com.sebas.paraulogic.joc.daos;

import com.sebas.paraulogic.joc.utils.DBAccess;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LetterDAO {
    private static final Logger _log = LoggerFactory.getLogger(LetterDAO.class);

    public List<Character> getLetters() {
        List<Character> letters = new ArrayList<>();
        String sql = "SELECT letter FROM letters";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                letters.add(rs.getString("letter").charAt(0));
            }

        } catch (SQLException e) {
            _log.error("Error obteniendo letras", e);
        }

        return letters;
    }

    public void saveLetters(List<Character> letras) {
        String deleteSql = "DELETE FROM letters";
        String insertSql = "INSERT INTO letters (letter) VALUES (?)";

        try (Connection conn = DBAccess.getConnection();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            deleteStmt.executeUpdate();

            for (Character letra : letras) {
                insertStmt.setString(1, String.valueOf(letra));
                insertStmt.addBatch();
            }

            insertStmt.executeBatch();
            _log.info("Letras guardadas correctamente.");

        } catch (SQLException e) {
            _log.error("Error guardando letras", e);
        }
    }
}