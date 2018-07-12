package com.example.justin.punk_apiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapter.CustomAdapter;
import Beer.Beer;
import Beer.remote.ControlBeerList;
import Beer.remote.PunkAPIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteBeerActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        List<Beer> beerList = new ArrayList<Beer>();
        setContentView(R.layout.activity_favourite);
        PunkAPIService service = ControlBeerList.getPunkAPIClient();
        Call<List<Beer>> call = service.getAllBeer();
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {

                for(Beer titou:response.body()){
                    if(titou.getIsFavourite()){
                        beerList.add(titou);
                    }
                }
                try{
                    generateDataList(beerList);
                }catch(NullPointerException e){
                    Toast.makeText(FavouriteBeerActivity.this,"no fav"+e,Toast.LENGTH_LONG);
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {

                Toast.makeText(FavouriteBeerActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<Beer> beerList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        //adapter = new CustomAdapter(this,beerList,);
        adapter = new CustomAdapter(this, beerList, new CustomAdapter.PostItemListener() {
            @Override
            public void onPostClick(int id) {
                Intent beerActivity = new Intent(FavouriteBeerActivity.this, BeerActivity.class);
                beerActivity.putExtra("id",id);
                startActivity(beerActivity);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FavouriteBeerActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
