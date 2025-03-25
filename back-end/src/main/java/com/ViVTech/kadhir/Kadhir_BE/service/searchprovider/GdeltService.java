package com.ViVTech.kadhir.Kadhir_BE.service.searchprovider;

import com.ViVTech.kadhir.Kadhir_BE.dto.SearchResponseDTO;
import com.ViVTech.kadhir.Kadhir_BE.model.external.GdeltApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GdeltService implements SearchProvider {

    private static final DateTimeFormatter GDELT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'");
    private static final String GDELT_API_URL = "https://api.gdeltproject.org/api/v2/doc/doc";
    private final WebClient webClient;

    @Autowired
    public GdeltService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(GDELT_API_URL).build();
    }

    @Override
    public List<String> getSupportedCategories() {
        return List.of("Tech News");
    }

    @Override
    public Mono<List<SearchResponseDTO>> search(String query) {
        if (query == null || query.trim().length() < 3) {
            return Mono.error(new IllegalArgumentException("Search query must be at least 3 characters long."));
        }

        query = query.replaceAll("\\bAI\\b", "Artificial Intelligence");

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String apiUrl = String.format("?query=%s&mode=artlist&maxrecords=10&format=json&timelimit=5", encodedQuery);

        System.out.println("🔵 Encoded API URL: " + apiUrl);

        return webClient.get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("🟢 GDELT API Response: " + response)) // ✅ Better Debugging
                .map(response -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        return objectMapper.readValue(response, GdeltApiResponse.class);
                    } catch (Exception e) {
                        System.err.println("🔴 Failed to parse GDELT response: " + response);
                        throw new RuntimeException("JSON Parsing Error: " + e.getMessage(), e);
                    }
                })
                .map(this::mapToSearchResponse)
                .defaultIfEmpty(List.of());
    }

    private List<SearchResponseDTO> mapToSearchResponse(GdeltApiResponse response) {
        if (response == null || response.getArticles() == null) {
            return new ArrayList<>();
        }

        return response.getArticles().stream().map(article -> {
            LocalDate publishDate = null;
            LocalTime publishTime = null;

            if (article.getPublishDate() != null) {
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(article.getPublishDate(), GDELT_DATE_FORMAT);

                    ZonedDateTime zonedDateTime = dateTime.atZone(ZoneOffset.UTC)
                            .withZoneSameInstant(ZoneId.systemDefault());

                    publishDate = zonedDateTime.toLocalDate();
                    publishTime = zonedDateTime.toLocalTime();

                } catch (Exception e) {
                    System.err.println("Error parsing date: " + article.getPublishDate() + " - " + e.getMessage());
                }
            }

            return new SearchResponseDTO(
                    article.getTitle(),
                    article.getUrl(),
                    article.getSourceCountry(),
                    article.getLanguage(),
                    publishDate,
                    publishTime,
                    null,
                    "Technology",
                    article.getSocialImage()
            );
        }).collect(Collectors.toList());
    }

}
