package com.example.vkcoin.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vkcoin.MainApplication;
import com.example.vkcoin.R;
import com.example.vkcoin.repository.BalanceRepository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class CoinFragment extends Fragment {

    //@BindView(R.id.balance) TextView mBalance;
    TextView mBalance;
    Button mClick;
    viewmodel mViewmodel;





    //@Inject BalanceRepository repository;

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
        mViewmodel = ViewModelProviders.of(this).get(viewmodel.class);




        mClick.setOnClickListener(v -> {
            BalanceRepository.getInstance(getContext()).click();//сделать inject, заменить BalanceRepository.getInstance(getContext()) на repository
        });




        //test
        Button testbg = view.findViewById(R.id.testbg);
        Button testclick = view.findViewById(R.id.testclick);
        //test
        testbg.setOnClickListener(v -> {
            BalanceRepository.getInstance(getContext()).increaseBg(0.01f);//сделать inject, заменить BalanceRepository.getInstance(getContext()) на repository
        });
        testclick.setOnClickListener(v -> {
            BalanceRepository.getInstance(getContext()).increaseClick(0.05f);//сделать inject, заменить BalanceRepository.getInstance(getContext()) на repository
        });
    }


    @SuppressLint("CheckResult")
    @Override
    public void onResume() {
        super.onResume();
        mViewmodel.getBalance(getContext()).observe(getActivity(), balance -> mBalance.setText(String.format("%.3f", balance)));
    }

}
