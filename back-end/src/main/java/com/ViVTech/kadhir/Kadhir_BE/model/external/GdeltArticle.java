package com.ViVTech.kadhir.Kadhir_BE.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GdeltArticle {

    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("socialimage")  // Ensure matches API response
    private String socialImage;

    @JsonProperty("sourcecountry")
    private String sourceCountry;

    @JsonProperty("language")
    private String language;

    @JsonProperty("seendate")  // Ensure matches API response
    private String publishDate;

    @JsonProperty("seentime")  // Ensure matches API response
    private String publishTime;

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

    public String getSocialImage() {
        return socialImage;
    }

    public void setSocialImage(String socialImage) {
        this.socialImage = socialImage;
    }

    public String getSourceCountry() {
        return sourceCountry;
    }

    public void setSourceCountry(String sourceCountry) {
        this.sourceCountry = sourceCountry;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }
}
