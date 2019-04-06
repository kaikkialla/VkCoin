package com.example.vkcoin.ui.fragment.shopFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vkcoin.Bonus;
import com.example.vkcoin.R;
import com.example.vkcoin.repository.BalanceRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopFragment extends BottomSheetDialogFragment {

    private RecyclerView recyclerView;
    viewmodel mViewmodel;
    shopAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new shopAdapter(getActivity());
        mViewmodel = ViewModelProviders.of(this).get(viewmodel.class);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));


    }

    @Override
    public void onResume() {
        super.onResume();
        //mViewmodel.getModules(getContext()).observe(getActivity(), modules -> adapter.swap(modules));
        adapter.swap(BalanceRepository.getInstance(getContext()).getModules());
    }
}
