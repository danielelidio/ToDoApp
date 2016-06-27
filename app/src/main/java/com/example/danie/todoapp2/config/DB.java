package com.example.danie.todoapp2.config;

import android.content.Context;

import com.example.danie.todoapp2.models.BuyingItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by danie on 27/06/2016.
 */
public class DB {
    public static String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }

    public static JSONArray loadDB(Context context) {
        JSONArray json = null;
        try {
            FileInputStream fis = context.openFileInput(Config.dbFileName);
            String text = DB.readTextFile(fis);
            if (text.compareTo("") != 0)
                json = new JSONArray(DB.readTextFile(fis));
            else
                json = new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static void saveDB(Context context, JSONArray json) {
        File file = new File(Config.dbFileName);

        if (!file.exists()) {
            String string = json.toString();
            FileOutputStream outputStream;

            try {
                outputStream = context.openFileOutput(Config.dbFileName, Context.MODE_PRIVATE);
                outputStream.write(string.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addItem(Context context, BuyingItem buyingItem) {
        try {
            JSONArray json = DB.loadDB(context);
            json.put(json.length(), buyingItem.toJSON());
            DB.saveDB(context, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void removeItem(Context context, int position) {
        JSONArray json = DB.loadDB(context);

        int len = json.length();
        final ArrayList<JSONObject> list = new ArrayList<JSONObject>(len);
        for (int i = 0; i < len; i++) {
            final JSONObject obj = json.optJSONObject(i);
            if (obj != null) {
                list.add(obj);
            }
        }

        list.remove(position);

        final JSONArray ja = new JSONArray();
        for (final JSONObject obj : list) {
            ja.put(obj);
        }

        DB.saveDB(context, ja);
    }
}
