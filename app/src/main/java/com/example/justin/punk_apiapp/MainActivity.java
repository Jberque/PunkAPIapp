package com.example.justin.punk_apiapp;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.acl.AclEntry;
import java.util.ArrayList;
import java.util.List;
import Adapter.CustomAdapter;
import Beer.remote.ControlBeerList;
import Beer.remote.PunkAPIService;
import Beer.Beer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    int pageNumber = 1;
    ProgressDialog progressDoalog;

    //---------------------ONCREATE---------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button btnfav = (findViewById(R.id.buttongotofav));
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();
        final Button btnNext = findViewById(R.id.nextbutton);
        final Button btnBack = findViewById(R.id.backbutton);
        final EditText EditTextRecherche = findViewById(R.id.edittext_recherche);
        final Button rechercheButton = findViewById(R.id.button_recherche);
        /*Create handle for the RetrofitInstance interface*/
        PunkAPIService service = ControlBeerList.getPunkAPIClient();

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(20);
        final TextView seekBarValue = (findViewById(R.id.seekText));

        btnfav.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent favActivity = new Intent(MainActivity.this,FavouriteBeerActivity.class);
                startActivity(favActivity);
            }
        });


        //-------------------------------PAGES-----------------------------------------------------------------
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //ehgfhsdgfjkgdsfhgjsdgfsdgf
                if(pageNumber>1) pageNumber-=1;
                if (EditTextRecherche.getText().toString().equals("")){
                    if(seekBar.getProgress()!=0){

                        seekBarValue.setText("abv Min: "+String.valueOf(seekBar.getProgress())+"%");
                        Call <List<Beer>> call = service.getBeerByAbv_Gt(seekBar.getProgress(), pageNumber);
                        call.enqueue(new Callback<List<Beer>>() {
                            @Override
                            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
                                generateDataList(response.body());
                            }
                            @Override
                            public void onFailure(Call<List<Beer>> call, Throwable t) {
                            }
                        });

//                        rechercheButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                List<Beer> ListBeerRecherche = new ArrayList<Beer>();
//                                if (EditTextRecherche.getText().toString().equals("")){
//
//                                }else{
//                                    if (seekBar.getProgress()==0){
//
//                                        Call<List<Beer>> call = service.getBeerByNameAndAbv_Gt(EditTextRecherche.getText().toString(),seekBar.getProgress(), pageNumber);
//                                        call.enqueue(new Callback<List<Beer>>(){
//                                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
//                                                generateDataList(response.body());
//                                            }
//                                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
//                                            }
//                                        });
//
//                                        pageNumber=1;
//                                        Call<List<Beer>> call = service.getBeerByName(EditTextRecherche.getText().toString(), pageNumber);
//                                        call.enqueue(new Callback<List<Beer>>(){
//                                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
//                                                try{
//                                                    generateDataList(response.body());
//                                                } catch(NullPointerException e){
//                                                    Toast.makeText(MainActivity.this,"Il n'y a rien ici", Toast.LENGTH_LONG).show();
//                                                }
//                                            }
//                                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
//                                            }
//                                        });
//                                    }
//                                    else{
//
//                                    }
//
//                                    EditTextRecherche.setText("");
//                                    generateDataList(ListBeerRecherche);
//                                }}
//                        });

                    }else{
                        Call<List<Beer>> call1 = service.getListBeer(pageNumber);
                        call1.enqueue(new Callback<List<Beer>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<Beer>> call1, @NonNull Response<List<Beer>> response) {
                                generateDataList(response.body());
                                Toast.makeText(MainActivity.this, "pageNumber: "+pageNumber, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(@NonNull Call<List<Beer>> call1, @NonNull Throwable t) {
                                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }else{
                    if(seekBar.getProgress()!=0){
                                List<Beer> ListBeerRecherche = new ArrayList<Beer>();
                                        Call<List<Beer>> call2 = service.getBeerByNameAndAbv_Gt(EditTextRecherche.getText().toString(),seekBar.getProgress(), pageNumber);
                                        call2.enqueue(new Callback<List<Beer>>(){
                                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                                                generateDataList(response.body());
                                            }
                                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                                            }
                                        });
                    }else{
                        List<Beer> ListBeerRecherche = new ArrayList<Beer>();
                        Call<List<Beer>> call3 = service.getBeerByName(EditTextRecherche.getText().toString(), pageNumber);
                        call3.enqueue(new Callback<List<Beer>>(){
                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                                try{
                                    generateDataList(response.body());
                                } catch(NullPointerException e){
                                    Toast.makeText(MainActivity.this,"Il n'y a rien ici", Toast.LENGTH_LONG).show();
                                }
                            }
                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                            }
                        });
                        generateDataList(ListBeerRecherche);
                }
            }
        }});

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pageNumber+=1;
                if (EditTextRecherche.getText().toString().equals("")){
                    if(seekBar.getProgress()!=0){
                        seekBarValue.setText("abv Min: "+String.valueOf(seekBar.getProgress())+"%");
                        Call <List<Beer>> call = service.getBeerByAbv_Gt(seekBar.getProgress(), pageNumber);
                        call.enqueue(new Callback<List<Beer>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                                generateDataList(response.body());
                            }
                            @Override
                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                            }
                        });



                    }else{
                        Call<List<Beer>> call1 = service.getListBeer(pageNumber);
                        call1.enqueue(new Callback<List<Beer>>() {
                            @Override
                            public void onResponse(@NonNull Call<List<Beer>> call1, @NonNull Response<List<Beer>> response) {
                                generateDataList(response.body());
                                Toast.makeText(MainActivity.this, "pageNumber: "+pageNumber, Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onFailure(@NonNull Call<List<Beer>> call1, @NonNull Throwable t) {
                                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }else{
                    if(seekBar.getProgress()!=0){
                        List<Beer> ListBeerRecherche = new ArrayList<Beer>();
                        Call<List<Beer>> call2 = service.getBeerByNameAndAbv_Gt(EditTextRecherche.getText().toString(),seekBar.getProgress(), pageNumber);
                        call2.enqueue(new Callback<List<Beer>>(){
                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                                generateDataList(response.body());
                            }
                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                            }
                        });
                    }else{
                        List<Beer> ListBeerRecherche = new ArrayList<Beer>();
                        Call<List<Beer>> call3 = service.getBeerByName(EditTextRecherche.getText().toString(), pageNumber);
                        call3.enqueue(new Callback<List<Beer>>(){
                            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                                try{
                                    generateDataList(response.body());
                                } catch(NullPointerException e){
                                    Toast.makeText(MainActivity.this,"Il n'y a rien ici", Toast.LENGTH_LONG).show();
                                }
                            }
                            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                            }
                        });
                        generateDataList(ListBeerRecherche);
                    }
                }
//                if(seekBar.getProgress()!=0){
//                    //----------------------SEEKBAR---------------------------------------------------------
//                            seekBarValue.setText("abv Min: "+String.valueOf(seekBar.getProgress())+"%");
//                            Call <List<Beer>> call = service.getBeerByAbv_Gt(seekBar.getProgress(), pageNumber);
//                            call.enqueue(new Callback<List<Beer>>() {
//                                @Override
//                                public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
//                                    generateDataList(response.body());
//                                }
//                                @Override
//                                public void onFailure(Call<List<Beer>> call, Throwable t) {
//                                }
//                            });
//
//                }else{
//                    //------------------------------SEARCHBUTTON----------------------------------------------------------
//                    rechercheButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            List<Beer> ListBeerRecherche = new ArrayList<Beer>();
//                            if (seekBar.getProgress()==0){
//                                pageNumber=1;
//                                Call<List<Beer>> call = service.getBeerByName(EditTextRecherche.getText().toString(), pageNumber);
//                                call.enqueue(new Callback<List<Beer>>(){
//                                    public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
//                                        try{
//                                            generateDataList(response.body());
//                                        } catch(NullPointerException e){
//                                            Toast.makeText(MainActivity.this,"Il n'y a rien ici", Toast.LENGTH_LONG).show();
//                                        }
//                                    }
//                                    public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
//                                    }
//                                });
//                            }
//                            else{
//                                Call<List<Beer>> call = service.getBeerByNameAndAbv_Gt(EditTextRecherche.getText().toString(),seekBar.getProgress(), pageNumber);
//                                call.enqueue(new Callback<List<Beer>>(){
//                                    public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
//                                        generateDataList(response.body());
//                                    }
//                                    public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
//                                    }
//                                });
//                            }
//
//                            EditTextRecherche.setText("");
//                            generateDataList(ListBeerRecherche);
//                        }
//                    });
//                    //Chargement des bieres au oncreate
//                    Call<List<Beer>> call = service.getListBeer(pageNumber);
//                    call.enqueue(new Callback<List<Beer>>() {
//                        @Override
//                        public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
//                            generateDataList(response.body());
////                        Toast.makeText(MainActivity.this, "pageNumber: "+pageNumber, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(MainActivity.this, "response: "+response, Toast.LENGTH_LONG).show();
//                        }
//                        @Override
//                        public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
//                            Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                }



            }
        });

        //----------------------SEEKBAR---------------------------------------------------------
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                pageNumber=1;
                // TODO Auto-generated method stub
                seekBarValue.setText("abv Min: "+String.valueOf(progress)+"%");
                if(EditTextRecherche.getText().toString().equals("")){
                    Call <List<Beer>> calla = service.getBeerByAbv_Gt(progress, pageNumber);
                    calla.enqueue(new Callback<List<Beer>>() {
                        @Override
                        public void onResponse(Call<List<Beer>> calla, Response<List<Beer>> response) {
							try{
								generateDataList(response.body());
							}catch(NullPointerException e){
								Toast.makeText(mainActivity.this,"une érreur est survenue")
							}	

                        }
                        @Override
                        public void onFailure(Call<List<Beer>> calla, Throwable t) {
                        }
                    });
                }else{
                    Call<List<Beer>> callit = service.getBeerByNameAndAbv_Gt(EditTextRecherche.getText().toString(),seekBar.getProgress(), pageNumber);
                    callit.enqueue(new Callback<List<Beer>>(){
                        @Override
                        public void onResponse(@NonNull Call<List<Beer>> callit, @NonNull Response<List<Beer>> response) {
                            try{
                                generateDataList(response.body());
                            }catch(NullPointerException e){
                                Toast.makeText(MainActivity.this,"catch"+response+"  +  "+e,Toast.LENGTH_LONG);

                            }
                        }
                        @Override
                        public void onFailure(@NonNull Call<List<Beer>> callit, @NonNull Throwable t) {
                        }
                    });
                }


            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        //------------------------------SEARCHBUTTON----------------------------------------------------------
        rechercheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Beer> ListBeerRecherche = new ArrayList<Beer>();
                pageNumber=1;
                if (seekBar.getProgress()==0){
                    Call<List<Beer>> call = service.getBeerByName(EditTextRecherche.getText().toString(), pageNumber);
                    call.enqueue(new Callback<List<Beer>>(){
                        public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                            try{
                                generateDataList(response.body());
                            } catch(NullPointerException e){
                                Toast.makeText(MainActivity.this,"Il n'y a rien ici", Toast.LENGTH_LONG).show();
                            }
                        }
                        public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                        }
                    });
                }
                else{
                    Call<List<Beer>> call = service.getBeerByNameAndAbv_Gt(EditTextRecherche.getText().toString(),seekBar.getProgress(), pageNumber);
                    call.enqueue(new Callback<List<Beer>>(){
                        public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                            try{
                                Toast.makeText(MainActivity.this,"response"+response,Toast.LENGTH_LONG);
                                generateDataList(response.body());
                            }catch(NullPointerException e){
                                Toast.makeText(MainActivity.this,"catch"+response+"  +  "+e,Toast.LENGTH_LONG);
                            }
                        }
                        public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                        }
                    });
                }

//                EditTextRecherche.setText("");
 //               generateDataList(ListBeerRecherche);
            }
        });
        //Chargement des bieres au oncreate
        Call<List<Beer>> call = service.getListBeer(pageNumber);
        call.enqueue(new Callback<List<Beer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Beer>> call, @NonNull Response<List<Beer>> response) {
                progressDoalog.dismiss();
                generateDataList(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<List<Beer>> call, @NonNull Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
//---------adapter-----------------------------
    private void generateDataList(List<Beer> beerList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        //adapter = new CustomAdapter(this,beerList,);
        adapter = new CustomAdapter(this, beerList, new CustomAdapter.PostItemListener() {
            @Override
            public void onPostClick(int id) {
                Intent beerActivity = new Intent(MainActivity.this, BeerActivity.class);
                beerActivity.putExtra("id",id);
                startActivity(beerActivity);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}