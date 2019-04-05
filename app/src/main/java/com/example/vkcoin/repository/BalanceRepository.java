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


    private BehaviorSubject<Long> balance = BehaviorSubject.create();

    private SharedPreferences balanceSP = context.getSharedPreferences("balance", Context.MODE_PRIVATE);
    private SharedPreferences.Editor balanceSPeditor = balanceSP.edit();

    public Observable<Long> getBalance() {
        return balance;
    }


    public void resumeBalance() {
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            long newbalance = balanceSP.getLong("balance", 0) + 1;
            balanceSPeditor.putLong("balance", newbalance).apply();
            balance.onNext(balanceSP.getLong("balance", 0));
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 1000);
    }

    public void  pauseBalance() {
        //Прекратить увеличение баланса, а мб и нет
    }

    public void click() {
        long newbalance = balanceSP.getLong("balance", 0) + 10;
        balanceSPeditor.putLong("balance", newbalance).apply();
        balance.onNext(balanceSP.getLong("balance", 0));
    }
}
