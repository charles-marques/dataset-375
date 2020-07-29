package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.java.common.StringUtils;

/**
 * ��Ѷ΢�� ǩ��ʵ����
 * 
 * @author Trinea 2011-9-22 ����12:42:23
 */
public class QqTSign implements Serializable {

    private static final long   serialVersionUID = 5163404287174100668L;

    /** ��url **/
    private String              baseUrl;
    /** url�ύ��ʽ **/
    private String              httpMethod;
    /** Ӧ������ **/
    private String              appSecret;
    /** token ���� ����Ϊ�� **/
    private String              tokenSecret;
    /** ����map **/
    private Map<String, String> parasMap;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getTokenSecret() {
        return tokenSecret;
    }

    public void setTokenSecret(String tokenSecret) {
        this.tokenSecret = tokenSecret;
    }

    public Map<String, String> getParasMap() {
        return parasMap;
    }

    public void setParasMap(Map<String, String> parasMap) {
        this.parasMap = parasMap;
    }

    /**
     * �����Ƿ�Ϸ�
     * 
     * @return
     */
    public boolean isValid() {
        return !(StringUtils.isEmpty(baseUrl) || StringUtils.isEmpty(httpMethod) || StringUtils.isEmpty(appSecret) || MapUtils.isEmpty(parasMap));
    }
}
