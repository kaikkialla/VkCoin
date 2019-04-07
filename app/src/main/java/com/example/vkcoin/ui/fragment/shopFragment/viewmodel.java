package com.example.vkcoin.ui.fragment.shopFragment;

import android.content.Context;

import com.example.vkcoin.Upgrade;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.Disposable;

public class viewmodel extends ViewModel {
    private MutableLiveData<List<Upgrade>> upgrade = new MutableLiveData<>();
    private Disposable mDisposable;
    Context context;


    LiveData<List<Upgrade>> getUpgrade(Context context) {
        this.context = context;
        subscribeUpgrade();
        return upgrade;
    }

    private void subscribeUpgrade() {
        if (mDisposable != null) {
            mDisposable.dispose();
        }
        mDisposable = UpgradeRepository.getInstance(context).getUpgrade()
                .subscribe(u-> {
                    this.upgrade.setValue(u);
                });
    }
}