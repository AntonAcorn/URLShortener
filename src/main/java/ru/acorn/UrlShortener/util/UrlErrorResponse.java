package ru.acorn.UrlShortener.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class UrlErrorResponse {
    private String error;
    private LocalDateTime timestamp;
}
