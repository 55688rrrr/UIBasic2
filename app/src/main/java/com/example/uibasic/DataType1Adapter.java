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

public class DataType1Adapter extends RecyclerView.Adapter<DataType1Adapter.ViewHolder> {

    private List<Type1> type1List;

    public DataType1Adapter(List<Type1> type1List) {
        this.type1List = type1List;
    }

    @NonNull
    @Override
    public DataType1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_type1, parent, false);
        return new DataType1Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataType1Adapter.ViewHolder holder, int position) {
        Type1 datatype1List = type1List.get(position);
        holder.textViewDataType4.setText(datatype1List.getType1_name()+"  "+datatype1List.getType1_deadline());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等
    }

    @Override
    public int getItemCount() {
        return type1List.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDataType4;
        ImageButton imageButtonFee4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDataType4 = itemView.findViewById(R.id.text_view_data_type1);
            imageButtonFee4 = itemView.findViewById(R.id.imageButtonFeed_type1);
        }
    }
}