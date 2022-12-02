package ru.acorn.UrlShortener.services;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class EncoderService {
    public String encodeUrl(String url)
    {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }
                    //*************10base65***************
//    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//    private char[] allowedCharacters = allowedString.toCharArray();
//    private int base = allowedCharacters.length;
//
//        public String encode(long number){
//        StringBuilder encodedString = new StringBuilder();
//        while (number > 0){
//            encodedString.append(allowedCharacters[(int)number % base]);
//            number = number / base;
//
//        }
//        return encodedString.reverse().toString();
//    }
//
//
//    public long decode(String input) {
//        long decodedResult = 0;
//        int counter = 1;
//        char[] characters = input.toCharArray();
//        for (int i = 0; i < characters.length; i++) {
//            decodedResult += allowedString.indexOf(i) * Math.pow(base, input.length() - counter);
//            counter++;
//        }
//        return decodedResult;
//    }

}
