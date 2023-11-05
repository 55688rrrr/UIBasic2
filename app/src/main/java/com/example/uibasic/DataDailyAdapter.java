package com.example.uibasic;

import static com.example.uibasic.JsonUtils.parseJsonToDailyList;
import static com.example.uibasic.JsonUtils.readJsonFromFile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class DataDailyAdapter extends RecyclerView.Adapter<DataDailyAdapter.ViewHolder> {

    //private List<Daily> dailyList;
    private ArrayList<Combine> combinedList;

    private ArrayList<Daily> dailyList;
    private ArrayList<Eat> eatList;
    private ArrayList<Health> healthList;
    private ArrayList<Type1> type1List;
    private ArrayList<Type2> type2List;

    private Context context; // 声明一个Context变量

    public DataDailyAdapter(Context context, ArrayList<Combine> combinedList, ArrayList<Daily> dailyList, ArrayList<Eat> eatList, ArrayList<Health> healthList
                            , ArrayList<Type1> type1List, ArrayList<Type2> type2List) {
        this.combinedList = combinedList;

        this.dailyList = dailyList;
        this.eatList = eatList;
        this.healthList = healthList;
        this.type1List = type1List;
        this.type2List = type2List;

        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data_daily, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Combine datacombinedList = combinedList.get(position);
        holder.textViewDataType1.setText(datacombinedList.getFirst());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等

        // 設置按鈕點擊事件
        holder.ButtonFeed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在這裡處理按鈕點擊事件
                String itemName = datacombinedList.getFirst();
                Integer itemType = datacombinedList.getSecond();

                // 根据itemType的值判断是哪种类型的项
                switch (itemType) {
                    case 1: // 如果itemType为1，表示daily类型
                        // 修改daily类型中itemName对应的项的done属性
                        // 在combinedList中找到daily类型的项，然后修改done属性
                        for (Daily daily : dailyList) {
                            if (daily.getDaily_name().equals(itemName)) {
                                System.out.println(daily.getDaily_done());
                                daily.setDaily_done(daily.getDaily_done() + 1); // done属性加1
                                System.out.println(daily.getDaily_done());
                                break;
                            }
                        }
                        JsonUtils.saveDataToJsonFile(context, "daily_data.json", dailyList);
                        break;
                    case 2: // 类似地处理eat类型
                        // 处理eat类型中itemName对应的项的done属性
                        for (Eat eat : eatList) {
                            if (eat.getEat_name().equals(itemName)) {
                                eat.setEat_done(eat.getEat_done() + 1); // done属性加1
                                break;
                            }
                        }
                        JsonUtils.saveDataToJsonFile(context, "eat_data.json", eatList);
                        break;
                    case 3: // 处理type1类型
                        // 处理type1类型中itemName对应的项的done属性
                        for (Health health : healthList) {
                            if (health.getHealth_name().equals(itemName)) {
                                health.setHealth_done(health.getHealth_done() + 1); // done属性加1
                                break;
                            }
                        }
                        JsonUtils.saveDataToJsonFile(context, "health_data.json", healthList);
                        break;
                    case 4: // 处理type2类型
                        // 处理type2类型中itemName对应的项的done属性
                        for (Type1 type1 : type1List) {
                            if (type1.getType1_name().equals(itemName)) {
                                type1.setType1_done(type1.getType1_done() + 1); // done属性加1
                                break;
                            }
                        }
                        JsonUtils.saveDataToJsonFile(context, "type1_data.json", type1List);
                        break;
                    case 5: // 处理另外一种类型
                        // 处理另外一种类型中itemName对应的项的done属性
                        for (Type2 type2 : type2List) {
                            if (type2.getType2_name().equals(itemName)) {
                                type2.setType2_done(type2.getType2_done() + 1); // done属性加1
                                break;
                            }
                        }
                        JsonUtils.saveDataToJsonFile(context, "type2_data.json", type2List);
                        break;
                    default:
                        // 处理默认情况
                        break;
                }


                // 刷新RecyclerView，使修改后的数据生效
                notifyDataSetChanged();

                // 讀取 JSON 檔案中的 daily 資料
                String jsonDailyData = readJsonFromFile(context,"daily_data.json");
                // 解析 JSON 字串為 ArrayList<Daily> 物件
                ArrayList<Daily> dailyList777 = parseJsonToDailyList(jsonDailyData);
                System.out.println(dailyList777.get(0).getDaily_name()+"oooooooooooooooo"+dailyList777.get(0).getDaily_done());


            }
        });
    }

    @Override
    public int getItemCount() {
        return combinedList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDataType1;
        Button ButtonFeed1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDataType1 = itemView.findViewById(R.id.text_view_data_daily);
            ButtonFeed1 = itemView.findViewById(R.id.ButtonFeed_daily);
        }
    }

}
