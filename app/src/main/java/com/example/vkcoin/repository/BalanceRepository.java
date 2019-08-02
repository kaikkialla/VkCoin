package com.example.vkcoin.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;

import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.BehaviorSubject;


public class BalanceRepository {
    private static Context context;
    private static volatile BalanceRepository instance;
    static CPUmodel cpu;
    static ServerModel server;

    public static BalanceRepository getInstance(Context c) {
        context = c;
//        cpu = cpUmodel;
//        server = serverModel;
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


    private float[] b;
    public void start() {
        UpgradeRepository.getInstance(context).getCpu()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u-> {
                    cpu = u;
                });

        UpgradeRepository.getInstance(context).getServer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u-> {
                    server = u;
                });


        b = new float[]{balanceSP.getFloat("balance", 0)};
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            Log.e("fjaofa", cpu.getGain() + " / " + server.getGain());
            b[0] = b[0] + server.getGain() + cpu.getGain();

            balance.onNext(b[0]);
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
        b[0] = b[0] + 1;
        balance.onNext(b[0]);
    }


    public void increaseBalance(float addition) {
        balanceSP.edit().putFloat("balance", balanceSP.getFloat("balance", 0) + addition).apply();
    }

    public void saveBalance(float balance) {
        balanceSP.edit().putFloat("balance", this.balance.getValue()).apply();
    }

}

/**
    Переделать сохранение баланса
    Чтобы сохранялся при закрытии приложения, а не при изменении
 */
