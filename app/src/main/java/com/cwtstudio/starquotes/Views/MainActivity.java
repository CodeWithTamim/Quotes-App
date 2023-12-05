package com.cwtstudio.starquotes.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cwtstudio.starquotes.Adapters.RVAdapter;
import com.cwtstudio.starquotes.Interfaces.ApiService;
import com.cwtstudio.starquotes.Models.QuoteModel;
import com.cwtstudio.starquotes.R;
import com.cwtstudio.starquotes.Retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
     Toolbar toolbar;
     RecyclerView recyclerView;
     private RVAdapter adapter;
     SwipeRefreshLayout refreshLayout;
     ProgressBar pgbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        recyclerView= findViewById(R.id.recyclerView);
        refreshLayout = findViewById(R.id.refresh);
        pgbar = findViewById(R.id.pgbar);
        setSupportActionBar(toolbar);
        callApi();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callApi();
            }
        });









    }

    private void callApi() {
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
        Call<List<QuoteModel>> call = apiService.getQuotes();
        call.enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                if (response.isSuccessful()){
                    List<QuoteModel> quoteList = response.body();
                    adapter = new RVAdapter(MainActivity.this,quoteList);
                    recyclerView.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                    pgbar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error,Please restart the app", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "onFailure: "+t.getLocalizedMessage());

            }
        });







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.menuPrivacyPolicy){
            String url = "https://docs.google.com/document/d/1iCfQR1XyZxyXv1B81qEZJ5TasfUAvWblmaRFdJoG7HY/edit?usp=sharing";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            return true;

        }




        return super.onOptionsItemSelected(item);
    }
}