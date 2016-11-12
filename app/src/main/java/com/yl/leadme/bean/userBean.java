package com.yl.leadme.bean;

/**
 * =================================
 * <p>
 * Created by yl on 2016/11/12.
 * <p>
 * 描述:
 */


public class userBean {

    /**
     * emailVerified : false
     * mobilePhoneVerified : false
     * username : 1235
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
