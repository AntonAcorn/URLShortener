package ru.acorn.UrlShortener.modells;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table (name = "url")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "long_url")
    private String longUrl;

    @Column (name = "created_date")
    private LocalDateTime creationTime;

    @Column (name = "expires_date")
    private LocalDateTime expirationDate;

    @Column(name = "short_url")
    private String shortUrl;
}
