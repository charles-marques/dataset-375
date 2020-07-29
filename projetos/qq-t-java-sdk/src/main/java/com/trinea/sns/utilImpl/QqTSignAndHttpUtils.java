package com.trinea.sns.utilImpl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.trinea.java.common.HttpUtils;
import com.trinea.java.common.MapUtils;
import com.trinea.java.common.StringUtils;
import com.trinea.sns.entity.QqTAppAndToken;
import com.trinea.sns.entity.QqTSign;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢��ǩ����http����
 * 
 * @author Trinea 2011-11-9 ����12:04:43
 */
public class QqTSignAndHttpUtils {

    /**
     * ����ǩ���������<a href="http://wiki.open.t.qq.com/index.php/OAuth%E6%8E%88%E6%9D%83%E8%AF%B4%E6%98%8E">��Ѷoauth api����</a>
     * 
     * @param parasMap ����map
     * @param appSecret Ӧ�����룬���ڼ��ܣ�����Ϊ��
     * @param tokenSecret token���룬���ڼ��ܣ���Ϊ��
     * @return ǩ��ֵ
     */
    public static String signature(QqTSign qqTSign) {
        if (qqTSign == null || !qqTSign.isValid()) {
            return null;
        }

        /** URL Encode�������http://open.t.qq.com/resource.php?i=1,2 **/
        StringBuilder url = new StringBuilder(qqTSign.getHttpMethod());
        url.append(HttpUtils.PARAMETERS_SEPARATOR).append(HttpUtils.utf8Encode(qqTSign.getBaseUrl()));
        url.append(HttpUtils.PARAMETERS_SEPARATOR).append(HttpUtils.utf8Encode(HttpUtils.getOrderedValueEncodeParas(qqTSign.getParasMap())));

        /** ǩ���㷨HMAC-SHA1����Կ��App Secret��Token Secret��ɣ��м�ʹ��&���ŷָ��� **/
        byte[] oauthSignature = null;
        try {
            Mac mac = Mac.getInstance(QqTConstant.MAC_ALGORITHM);
            String oauthKey = HttpUtils.utf8Encode(qqTSign.getAppSecret())
                              + "&"
                              + (StringUtils.isEmpty(qqTSign.getTokenSecret()) ? "" : HttpUtils.utf8Encode(qqTSign.getTokenSecret()));
            SecretKeySpec spec = new SecretKeySpec(oauthKey.getBytes(QqTConstant.MAC_ENCODING),
                                                   QqTConstant.MAC_ALGORITHM);
            mac.init(spec);
            oauthSignature = mac.doFinal(url.toString().getBytes(QqTConstant.MAC_ENCODING));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64Encoder.encode(oauthSignature);
    }

    /**
     * �Բ�������ǩ�������ҷ�http get���󵽹̶�url����������
     * <ul>
     * <li><strong>ע�⣺</strong>��ı�parasMap����sign���ֵ��Ϊvalue��put��parasMap��</li>
     * </ul>
     * 
     * @param url �����url
     * @param parasMap keyΪ��������valueΪ����ֵ
     * @param qqTAppAndToken �û���Ϣ
     * @return
     */
    public static String signAndHttpGet(String url, Map<String, String> parasMap, QqTAppAndToken qqTAppAndToken) {
        if (StringUtils.isEmpty(url) || MapUtils.isEmpty(parasMap) || qqTAppAndToken == null
            || !qqTAppAndToken.isValid()) {
            return null;
        }

        /** �Բ�������ǩ�������ҷ�http get���󵽹̶�url���������� **/
        QqTSign qqTSign = new QqTSign();
        qqTSign.setBaseUrl(url);
        qqTSign.setHttpMethod(HttpUtils.HTTP_GET_METHOD.toUpperCase());
        qqTSign.setAppSecret(qqTAppAndToken.getAppSecret());
        qqTSign.setTokenSecret(qqTAppAndToken.getTokenSecret());
        qqTSign.setParasMap(parasMap);
        parasMap.put(QqTConstant.PARA_OAUTH_SIGNATURE, signature(qqTSign));
        return HttpUtils.httpGetEncodeParas(qqTSign.getBaseUrl(), parasMap);
    }

    /**
     * �Բ�������ǩ�������ҷ�http post���󵽹̶�url����������
     * <ul>
     * <li><strong>ע�⣺</strong>��ı�parasMap����sign���ֵ��Ϊvalue��put��parasMap��</li>
     * </ul>
     * 
     * @param url �����url
     * @param parasMap keyΪ��������valueΪ����ֵ
     * @param qqTAppAndToken �û���Ϣ
     * @return
     */
    public static String signAndHttpPost(String url, Map<String, String> parasMap, QqTAppAndToken qqTAppAndToken) {
        if (StringUtils.isEmpty(url) || MapUtils.isEmpty(parasMap) || qqTAppAndToken == null
            || !qqTAppAndToken.isValid()) {
            return null;
        }

        /** �Բ�������ǩ�������ҷ�http post���󵽹̶�url���������� **/
        QqTSign qqTSign = new QqTSign();
        qqTSign.setBaseUrl(url);
        qqTSign.setHttpMethod(HttpUtils.HTTP_POST_METHOD.toUpperCase());
        qqTSign.setAppSecret(qqTAppAndToken.getAppSecret());
        qqTSign.setTokenSecret(qqTAppAndToken.getTokenSecret());
        qqTSign.setParasMap(parasMap);
        parasMap.put(QqTConstant.PARA_OAUTH_SIGNATURE, signature(qqTSign));
        return HttpUtils.httpPost(qqTSign.getBaseUrl(), parasMap);
    }

    /**
     * �Բ�������ǩ�������ܲ���Ȼ��http post���󵽹̶�url����������
     * <ul>
     * <li><strong>ע�⣺</strong>��ı�parasMap����sign���ֵ��Ϊvalue��put��parasMap��</li>
     * </ul>
     * 
     * @param url
     * @param parasMap keyΪ��������valueΪ����ֵ
     * @param qqTAppAndToken
     * @return
     */
    public static String signAndHttpPostEncodeParas(String url, Map<String, String> parasMap,
                                                    QqTAppAndToken qqTAppAndToken) {
        if (StringUtils.isEmpty(url) || MapUtils.isEmpty(parasMap) || qqTAppAndToken == null
            || !qqTAppAndToken.isValid()) {
            return null;
        }

        /** �Բ�������ǩ�������ܲ���Ȼ��http post���󵽹̶�url���������� **/
        QqTSign qqTSign = new QqTSign();
        qqTSign.setBaseUrl(url);
        qqTSign.setHttpMethod(HttpUtils.HTTP_POST_METHOD.toUpperCase());
        qqTSign.setAppSecret(qqTAppAndToken.getAppSecret());
        qqTSign.setTokenSecret(qqTAppAndToken.getTokenSecret());
        qqTSign.setParasMap(parasMap);
        parasMap.put(QqTConstant.PARA_OAUTH_SIGNATURE, signature(qqTSign));
        return HttpUtils.httpPostEncodeParas(qqTSign.getBaseUrl(), parasMap);
    }

    /**
     * �Բ�������ǩ�������ܲ���Ȼ��http post�����Լ��ļ����̶�url����������
     * <ul>
     * <li><strong>ע�⣺</strong>��ı�parasMap����sign���ֵ��Ϊvalue��put��parasMap��</li>
     * </ul>
     * 
     * @param url
     * @param filePathMap keyΪ��������valueΪ�ļ�·��
     * @param parasMap keyΪ��������valueΪ����ֵ
     * @param qqTAppAndToken
     * @return
     */
    public static String signAndHttpPostWithFile(String url, Map<String, String> filePathMap,
                                                 Map<String, String> parasMap, QqTAppAndToken qqTAppAndToken) {
        if (StringUtils.isEmpty(url) || MapUtils.isEmpty(parasMap) || qqTAppAndToken == null
            || !qqTAppAndToken.isValid()) {
            return null;
        }

        /** �Բ�������ǩ�������ܲ���Ȼ��http post���󵽹̶�url���������� **/
        QqTSign qqTSign = new QqTSign();
        qqTSign.setBaseUrl(url);
        qqTSign.setHttpMethod(HttpUtils.HTTP_POST_METHOD.toUpperCase());
        qqTSign.setAppSecret(qqTAppAndToken.getAppSecret());
        qqTSign.setTokenSecret(qqTAppAndToken.getTokenSecret());
        qqTSign.setParasMap(parasMap);
        parasMap.put(QqTConstant.PARA_OAUTH_SIGNATURE, signature(qqTSign));

        return HttpUtils.httpPostWithFile(qqTSign.getBaseUrl(), parasMap, filePathMap);
    }
}
