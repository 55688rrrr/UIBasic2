package com.example.uibasic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataDailyAdapter extends RecyclerView.Adapter<DataDailyAdapter.ViewHolder> {

    private List<Daily> dailyList;

    public DataDailyAdapter(List<Daily> dailyList) {
        this.dailyList = dailyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_daily, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Daily datadailyList = dailyList.get(position);
        holder.textViewDataType1.setText(datadailyList.getDaily_name()+"  "+datadailyList.getDaily_done());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDataType1;
        ImageButton imageButtonFeed1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDataType1 = itemView.findViewById(R.id.text_view_data_daily);
            imageButtonFeed1 = itemView.findViewById(R.id.imageButtonFeed_daily);
        }
    }
}
