package com.example.vkcoin.ui.fragment.shopFragment;

import android.content.Context;

import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;
import com.example.vkcoin.upgrades.Upgrade;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;

//public class viewmodel extends ViewModel {
//    private MutableLiveData<Upgrade> upgrade = new MutableLiveData<>();
//    private Disposable mDisposable;
//    Context context;
//
//
//    LiveData<Upgrade> getBalance(Context context) {
//        this.context = context;
//        subscribeUpgrade();
//        return upgrade;
//    }
//
//    private void subscribeUpgrade() {
//        if (mDisposable != null) {
//            mDisposable.dispose();
//        }
////        mDisposable = UpgradeRepository.getInstance(context).getBalance()
////                .subscribe(balance -> {
////                    this.upgrade.setValue(balance);
////                });
//    }
//}