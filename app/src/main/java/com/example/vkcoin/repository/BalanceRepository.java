package com.example.vkcoin.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


public class BalanceRepository {
    private static Context context;
    private static volatile BalanceRepository instance;

    public static BalanceRepository getInstance(Context c) {
        context = c;
        BalanceRepository localInstance = instance;
        if (localInstance == null) {
            synchronized (BalanceRepository.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BalanceRepository();
                }
            }
        }
        return localInstance;
    }


    private SharedPreferences balanceSP = context.getSharedPreferences("balance", Context.MODE_PRIVATE);


    private BehaviorSubject<Float> balance = BehaviorSubject.create();

    public Observable<Float> getBalance() {
        return balance;
    }


    public void start() {
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            float newbalance = balanceSP.getFloat("balance", 0) + 1;
            balanceSP.edit()
                    .putFloat("balance", newbalance)
                    .apply();
            balance.onNext(balanceSP.getFloat("balance", 0f));
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 1000);
    }


    public void click() {
        float newbalance = balanceSP.getFloat("balance", 0) +0.001f;

        balanceSP.edit()
                .putFloat("balance", newbalance)
                .apply();
        balance.onNext(balanceSP.getFloat("balance", 0f));
    }


    public void increaseBalance(float addition) {
        balanceSP.edit().putFloat("balance", balanceSP.getFloat("balance", 0) + addition).apply();
    }
}
