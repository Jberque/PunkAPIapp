//package com.example.justin.punk_apiapp;
//
//import android.app.ProgressDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.DividerItemDecoration;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.widget.Toast;
//
//import java.util.List;
//
//import Adapter.BeerAdapter;
//import Adapter.CustomAdapter;
//import Beer.remote.ControlBeerList;
//import Beer.remote.PunkAPIService;
//import Beer.Beer;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainActivity extends AppCompatActivity {
//    private CustomAdapter mAdapter;
//    private RecyclerView mRecyclerView;
//    private PunkAPIService mService;
////    ProgressDialog progressDoalog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
////        progressDoalog = new ProgressDialog(MainActivity.this);
////        progressDoalog.setMessage("Loading....");
////        progressDoalog.show();
//
//
//        mService = ControlBeerList.getPunkAPIClient();
//
//        Call<List<Beer>> call = mService.getListBeer();
//        call.enqueue(new Callback<List<Beer>>() {
//            @Override
//            public void onResponse(Call<List<Beer>> call, Response<List<Beer>> response) {
////                progressDoalog.dismiss();
//                generateDataList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<Beer>> call, Throwable t) {
////                progressDoalog.dismiss();
////                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
//    private void generateDataList (List < Beer > beerList) {
//        mRecyclerView = findViewById(R.id.customRecyclerView);
//        mAdapter = new CustomAdapter(this, beerList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//        mRecyclerView.setLayoutManager(layoutManager);
//        mRecyclerView.setAdapter(mAdapter);
//    }}
//
////
////        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewLayout);
////        mAdapter = new BeerAdapter(this, new ArrayList<Beer>(0), new BeerAdapter.PostItemListener() {
////
////            @Override
////            public void onPostClick(long id) {
////                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
////            }
////        });
////
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
////        mRecyclerView.setLayoutManager(layoutManager);
////        mRecyclerView.setAdapter(mAdapter);
////        mRecyclerView.setHasFixedSize(true);
////        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
////        mRecyclerView.addItemDecoration(itemDecoration);
//
////        loadAnswers();
//
//
//
//
//
//
//
////    public void loadAnswers(){
////        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.punkapi.com").addConverterFactory(GsonConverterFactory.create()).build();
////        PunkAPIService service = retrofit.create(PunkAPIService.class);
////        mService.getListBeer().enqueue(new Callback<SOAnswersResponse>()  {
////            @Override
////            public void onResponse(@NonNull Call<SOAnswersResponse> call, @NonNull retrofit2.Response<SOAnswersResponse> response) {
////                if(response.isSuccessful()) {
////                    assert response.body() != null;
////                    mAdapter.updateAnswers(response.body().getBeers());
////                    Log.d("MainActivity", "posts loaded from API");
////                }else {
////                    int statusCode  = response.code();
////                    // handle request errors depending on status code
////                }
////            }
////
////
////            @Override
////            public void onFailure(@NonNull Call<SOAnswersResponse> call, Throwable t) {
////                Log.d("MainActivity", "error loading from API");
////                Throwable toto = t;
////
////            }
////
////        });
////    }
//
////    public void loadAnswers() {
////        mService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
////
////            @Override
////            public void onResponse(@NonNull retrofit2.Call<SOAnswersResponse> call, @NonNull retrofit2.Response<SOAnswersResponse> response) {
////
////                Log.d("loadAnswers()","IN");
////
////                if(response.isSuccessful()) {
////                    mAdapter.updateAnswers(response.body().getBeers());
////                    try {
////                        call.execute();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                    Log.d("MainActivity", "posts loaded from API");
////                }else {
////                    int statusCode  = response.code();
////                    Log.d("MainActivity","code status: "+statusCode);
////                    // handle request errors depending on status code
////                }
////            }
////            @Override
////            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
////            }
////        });
////    }
