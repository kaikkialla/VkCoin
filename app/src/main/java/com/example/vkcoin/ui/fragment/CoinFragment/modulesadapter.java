package com.example.vkcoin.ui.fragment.CoinFragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vkcoin.R;
import com.example.vkcoin.model.CPUmodel;
import com.example.vkcoin.model.ServerModel;
import com.example.vkcoin.repository.UpgradeRepository;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class modulesadapter extends RecyclerView.Adapter<modulesadapter.ViewHolder> {
    FragmentActivity activity;
    CPUmodel cpu;
    ServerModel server;

    public modulesadapter(FragmentActivity activity) {
        this.activity = activity;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.modulerow, parent, false );
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(position == 0 && cpu != null) {
            holder.name.setText(cpu.getName());
            holder.gain.setText("+" + cpu.getGain() + "/сек");
            holder.price.setText("x" + cpu.getQuantity());
//            holder.itemView.setOnClickListener(v -> {
//                Log.e("TEST0", "Click " + cpu);
//                UpgradeRepository.getInstance(activity).buyCPU(cpu);
//            });
        } else if(position == 1 && server != null) {
            holder.name.setText(server.getName());
            holder.gain.setText("+" + server.getGain() + "/сек");
            holder.price.setText("x" + server.getQuantity());
            //holder.itemView.setOnClickListener(v -> UpgradeRepository.getInstance(activity).buyServer(server));
        }
    }

    public void swapCpu(CPUmodel cpumodel){
        //Log.v("TEST2", cpumodel.toString());
        this.cpu = cpumodel;
        notifyDataSetChanged();
    }

    public void swapServer(ServerModel servermodel){
        this.server = servermodel;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return 2;
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
            this.price = itemView.findViewById(R.id.quantity);
            this.gain = itemView.findViewById(R.id.gain);
        }
    }
}