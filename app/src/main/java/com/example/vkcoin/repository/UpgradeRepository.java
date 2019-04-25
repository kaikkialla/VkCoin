package com.example.vkcoin.repository;

import android.content.Context;
import android.util.Log;

import com.example.vkcoin.Executor;
import com.example.vkcoin.database.cpu.CpuDao;
import com.example.vkcoin.database.cpu.CpuDatabase;
import com.example.vkcoin.database.server.ServerDao;
import com.example.vkcoin.database.server.ServerDatabase;
import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;



import androidx.room.Room;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
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


    private BehaviorSubject<Long> increment = BehaviorSubject.create();

    public Observable<Long> getIncrement() {
        return increment;
    }



    private BehaviorSubject<CPUmodel> cpu= BehaviorSubject.create();
    private BehaviorSubject<ServerModel> server= BehaviorSubject.create();

    CpuDao cpuDao;
    ServerDao serverDao;

    public void initialize() {
        cpuDao = Room.databaseBuilder(context.getApplicationContext(), CpuDatabase.class, "cpudb").build().getCpuDao();
        serverDao = Room.databaseBuilder(context.getApplicationContext(), ServerDatabase.class, "serverdb").build().getServerDao();
//        loadCPU();
//        loadServer();
    }


    public Observable<CPUmodel> getCpu() {
        loadCPU();
        return cpu;
    }

    public Observable<ServerModel> getServer() {
        loadServer();
        return server;
    }


    public void loadCPU() {
        Executor.EXECUTOR.execute(() -> {
            final CPUmodel cpumodel = cpuDao.getAll();
            if(cpumodel != null) {
                cpu.onNext(cpumodel);
            }
        });
    }

    public void loadServer() {
        Executor.EXECUTOR.execute(() -> {
            final ServerModel servermodel = serverDao.getAll();
            if (servermodel != null) {
                server.onNext(servermodel);
            }
        });
    }

    public void saveCPU(CPUmodel cpumodel) {
        Executor.EXECUTOR.execute(() -> {
            Single.fromCallable(() -> {
                cpuDao.deleteAll();
                cpuDao.insert(cpumodel);
                return true;
            }).subscribeOn(Schedulers.io()).subscribe(ignore -> {}, e -> Log.e("TEST", "", e));
        });
    }

    public void saveServer(ServerModel serverModel) {
        Executor.EXECUTOR.execute(() -> {
            Single.fromCallable(() -> {
                serverDao.deleteAll();
                serverDao.insert(serverModel);
                return true;
            }).subscribeOn(Schedulers.io()).subscribe(ignore -> {}, e -> Log.e("TEST", "", e));
        });
    }

    public void buyCPU(CPUmodel cpUmodel) {
        cpUmodel.setQuantity(cpUmodel.getQuantity() + 1);
        cpUmodel.setPrice(cpUmodel.getPrice() + 10);
        cpUmodel.setGain(cpUmodel.getGain() + 5);
        saveCPU(cpUmodel);
        loadCPU();
    }

    public void buyServer(ServerModel serverModel) {
        serverModel.setQuantity((long) (serverModel.getQuantity() + 1));
        serverModel.setPrice(serverModel.getPrice() + 15);
        serverModel.setGain(serverModel.getGain() + 1);
        saveServer(serverModel);
        loadServer();
    }
}
