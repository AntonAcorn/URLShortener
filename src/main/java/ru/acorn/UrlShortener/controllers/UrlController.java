package ru.acorn.UrlShortener.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acorn.UrlShortener.dto.UrlDto;
import ru.acorn.UrlShortener.modells.Url;
import ru.acorn.UrlShortener.services.EncoderService;
import ru.acorn.UrlShortener.services.UrlService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

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
       UrlDto urlToCreate =  convertFromUrl(longUrl);
        return urlService.convertShortUrl(urlToCreate);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<HttpStatus> redirect(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Url url = urlService.findByShortUrl(shortUrl);
        System.out.println(shortUrl);
    response.sendRedirect(url.getLongUrl());
    return ResponseEntity.ok(HttpStatus.FOUND);
    }





    private Url convertFromUrlDto(UrlDto urlDto) {
        return modelMapper.map(urlDto, Url.class);
    }
    private UrlDto convertFromUrl(Url url){
        return modelMapper.map(url, UrlDto.class);
    }
}
