package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ��Ѷ΢��api��ʱ���ߡ��û��б��api���ؽ���е�data����
 * 
 * @author Trinea 2011-11-13 ����11:34:35
 */
public class QqTListData implements Serializable {

    private static final long   serialVersionUID = -7545241973832906747L;

    /** ������ʱ������������ڷ�ҳ **/
    private long                timeStamp;

    /**
     * �Ƿ������ݿ�����ȡ��0��ʾ����΢������ȡ ��1����ȡ���
     * �Ի���ʱ����api 2��ʾ�������Ϸ� 1 ��ʾ�������·���0��ʾ���߶����Է� 3��ʾ���߶����ܷ���
     **/
    private int                 hasNext;

    /**
     * ��Ϣ�б�
     * ��ʱ����api��ʾ΢���б�
     * ���û���Ϣapi��ʾ�û��б�
     **/
    private List<?>             info;

    /** ʱ����api ��¼����ʼλ�ã���һ����������0��������������ϴη��ص�pos�� **/
    private long                position;

    /** ������ϵ���б�api ��ʼλ�ã���һ����������0��������������ϴη��ص�nextstartpos�� **/
    private long                positionForMutualList;

    /** ʱ����api ���м�¼������ **/
    private long                totalNumber;

    /** ����ʱ����api ��ҳ��ʶ����һҳ ��գ�������ҳ�������ϴη��ص� pageinfo�� **/
    private String              pageInfo;

    /** ����ʱ����api �Ƿ��ղ� ��1�� 0�� **/
    private boolean             isColloct;

    /** ����ʱ����api ����id **/
    private long                topicId;

    /** �ղص�΢���б� api ���·�ҳ��ʼʱ�䣨��һҳ ʱ��0��������ҳ������һ�����󷵻ص�nexttimeʱ�䣩 **/
    private long                nextTime;

    /** �ղص�΢���б� api ���Ϸ�ҳ��ʼʱ�䣨��һҳ ʱ��0��������ҳ������һ�����󷵻ص�prevTimeʱ�䣩 **/
    private long                prevTime;

    /** ��������΢��api����ʾ�������ѵ�ʱ�� **/
    private long                costTime;

    /** ʱ����api���ص������漰�����û����ʺ����ǳ�ӳ�� **/
    private Map<String, String> relatedUser;

    /** �Ƿ�����Ϣ������ȡ **/
    public boolean hastNext() {
        return this.hasNext == 0;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getHasNext() {
        return hasNext;
    }

    public void setHasNext(int hasnext) {
        this.hasNext = hasnext;
    }

    public List<?> getInfo() {
        return info;
    }

    public void setInfo(List<?> info) {
        this.info = info;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public void setPositionForMutualList(long positionForMutualList) {
        this.positionForMutualList = positionForMutualList;
    }

    public long getPositionForMutualList() {
        return positionForMutualList;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(long totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(String pageInfo) {
        this.pageInfo = pageInfo;
    }

    public boolean isColloct() {
        return isColloct;
    }

    public void setColloct(boolean isColloct) {
        this.isColloct = isColloct;
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getNextTime() {
        return nextTime;
    }

    public void setNextTime(long nextTime) {
        this.nextTime = nextTime;
    }

    public long getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(long prevTime) {
        this.prevTime = prevTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setRelatedUser(Map<String, String> relatedUser) {
        this.relatedUser = relatedUser;
    }

    public Map<String, String> getRelatedUser() {
        return relatedUser;
    }
}
