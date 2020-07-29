package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢��ʱ�������api�Ĳ���������һ��api��һ����ʹ�õ����в���
 * <ul>
 * <li>Ϊ�˷����ȡ��д�룬�ܶ�long��int���Ͷ���String��ʾ</li>
 * </ul>
 * 
 * @author gengxin.wugx 2011-9-29 ����20:02:43
 */
public class QqTTimelinePara implements Serializable {

    private static final long serialVersionUID           = 4318704342139880029L;

    /** �������ݵĸ�ʽ **/
    private String            format;

    /** ��ҳ��ʶ��0����һҳ��1�����·�ҳ��2���Ϸ�ҳ�� **/
    private int               pageFlag;

    /** ��ҳ��ʼʱ�䣨��һҳ ʱ��0��������ҳ������һ�����󷵻ص����һ����¼ʱ�䣩������Ϊ��λ **/
    private long              pageTime;

    /** ��һҳ ʱ��0,�������·�ҳ������һ�����󷵻ص����һ����¼id����ҳ�� **/
    private long              lastId;

    /** ��Ҫ��ȡ���û��û�����˽�Ž����� **/
    private String            userName;

    /** ��Ҫ��ȡ���û�open id **/
    private String            userOpenId;

    /** ÿ�������¼��������1-70���� **/
    private int               pageReqNum;

    /** ��ȡ����, 0x1 ԭ������ 0x2 ת�� 0x8 �ظ� 0x10 �ջ� 0x20 �ἰ 0x40 ���� , ������ȡ���������|��(0x1|0x2) �õ�3��type=3����,�����ʾ��ȡ�������� **/
    private int               statusType;

    /** ���ݹ��� �����ʾ�������� 1-���ı� 2-������ 4ͼƬ 8-����Ƶ 0x10-����Ƶ **/
    private int               contentType;

    /** Ȩ�ޱ�ʶ 1 ��ʾֻ��ʾ�ҷ���� **/
    private int               accessLevel;

    /** ��¼����ʼλ�ã���һ����������0��������������ϴη��ص�pos�� **/
    private long              position;

    /** ��ʶ0 ת���б�1�����б� 2 ������ת���б� **/
    private int               repostOrCommentFlag;

    /** ת�����߻ظ������id��Դ΢��id�� **/
    private long              rootId;

    /** 1-100����0,�������·�ҳ������һ�����󷵻ص����һ����¼id����ҳ�� **/
    private long              twitterId;

    /** ��ȡ�ղص�΢���б�ʱʹ�ã����·�ҳ��ʼʱ�䣨��һҳ ʱ��0��������ҳ������һ�����󷵻ص�nexttimeʱ�䣩 **/
    private long              nextTime;

    /** ��ȡ�ղص�΢���б�ʱʹ�ã����·� **/
    private long              prevTime;

    /** Ĭ��ֵ **/
    private static String     defaultFormat              = "";
    private static int        defaultPageFlag            = -1;
    private static long       defaultPageTime            = -1;
    private static long       defaultLastId              = -1;
    private static String     defaultUsername            = "";
    private static String     defaultUserOpenId          = "";
    private static int        defaultPageReqNum          = -1;
    private static int        defaultStatusType          = -1;
    private static int        defaultContentType         = -1;
    private static long       defaultPosiition           = -1;
    private static int        defaultAccessLevel         = -1;
    private static int        defaultRepostOrCommentFlag = -1;
    private static long       defaultRootId              = -1;
    private static long       defaultTwitterId           = -1;
    private static long       defaultNextTime            = -1;
    private static long       defaultPrevTime            = -1;

    public QqTTimelinePara(){
        super();

        this.format = defaultFormat;
        this.pageFlag = defaultPageFlag;
        this.pageTime = defaultPageTime;
        this.lastId = defaultLastId;
        this.userName = defaultUsername;
        this.userOpenId = defaultUserOpenId;
        this.pageReqNum = defaultPageReqNum;
        this.statusType = defaultStatusType;
        this.contentType = defaultContentType;
        this.accessLevel = defaultAccessLevel;
        this.position = defaultPosiition;
        this.repostOrCommentFlag = defaultRepostOrCommentFlag;
        this.rootId = defaultRootId;
        this.twitterId = defaultTwitterId;
        this.nextTime = defaultNextTime;
        this.prevTime = defaultPrevTime;
    }

    /**
     * ��ҳ��ת��ΪpageFlag��Ĭ��ҳ������1��ʾ���·�ҳ
     * 
     * @param page ҳ��
     * @return
     */
    public int transformPageToPageFlag(int page) {
        return transformPageToPageFlag(page, true);
    }

    /**
     * ��ҳ��ת��ΪpageFlag
     * 
     * @param page ҳ��
     * @param pageDown �Ƿ����·�ҳ
     * @return
     *         <ul>
     *         <li>page С�ڵ���0��������ҳ��־</li>
     *         <li>��page����0��pageDownΪtrue�� page����1��������ҳ��־�����򷵻���ҳ��־</li>
     *         <li>��page����0��pageDownΪfalse�� page����1��������ҳ��־�����򷵻���ҳ��־</li>
     *         </ul>
     */
    public int transformPageToPageFlag(int page, boolean pageDown) {
        if (page <= 0) {
            return QqTConstant.VALUE_FIRST_PAGE;
        }
        if (pageDown) {
            return (page == 1) ? QqTConstant.VALUE_FIRST_PAGE : QqTConstant.VALUE_NEXT_PAGE;
        } else {
            return (page == 1) ? QqTConstant.VALUE_FIRST_PAGE : QqTConstant.VALUE_LAST_PAGE;
        }
    }

    /**
     * �õ��������͵�statusType
     * multiStatusType({@link QqTConstant#VALUE_STATUS_TYPE_COMMENT}, {@link QqTConstant#VALUE_STATUS_TYPE_Mention})
     * 
     * @param statusType ���Դ�����type
     * @return
     */
    public String multiStatusType(int... statusType) {
        int totalType = 0;
        for (int type : statusType) {
            totalType |= type;
        }
        return Integer.toString(totalType);
    }

    /**
     * ���ƶ���
     * 
     * @param source Դ����
     * @return
     */
    public QqTTimelinePara copy(QqTTimelinePara source) {
        QqTTimelinePara destin = new QqTTimelinePara();
        destin.format = source.format;
        destin.pageFlag = source.pageFlag;
        destin.pageTime = source.pageTime;
        destin.lastId = source.lastId;
        destin.userName = source.userName;
        destin.userOpenId = source.userOpenId;
        destin.pageReqNum = source.pageReqNum;
        destin.statusType = source.statusType;
        destin.contentType = source.contentType;
        destin.accessLevel = source.accessLevel;
        destin.position = source.position;
        destin.rootId = source.rootId;
        destin.twitterId = source.twitterId;
        destin.nextTime = source.nextTime;
        destin.prevTime = source.prevTime;
        return destin;
    }

    /**
     * ��ʱ���߶���ת��Ϊapi��Ҫ��map
     * 
     * @return
     */
    public Map<String, String> getParasMap() {
        Map<String, String> parasMap = new HashMap<String, String>();
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_FORMAT, format);
        if (pageFlag >= 0) {
            parasMap.put(QqTConstant.PARA_PAGE_FLAG, Integer.toString(pageFlag));
        }
        if (pageTime >= 0) {
            parasMap.put(QqTConstant.PARA_PAGE_TIME, Long.toString(pageTime));
        }
        if (lastId >= 0) {
            parasMap.put(QqTConstant.PARA_LAST_ID, Long.toString(lastId));
        }
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_USER_NAME, userName);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_USER_OPEN_ID, userOpenId);
        if (pageReqNum >= 0) {
            parasMap.put(QqTConstant.PARA_PAGE_REQ_NUM, Integer.toString(pageReqNum));
        }
        if (statusType >= 0) {
            parasMap.put(QqTConstant.PARA_STATUS_TYPE, Integer.toString(statusType));
        }
        if (contentType >= 0) {
            parasMap.put(QqTConstant.PARA_CONTENT_TYPE, Integer.toString(contentType));
        }
        if (accessLevel >= 0) {
            parasMap.put(QqTConstant.PARA_ACCESS_LEVEL, Integer.toString(accessLevel));
        }
        if (position >= 0) {
            parasMap.put(QqTConstant.PARA_POSITION, Long.toString(position));
        }
        if (repostOrCommentFlag >= 0) {
            parasMap.put(QqTConstant.PARA_REPOST_OR_COMMENT_FLAG, Long.toString(repostOrCommentFlag));
        }
        if (rootId >= 0) {
            parasMap.put(QqTConstant.PARA_ROOT_ID, Long.toString(rootId));
        }
        if (twitterId >= 0) {
            parasMap.put(QqTConstant.PARA_TWITTER_ID, Long.toString(twitterId));
        }
        if (nextTime >= 0) {
            parasMap.put(QqTConstant.PARA_NEXT_TIME, Long.toString(nextTime));
        }
        if (prevTime >= 0) {
            parasMap.put(QqTConstant.PARA_PREV_TIME, Long.toString(prevTime));
        }
        return parasMap;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(int pageFlag) {
        this.pageFlag = pageFlag;
    }

    public void setPageFlagByPage(int page) {
        this.pageFlag = transformPageToPageFlag(page);
    }

    public long getPageTime() {
        return pageTime;
    }

    public void setPageTime(long pageTime) {
        this.pageTime = pageTime;
    }

    public long getLastId() {
        return lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    public int getPageReqNum() {
        return pageReqNum;
    }

    public void setPageReqNum(int pageReqNum) {
        this.pageReqNum = pageReqNum;
    }

    public int getStatusType() {
        return statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public void setRepostOrCommentFlag(int repostOrCommentFlag) {
        this.repostOrCommentFlag = repostOrCommentFlag;
    }

    public int getRepostOrCommentFlag() {
        return repostOrCommentFlag;
    }

    public long getRootId() {
        return rootId;
    }

    public void setRootId(long rootId) {
        this.rootId = rootId;
    }

    public long getTwitterId() {
        return twitterId;
    }

    public void setTwitterId(long twitterId) {
        this.twitterId = twitterId;
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
}
