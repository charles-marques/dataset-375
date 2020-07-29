package com.trinea.sns.utilImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.java.common.StringUtils;
import com.trinea.sns.entity.QqTHotStatusPara;
import com.trinea.sns.entity.QqTSearchPara;
import com.trinea.sns.entity.QqTStatusInfoPara;
import com.trinea.sns.entity.QqTTimelinePara;
import com.trinea.sns.entity.QqTUserEduPara;
import com.trinea.sns.entity.QqTUserPara;
import com.trinea.sns.entity.QqTUserRelationPara;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢��api��Ҫ�Ĳ���map
 * 
 * @author Trinea 2011-11-9 ����12:04:19
 */
public class QqTParaMapUtils {

    /**
     * �õ�ʹ��api����ı�׼����
     * 
     * @param appKey
     * @param accessToken
     * @return
     *         <ul>
     *         <li>ע�����в�����oauth_signature��������Ϊ�˲�����Ҫ���ݴ��ݵ����в������ܵõ�</li>
     *         </ul>
     */
    public static Map<String, String> getStandardParaMap(String appKey, String accessToken) {
        if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(accessToken)) {
            return null;
        }

        Map<String, String> parasMap = new HashMap<String, String>();
        parasMap.put(QqTConstant.PARA_OAUTH_CONSUMER_KEY, appKey);
        parasMap.put(QqTConstant.PARA_OAUTH_TOKEN, accessToken);
        parasMap.put(QqTConstant.PARA_OAUTH_SIGNATURE_METHOD, QqTConstant.VALUE_OAUTH_SIGNATURE_METHOD);
        parasMap.put(QqTConstant.PARA_OAUTH_TIMESTAMP, Long.toString(((new Date()).getTime()) / 1000));
        parasMap.put(QqTConstant.PARA_OAUTH_NONCE, StringUtils.getRandomNumbersAndLetters(32));
        // parasMap.put(QqTConstant.PARA_OAUTH_TIMESTAMP, "1321153765");
        // parasMap.put(QqTConstant.PARA_OAUTH_NONCE, "9752880");
        parasMap.put(QqTConstant.PARA_OAUTH_VERSION, QqTConstant.VALUE_OAUTH_VERSION);
        return parasMap;
    }

    /**
     * �õ�api����Ĳ�����ʱ���߶���ת����Ĳ����ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param qqTTimelinePara
     * @return
     */
    public static Map<String, String> getStdAndQqTTLParaMap(String appKey, String accessToken,
                                                            QqTTimelinePara qqTTimelinePara) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> qqTTimelineParaMap = null;
        if (qqTTimelinePara != null) {
            qqTTimelineParaMap = qqTTimelinePara.getParasMap();
        }
        if (parasMap == null) {
            return qqTTimelineParaMap;
        } else {
            if (!MapUtils.isEmpty(qqTTimelineParaMap)) {
                parasMap.putAll(qqTTimelineParaMap);
            }
        }
        return parasMap;
    }

    /**
     * �õ�api����Ĳ�����΢������ת����Ĳ����ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param updateStatusInfo
     * @return
     */
    public static Map<String, String> getStdAndQqTSIParaMap(String appKey, String accessToken,
                                                            QqTStatusInfoPara updateStatusInfo) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> updateStatusInfoParaMap = null;
        if (updateStatusInfo != null) {
            updateStatusInfoParaMap = updateStatusInfo.getParasMap();
        }
        if (parasMap == null) {
            return updateStatusInfoParaMap;
        } else {
            if (!MapUtils.isEmpty(updateStatusInfoParaMap)) {
                parasMap.putAll(updateStatusInfoParaMap);
            }
        }
        return parasMap;
    }

    /**
     * �õ�api����Ĳ������û���Ϣ���µĲ����ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param qqTUserPara
     * @return
     */
    public static Map<String, String> getStdAndQqTUserParaMap(String appKey, String accessToken, QqTUserPara qqTUserPara) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> qqTUserParaMap = null;
        if (qqTUserPara != null) {
            qqTUserParaMap = qqTUserPara.getParasMap();
        }
        if (parasMap == null) {
            return qqTUserParaMap;
        } else {
            if (!MapUtils.isEmpty(qqTUserParaMap)) {
                parasMap.putAll(qqTUserParaMap);
            }
        }
        return parasMap;
    }

    /**
     * �õ�api����Ĳ������û���ϵ�������ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param qqTUserEduPara
     * @return
     */
    public static Map<String, String> getStdAndQqTURParaMap(String appKey, String accessToken,
                                                            QqTUserRelationPara qqTUserRelationPara) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> qqTUserRelationParaMap = null;
        if (qqTUserRelationPara != null) {
            qqTUserRelationParaMap = qqTUserRelationPara.getParasMap();
        }
        if (parasMap == null) {
            return qqTUserRelationParaMap;
        } else {
            if (!MapUtils.isEmpty(qqTUserRelationParaMap)) {
                parasMap.putAll(qqTUserRelationParaMap);
            }
        }
        return parasMap;
    }

    /**
     * �õ�api����Ĳ������û�������Ϣ���µĲ����ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param qqTUserRelationPara
     * @return
     */
    public static Map<String, String> getStdAndQqTUEParaMap(String appKey, String accessToken,
                                                            QqTUserEduPara qqTUserEduPara) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> qqTUserEduParaMap = null;
        if (qqTUserEduPara != null) {
            qqTUserEduParaMap = qqTUserEduPara.getParasMap();
        }
        if (parasMap == null) {
            return qqTUserEduParaMap;
        } else {
            if (!MapUtils.isEmpty(qqTUserEduParaMap)) {
                parasMap.putAll(qqTUserEduParaMap);
            }
        }
        return parasMap;
    }

    /**
     * �õ�api����Ĳ��������������ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param qqTSearchPara
     * @return
     */
    public static Map<String, String> getStdAndQqTSearchParaMap(String appKey, String accessToken,
                                                                QqTSearchPara qqTSearchPara) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> qqTSearchParaMap = null;
        if (qqTSearchPara != null) {
            qqTSearchParaMap = qqTSearchPara.getParasMap();
        }
        if (parasMap == null) {
            return qqTSearchParaMap;
        } else {
            if (!MapUtils.isEmpty(qqTSearchParaMap)) {
                parasMap.putAll(qqTSearchParaMap);
            }
        }
        return parasMap;
    }

    /**
     * �õ�api����Ĳ������ȶȡ�������Ϣ�ϲ���map
     * 
     * @param appKey Ӧ�õ�key
     * @param accessToken access Toke
     * @param qqTHotStatusPara
     * @return
     */
    public static Map<String, String> getStdAndQqTHSParaMap(String appKey, String accessToken,
                                                            QqTHotStatusPara qqTHotStatusPara) {
        Map<String, String> parasMap = getStandardParaMap(appKey, accessToken);
        Map<String, String> qqTHotStatusParaMap = null;
        if (qqTHotStatusPara != null) {
            qqTHotStatusParaMap = qqTHotStatusPara.getParasMap();
        }
        if (parasMap == null) {
            return qqTHotStatusParaMap;
        } else {
            if (!MapUtils.isEmpty(qqTHotStatusParaMap)) {
                parasMap.putAll(qqTHotStatusParaMap);
            }
        }
        return parasMap;
    }
}
