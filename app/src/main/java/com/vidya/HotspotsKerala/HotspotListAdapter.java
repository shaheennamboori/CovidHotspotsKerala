package com.vidya.HotspotsKerala;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HotspotListAdapter extends RecyclerView.Adapter<HotspotListAdapter.HotspotViewHolder> implements Filterable {

    LayoutInflater inflater;
    List<HotspotModel> hotspots;
    Context mcontext;


    private List<HotspotModel> hotspotscopy;

    public HotspotListAdapter(Context ctx, List<HotspotModel> hotspots){
        this.inflater = LayoutInflater.from(ctx);
        this.hotspots = hotspots;
        this.mcontext = ctx;
        hotspotscopy = new ArrayList<>(hotspots);
    }

    @NonNull
    @Override
    public HotspotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.hotspots_layout,parent,false);

        return new HotspotViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HotspotViewHolder holder, int position) {

        holder.container.setAnimation(AnimationUtils.loadAnimation(mcontext,R.anim.myanimation));

        holder.districtTitle.setText(hotspots.get(position).getDistrict());
        holder.LSGD.setText(hotspots.get(position).getLsgd());
        holder.Wards.setText(hotspots.get(position).getWards());
    }

    @Override
    public int getItemCount() {
        return hotspots.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<HotspotModel> filteredList = new ArrayList<>();

            if(charSequence == null  ||  charSequence.length() ==0 ){
                filteredList.addAll(hotspotscopy);
            }else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (HotspotModel hotspot : hotspotscopy){
                    if (hotspot.getDistrict().toLowerCase().contains(filterPattern)){
                        filteredList.add(hotspot);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            hotspots.clear();
            hotspots.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class HotspotViewHolder extends RecyclerView.ViewHolder{

        TextView districtTitle,LSGD,Wards;
        LinearLayout container;

        public HotspotViewHolder(@NonNull View itemView) {
            super(itemView);
            container=itemView.findViewById(R.id.container);
            districtTitle = itemView.findViewById(R.id.district);
            LSGD = itemView.findViewById(R.id.lsgd);
            Wards = itemView.findViewById(R.id.wards);

        }
    }
}
