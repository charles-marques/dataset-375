package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢��api��������ɾ����api���ؽ���е�data����
 * 
 * @author Trinea 2011-11-14 ����12:06:25
 */
public class QqTIdAndTime implements Serializable {

    private static final long serialVersionUID = -2007975028984227555L;

    /**
     * ����΢�����api����ʾ΢��id
     * ���ڱ�ǩ���api����ʾ��ǩid
     * ����˽�����api����ʾ˽��id
     **/
    private long              id;

    /** �������ʱ�� **/
    private long              timeStamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
