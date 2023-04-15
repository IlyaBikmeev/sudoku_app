package org.example;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Sudoku {
    private final char[][] board;
    private final boolean[][] possible;


    public Sudoku() {
        this.board = new char[9][9];
        this.possible = new boolean[9][9];
    }

    public Sudoku(Reader reader) throws IOException {
        this.board = new char[9][9];
        this.possible = new boolean[9][9];
        Scanner scanner = new Scanner(reader);
        for(int i = 0; i < 9; ++i) {
            String line = scanner.nextLine();
            for(int j = 0; j < 9; ++j) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == '*') {
                    possible[i][j] = true;
                }
            }
        }
    }

    private boolean outOfBounds(int row, int column) {
        return row < 0 || column < 0 || row >= 9 || column >= 9;
    }

    private void check(int row, int column) {
        if(outOfBounds(row, column)) {
            throw new IndexOutOfBoundsException("Row and column must be in range[0, 9]");
        }
    }

    private int countFreeCells() {
        int cnt = 0;
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                if(board[i][j] == '*') {
                    ++cnt;
                }
            }
        }
        return cnt;
    }

    public void clear() {
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                if(possible[i][j]) {
                    board[i][j] = '*';
                }
            }
        }
    }

    public char get(int row, int column) {
        check(row, column);
        return board[row][column];
    }

    public boolean isCorrect() {
        Set<Character> set = new HashSet<>();
        //Проверяем строки
        for(int row = 0; row < 9; ++row) {
            for(int col = 0; col < 9; ++col) {
                char curValue = board[row][col];
                if(curValue != '*' && set.contains(curValue)) {
                    return false;
                }
                if(curValue != '*') {
                    set.add(curValue);
                }
            }
            set.clear();
        }

        //Проверяем столбцы
        for(int col = 0; col < 9; ++col) {
            for(int row = 0; row < 9; ++row) {
                char curValue = board[row][col];
                if(curValue != '*' && set.contains(curValue)) {
                    return false;
                }
                if(curValue != '*') {
                    set.add(curValue);
                }
            }
            set.clear();
        }

        //Проверяем квадраты 3х3
        for(int row = 0; row < 9; row += 3) {
            for(int col = 0; col < 9; col += 3) {
                for(int dRow = 0; dRow < 3; ++dRow) {
                    for(int dCol = 0; dCol < 3; ++dCol) {
                        char curValue = board[row + dRow][col + dCol];
                        if(curValue != '*' && set.contains(curValue)) {
                            return false;
                        }
                        if(curValue != '*') {
                            set.add(curValue);
                        }
                    }
                }
                set.clear();
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return isCorrect() && countFreeCells() == 0;
    }

    public void set(int row, int column, char value) {
        check(row, column);
        if(possible[row][column]) {
            board[row][column] = value;
        }
    }

    public void print() {
        for(int i = 0; i < 9; ++i) {
            for(int j = 0; j < 9; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
