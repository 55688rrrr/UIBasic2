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

public class DataEatAdapter extends RecyclerView.Adapter<DataEatAdapter.ViewHolder> {

    private List<Eat> eatList;

    public DataEatAdapter(List<Eat> eatList) {
        this.eatList = eatList;
    }

    @NonNull
    @Override
    public DataEatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_eat, parent, false);
        return new DataEatAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataEatAdapter.ViewHolder holder, int position) {
        Eat dataeatList = eatList.get(position);
        holder.textViewDataType2.setText(dataeatList.getEat_name()+"  "+dataeatList.getEat_done());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等
    }

    @Override
    public int getItemCount() {
        return eatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDataType2;
        ImageButton imageButtonFeed2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDataType2 = itemView.findViewById(R.id.text_view_data_eat);
            imageButtonFeed2 = itemView.findViewById(R.id.imageButtonFeed_eat);
        }
    }
}
