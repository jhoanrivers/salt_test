package com.example.salt.ui.main_activity;

import com.example.salt.model.NewsResponse;

public interface MainViewInterface {


    void showToast(String s);
    void displayNews(NewsResponse movieResponse);
    void displayError(String s);
    void displayLoading(boolean loading);
}
