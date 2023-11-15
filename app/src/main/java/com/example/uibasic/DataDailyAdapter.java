package com.example.uibasic;

import static com.example.uibasic.JsonUtils.parseJsonToDailyList;
import static com.example.uibasic.JsonUtils.readJsonFromFile;

import android.app.Activity;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataDailyAdapter extends RecyclerView.Adapter<DataDailyAdapter.ViewHolder> {

    //private List<Daily> dailyList;
    private ArrayList<Combine> combinedList;

    private ArrayList<Daily> dailyList;
    private ArrayList<Eat> eatList;
    private ArrayList<Health> healthList;
    private ArrayList<Type1> type1List;
    private ArrayList<Type2> type2List;

    private String todayDateString;

    //private String itemName ;
    //private Integer itemType;

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
        Combine combinedListfianl = combinedList.get(position);
        holder.textViewDataType1.setText(combinedListfianl.getFirst());
        // 在这里设置其他视图元素的数据，例如按钮的点击事件等

        // 获取今天的日期
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // 将今天的日期转换为字符串，假设日期格式为 "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        todayDateString = dateFormat.format(currentDate);

        //holder.ButtonFeed1.setVisibility(View.GONE);
        // 在這裡處理按鈕點擊事件
        String itemName = combinedListfianl.getFirst();
        Integer itemType = combinedListfianl.getSecond();

        // 根据itemType的值判断是哪种类型的项
        switch (itemType) {
            case 1: // 如果itemType为1，表示daily类型
                for (Daily daily : dailyList) {
                    if (daily.getDaily_name().equals(itemName)) {
                        if (shouldSetButtonDaily(daily)) {
                            // 符合条件才设置按钮可用
                            holder.ButtonFeed1.setEnabled(true);
                        } else {
                            // 不符合条件，按钮不可用
                            holder.ButtonFeed1.setEnabled(false);
                        }
                        break;
                    }
                }
                break;
            case 2: // 类似地处理eat类型
                for (Eat eat : eatList) {
                    if (eat.getEat_name().equals(itemName)) {
                        if (shouldSetButtonEat(eat)) {
                            // 符合条件才设置按钮可用
                            holder.ButtonFeed1.setEnabled(true);
                        } else {
                            // 不符合条件，按钮不可用
                            holder.ButtonFeed1.setEnabled(false);
                        }
                        break;
                    }
                }
                break;
            case 3: // health
                for (Health health : healthList) {
                    if (health.getHealth_name().equals(itemName)) {
                        if (shouldSetButtonHealth(health)) {
                            // 符合条件才设置按钮可用
                            holder.ButtonFeed1.setEnabled(true);
                        } else {
                            // 不符合条件，按钮不可用
                            holder.ButtonFeed1.setEnabled(false);
                        }
                        break;
                    }
                }
                break;
            case 4: // 处理type1类型
                for (Type1 type1 : type1List) {
                    if (type1.getType1_name().equals(itemName)) {
                        if (shouldSetButtonType1(type1)) {
                            // 符合条件才设置按钮可用
                            holder.ButtonFeed1.setEnabled(true);
                            System.out.println(shouldSetButtonType1(type1));
                        } else {
                            // 不符合条件，按钮不可用
                            holder.ButtonFeed1.setEnabled(false);
                            System.out.println("6666666666666666666666666666666666666666");
                        }
                        break;
                    }
                }
                break;
            case 5: // 处理另外一种类型
                for (Type2 type2 : type2List) {
                    if (type2.getType2_name().equals(itemName)) {
                        if (shouldSetButtonType2(type2)) {
                            // 符合条件才设置按钮可用
                            holder.ButtonFeed1.setEnabled(true);
                        } else {
                            // 不符合条件，按钮不可用
                            holder.ButtonFeed1.setEnabled(false);
                        }
                        break;
                    }
                }
                break;
            default:
                // 处理默认情况
                break;
        }

        // 設置按鈕點擊事件
        holder.ButtonFeed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Activity) context).runOnUiThread(new Runnable(){
                    @Override
                    public void run() {

                        // 根据itemType的值判断是哪种类型的项
                        switch (itemType) {
                            case 1: // 如果itemType为1，表示daily类型
                                // 修改daily类型中itemName对应的项的done属性
                                // 在combinedList中找到daily类型的项，然后修改done属性
                                for (Daily daily : dailyList) {
                                    if (daily.getDaily_name().equals(itemName)) {
                                        if (shouldSetButtonDaily(daily)) {
                                            // 符合条件才更新数据
                                            System.out.println(daily.getDaily_done()+"嗨嗨嗨嗨嗨嗨嗨嗨嗨嗨嗨嗨嗨嗨");
                                            daily.setDaily_done(daily.getDaily_done() + 1); // done属性加1
                                            daily.setDaily_color(todayDateString);
                                            System.out.println(daily.getDaily_color()+"哈哈哈哈哈哈哈哈哈哈哈哈"+daily.getDaily_name());

                                        }
                                        break;
                                    }
                                    // 更新完数据后，按钮不再可用
                                    holder.ButtonFeed1.setEnabled(false);
                                }
                                JsonUtils.saveDataToJsonFile(context, "daily_data.json", dailyList);
                                break;
                            case 2: // 类似地处理eat类型
                                // 修改daily类型中itemName对应的项的done属性
                                // 在combinedList中找到daily类型的项，然后修改done属性
                                for (Eat eat : eatList) {
                                    if (eat.getEat_name().equals(itemName)) {
                                        if (shouldSetButtonEat(eat)) {
                                            // 符合条件才更新数据
                                            eat.setEat_done(eat.getEat_done() + 1); // done属性加1
                                            eat.setEat_color(todayDateString);

                                        }
                                        break;
                                    }
                                    // 更新完数据后，按钮不再可用
                                    holder.ButtonFeed1.setEnabled(false);
                                }
                                JsonUtils.saveDataToJsonFile(context, "eat_data.json", eatList);
                                break;
                            case 3: // health
                                // 修改daily类型中itemName对应的项的done属性
                                // 在combinedList中找到daily类型的项，然后修改done属性
                                for (Health health : healthList) {
                                    if (health.getHealth_name().equals(itemName)) {
                                        if (shouldSetButtonHealth(health)) {
                                            // 符合条件才更新数据
                                            health.setHealth_done(health.getHealth_done() + 1); // done属性加1
                                            health.setHealth_color(todayDateString);

                                        }
                                        break;
                                    }
                                    // 更新完数据后，按钮不再可用
                                    holder.ButtonFeed1.setEnabled(false);
                                }
                                JsonUtils.saveDataToJsonFile(context, "health_data.json", healthList);
                                break;
                            case 4: // type1
                                // 修改daily类型中itemName对应的项的done属性
                                // 在combinedList中找到daily类型的项，然后修改done属性
                                for (Type1 type1 : type1List) {
                                    if (type1.getType1_name().equals(itemName)) {
                                        if (shouldSetButtonType1(type1)) {
                                            // 符合条件才更新数据
                                            type1.setType1_done(type1.getType1_done() + 1); // done属性加1
                                            type1.setType1_color(todayDateString);

                                        }
                                        break;
                                    }
                                    // 更新完数据后，按钮不再可用
                                    holder.ButtonFeed1.setEnabled(false);
                                }
                                JsonUtils.saveDataToJsonFile(context, "type1_data.json", type1List);
                                break;
                            case 5: // 处理另外一种类型
                                // 修改daily类型中itemName对应的项的done属性
                                // 在combinedList中找到daily类型的项，然后修改done属性
                                for (Type2 type2 : type2List) {
                                    if (type2.getType2_name().equals(itemName)) {
                                        if (shouldSetButtonType2(type2)) {
                                            // 符合条件才更新数据
                                            type2.setType2_done(type2.getType2_done() + 1); // done属性加1
                                            type2.setType2_color(todayDateString);

                                        }
                                        break;
                                    }
                                    // 更新完数据后，按钮不再可用
                                    holder.ButtonFeed1.setEnabled(false);
                                }
                                JsonUtils.saveDataToJsonFile(context, "type2_data.json", type2List);
                                break;
                            default:
                                // 处理默认情况
                                break;
                        }

                    }

                });




                // 刷新RecyclerView，使修改后的数据生效
                notifyDataSetChanged();

                // 讀取 JSON 檔案中的 daily 資料
                String jsonDailyData = readJsonFromFile(context,"daily_data.json");
                // 解析 JSON 字串為 ArrayList<Daily> 物件
                ArrayList<Daily> dailyList777 = parseJsonToDailyList(jsonDailyData);
                //System.out.println(dailyList777.get(0).getDaily_name()+"oooooooooooooooo"+dailyList777.get(0).getDaily_done());


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

    // 添加檢查是否需要設置按鈕的方法
    private boolean shouldSetButtonDaily(Daily daily) {
        // 取得 daily 的 color 字符串
        try {
            // 取得 daily 的 color 字符串
            String dailyColor = daily.getDaily_color();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dailyDate = dateFormat.parse(dailyColor);
            Date todayDate = dateFormat.parse(todayDateString);

            // 检查 dailyColor 是否在 todayDateString 之前
            if (dailyDate.before(todayDate) && daily.getDaily_done() < daily.getDaily_goal()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 解析日期出错，你可以根据实际情况处理，这里返回 false
            return false;
        }
    }

    //eat
    private boolean shouldSetButtonEat(Eat eat) {
        // 取得 Eat 的 color 字符串
        try {
            // 取得 Eat 的 color 字符串
            String eatColor = eat.getEat_color();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date eatDate = dateFormat.parse(eatColor);
            Date todayDate = dateFormat.parse(todayDateString);

            // 检查 dailyColor 是否在 todayDateString 之前
            if (eatDate.before(todayDate) && eat.getEat_done() < eat.getEat_goal()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 解析日期出错，你可以根据实际情况处理，这里返回 false
            return false;
        }
    }

    //health
    private boolean shouldSetButtonHealth(Health health) {
        // 取得 Health 的 color 字符串
        try {
            // 取得 Health 的 color 字符串
            String healthColor = health.getHealth_color();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date healthDate = dateFormat.parse(healthColor);
            Date todayDate = dateFormat.parse(todayDateString);

            // 检查 healthColor 是否在 todayDateString 之前
            if (healthDate.before(todayDate) && health.getHealth_done() < health.getHealth_goal()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 解析日期出错，你可以根据实际情况处理，这里返回 false
            return false;
        }
    }

    //type1
    private boolean shouldSetButtonType1(Type1 type1) {
        // 取得 Type1 的 color 字符串
        try {
            // 取得 Health 的 color 字符串
            String type1Color = type1.getType1_color();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = dateFormat.parse(todayDateString);
            Date deadlineDate = dateFormat.parse(type1.getType1_deadline());

            System.out.println(todayDate);
            System.out.println(deadlineDate);

            // 检查 type1Date 是否在 todayDateString 之前
            if (todayDate.before(deadlineDate) && type1.getType1_done() < 2) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 解析日期出错，你可以根据实际情况处理，这里返回 false
            return false;
        }
    }

    //type2
    private boolean shouldSetButtonType2(Type2 type2) {
        // 取得 Type2 的 color 字符串
        try {
            // 取得 Type2 的 color 字符串
            String type2Color = type2.getDaily_color();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date type2Date = dateFormat.parse(type2Color);
            Date todayDate = dateFormat.parse(todayDateString);

            // 检查 healthColor 是否在 todayDateString 之前
            if (type2Date.before(todayDate) && type2.getType2_done() < type2.getType2_goal()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            // 解析日期出错，你可以根据实际情况处理，这里返回 false
            return false;
        }
    }

}
