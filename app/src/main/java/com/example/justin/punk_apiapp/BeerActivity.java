package com.example.justin.punk_apiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import Adapter.BeerAdapter;
import Adapter.CustomAdapter;
import Beer.Beer;
import Beer.remote.ControlBeerList;
import Beer.remote.PunkAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerActivity extends AppCompatActivity {
    private BeerAdapter adapter;
    private Beer beer;
    FloatingActionButton btnFavorite;
    private RecyclerView recyclerView;




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        btnFavorite = findViewById(R.id.btn_favorite);
        int id = getIntent().getExtras().getInt("id");
        //Toast.makeText(BeerActivity.this, "Post id iss: " + id, Toast.LENGTH_SHORT).show();
        PunkAPIService service = ControlBeerList.getPunkAPIClient();

        Call<List<Beer>> call = service.getBeer(id);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response1) {
//                Toast.makeText(BeerActivity.this, "In the OnResponse" + response1.body().get(0).getId(), Toast.LENGTH_LONG).show();
                List<Beer> Titou = response1.body();
//                for (Beer beer:Titou) {
//                    Toast.makeText(BeerActivity.this,"id: "+beer.getId(),Toast.LENGTH_SHORT).show();
//                }
////                adapter = new BeerAdapter(response1.body(),BeerActivity.this);
                beer=response1.body().get(0);
                gestionFav();
                generateDataList(beer);
            }
            @Override
            public void onFailure(Call<List<Beer>> call, Throwable t) {
                Toast.makeText(BeerActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });

    }
        private void gestionFav(){

        btnFavorite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(beer.getIsFavourite()){
                    beer.setFavourite(false);
                    Toast.makeText(BeerActivity.this,beer.getName()+" has been removed from your favorite beers",Toast.LENGTH_LONG).show();
                }
                else{
                    beer.setFavourite(true);
                    Toast.makeText(BeerActivity.this,beer.getName()+" has been added to your favorite beers",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


        private void generateDataList(Beer beer) {

            if (beer.getImageUrl() == null) {
                Picasso.with(this).load(beer.getImageUrl());
            } else {
                Picasso.with(this).load(beer.getImageUrl())
                        .into((ImageView) findViewById(R.id.beer_cover));
            }
            ((TextView) findViewById(R.id.name)).setText(beer.getName());
            ((TextView) findViewById(R.id.tagline)).setText(beer.getTagline());
            ((TextView) findViewById(R.id.first_brewed)).setText(beer.getFirstBrewed());
            ((TextView) findViewById(R.id.abv)).setText(String.valueOf(beer.getAbv()));
            ((TextView) findViewById(R.id.ibu)).setText(String.valueOf(beer.getAbv()));
            ((TextView) findViewById(R.id.ebc)).setText(String.valueOf(beer.getEbc()));
            ((TextView) findViewById(R.id.description)).setText(beer.getDescription());
        }

}
