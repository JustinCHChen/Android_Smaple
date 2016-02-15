package com.example.justin_chen.drawer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


/**
 * Created by justin_chen on 2016/2/4.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListViewHolder> {

    private Context mContext;
    private ArrayList<String> mHomeList = new ArrayList<>();

    public HomeListAdapter(Context context) {
        mContext = context;
    }

    @Override
    public HomeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false);

        return new HomeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeListViewHolder holder, int position) {
        holder.photoView.setImageUrl(mHomeList.get(position), VolleyManager.getInstance(mContext.getApplicationContext()).getImageLoader());
    }

    @Override
    public int getItemCount() {
        return mHomeList.size();
    }

    public void updateList(ArrayList<String> list) {
        mHomeList.clear();
        if (list != null) {
            mHomeList.addAll(list);
        }

        notifyDataSetChanged();
    }
}

class HomeListViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView photoView;

    public HomeListViewHolder(View itemView) {
        super(itemView);

        photoView = (NetworkImageView) itemView.findViewById(R.id.photoView);
    }
}
