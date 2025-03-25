package com.ViVTech.kadhir.Kadhir_BE.service;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.apache.commons.math3.linear.*;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.index.*;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.document.*;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.*;

@Service
public class QueryCategorizerService {
	private final Map<String, List<String>> categoryKeywords;
	private final Map<String, String> categoryTexts;

	public QueryCategorizerService() {
		categoryKeywords = new HashMap<>();
		categoryTexts = new HashMap<>();

		categoryKeywords.put("Tech News", Arrays.asList(
				"latest tech", "technology news", "AI trends", "new blockchain project", "tech updates",
				"machine learning news", "cybersecurity trends", "cloud computing updates", "big data trends",
				"software development news", "programming trends", "new programming languages", "Web3 innovations"
		));

		categoryKeywords.put("Research Papers", Arrays.asList(
				"research paper", "arxiv", "semantic scholar", "scientific study", "IEEE paper", "ML research",
				"AI paper", "deep learning research", "academic study", "published research", "technical paper",
				"AI whitepaper", "scientific journal", "conference paper"
		));

		categoryKeywords.put("Open Source & GitHub", Arrays.asList(
				"github repository", "open source project", "codebase", "developer tool", "open-source contribution",
				"open-source library", "GitHub trending repos", "new open-source framework", "best GitHub projects",
				"opensource AI", "top GitHub repositories", "GitHub stars", "forked repository"
		));

		categoryKeywords.put("Hacker News", Arrays.asList(
				"startup funding", "early-stage tech", "YCombinator", "new AI product", "seed funding",
				"tech startup news", "emerging startups", "angel investment", "venture capital news",
				"AI startup funding", "disruptive tech", "Hacker News trending", "new AI startup", "YC startup"
		));

		categoryKeywords.put("Product Hunt", Arrays.asList(
				"new product", "AI tool", "startup launch", "SaaS AI", "AI-powered software", "new SaaS product",
				"trending AI app", "new tech product", "automation tool", "productivity AI", "best AI tools",
				"latest AI software", "AI-powered service", "top AI products"
		));

		// Longer Descriptions for TF-IDF Matching
		categoryTexts.put("Tech News", "Latest trends in artificial intelligence, blockchain, software engineering, DevOps, cloud computing, cybersecurity, big data, and Web3.");
		categoryTexts.put("Research Papers", "Academic publications from arXiv, Semantic Scholar, IEEE, machine learning research, deep learning, NLP, robotics, and AI papers.");
		categoryTexts.put("Open Source & GitHub", "GitHub repositories, open-source contributions, trending developer tools, frameworks, libraries, and codebases.");
		categoryTexts.put("Hacker News", "Early-stage tech startups, innovative ideas, new AI projects, venture capital funding news, seed investment, and angel investors.");
		categoryTexts.put("Product Hunt", "New AI tools, trending tech products, SaaS, AI-driven startups, AI-powered applications, automation tools, and developer-focused platforms.");

	}

	public List<String> categorize(String query) throws Exception {
		query = query.toLowerCase().trim();
		Set<String> matchedCategories = new HashSet<>();

		for (Map.Entry<String, List<String>> entry : categoryKeywords.entrySet()) {
			for (String keyword : entry.getValue()) {
				String keywordLower = keyword.toLowerCase();
				if (query.contains(keywordLower)) {
					matchedCategories.add(entry.getKey());
					System.out.println("Keyword Match: " + keyword + " → " + entry.getKey());
				}
			}
		}

		LevenshteinDistance levenshtein = new LevenshteinDistance();
		int maxDistance = Math.max(2, query.length() / 5);
		for (Map.Entry<String, List<String>> entry : categoryKeywords.entrySet()) {
			for (String keyword : entry.getValue()) {
				int distance = levenshtein.apply(query, keyword);
				if (distance <= maxDistance) {
					matchedCategories.add(entry.getKey());
					System.out.println("Fuzzy Match: " + keyword + " → " + entry.getKey());
				}
			}
		}

		Directory ramDirectory = new ByteBuffersDirectory();
		Analyzer analyzer = new SimpleAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(ramDirectory, config);

		for (Map.Entry<String, String> entry : categoryTexts.entrySet()) {
			Document doc = new Document();
			doc.add(new TextField("content", entry.getValue(), Field.Store.YES));
			writer.addDocument(doc);
		}

		writer.close();
		IndexReader reader = DirectoryReader.open(ramDirectory);
		Map<String, Float> queryVector = computeTFIDFVector(query, reader);

		double threshold = 0.2;
		for (Map.Entry<String, String> entry : categoryTexts.entrySet()) {
			Map<String, Float> categoryVector = computeTFIDFVector(entry.getValue(), reader);
			double similarity = cosineSimilarity(queryVector, categoryVector);
			System.out.println("Cosine Similarity: " + entry.getKey() + " → " + similarity);
			if (similarity > threshold) {
				matchedCategories.add(entry.getKey());
			}
		}

		reader.close();
		return new ArrayList<>(matchedCategories);
	}

	private Map<String, Float> computeTFIDFVector(String text, IndexReader reader) throws Exception {
		Map<String, Integer> termFreq = new HashMap<>();
		TokenStream tokenStream = new SimpleAnalyzer().tokenStream("content", new StringReader(text));
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();

		while (tokenStream.incrementToken()) {
			String term = charTermAttribute.toString();
			termFreq.put(term, termFreq.getOrDefault(term, 0) + 1);
		}
		tokenStream.close();

		Map<String, Float> tfidfVector = new HashMap<>();
		for (Map.Entry<String, Integer> entry : termFreq.entrySet()) {
			String term = entry.getKey();
			int termFreqValue = entry.getValue();
			int docFreq = reader.docFreq(new Term("content", term));
			if (docFreq == 0) continue;
			float idf = (float) Math.log(reader.numDocs() / (double) docFreq);
			tfidfVector.put(term, termFreqValue * idf);
		}

		return tfidfVector;
	}

	private double cosineSimilarity(Map<String, Float> vec1, Map<String, Float> vec2) {
		Set<String> globalTerms = new HashSet<>(vec1.keySet());
		globalTerms.addAll(vec2.keySet());

		RealVector vector1 = toRealVector(vec1, globalTerms);
		RealVector vector2 = toRealVector(vec2, globalTerms);

		if (vector1.getNorm() == 0 || vector2.getNorm() == 0) {
			return 0;
		}

		return (vector1.dotProduct(vector2)) / (vector1.getNorm() * vector2.getNorm());
	}


	private RealVector toRealVector(Map<String, Float> map, Set<String> globalTerms) {
		double[] values = new double[globalTerms.size()];
		int index = 0;

		for (String term : globalTerms) {
			values[index++] = map.getOrDefault(term, 0.0f);
		}

		return new ArrayRealVector(values, false);
	}

}
