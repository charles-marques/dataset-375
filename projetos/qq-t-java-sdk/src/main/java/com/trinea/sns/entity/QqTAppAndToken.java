package com.trinea.sns.entity;

import java.io.Serializable;

import com.trinea.java.common.StringUtils;

/**
 * ��Ѷ΢�� Ӧ�ú�tokenʵ���࣬��Ҫ����api��
 * 
 * @author Trinea 2011-9-25 ����11:41:38
 */
public class QqTAppAndToken implements Serializable {

    private static final long serialVersionUID = 5951645915037611059L;

    /** Ӧ��key **/
    private String            appKey;
    /** Ӧ������ **/
    private String            appSecret;
    /** access token **/
    private String            accessToken;
    /** token ���� ����Ϊ�� **/
    private String            tokenSecret;

    /**
     * �����Ƿ�Ϸ�
     * 
     * @return
     */
    public boolean isValid() {
        return !(StringUtils.isEmpty(appKey) || StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(accessToken) || StringUtils.isEmpty(tokenSecret));
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }
}
