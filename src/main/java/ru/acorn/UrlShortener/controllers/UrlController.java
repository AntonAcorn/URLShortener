package ru.acorn.UrlShortener.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.acorn.UrlShortener.dto.UrlDto;
import ru.acorn.UrlShortener.modells.Url;
import ru.acorn.UrlShortener.services.EncoderService;
import ru.acorn.UrlShortener.services.UrlService;

@RestController
@RequestMapping
public class UrlController {
    private final EncoderService encoderService;
    private final UrlService urlService;
    private final ModelMapper modelMapper;

    public UrlController(EncoderService encoderService, UrlService urlService, ModelMapper modelMapper) {
        this.encoderService = encoderService;
        this.urlService = urlService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public String createShortUrl(@RequestBody Url longUrl) {
        urlService.convertShortUrl(longUrl);
        System.out.println(longUrl.getLongUrl());
        return longUrl.getLongUrl();

    }

    private Url convertFromUrlDto(UrlDto urlDto) {
        return modelMapper.map(urlDto, Url.class);
    }
}
