package com.sealow.nytimes.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sealow.nytimes.R;
import com.sealow.nytimes.interfaces.AdapterEvents;
import com.sealow.nytimes.models.HomeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private Context mContext;
    private List<HomeModel> mList;
    private  AdapterEvents adapterEvents;

    public HomeAdapter(Context mContext, List<HomeModel> mList , AdapterEvents adapterEvents) {
        this.mContext = mContext;
        this.adapterEvents = adapterEvents;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.row_item_home , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(mList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterEvents.onItemClicked(mList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView txtDesc;
        TextView txtBy;
        TextView txtDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgItem);
            txtDesc = itemView.findViewById(R.id.txtDesc);
            txtBy = itemView.findViewById(R.id.txtBy);
            txtDate = itemView.findViewById(R.id.txtDate);
        }

        public void Bind(HomeModel homeModel) {
            txtBy.setText(homeModel.getByline());
            txtDesc.setText(homeModel.getTitle());
            txtDate.setText(homeModel.getPublished_date());

            try {
                Picasso.get().load(homeModel.getMedia().get(0).getMediaMetaData().get(1).getUrl()).into(img);
            } catch (Exception e) {
                Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Error.svg/1200px-Error.svg.png").into(img);
            }
        }
    }
}
