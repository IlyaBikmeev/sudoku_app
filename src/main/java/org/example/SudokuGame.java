package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class SudokuGame {
    public static void main(String[] args) {
        try(Reader reader = new FileReader("sudoku.txt")) {
            Sudoku sudoku = new Sudoku(reader);
            System.out.println(sudoku.isCorrect());
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
