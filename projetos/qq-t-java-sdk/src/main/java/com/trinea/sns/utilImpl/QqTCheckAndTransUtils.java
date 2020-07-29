package com.trinea.sns.utilImpl;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.trinea.java.common.StringUtils;
import com.trinea.sns.entity.QqTStatus;
import com.trinea.sns.entity.QqTTopicSimple;
import com.trinea.sns.entity.QqTUpdateNumInfo;
import com.trinea.sns.entity.QqTUser;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢����鷵�صĽ���Ƿ���ȷ����ȷ�Ļ����и�ʽת����
 * 
 * @author Trinea 2011-11-9 ����12:03:38
 */
public class QqTCheckAndTransUtils {

    /**
     * ����޸Ĳ����Ƿ���ȷ���أ����ݷ��������ص��ַ����а���msg�ֶΣ������ֶ�Ϊok�����ʾ�ɹ�
     * 
     * @param response ���������ص��ַ���
     * @return ��ȷ����true
     */
    public static boolean checkModifyResult(JSONObject responseObj) {
        if (responseObj == null) {
            return false;
        }

        /** �� msg����ok **/
        try {
            return QqTConstant.RET_VALUE_MSG.equals(responseObj.getString(QqTConstant.RET_PARA_MSG));
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * ����޸Ĳ����Ƿ���ȷ���أ����ݷ��������ص��ַ����а���msg�ֶΣ������ֶ�Ϊok�����ʾ�ɹ�
     * 
     * @param response ���������ص��ַ���
     * @return ��ȷ����true
     *         <ul>
     *         <li>1���ַ���ת��ΪJSONObject</li>
     *         <li>2������{@link QqTCheckAndTransUtils#checkModifyResult(JSONObject)}</li>
     *         </ul>
     */
    public static boolean checkModifyResult(String response) {
        if (StringUtils.isEmpty(response)) {
            return false;
        }

        try {
            return checkModifyResult(new JSONObject(response));
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * ���ӷ��������ص�΢���б���Ϣת����QqTStatus lilst
     * 
     * @param statusesObj
     * @return
     *         <ul>
     *         <li>1���ȼ����ַ����е�msg�Ƿ���ok</li>
     *         <li>2������{@link QqTTransformUtils#transTLStatusesToList(String)}</li>
     *         </ul>
     */
    public static List<QqTStatus> transStatusesToList(String statusesJsonStr) {
        if (!QqTCheckAndTransUtils.checkModifyResult(statusesJsonStr)) {
            return null;
        }
        return QqTTransformUtils.transTLStatusesToList(statusesJsonStr);
    }

    /**
     * ���ӷ��������ص��û��б���Ϣת����QqTUser lilst
     * 
     * @param useresObj
     * @return
     *         <ul>
     *         <li>1���ȼ����ַ����е�msg�Ƿ���ok</li>
     *         <li>2������{@link QqTTransformUtils#transUsersToList(String)}</li>
     *         </ul>
     */
    public static List<QqTUser> transUsersToList(String useresJsonStr) {
        if (!QqTCheckAndTransUtils.checkModifyResult(useresJsonStr)) {
            return null;
        }
        return QqTTransformUtils.transUsersToList(useresJsonStr);
    }

    /**
     * ���ӷ��������ص��û������б���Ϣת����String lilst
     * 
     * @param useresObj
     * @return
     *         <ul>
     *         <li>1���ȼ����ַ����е�msg�Ƿ���ok</li>
     *         <li>2������{@link QqTTransformUtils#transUserNamesToList(String)}</li>
     *         </ul>
     */
    public static List<String> transUserNamesToList(String userNamesJsonStr) {
        if (!QqTCheckAndTransUtils.checkModifyResult(userNamesJsonStr)) {
            return null;
        }
        return QqTTransformUtils.transUserNamesToList(userNamesJsonStr);
    }

    /**
     * ���ӷ��������ص�������Ϣת����QqTTopicSimple lilst
     * 
     * @param topicsJsonStr
     * @return
     *         <ul>
     *         <li>1���ȼ����ַ����е�msg�Ƿ���ok</li>
     *         <li>2������{@link QqTTransformUtils#transTopicsToList(String)}</li>
     *         </ul>
     */
    public static List<QqTTopicSimple> transTopicsToList(String topicsJsonStr) {
        if (!QqTCheckAndTransUtils.checkModifyResult(topicsJsonStr)) {
            return null;
        }
        return QqTTransformUtils.transTopicsToList(topicsJsonStr);
    }

    /**
     * ���ӷ��������ص����ݸ�����Ϣת����QqTUpdateNumInfo
     * 
     * @param updateNumInfo
     * @return
     *         <ul>
     *         <li>1���ȼ����ַ����е�msg�Ƿ���ok</li>
     *         <li>2������{@link QqTTransformUtils#transQqTUpdateNumInfo(String)}</li>
     *         </ul>
     */
    public static QqTUpdateNumInfo transQqTUpdateNumInfo(String updateNumInfo) {
        if (!QqTCheckAndTransUtils.checkModifyResult(updateNumInfo)) {
            return null;
        }
        return QqTTransformUtils.transQqTUpdateNumInfo(updateNumInfo);
    }
}
