package ru.acorn.UrlShortener.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter @Setter
@ToString
@NoArgsConstructor
public class UrlDto {

    private String longUrl;

    private LocalDateTime expirationDate;
}
