package com.example.vkcoin.ui.fragment;

import android.content.Context;

import com.example.vkcoin.repository.BalanceRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;

public class viewmodel extends ViewModel {
    private MutableLiveData<Float> balace = new MutableLiveData<>();
    private Disposable mDisposable;
    Context context;


    LiveData<Float> getBalance(Context context) {
        this.context = context;
        subscribeBalance();
        return balace;
    }

    private void subscribeBalance() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        mDisposable = BalanceRepository.getInstance(context).getBalance()
                .subscribe(balance -> {
                    this.balace.setValue(balance);
                });
    }
}