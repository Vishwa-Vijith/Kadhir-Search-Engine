package com.ViVTech.kadhir.Kadhir_BE;

//
import com.ViVTech.kadhir.Kadhir_BE.dto.SearchResponseDTO;
import com.ViVTech.kadhir.Kadhir_BE.service.SearchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import reactor.core.publisher.Mono;
//
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ViVTech.kadhir.Kadhir_BE")
public class KadhirBeApplication {

	public static void main(String[] args) throws Exception {
		//SpringApplication.run(KadhirBeApplication.class, args);
		ApplicationContext context = SpringApplication.run(KadhirBeApplication.class, args);

		// Get the SearchService bean
//		SearchService searchService = context.getBean(SearchService.class);
//
//		// Define a test query
//		String query = "Latest artificial intelligence advancements";
//
//		// Call search() and subscribe to get results
//		Mono<List<SearchResponseDTO>> searchResults = searchService.performSearch(query);
//
//		// Block and get the results synchronously (Only for testing)
//		List<SearchResponseDTO> results = searchResults.block();
//
//		// Print the results
//		if (results != null) {
//			results.forEach(result -> System.out.println(result.getTitle() + " - " + result.getUrl()));
//		} else {
//			System.out.println("No results found.");
//		}

//		String dateStr = "20250306T044500Z";
//		OffsetDateTime dateTime = parseGdeltDate(dateStr);
//		System.out.println("Parsed DateTime: " + dateTime);

	}

//	public static OffsetDateTime parseGdeltDate(String dateStr) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmssX");
//		return OffsetDateTime.parse(dateStr, formatter);
//	}

}
