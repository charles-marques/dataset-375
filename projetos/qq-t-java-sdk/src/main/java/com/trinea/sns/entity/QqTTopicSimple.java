package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢���������Ϣ
 * 
 * @author Trinea 2011-11-7 ����11:50:09
 */
public class QqTTopicSimple implements Serializable {

    private static final long serialVersionUID = -3327396691040485066L;

    /** ����ؼ��� **/
    private String            name;
    /** 0�û���ӿ������� 1�������ӿ������� **/
    private int               type;
    /** �����ؼ��� **/
    private String            keyWords;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}
