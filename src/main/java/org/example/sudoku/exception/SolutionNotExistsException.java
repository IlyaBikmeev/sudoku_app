package org.example.sudoku.exception;

public class SolutionNotExistsException extends RuntimeException {

    public SolutionNotExistsException() {
    }

    public SolutionNotExistsException(String message) {
        super(message);
    }
}
