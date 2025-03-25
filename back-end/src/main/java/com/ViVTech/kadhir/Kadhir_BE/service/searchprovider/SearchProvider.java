package com.ViVTech.kadhir.Kadhir_BE.service.searchprovider;

import com.ViVTech.kadhir.Kadhir_BE.dto.SearchResponseDTO;
import reactor.core.publisher.Mono;

import java.util.List;

public interface SearchProvider {
    List<String> getSupportedCategories();
    Mono<List<SearchResponseDTO>> search(String query);
}
