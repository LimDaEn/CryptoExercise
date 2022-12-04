package com.example.demo.exception;

public class TranscationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public TranscationException() {
        super("An Error Has Occured");
    }

    public TranscationException(String data) {
        super(data);
    }
}
