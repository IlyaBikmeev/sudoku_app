package org.example;

import org.example.sudoku.Sudoku;
import org.example.sudoku.generator.SudokuGenerator;
import org.example.sudoku.solver.SudokuSolver;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class SudokuGame {
    public static void main(String[] args) throws Exception {
//        try(Reader reader = new FileReader("sudoku.txt")) {
//            Sudoku sudoku = new Sudoku(reader);
//            SudokuSolver solver = new SudokuSolver(sudoku);
//            Sudoku solution = solver.getSolution();
//            solution.print();
//
//        } catch(IOException | CloneNotSupportedException ex) {
//            ex.printStackTrace();
//        }
        new SudokuGenerator();
    }
}
