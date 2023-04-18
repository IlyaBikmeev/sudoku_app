package org.example;

import org.example.sudoku.Sudoku;
import org.example.sudoku.generator.SudokuGenerator;
import org.example.sudoku.solver.SudokuSolver;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class SudokuGame {
    public static void main(String[] args) throws Exception {
        SudokuGenerator generator = new SudokuGenerator();
        Sudoku sudoku = generator.generate();
        sudoku.print();
        System.out.println();
        Sudoku solution = new SudokuSolver(sudoku).getSolution();
        solution.print();
    }
}
