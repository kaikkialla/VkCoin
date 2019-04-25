package com.example.vkcoin.ui.fragment.shopFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkcoin.R;
import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.repository.BalanceRepository;
import com.example.vkcoin.repository.UpgradeRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopFragment extends BottomSheetDialogFragment {


    private RecyclerView recyclerView;
    private shopAdapter adapter;
    viewmodel mViewmodel;
    CPUmodel cpUmodel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewmodel = ViewModelProviders.of(this).get(viewmodel.class);
        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new shopAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

    }

    @Override
    public void onResume() {
        super.onResume();
        mViewmodel.getCPU(getContext()).observe(getActivity(), cpu -> {
            adapter.swapCpu(cpu);
            cpUmodel = cpu;
        });
        mViewmodel.getServer(getContext()).observe(getActivity(), server -> {
            adapter.swapServer(server);
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        UpgradeRepository.getInstance(getActivity()).saveCPU(cpUmodel);
    }
}
