package com.example.danie.todoapp2.config;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by danie on 27/06/2016.
 */
public class Config {
    public static String dbFileName = "db.json";

    public static void tryInitializeDBSampleData(Context context) {
        File file = context.getFileStreamPath(Config.dbFileName);

        if (file == null || !file.exists()) {
            String string = "[\n" +
                    "  {\n" +
                    "    \"name\": \"Produto 1\",\n" +
                    "    \"amount\": \"1.5\",\n" +
                    "    \"greatness\": \"KILOGRAM\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Produto 2\",\n" +
                    "    \"amount\": \"5.5\",\n" +
                    "    \"greatness\": \"LITERS\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Produto 3\",\n" +
                    "    \"amount\": \"2\",\n" +
                    "    \"greatness\": \"POT\"\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"name\": \"Produto 4\",\n" +
                    "    \"amount\": \"3\",\n" +
                    "    \"greatness\": \"PACKAGE\"\n" +
                    "  }\n" +
                    "]";
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
}
