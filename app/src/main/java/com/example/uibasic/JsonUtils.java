package com.example.uibasic;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonUtils {

    public static void saveDataToJsonFile(Context context, String fileName, Object data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);

        try (FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            bufferedWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readJsonFromFile(Context context, String fileName) {
        String json = "";
        try (InputStream inputStream = context.openFileInput(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static ArrayList<Daily> parseJsonToDailyList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Daily>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public static ArrayList<Eat> parseJsonToEatList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Eat>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public static ArrayList<Health> parseJsonToHealthList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Health>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public static ArrayList<Type1> parseJsonToType1List(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Type1>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public static ArrayList<Type2> parseJsonToType2List(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Type2>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    // 刪除指定檔案
    public static void deleteFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    // 刪除所有檔案
    public static void deleteAllFiles(Context context) {
        File filesDir = context.getFilesDir();
        File[] files = filesDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
}
