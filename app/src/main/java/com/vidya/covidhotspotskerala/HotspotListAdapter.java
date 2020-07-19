package com.vidya.covidhotspotskerala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotspotListAdapter extends RecyclerView.Adapter<HotspotListAdapter.HotspotViewHolder>{

    LayoutInflater inflater;
    List<hotspots> hotspots;

    public HotspotListAdapter(Context ctx, List<hotspots> hotspots){
        this.inflater = LayoutInflater.from(ctx);
        this.hotspots = hotspots;
    }

    @NonNull
    @Override
    public HotspotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.hotspots_layout,parent,false);

        return new HotspotViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotspotViewHolder holder, int position) {
        holder.districtTitle.setText(hotspots.get(position).getDistrict());
        holder.LSGD.setText(hotspots.get(position).getLsgd());
        holder.Wards.setText(hotspots.get(position).getWards());
    }

    @Override
    public int getItemCount() {
        return hotspots.size();
    }

    public class HotspotViewHolder extends RecyclerView.ViewHolder{

        TextView districtTitle,LSGD,Wards;

        public HotspotViewHolder(@NonNull View itemView) {
            super(itemView);

            districtTitle = itemView.findViewById(R.id.district);
            LSGD = itemView.findViewById(R.id.lsgd);
            Wards = itemView.findViewById(R.id.wards);
        }
    }
}
