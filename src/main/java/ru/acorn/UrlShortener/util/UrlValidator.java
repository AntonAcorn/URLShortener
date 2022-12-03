package ru.acorn.UrlShortener.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.acorn.UrlShortener.modells.Url;
import ru.acorn.UrlShortener.services.UrlService;

import java.time.LocalDateTime;

@Component
public class UrlValidator implements Validator {
    private final UrlService urlService;

    public UrlValidator(UrlService urlService) {
        this.urlService = urlService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Url.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Url url = (Url) target;
        if (urlService.findByLongUrl(url.getLongUrl()).isPresent()){
            errors.rejectValue("longUrl", "400", "Url is already exists");
        }
        if(url.getExpirationDate() == null) return;
        if (url.getExpirationDate().isBefore(LocalDateTime.now())){
            errors.rejectValue("expirationDate", "", "Short Url is expired, try to refresh it");
        }
    }
}
