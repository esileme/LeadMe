package com.yl.leadme.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * =================================
 * <p>
 * Created by yl on 2016/10/30.
 * <p>
 * 描述:我的工具类
 */


public class MyUtils {
    /**
     * 判断密码是否长度可用
     * @param password
     * @return
     */
    public static boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * 图片以流的方式上传
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }



    /**
     * 把指定AutoCompleteTextView中内容保存到sharedPreference中指定的字符段
     *
     * @param field 保存在sharedPreference中的字段名
     * @param auto  要操作的AutoCompleteTextView
     */
    public static void saveHistory(String field, AutoCompleteTextView auto,Activity activity) {
        String text = auto.getText().toString();
        SharedPreferences sp = activity.getSharedPreferences("network_url", 0);
        String longhistory = sp.getString(field, "nothing");
        if (!longhistory.contains(text + ",")) {
            StringBuilder sb = new StringBuilder(longhistory);
            sb.insert(0, text + ",");
            sp.edit().putString("history", sb.toString()).commit();
        }


    }


    /**
     * 初始化AutoCompleteTextView，最多显示5项提示，使
     * AutoCompleteTextView在一开始获得焦点时自动提示
     *
     * @param field 保存在sharedPreference中的字段名
     * @param auto  要操作的AutoCompleteTextView
     */
    public static void initAutoComplete(String field, AutoCompleteTextView auto,Activity activity) {
        SharedPreferences sp = activity.getSharedPreferences("network_url", 0);
        String longhistory = sp.getString("history", "nothing");
        String[] hisArrays = longhistory.split(",");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity,
                android.R.layout.simple_dropdown_item_1line, hisArrays);
        //只保留最近的50条的记录
        if (hisArrays.length > 50) {
            String[] newArrays = new String[50];
            System.arraycopy(hisArrays, 0, newArrays, 0, 50);
            adapter = new ArrayAdapter<String>(activity,
                    android.R.layout.simple_dropdown_item_1line, newArrays);
        }
        auto.setAdapter(adapter);
        auto.setDropDownHeight(350);
        auto.setThreshold(1);
        auto.setCompletionHint("最近的5条记录");
        auto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                AutoCompleteTextView view = (AutoCompleteTextView) v;
                if (hasFocus) {
                    view.showDropDown();
                }
            }
        });
    }







}
