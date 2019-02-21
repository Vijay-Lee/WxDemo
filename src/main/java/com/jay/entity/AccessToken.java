package com.jay.entity;

/**
 * Created by 李文杰 on 2018/11/26
 * 功能：
 */
public class AccessToken {
    private String accessToken;
    private long expireTime;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public AccessToken(String accessToken, String expireIn) {
        super();
        this.accessToken = accessToken;
        expireTime = System.currentTimeMillis()+Integer.parseInt(expireIn)*1000;
    }

    //判断token是否过期
    public boolean isExpired(){
        return System.currentTimeMillis()>expireTime;
    }
}
