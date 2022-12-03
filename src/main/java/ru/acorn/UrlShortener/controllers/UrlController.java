package ru.acorn.UrlShortener.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.acorn.UrlShortener.dto.UrlDto;
import ru.acorn.UrlShortener.dto.UrlShortLinkWrapper;
import ru.acorn.UrlShortener.modells.Url;
import ru.acorn.UrlShortener.services.EncoderService;
import ru.acorn.UrlShortener.services.UrlService;
import ru.acorn.UrlShortener.util.*;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping
public class UrlController {
    private final EncoderService encoderService;
    private final UrlService urlService;
    private final ModelMapper modelMapper;
    private final UrlValidator urlValidator;

    public UrlController(EncoderService encoderService, UrlService urlService,
                         ModelMapper modelMapper, UrlValidator urlValidator) {
        this.encoderService = encoderService;
        this.urlService = urlService;
        this.modelMapper = modelMapper;
        this.urlValidator = urlValidator;
    }

    @PostMapping("/create")
    @Cacheable (value = "urls")
    public ResponseEntity<UrlShortLinkWrapper> createShortUrl(@RequestBody @Valid Url longUrl, BindingResult bindingResult) {
        urlValidator.validate(longUrl, bindingResult);
        if(bindingResult.hasErrors()){
            ErrorsUtil.returnErrorMessage(bindingResult);
        }

        UrlDto urlToCreate = convertFromUrl(longUrl);
        String shortUrl = urlService.convertShortUrl(urlToCreate);
        UrlShortLinkWrapper urlShortLinkWrapper = new UrlShortLinkWrapper(shortUrl);
        return new ResponseEntity<>(urlShortLinkWrapper, HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<?> redirect(@PathVariable @Valid String shortUrl) {
        Url url = urlService.findByShortUrl(shortUrl);
        if(url.getExpirationDate().isBefore(LocalDateTime.now())){
            UrlErrorResponse urlErrorResponse = new UrlErrorResponse(
                    "Link is expired, try to refresh it", LocalDateTime.now());
            urlService.deleteUrl(url);
            return new ResponseEntity <>(urlErrorResponse, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url.getLongUrl())).build();
    }

    @ExceptionHandler
    private ResponseEntity <UrlErrorResponse> handleException (UrlNotFoundException e){
        UrlErrorResponse urlErrorResponse = new UrlErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(urlErrorResponse, HttpStatus.BAD_REQUEST);
    }


    private Url convertFromUrlDto(UrlDto urlDto) {
        return modelMapper.map(urlDto, Url.class);
    }

    private UrlDto convertFromUrl(Url url) {
        return modelMapper.map(url, UrlDto.class);
    }
}
