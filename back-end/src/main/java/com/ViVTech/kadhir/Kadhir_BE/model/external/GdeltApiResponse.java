package com.ViVTech.kadhir.Kadhir_BE.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class GdeltApiResponse {
    @JsonProperty("articles")
    private List<GdeltArticle> articles;

    public List<GdeltArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<GdeltArticle> articles) {
        this.articles = articles;
    }

}

