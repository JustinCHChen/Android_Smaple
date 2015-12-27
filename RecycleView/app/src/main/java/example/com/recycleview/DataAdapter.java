package example.com.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by justin on 12/27/15.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<String> mDataList = new ArrayList<>();

    public DataAdapter(ArrayList<String> dataList) {
        if(dataList != null){
            this.mDataList.addAll(dataList);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text1.setText(getItem(position));
        holder.text2.setText(getItem(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public String getItem(int position) {
        String result = null;
        if (position < mDataList.size()) {
            result = mDataList.get(position);
        }
        return result;
    }

    public void updateData(ArrayList<String> dataList) {
        mDataList.clear();
        if (dataList != null) {
            this.mDataList = dataList;
            this.notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text1;
        public TextView text2;

        public ViewHolder(View v) {
            super(v);
            text1 = (TextView) v.findViewById(R.id.text1);
            text2 = (TextView) v.findViewById(R.id.text2);
        }
    }
}
