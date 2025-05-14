package com.sebas.paraulogic.joc.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

public class LetterGenerator {

    public static List<Character> generateValidLetters() {
        List<String> validWords = getWordsFromDB();
        Set<Character> allLetters = validWords.stream()
                .flatMapToInt(String::chars)
                .mapToObj(c -> (char) c)
                .collect(Collectors.toSet());

        List<List<Character>> combinations = new ArrayList<>();

        List<Character> letterList = new ArrayList<>(allLetters);
        Random rand = new Random();

        for (int i = 0; i < 5000; i++) {
            Collections.shuffle(letterList);
            List<Character> sub = new ArrayList<>(letterList.subList(0, 7));
            if (isValidCombination(sub, validWords)) {
                return sub;
            }
        }
        return List.of('A', 'B', 'C', 'D', 'E', 'F', 'G'); // fallback
    }

    private static boolean isValidCombination(List<Character> letters, List<String> dictionary) {
        Set<Character> set = new HashSet<>(letters);
        char center = letters.get(0);

        int count = 0;
        for (String word : dictionary) {
            if (word.length() >= 3 && word.indexOf(center) != -1) {
                boolean valid = true;
                for (char c : word.toCharArray()) {
                    if (!set.contains(c)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) count++;
                if (count >= 2) return true;
            }
        }
        return false;
    }

    private static List<String> getWordsFromDB() {
        List<String> words = new ArrayList<>();
        try (Connection conn = DBAccess.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT text FROM dictionary");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                words.add(rs.getString("text").toLowerCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }
}
