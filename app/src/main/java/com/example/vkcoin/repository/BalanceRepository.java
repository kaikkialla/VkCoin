package com.example.vkcoin.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

import com.example.vkcoin.Upgrades;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private SharedPreferences modulesSp = context.getSharedPreferences("module", Context.MODE_PRIVATE);
    private SharedPreferences bgincrement = context.getSharedPreferences("bg", Context.MODE_PRIVATE);
    private SharedPreferences clickincrement = context.getSharedPreferences("click", Context.MODE_PRIVATE);


    private BehaviorSubject<Float> balance = BehaviorSubject.create();
    //private BehaviorSubject<List<Bonus>> modules = BehaviorSubject.create();
    public List<Upgrades> moduleslist = new ArrayList<>();

    public Observable<Float> getBalance() {
        return balance;
    }
//    public Observable<List<Bonus>> getModules() {
//        return modules;
//    }

    public List<Upgrades> getModules() {
        Gson gson = new Gson();
        String jsonText1 = modulesSp.getString("module", null);
        Upgrades[] text = gson.fromJson(jsonText1, Upgrades[].class);
        return new ArrayList<>(Arrays.asList(text));
    }

    public void start() {
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            float newbalance = balanceSP.getFloat("balance", 0) + bgincrement.getFloat("bg", 0);
            //float newbalance = 0;

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


    public void increaseBg(float difference) {
        bgincrement.edit()
                .putFloat("bg", bgincrement.getFloat("bg", 0) + difference)
                .apply();
    }


    public void increaseClick(float difference) {
        clickincrement.edit()
                .putFloat("click", clickincrement.getFloat("click", 0) + difference)
                .apply();
    }



    public void addmodule(Upgrades bonus) {
        moduleslist.add(bonus);

        Gson gson = new Gson();
        List<Upgrades> textList = new ArrayList<>();
        textList.addAll(moduleslist);
        String jsonText = gson.toJson(textList);
        modulesSp.edit().putString("module", jsonText).apply();

//        String jsonText1 = modulesSp.getString("key", null);
//        Bonus[] text = gson.fromJson(jsonText1, Bonus[].class);
//        new ArrayList<Bonus>(Arrays.asList(text));
        //modules.onNext(new ArrayList<Bonus>(Arrays.asList(text)));
    }
}
