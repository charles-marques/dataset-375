package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢����Ƶ��Ϣ
 * 
 * @author Trinea 2011-11-2 ����11:10:47
 */
public class QqTVideoInfo implements Serializable {

    private static final long serialVersionUID = 1867062995454017877L;

    /** ��ƵСͼƬ��ַ **/
    private String            miniPicUrl;
    /** ��������ַ **/
    private String            playerUrl;
    /** ��Ƶʵ�ʵ�ַ **/
    private String            realUrl;
    /** ��Ƶ��url **/
    private String            shortUrl;
    /** ��Ƶ���� **/
    private String            title;

    public String getMiniPicUrl() {
        return miniPicUrl;
    }

    public void setMiniPicUrl(String miniPicUrl) {
        this.miniPicUrl = miniPicUrl;
    }

    public String getPlayerUrl() {
        return playerUrl;
    }

    public void setPlayerUrl(String playerUrl) {
        this.playerUrl = playerUrl;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
