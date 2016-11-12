package com.yl.leadme.bean;

/**
 * =================================
 * <p>
 * Created by yl on 2016/11/12.
 * <p>
 * 描述:[
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-10-30T10:33:00.160Z",
 "objectId": "5815cc5c2e958a00549c5828",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "静好"
 },
 "updatedAt": "2016-10-30T10:33:00.160Z"
 },
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-10-30T14:44:20.752Z",
 "objectId": "581607440ce46300584cf3b5",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "258080"
 },
 "updatedAt": "2016-10-30T14:44:20.752Z"
 },
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-10-31T04:26:48.040Z",
 "objectId": "5816c808bf22ec006897507b",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "11111"
 },
 "updatedAt": "2016-10-31T04:26:48.040Z"
 },
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-10-31T12:33:31.416Z",
 "objectId": "58173a1b0ce463005853cef4",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "1230"
 },
 "updatedAt": "2016-10-31T12:33:31.416Z"
 },
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-10-31T15:39:02.446Z",
 "objectId": "581765962f301e005ce80044",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "12345"
 },
 "updatedAt": "2016-10-31T15:39:02.446Z"
 },
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-11-11T12:56:36.324Z",
 "objectId": "5825c0042e958a1299349c03",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "1235"
 },
 "updatedAt": "2016-11-11T12:56:36.324Z"
 },
 {
 "@type": "com.avos.avoscloud.AVUser",
 "className": "_User",
 "createdAt": "2016-11-11T13:18:29.015Z",
 "objectId": "5825c52567f3560058d21906",
 "serverData": {
 "emailVerified": false,
 "mobilePhoneVerified": false,
 "username": "25800"
 },
 "updatedAt": "2016-11-11T13:18:29.015Z"
 }
 ]
 */


public class UserData {


    /**
     * type : com.avos.avoscloud.AVUser
     * className : _User
     * createdAt : 2016-10-30T10:33:00.160Z
     * objectId : 5815cc5c2e958a00549c5828
     * serverData : {"emailVerified":false,"mobilePhoneVerified":false,"username":"静好"}
     * updatedAt : 2016-10-30T10:33:00.160Z
     */

    private String type;
    private String className;
    private String createdAt;
    private String objectId;
    private ServerDataBean serverData;
    private String updatedAt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public ServerDataBean getServerData() {
        return serverData;
    }

    public void setServerData(ServerDataBean serverData) {
        this.serverData = serverData;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class ServerDataBean {
        /**
         * emailVerified : false
         * mobilePhoneVerified : false
         * username : 静好
         */

        private boolean emailVerified;
        private boolean mobilePhoneVerified;
        private String username;

        public boolean isEmailVerified() {
            return emailVerified;
        }

        public void setEmailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
        }

        public boolean isMobilePhoneVerified() {
            return mobilePhoneVerified;
        }

        public void setMobilePhoneVerified(boolean mobilePhoneVerified) {
            this.mobilePhoneVerified = mobilePhoneVerified;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
