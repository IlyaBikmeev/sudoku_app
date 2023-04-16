package org.example.sudoku.solver;

import org.example.sudoku.Sudoku;
import org.example.sudoku.exception.SolutionNotExistsException;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
    private Sudoku sudoku;
    private Sudoku solution;
    private List<Sudoku> allSolutions;
    private boolean solved;

    private static int ITERATIONS = 0;

    public SudokuSolver(Sudoku sudoku) throws CloneNotSupportedException {
        this.sudoku = sudoku;
        this.solution = (Sudoku) sudoku.clone();
        this.allSolutions = new ArrayList<>();
    }

    public Sudoku getOriginal() {
        return this.sudoku;
    }

    private void solve(int orderNum) throws CloneNotSupportedException {
        ++ITERATIONS;
        int row = orderNum / 9;
        int col = orderNum % 9;
        if(orderNum == 81) {
            System.out.println("ITERATIONS: " + ITERATIONS);
            allSolutions.add((Sudoku) solution.clone());
            return;
        }
        else if(!solution.isCorrect()) {
            return;
        }
        else if(solution.get(row, col) != '*') {
            solve(orderNum + 1);
            return;
        }

        for(char c = '1'; c <= '9'; c++) {
            solution.set(row, col, c);
            solve(orderNum + 1);
            solution.set(row, col, '*');
        }
    }

    private void solve() throws CloneNotSupportedException {
        solve(0);
        this.solved = true;
    }

    public Sudoku getSolution() throws Exception {
        if(!this.solved) {
            solve();
        }
        return allSolutions.stream()
                .findAny()
                .orElseThrow(() -> new SolutionNotExistsException("This sudoku is unsolvable"));
    }

    public int solutionsNumber() throws CloneNotSupportedException {
        if(!this.solved) {
            solve();
        }
        return allSolutions.size();
    }
}
