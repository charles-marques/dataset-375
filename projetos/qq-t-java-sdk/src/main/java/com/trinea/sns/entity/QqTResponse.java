package com.trinea.sns.entity;

import java.io.Serializable;

import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢��api���ص�������Ϣ
 * 
 * @author Trinea 2011-11-13 ����11:33:04
 */
public class QqTResponse implements Serializable {

    private static final long serialVersionUID = -2009665772693739186L;

    /** ����״̬��0�ɹ�����0ʧ�� **/
    private int               returnStatus;

    /** ������Ϣ��ok��error�� **/
    private String            message;

    /** ���� �Ĵ������ **/
    private int               errorCode;

    /** ���ص����ݣ����ڲ�ͬ��api�����ز�ͬ��������΢���б��������û���Ϣ�������ǵ���΢���������ǲ����Ƿ���ȷ��� **/
    private Object            data;

    /** ���صĹ������ݣ�һ��api���û����ݱ�����{@link QqTResponse#data}�У�������֤�ʻ��Ϸ�api�����Ϸ��������û���Ϣ **/
    private Object            info;

    /** ��ʾ�Ƿ���ȷ���� **/
    public boolean getIsOk() {
        return QqTConstant.RET_VALUE_MSG.equals(message);
    }

    public int getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(int returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Object getInfo() {
        return info;
    }
}
