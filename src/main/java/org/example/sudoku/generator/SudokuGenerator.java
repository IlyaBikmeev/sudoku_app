package org.example.sudoku.generator;

import org.example.sudoku.Sudoku;
import org.example.sudoku.solver.SudokuSolver;

import java.util.Random;

public class SudokuGenerator {
    private final char[][] base;
    private final Random random;

    public SudokuGenerator() {
        this.base = new char[9][9];
        this.random = new Random();
        init();
    }

    private void init() {
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                base[i][j] = (char) ((i * 3 + i / 3 + j) % 9 + 1 + '0');
            }
        }
    }

    public Sudoku generate() throws Exception {
        iterations(1000);

        int cnt = 0;

        while(cnt < 50) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            char val = base[row][col];
            base[row][col] = '*';
            if(new SudokuSolver(new Sudoku(base)).solutionsNumber() == 1) {
                ++cnt;
            }
            else {
                base[row][col] = val;
            }
        }

        return new Sudoku(base);
    }

    private void iterations(int count) {
        for(int i = 0; i < count; ++i) {
            int choise = random.nextInt(5);
            if(choise == 0) {
                transpose();
            }
            else if(choise == 1) {
                swapRowsSmall();
            }
            else if(choise == 2) {
                swapColsSmall();
            }
            else if(choise == 3){
                swapRowsArea();
            }
            else {
                swapColsArea();
            }
        }
    }

    public void print() {
        for(int i = 0; i < base.length; i++) {
            for (int j = 0; j < base.length; j++) {
                System.out.print(base[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void transpose() {
        for(int i = 0; i < 9; ++i) {
            for(int j = i; j < 9; ++j) {
                char buf = this.base[i][j];
                this.base[i][j] = this.base[j][i];
                this.base[j][i] = buf;
            }
        }
    }

    private void swapRowsSmall() {
        int firstRow = random.nextInt(3) * 3;
        int secondRow = firstRow + 2;
        char[] buf = this.base[firstRow];
        this.base[firstRow] = this.base[secondRow];
        this.base[secondRow] = buf;
    }

    private void swapColsSmall() {
        int firstCol = random.nextInt(3) * 3;
        int secondCol = firstCol + 2;

        for(int i = 0; i < 9; ++i) {
            char buf = this.base[i][firstCol];
            this.base[i][firstCol] = this.base[i][secondCol];
            this.base[i][secondCol] = buf;
        }
    }

    private void swapRowsArea() {
        int firstArea = 3 * random.nextInt(2);
        int secondArea = firstArea + 3;

        for(int i = 0; i < 3; ++i) {
            char[] buf = base[firstArea + i];
            base[firstArea + i] = base[secondArea + i];
            base[secondArea + i] = buf;
        }
    }

    private void swapColsArea() {
        int firstArea = 3 * random.nextInt(2);
        int secondArea = firstArea + 3;

        for(int j = 0; j < 3; ++j) {
            for(int i = 0; i < 9; ++i) {
                char buf = base[i][firstArea + j];
                base[i][firstArea + j] = base[i][secondArea + j];
                base[i][secondArea + j] = buf;
            }
        }
    }
}
