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

public class DataType2Adapter extends RecyclerView.Adapter<DataType2Adapter.ViewHolder> {

    private List<Type2> type2List;

    public DataType2Adapter(List<Type2> type2List) {
        this.type2List = type2List;
    }

    @NonNull
    @Override
    public DataType2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_type2, parent, false);
        return new DataType2Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataType2Adapter.ViewHolder holder, int position) {
        Type2 datatype2List = type2List.get(position);
        holder.textViewDataType5.setText(datatype2List.getType2_name()+"  "+datatype2List.getType2_done());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等
    }

    @Override
    public int getItemCount() {
        return type2List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDataType5;
        ImageButton imageButtonFee5;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDataType5 = itemView.findViewById(R.id.text_view_data_type2);
            imageButtonFee5 = itemView.findViewById(R.id.imageButtonFeed_type2);
        }
    }
}