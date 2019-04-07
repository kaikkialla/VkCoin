package com.example.vkcoin.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.vkcoin.Upgrade;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class UpgradeRepository {
    private static Context context;
    private static volatile UpgradeRepository instance;

    public static UpgradeRepository getInstance(Context c) {
        context = c;
        UpgradeRepository localInstance = instance;
        if (localInstance == null) {
            synchronized (UpgradeRepository.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UpgradeRepository();
                }
            }
        }
        return localInstance;
    }


    private SharedPreferences upgradeSp = context.getSharedPreferences("upgrade", Context.MODE_PRIVATE);
    private BehaviorSubject<List<Upgrade>> upgrades = BehaviorSubject.create();


//    private BehaviorSubject<Float> increment = BehaviorSubject.create();
//    private SharedPreferences gainSP = context.getSharedPreferences("gain", Context.MODE_PRIVATE);

    public Observable<List<Upgrade>> getUpgrade() {
        upgrades.onNext(fromGson());
        return upgrades;
    }

//    public Observable<Float> getIncrement() {
//        returnIncrement();
//        return increment;
//    }


//    private void returnIncrement(){
//        float gain = 0;
//        List<Upgrade> a = fromGson();
//        for(Upgrade u : a) {
//            gain = gain + u.getGain();
//        }
//        gainSP.edit().putFloat("gain", gain).apply();
//        increment.onNext(gain);
//    }


    private void toGson(List<Upgrade> list) {
        Gson gson = new Gson();
        List<Upgrade> textList = new ArrayList<>();
        textList.addAll(list);
        String jsonText = gson.toJson(textList);
        upgradeSp.edit().putString("upgrade", jsonText).apply();
    }

    private ArrayList<Upgrade> fromGson() {
        Gson gson = new Gson();
        String jsonText1 = upgradeSp.getString("upgrade", null);
        Upgrade[] text = gson.fromJson(jsonText1, Upgrade[].class);
        return new ArrayList<>(Arrays.asList(text));
    }


    public void add(Upgrade upgrade) {
        List<Upgrade> list = new ArrayList<>();
        list.add(upgrade);
        toGson(list);
        upgrades.onNext(fromGson());
    }
}
