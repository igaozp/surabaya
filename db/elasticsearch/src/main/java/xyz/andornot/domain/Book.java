package xyz.andornot.domain;

import java.time.LocalDate;

public record Book(String title, String author, LocalDate releaseDate, Double amazonRating, Boolean bestSeller,
                   Price prices) {
}

record Price(Double usd, Double gbp, Double eur) {
}