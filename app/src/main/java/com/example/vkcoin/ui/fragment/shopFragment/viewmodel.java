package com.example.vkcoin.ui.fragment.shopFragment;

import android.content.Context;

import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;
import com.example.vkcoin.repository.UpgradeRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;

public class viewmodel extends ViewModel {
    private MutableLiveData<CPUmodel> cpu= new MutableLiveData<>();
    private MutableLiveData<ServerModel> server = new MutableLiveData<>();
    private Disposable mCPUDisposable;
    private Disposable mServerDisposable;
    Context context;



    LiveData<CPUmodel> getCPU(Context context) {
        this.context = context;
        subscribeCPU();
        return cpu;
    }

    LiveData<ServerModel> getServer(Context context) {
        this.context = context;
        subscribeServer();
        return server;
    }


    private void subscribeCPU() {
        if (mCPUDisposable != null) {
            mCPUDisposable.dispose();
        }
        mCPUDisposable = UpgradeRepository.getInstance(context).getCpu()
                .subscribe(u-> this.cpu.setValue(u));
    }


    private void subscribeServer() {
        if (mServerDisposable != null) {
            mServerDisposable.dispose();
        }
        mServerDisposable = UpgradeRepository.getInstance(context).getServer()
                .subscribe(u-> this.server.setValue(u));
    }
}