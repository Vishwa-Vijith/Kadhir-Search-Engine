package com.ViVTech.kadhir.Kadhir_BE.controller;

import com.ViVTech.kadhir.Kadhir_BE.dto.SearchResponseDTO;
import com.ViVTech.kadhir.Kadhir_BE.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ViVTech.kadhir.Kadhir_BE.service.QueryCategorizerService;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService;

	public SearchController(SearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping
	public Mono<ResponseEntity<List<SearchResponseDTO>>> search(@RequestParam String query) throws Exception {
		return searchService.performSearch(query)
				.map(ResponseEntity::ok);
	}

}
