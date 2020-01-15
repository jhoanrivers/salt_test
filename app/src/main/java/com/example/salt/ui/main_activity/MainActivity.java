package com.example.salt.ui.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.salt.R;
import com.example.salt.adapter.NewsAdapter;
import com.example.salt.model.NewsResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;


    @BindView(R.id.pb_loading)
    ProgressBar progressBar;

    private String TAG = "MainActivity";
    RecyclerView.Adapter adapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        setupMVP();
        setupViews();
        getNewsList();


    }


    private void setupMVP(){
        Log.e("mainPres", "setupMVP: ");
        mainPresenter = new MainPresenter(this);
    }

    private void setupViews(){
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
    }


    private void getNewsList(){
        Log.e("gituaja", "getMovieList: ");
        mainPresenter.getNews();
    }


    @Override
    public void showToast(String str){
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayNews (NewsResponse newsResponse){
        if(newsResponse != null){
            Log.e(TAG, newsResponse.getArticles().get(1).getTitle());
            adapter = new NewsAdapter(newsResponse.getArticles(), MainActivity.this);
            rvMovies.setAdapter(adapter);
        } else{
            Log.e(TAG, "Movies Response null");
        }
    }

    @Override
    public void displayError(String e){
        showToast(e);
    }

    @Override
    public void displayLoading(boolean loading) {
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


}
