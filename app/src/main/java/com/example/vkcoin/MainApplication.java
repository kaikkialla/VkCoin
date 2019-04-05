package com.example.vkcoin;

import android.app.Application;

import com.example.vkcoin.dagger.DaggerMainComponent;
import com.example.vkcoin.dagger.MainComponent;
import com.example.vkcoin.dagger.MainModule;
import com.example.vkcoin.repository.BalanceRepository;


public class MainApplication extends Application {

    public static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        BalanceRepository.getInstance(getApplicationContext()).resumeBalance();
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
    }
}
