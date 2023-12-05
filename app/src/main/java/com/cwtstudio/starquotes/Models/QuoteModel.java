package com.cwtstudio.starquotes.Models;

public class QuoteModel {
    private String author,content;

    public QuoteModel(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return "-"+author;
    }



    public String getContent() {
        return content;
    }
}
