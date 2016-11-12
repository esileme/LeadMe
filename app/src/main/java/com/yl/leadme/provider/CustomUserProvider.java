package com.yl.leadme.provider;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.google.gson.Gson;
import com.yl.leadme.bean.UserData;
import com.yl.leadme.bean.userBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.LCChatProfileProvider;
import cn.leancloud.chatkit.LCChatProfilesCallBack;

/**
 * =================================
 * <p>
 * Created by yl on 2016/10/30.
 * <p>
 * 描述:
 */


public class CustomUserProvider implements LCChatProfileProvider {

    private UserData userData;

    private static CustomUserProvider customUserProvider;
    private int size;


    public synchronized static CustomUserProvider getInstance() {
        if (null == customUserProvider) {
            customUserProvider = new CustomUserProvider();
        }
        return customUserProvider;
    }

    private CustomUserProvider() {
        //userCount();
        userCounts();
        //addUsersFromServer();
    }

    private static List<LCChatKitUser> partUsers = new ArrayList<LCChatKitUser>();


    /*// 此数据均为模拟数据，仅供参考
    static {
        partUsers.add(new LCChatKitUser("Tom", "Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
        partUsers.add(new LCChatKitUser("Jerry", "Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
        partUsers.add(new LCChatKitUser("Harry", "Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
        partUsers.add(new LCChatKitUser("William", "William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
        partUsers.add(new LCChatKitUser("Bob", "Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
        partUsers.add(new LCChatKitUser("11111", "11111", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("258080", "258080", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
    }*/

    /**
     * 添加服务器中用户数据
     */

    public void addUsersFromServer() {
        for (int i = 0; i < size; i++) {
            //String id = userData.getServerData().getUsername();
            partUsers.add(new LCChatKitUser(i+"qq",i+"q", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        }
        partUsers.add(new LCChatKitUser("25800", "25800", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("1235", "1235", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("12345", "12345", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("1230", "1230", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("11111", "11111", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("258080", "258080", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));
        partUsers.add(new LCChatKitUser("静好", "静好", "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));


    }

    @Override
    public void fetchProfiles(List<String> list, LCChatProfilesCallBack callBack) {
        List<LCChatKitUser> userList = new ArrayList<LCChatKitUser>();
        for (String userId : list) {
            for (LCChatKitUser user : partUsers) {
                if (user.getUserId().equals(userId)) {
                    userList.add(user);
                    break;
                }
            }
        }
        callBack.done(userList, null);
    }

    public List<LCChatKitUser> getAllUsers() {
        return partUsers;
    }

    /**
     * 查询数据库中所有用户的数量
     */
    //int count1;//用户数量

    /*private void userCount() {
        AVQuery query = AVQuery.getQuery("_User");
        query.countInBackground(new CountCallback() {
            @Override
            public void done(int i, AVException e) {
                if (e == null) {
                    System.out.println("查詢成功" + i);
                    count1 = i;
                } else {
                    System.out.println("查询失败");
                    e.printStackTrace();
                }
            }
        });
    }*/


    /**
     * 获取用户的名字
     */
    private void userCounts() {
        AVQuery query = AVQuery.getQuery("_User");
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List list, AVException e) {

            }

            @Override
            protected void internalDone0(Object object, AVException e) {
                if (e == null) {
                    String data = object.toString();

                    System.out.println("查询成功2");
                    System.out.println(data + "---------");

                    parseData(data);

                } else {
                    e.printStackTrace();
                    System.out.println("查询失败2");
                }

            }
        });
    }


    private void parseData(String str) {
        try {
            JSONArray jsonArray=new JSONArray(str);
            size = jsonArray.length();
            System.out.println(size +"数组的长度");
            for (int i = 0; i< size; i++) {
                JSONObject jsonObj= jsonArray.getJSONObject(i);
                System.out.println(jsonObj+"2222222");

                String jsonObject1=  jsonObj.getString("serverData");
                System.out.println(jsonObject1+"22222223");

                /*String jsonObject2=jsonObject1.getString("username");
                System.out.println(jsonObject2+"22222224");*/
                Gson gson= new Gson();
                userBean username=gson.fromJson(jsonObject1, userBean.class);
                System.out.println(username.getUsername()+"2222222");

                partUsers.add(new LCChatKitUser(username.getUsername(),username.getUsername(), "http://i1.wp.com/leancloud.cn/images/static/default-avatar.png"));

            }
            //addUsersFromServer();


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
