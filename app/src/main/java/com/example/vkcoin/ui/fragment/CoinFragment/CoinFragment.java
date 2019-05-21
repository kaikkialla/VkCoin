package com.example.vkcoin.ui.fragment.CoinFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vkcoin.MainApplication;
import com.example.vkcoin.R;
import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;
import com.example.vkcoin.ui.fragment.shopFragment.ShopFragment;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.vkcoin.ui.MainActivity.SCREEN_WIDTH;
import static com.example.vkcoin.ui.MainActivity.density;

public class CoinFragment extends Fragment {

    //@BindView(R.id.balance) TextView mBalance;
    private TextView mBalance;
    private ImageView mClick;
    private CardView mShop;
    private CardView mTransaction;
    private CardView mLeaderboard;

    private viewmodel mViewmodel;
    //@Inject BalanceRepository repository;
    private float balance1;
    private RecyclerView recyclerView;
    modulesadapter adapter;
    CPUmodel cpUmodel;
    ServerModel serverModel;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        MainApplication.mainComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coin_fragment, container, false);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(getActivity());
        mBalance = view.findViewById(R.id.balance);
        mClick = view.findViewById(R.id.click);
        mTransaction = view.findViewById(R.id.transaction);
        mShop = view.findViewById(R.id.shop);
        mLeaderboard = view.findViewById(R.id.leaderboard);

        mViewmodel = ViewModelProviders.of(this).get(viewmodel.class);

        recyclerView = view.findViewById(R.id.recyclerview);

        adapter = new modulesadapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        setSizes();



        mClick.setOnClickListener(v -> {
            BalanceRepository.getInstance(getContext()).click();//сделать inject, заменить BalanceRepository.getInstance(getContext()) на repository
        });

        mShop.setOnClickListener(v -> {
            ShopFragment bottomSheet = new ShopFragment();
            bottomSheet.show(getFragmentManager(), "BottomSheet");
        });
    }


    @SuppressLint("CheckResult")
    @Override
    public void onResume() {
        super.onResume();
        mViewmodel.getBalance(getContext()).observe(getActivity(), balance -> {
            balance1 = balance;
            mBalance.setText(String.format("%.3f", balance1));
        });
        mViewmodel.getCPU(getContext()).observe(getActivity(), cpu -> {
            adapter.swapCpu(cpu);
            cpUmodel = cpu;
        });
        mViewmodel.getServer(getContext()).observe(getActivity(), server -> {
            adapter.swapServer(server);
            serverModel = server;
        });
    }

    private void setSizes() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(SCREEN_WIDTH / 3 - (8 * 4), (int) ((SCREEN_WIDTH / 3 - (8 * 4)) / 1.6), ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins((int) (4*density), 0, (int) (4 * density), 0);
        mShop.setLayoutParams(layoutParams);
        mTransaction.setLayoutParams(layoutParams);
        mLeaderboard.setLayoutParams(layoutParams);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("fkpafapfa", String.valueOf(balance1));
        BalanceRepository.getInstance(getContext()).saveBalance(balance1);
        UpgradeRepository.getInstance(getActivity()).saveCPU(cpUmodel);
        UpgradeRepository.getInstance(getActivity()).saveServer(serverModel);
    }
}
