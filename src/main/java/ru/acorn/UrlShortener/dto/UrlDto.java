package ru.acorn.UrlShortener.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UrlDto {
    @Getter @Setter
    private String longUrl;

    @Getter @Setter
    private LocalDateTime expirationDate;

    public UrlDto() {
    }



}
