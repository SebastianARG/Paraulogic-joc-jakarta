/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sebas.paraulogic.joc.model;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sebastianr
 */

@Data
@NoArgsConstructor
public class Game {
    private int id;
    private List<Letter> letters;
    private List<Word> foundWords;
    private Score score;
}
