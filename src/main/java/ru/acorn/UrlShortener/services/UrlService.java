package ru.acorn.UrlShortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.UrlShortener.modells.Url;
import ru.acorn.UrlShortener.repositories.UrlRepository;

import java.time.LocalDateTime;


@Service
@Transactional (readOnly = true)
public class UrlService{
    private final UrlRepository urlRepository;
    private final EncoderService encoderService;

    public UrlService(UrlRepository urlRepository, EncoderService encoderService) {
        this.urlRepository = urlRepository;
        this.encoderService = encoderService;
    }

    @Transactional
    public String convertShortUrl (Url url){
        url.setLongUrl(url.getLongUrl());
        url.setCreationTime(LocalDateTime.now());
        url.setExpirationDate(LocalDateTime.now().plusHours(2));

        Url urlToConvert = urlRepository.save(url);

        return encoderService.encodeUrl(String.valueOf(urlToConvert));
    }
}
