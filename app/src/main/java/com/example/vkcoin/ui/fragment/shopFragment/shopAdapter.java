package com.example.vkcoin.ui.fragment.shopFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vkcoin.R;
import com.example.vkcoin.Upgrades;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class shopAdapter extends RecyclerView.Adapter<shopAdapter.ViewHolder> {
    private List<Upgrades> bonuses = new ArrayList<>();
    FragmentActivity activity;

    public shopAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.shoprow   , parent, false );
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(bonuses.get(position).getName());

        List<Integer> ids = new ArrayList<>();
        for(Upgrades bonus : bonuses) {
            ids.add(bonus.getid());
        }
        holder.gain.setText(String.valueOf(Collections.frequency(ids, bonuses.get(position).getid())));

    }

    @Override
    public int getItemCount() {
        return bonuses.size();
    }


    public void swap(List<Upgrades> list) {
        this.bonuses = list;
        notifyDataSetChanged();
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





