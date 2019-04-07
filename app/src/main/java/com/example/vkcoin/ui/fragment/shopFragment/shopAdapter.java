package com.example.vkcoin.ui.fragment.shopFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vkcoin.R;
import com.example.vkcoin.Upgrade;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.vkcoin.ui.MainActivity.UpgradeType.CPU;
import static com.example.vkcoin.ui.MainActivity.UpgradeType.SERVER;


public class shopAdapter extends RecyclerView.Adapter<shopAdapter.ViewHolder> {
    FragmentActivity activity;

    public shopAdapter(FragmentActivity activity) {
        this.activity = activity;
    }
    List<Upgrade> upgradeList = new ArrayList<>();

    //UpgradeRepository.UpgradeType[] upgrades = {CPU, UpgradeRepository.UpgradeType.SERVER};
    Upgrade[] available = {new Upgrade(CPU, "CPU", 0.01f, 0.001f), new Upgrade(SERVER, "Server", 0.1f, 0.01f)};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.shoprow   , parent, false );
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Upgrade item;
        if(upgradeList.size() == 0) {
            item = available[position];
        } else {
            item = upgradeList.get(position);
        }
        holder.name.setText(item.getName());
        holder.gain.setText(String.valueOf(item.getGain()));
        holder.price.setText(String.valueOf(item.getPrice()));
        Log.e("gkpisgpis", String.valueOf(upgradeList.size()));
    }


    public void swap(List<Upgrade> list) {
        this.upgradeList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        int returnVal = 0;
        if(upgradeList.size() == 0) {
            returnVal = available.length;
        } else if(upgradeList != null) {
            returnVal = upgradeList.size();
        }
        return returnVal;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView name;
        TextView price;
        TextView gain;


        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.name = itemView.findViewById(R.id.name);
            this.price = itemView.findViewById(R.id.price);
            this.gain = itemView.findViewById(R.id.gain);
        }
    }
}