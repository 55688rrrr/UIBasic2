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

public class DataHealthAdapter extends RecyclerView.Adapter<DataHealthAdapter.ViewHolder> {

    private List<Health> healthList;

    public DataHealthAdapter(List<Health> healthList) {
        this.healthList = healthList;
    }

    @NonNull
    @Override
    public DataHealthAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_health, parent, false);
        return new DataHealthAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataHealthAdapter.ViewHolder holder, int position) {
        Health datahealthList = healthList.get(position);
        holder.textViewDataType3.setText(datahealthList.getHealth_name()+"  "+datahealthList.getHealth_done());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等
    }

    @Override
    public int getItemCount() {
        return healthList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDataType3;
        ImageButton imageButtonFee3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDataType3 = itemView.findViewById(R.id.text_view_data_health);
            imageButtonFee3 = itemView.findViewById(R.id.imageButtonFeed_health);
        }
    }
}