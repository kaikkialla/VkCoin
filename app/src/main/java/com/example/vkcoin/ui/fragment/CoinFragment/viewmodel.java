package com.example.vkcoin.ui.fragment.CoinFragment;

import android.content.Context;
import android.util.Log;

import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class viewmodel extends ViewModel {
    private MutableLiveData<Float> balance = new MutableLiveData<>();
    private Disposable mBalanceDisposable;
    Context context;

    private MutableLiveData<CPUmodel> cpu= new MutableLiveData<>();
    private MutableLiveData<ServerModel> server = new MutableLiveData<>();
    private Disposable mCPUDisposable;
    private Disposable mServerDisposable;

    LiveData<Float> getBalance(Context context) {
        this.context = context;
        subscribeBalance();
        return balance;
    }

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



    private void subscribeBalance() {
        if (mBalanceDisposable != null) {
            mBalanceDisposable.dispose();
        }
        mBalanceDisposable = BalanceRepository.getInstance(context).getBalance()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(balance -> {
                    this.balance.setValue(balance);
                }, e -> Log.v("TEST", "subscribeBalance", e));
    }


    private void subscribeCPU() {
        if (mCPUDisposable != null) {
            mCPUDisposable.dispose();
        }
        mCPUDisposable = UpgradeRepository.getInstance(context).getCpu()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u-> {
                    this.cpu.setValue(u);
                });
    }

    private void subscribeServer() {
        if (mServerDisposable != null) {
            mServerDisposable.dispose();
        }
        mServerDisposable = UpgradeRepository.getInstance(context).getServer()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u-> {
                    this.server.setValue(u);
                });
    }
}