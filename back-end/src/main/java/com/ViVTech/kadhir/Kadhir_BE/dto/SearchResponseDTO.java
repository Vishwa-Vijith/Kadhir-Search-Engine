package com.ViVTech.kadhir.Kadhir_BE.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class SearchResponseDTO {
    private String title;
    private String url;
    private String source;
    private String snippet;
    private LocalDate publishDate;
    private LocalTime publishTime;
    private String author;
    private String category;
    private String imageUrl;

    public SearchResponseDTO(String title, String url, String source, String snippet, LocalDate publishDate, LocalTime publishTime, String author, String category, String imageUrl) {
        this.title = title;
        this.url = url;
        this.source = source;
        this.snippet = snippet;
        this.publishDate = publishDate;
        this.publishTime = publishTime;
        this.author = author;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public SearchResponseDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public LocalTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
