package com.yl.leadme.utils;

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










}
