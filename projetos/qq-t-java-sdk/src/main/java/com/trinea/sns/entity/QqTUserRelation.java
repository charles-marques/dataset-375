package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢���û����Լ��Ĺ�ϵ
 * 
 * @author Trinea 2011-11-4 ����11:46:04
 */
public class QqTUserRelation implements Serializable {

    private static final long serialVersionUID = 7154885505952051486L;

    /** �û��ʻ��� **/
    private String            userName;
    /** �Ƿ��ע���Լ� **/
    private boolean           isFan;
    /** �Ƿ��Լ���ע **/
    private boolean           isInterested;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isFan() {
        return isFan;
    }

    public void setFan(boolean isFan) {
        this.isFan = isFan;
    }

    public boolean isInterested() {
        return isInterested;
    }

    public void setInterested(boolean isInterested) {
        this.isInterested = isInterested;
    }
}
