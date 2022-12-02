package ru.acorn.UrlShortener.util;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException(String msg) {
        super(msg);
    }
}
