package ru.acorn.UrlShortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.UrlShortener.dto.UrlDto;
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
    public String convertShortUrl (UrlDto urlDto){
        String convertedUrl = encoderService.encode(Long.parseLong(urlDto.getLongUrl()));
        Url urlToPersist = new Url();

        urlToPersist.setLongUrl(urlDto.getLongUrl());
        urlToPersist.setCreationTime(LocalDateTime.now());
        urlToPersist.setExpirationDate(LocalDateTime.now().plusHours(2));

        urlRepository.save(urlToPersist);

        return convertedUrl;
    }
}
