package com.company;

public class SearchReturnedNoMatchesException extends RuntimeException{
    public SearchReturnedNoMatchesException(String message) {
        super(message);
    }
}

