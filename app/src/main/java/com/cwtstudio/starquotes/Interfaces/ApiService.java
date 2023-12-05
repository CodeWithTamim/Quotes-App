package com.cwtstudio.starquotes.Interfaces;

import com.cwtstudio.starquotes.Models.QuoteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/quotes/random?limit=10")
    Call<List<QuoteModel>> getQuotes();



}
