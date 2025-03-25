package com.ViVTech.kadhir.Kadhir_BE.service;

import com.ViVTech.kadhir.Kadhir_BE.dto.SearchResponseDTO;
import com.ViVTech.kadhir.Kadhir_BE.service.searchprovider.SearchProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchService {

    private final QueryCategorizerService queryCategorizer;
    private final List<SearchProvider> searchProviders;

    @Autowired
    public SearchService(QueryCategorizerService queryCategorizer, List<SearchProvider> searchProviders) {
        this.queryCategorizer = queryCategorizer;
        this.searchProviders = searchProviders;
    }

    public Mono<List<SearchResponseDTO>> performSearch(String query) throws Exception {
        List<String> categories = queryCategorizer.categorize(query);

        List<Mono<List<SearchResponseDTO>>> apiCalls = searchProviders.stream()
                .filter(provider -> provider.getSupportedCategories().stream().anyMatch(categories::contains))
                .map(provider -> provider.search(query))
                .toList();

        return Mono.zip(apiCalls, results ->
                Stream.of(results)
                        .map(result -> (List<SearchResponseDTO>) result)
                        .flatMap(List::stream)
                        .collect(Collectors.toList())
        );

    }
}


