package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢�����ݸ���������Ϣ
 * 
 * @author Trinea 2011-10-8 ����11:45:09
 */
public class QqTUpdateNumInfo implements Serializable {

    private static final long serialVersionUID = -1884964385784308153L;

    /** ��ҳ������ **/
    private int               home;
    /** ˽�Ÿ����� **/
    private int               privateMessage;
    /** ���ڸ����� **/
    private int               fans;
    /** �ἰ�ҵ� **/
    private int               mentions;
    /** ��ҳ�㲥��ԭ���������� **/
    private int               create;

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public int getPrivateMessage() {
        return privateMessage;
    }

    public void setPrivateMessage(int privateMessage) {
        this.privateMessage = privateMessage;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getMentions() {
        return mentions;
    }

    public void setMentions(int mentions) {
        this.mentions = mentions;
    }

    public int getCreate() {
        return create;
    }

    public void setCreate(int create) {
        this.create = create;
    }
}
