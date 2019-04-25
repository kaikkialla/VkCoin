package com.example.vkcoin.dagger;


import android.content.Context;

import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    BalanceRepository provideBalanceRepository(Context context) {
        return BalanceRepository.getInstance(context);//Зависимость
    }

    @Provides
    UpgradeRepository provideUpgradeRepository(Context context) {
        return UpgradeRepository.getInstance(context);//Зависимость
    }
}