package ru.acorn.UrlShortener.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.acorn.UrlShortener.dto.UrlDto;
import ru.acorn.UrlShortener.modells.Url;
import ru.acorn.UrlShortener.repositories.UrlRepository;
import ru.acorn.UrlShortener.util.UrlNotFoundException;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;


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
        String shortUrl = encoderService.encodeUrl(urlDto.getLongUrl());
        Url urlToPersist = new Url();

        urlToPersist.setLongUrl(urlDto.getLongUrl());
        urlToPersist.setCreationTime(LocalDateTime.now());
        urlToPersist.setExpirationDate(LocalDateTime.now().plusHours(2));
        urlToPersist.setShortUrl(shortUrl);

        urlRepository.save(urlToPersist);

        return shortUrl;
    }

    public Url findByShortUrl(String shortUrl){
        return urlRepository.findByShortUrl(shortUrl).orElseThrow(()-> new UrlNotFoundException("ShortUrl is not found"));
    }

    public Optional <Url> findByLongUrl (String longUrl){
       return urlRepository.findByLongUrl(longUrl);
    }
}
