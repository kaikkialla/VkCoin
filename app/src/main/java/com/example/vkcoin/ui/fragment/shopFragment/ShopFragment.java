package com.example.vkcoin.ui.fragment.shopFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vkcoin.R;
import com.example.vkcoin.repository.BalanceRepository;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShopFragment extends BottomSheetDialogFragment {


    TextView cpuname;
    TextView cpuprice;
    TextView cpugain;
    TextView cpuquantity;

    TextView servername;
    TextView serverprice;
    TextView servergain;
    TextView serverquantity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.shop_fragment, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cpuname = view.findViewById(R.id.cpuname);
        cpuprice = view.findViewById(R.id.cpuprice);
        cpugain = view.findViewById(R.id.cpugain);
        cpuquantity = view.findViewById(R.id.cpuquantity);

        servername = view.findViewById(R.id.servername);
        serverprice = view.findViewById(R.id.serverprice);
        servergain = view.findViewById(R.id.servergain);
        serverquantity = view.findViewById(R.id.serverquantity);

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
