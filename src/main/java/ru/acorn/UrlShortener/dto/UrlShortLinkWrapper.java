package ru.acorn.UrlShortener.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlShortLinkWrapper {
    private String shortLink;
}
