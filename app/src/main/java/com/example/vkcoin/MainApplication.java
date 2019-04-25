package com.example.vkcoin;

import android.app.Application;

import com.example.vkcoin.dagger.DaggerMainComponent;
import com.example.vkcoin.dagger.MainComponent;
import com.example.vkcoin.dagger.MainModule;
import com.example.vkcoin.database.server.ServerDatabase;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.PushRepository;
import com.example.vkcoin.repository.UpgradeRepository;


public class MainApplication extends Application {

    public static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Executor.EXECUTOR.start();
        UpgradeRepository.getInstance(getApplicationContext()).initialize();
        BalanceRepository.getInstance(getApplicationContext()).start();

        PushRepository.getInstance(getApplicationContext()).initialize();
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
    }

}
