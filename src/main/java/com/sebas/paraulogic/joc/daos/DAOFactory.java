/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebas.paraulogic.joc.daos;

/**
 *
 * @author sebastianr
 */
public class DAOFactory {

    private static WordDAO wordDAO;
    private static GameDAO gameDAO;
    private static LetterDAO letterDAO;

    public static WordDAO getWordDAO() {
        if (wordDAO == null) {
            wordDAO = new WordDAO();
        }
        return wordDAO;
    }

    public static GameDAO getGameDAO() {
        if (gameDAO == null) {
            gameDAO = new GameDAO();
        }
        return gameDAO;
    }
    public static LetterDAO getLetterDAO() {
        if (letterDAO == null) {
            letterDAO = new LetterDAO();
        }
        return letterDAO;
    }
    
}
    
