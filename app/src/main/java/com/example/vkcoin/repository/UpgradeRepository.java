package com.example.vkcoin.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.vkcoin.upgrades.CPU;
import com.example.vkcoin.upgrades.Server;

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


    private BehaviorSubject<Float> gain = BehaviorSubject.create();
    private SharedPreferences gainSp = context.getSharedPreferences("gain", Context.MODE_PRIVATE);


    public Observable<Float> getGain() {
        return gain;
    }


    public void addCpu(CPU cpu){
        cpu.setPrice(cpu.getPrice() * 1.3f);
        cpu.setQuantity(cpu.getQuantity() + 1);
        float newgain = gainSp.getFloat("gain", 0) + cpu.getGain();
        gainSp.edit().putFloat("gain", newgain).apply();
        gain.onNext(newgain);
    }


    public void addServer(Server server) {
        server.setPrice(server.getPrice() * 1.3f);
        server.setQuantity(server.getQuantity() + 1);
        float newgain = gainSp.getFloat("gain", 0) + server.getGain();
        gainSp.edit().putFloat("gain", newgain).apply();
        gain.onNext(newgain);
    }
}
