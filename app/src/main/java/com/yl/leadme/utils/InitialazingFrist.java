package com.yl.leadme.utils;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.yl.leadme.provider.CustomUserProvider;

import cn.leancloud.chatkit.LCChatKit;

/**
 * =================================
 * <p/>
 * Created by yl on 2016/10/30.
 * <p/>
 * 描述:此方法通过在androidManifest中动态加载
 */

public class InitialazingFrist extends Application {

    private final String APP_ID = "kB7LKPIvDopV1xi6dndGB0lI-gzGzoHsz";
    private final String APP_KEY = "a62FBLhvN9HiAfUXFYmuMyrz";

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(getApplicationContext(),APP_ID,APP_KEY);
        AVOSCloud.useAVCloudCN();
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(getApplicationContext(), APP_ID, APP_KEY);
        AVIMClient.setOfflineMessagePush(true);//开启未读消息

    }
}
