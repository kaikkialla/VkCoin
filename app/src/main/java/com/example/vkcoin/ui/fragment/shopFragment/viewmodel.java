package com.example.vkcoin.ui.fragment.shopFragment;


import android.content.Context;

import com.example.vkcoin.Bonus;
import com.example.vkcoin.repository.BalanceRepository;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;

public class viewmodel extends ViewModel {
//    private MutableLiveData<List<Bonus>> modules = new MutableLiveData<>();
//    private Disposable mDisposable;
//    Context context;
//
//
//    LiveData<List<Bonus>> getModules(Context context) {
//        this.context = context;
//        subscribeModules();
//        return modules;
//    }
//
//    private void subscribeModules() {
//        if (mDisposable != null) {
//            mDisposable.dispose();
//        }
//        mDisposable = BalanceRepository.getInstance(context).getModules()
//                .subscribe(modules -> this.modules.setValue(modules));
//    }
}