package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢���ȶȡ�������Ϣ����
 * http://wiki.open.t.qq.com/index.php/%E7%83%AD%E5%BA%A6%EF%BC%8C%E8%B6%8B%E5%8A%BF/%E8%AF%9D%E9%A2%98%E7%83%AD%E6%A6%9
 * C
 * 
 * @author Trinea 2011-11-5 ����07:36:12
 */
public class QqTHotStatusPara implements Serializable {

    private static final long serialVersionUID    = -989824436462485204L;

    /** �������ݵĸ�ʽ **/
    private String            format;

    /**
     * ��������
     * ���ڻ������ 1 ��������2 �����ؼ��� 3 �������Ͷ���
     * ����ת���Ȱ���� 0x1-���ı� 0x2-������ 0x4ͼƬ 0x8-����Ƶ
     * ������ȡ���������ʹ��|����(0x1|0x2)�õ�3����ʱtype=3���ɣ������ʾ��ȡ��������
     **/
    private String            type;

    /** ������������20�� **/
    private int               reqNum;

    /** ����λ�ã���һ������ʱ��0���������ϴη��ص�pos **/
    private int               lastPosition;

    /** Ĭ��ֵ **/
    private static String     defaultFormat       = "";
    private static String     defaultType         = "";
    private static int        defaultReqNum       = -1;
    private static int        defaultLastPosition = -1;

    public QqTHotStatusPara(){
        super();

        this.format = defaultFormat;
        this.type = defaultType;
        this.reqNum = defaultReqNum;
        this.lastPosition = defaultLastPosition;
    }

    /**
     * ���ȶȡ�������Ϣ��������ת��Ϊapi��Ҫ��map
     * 
     * @return
     */
    public Map<String, String> getParasMap() {
        Map<String, String> parasMap = new HashMap<String, String>();
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_FORMAT, format);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_HOT_SEARCH_TYPE, type);
        if (reqNum >= 0) {
            parasMap.put(QqTConstant.PARA_REQ_NUMBER, Integer.toString(reqNum));
        }
        if (lastPosition >= 0) {
            parasMap.put(QqTConstant.PARA_LAST_POSITION, Integer.toString(lastPosition));
        }
        return parasMap;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReqNum() {
        return reqNum;
    }

    public void setReqNum(int reqNum) {
        this.reqNum = reqNum;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }
}
