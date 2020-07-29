package com.trinea.sns.service;

import java.util.List;
import java.util.Map;

import com.trinea.sns.entity.QqTAppAndToken;
import com.trinea.sns.entity.QqTHotStatusPara;
import com.trinea.sns.entity.QqTResponse;
import com.trinea.sns.entity.QqTSearchPara;
import com.trinea.sns.entity.QqTStatus;
import com.trinea.sns.entity.QqTStatusInfoPara;
import com.trinea.sns.entity.QqTTimelinePara;
import com.trinea.sns.entity.QqTTopicSimple;
import com.trinea.sns.entity.QqTUpdateNumInfo;
import com.trinea.sns.entity.QqTUser;
import com.trinea.sns.entity.QqTUserEduPara;
import com.trinea.sns.entity.QqTUserPara;
import com.trinea.sns.entity.QqTUserRelation;
import com.trinea.sns.entity.QqTUserRelationPara;
import com.trinea.sns.entity.QqTVideoInfo;
import com.trinea.sns.util.QqTConstant;

public interface QqTSdkService {

    /**
     * �õ�Ӧ�ú��û������Ϣ
     * 
     * @return
     */
    public QqTAppAndToken getQqTAppAndToken();

    /**
     * ����Ӧ�ú��û������Ϣ
     * 
     * @param qqTAppAndToken
     */
    public void setQqTAppAndToken(QqTAppAndToken qqTAppAndToken);

    /**
     * ʱ����ͨ��api���أ��ַ�������
     * <ul>
     * <li>{@link QqTTimelinePara#setFormat(String)}��ȡֵ��{@link QqTConstant#VALUE_FORMAT_JSON}�Լ�
     * {@link QqTConstant#VALUE_FORMAT_XML}</li>
     * <li>{@link QqTTimelinePara#setStatusType(int)}��ȡֵ��{@link QqTConstant#VALUE_STATUS_TYPE_TL_ALL}��
     * {@link QqTConstant#VALUE_STATUS_TYPE_TL_COMMENT}�Լ�QqTConstant������{@code VALUE_STATUS_TYPE_TL_��}���Լ���|����ĵ�����ֵ</li>
     * <li>{@link QqTTimelinePara#setContentType(int)}��ȡֵ��{@link QqTConstant#VALUE_CONTENT_TYPE_TL_ALL}��
     * {@link QqTConstant#VALUE_CONTENT_TYPE_TL_MUSIC}�Լ�QqTConstant������{@code VALUE_CONTENT_TYPE_TL_��}</li>
     * </ul>
     * 
     * @param url �����url
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public String getTimeLineCommonStr(String url, QqTTimelinePara qqTTimelinePara);

    /**
     * ʱ����ͨ��api���أ�QqTStatus�����б���
     * 
     * @param url �����url
     * @param qqTTimelinePara ʱ���߲���
     * @param qq@return �Զ���list����ʽ����
     *            <ul>
     *            <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *            {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *            <li>����{@link QqTSdkService#getTimeLineCommonStr(String, QqTTimelinePara)}��ת��Ϊ����</li>
     *            </ul>
     */
    public List<QqTStatus> getTimeLineCommon(String url, QqTTimelinePara qqTTimelinePara);

    /**
     * ʱ����ͨ��api���أ�QqTResponse���󷵻�
     * 
     * @param url �����url
     * @param qqTTimelinePara ʱ���߲���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getTimeLineCommonStr(String, QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getTimeLineCommonRes(String url, QqTTimelinePara qqTTimelinePara);

    /**
     * ��ҳʱ���ߣ���ʾ��ȡ΢���б�����ͨ����type��contenttype���������û�ȡ��ͬ��΢���б�<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_14">��Ѷ΢��<strong>��ҳʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getHomeTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ҳʱ���ߣ���ʾ��ȡ΢���б�����ͨ����type��contenttype���������û�ȡ��ͬ��΢���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getHomeTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getHomeTL(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ҳʱ���ߣ�����QqTResponse������ͨ����type��contenttype���������û�ȡ��ͬ��΢���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getHomeTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getHomeTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �㲥����ʱ���ߣ���ʾ�㲥������΢����Ϣ<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_15">��Ѷ΢��<strong>�㲥����ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getPublicTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �㲥����ʱ���ߣ���ʾ�㲥������΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getPublicTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getPublicTL(QqTTimelinePara qqTTimelinePara);

    /**
     * �㲥����ʱ���ߣ���ʾ�㲥������΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getPublicTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getPublicTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �����û�����ʱ���ߣ���ʾ��ȡ�����û���΢����Ϣ<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_16">��Ѷ΢��<strong>�����û�����ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getUserTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �����û�����ʱ���ߣ���ʾ��ȡ�����û���΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getUserTL(QqTTimelinePara qqTTimelinePara);

    /**
     * �����û�����ʱ���ߣ���ʾ��ȡ�����û���΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getUserTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �û��ἰʱ���ߣ���ʾ��ȡ�ἰ���Լ���΢������@<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_17">��Ѷ΢��<strong>�û��ἰʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getMentionsTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �û��ἰʱ���ߣ���ʾ��ȡ�ἰ���Լ���΢������@
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getMentionsTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getMentionsTL(QqTTimelinePara qqTTimelinePara);

    /**
     * �û��ἰʱ���ߣ���ʾ��ȡ�ἰ���Լ���΢������@
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getMentionsTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getMentionsTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ����ʱ���ߣ���ʾ��ȡ��ػ����΢����Ϣ<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_18">��Ѷ΢��<strong>����ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getTopicTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ����ʱ���ߣ���ʾ��ȡ��ػ����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getTopicTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getTopicTL(QqTTimelinePara qqTTimelinePara);

    /**
     * ����ʱ���ߣ���ʾ��ȡ��ػ����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getTopicTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getTopicTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ���ߣ���ʾ��ȡ�ҷ����΢����Ϣ<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_19">��Ѷ΢��<strong>�ҷ���ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getBroadcastTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ���ߣ���ʾ��ȡ�ҷ����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getBroadcastTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getBroadcastTL(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ���ߣ���ʾ��ȡ�ҷ����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getBroadcastTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getBroadcastTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �ر��������˷���ʱ���ߣ���ʾ��ȡ���ر���յ��û������΢����Ϣ<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_20">��Ѷ΢��<strong>�ر��������˷���ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getSpecialTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �ر��������˷���ʱ���ߣ���ʾ��ȡ���ر���յ��û������΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getSpecialTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getSpecialTL(QqTTimelinePara qqTTimelinePara);

    /**
     * �ر��������˷���ʱ���ߣ���ʾ��ȡ���ر���յ��û������΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getSpecialTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getSpecialTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ��������ʱ���ߣ���ʾ��ȡĳ�����������΢����Ϣ<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_72">��Ѷ΢��<strong>��������ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getAreaTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ���ߣ���ʾ��ȡ�ҷ����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getAreaTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getAreaTL(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ���ߣ���ʾ��ȡ�ҷ����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getAreaTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getAreaTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ҳʱ��������<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_77">��Ѷ΢��<strong>��ҳʱ��������</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getHomeTLIdsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ҳʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getHomeTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getHomeTLIds(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ҳʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getHomeTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getHomeTLIdsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �����û�����ʱ��������<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_79">��Ѷ΢��<strong>�����û�����ʱ��������</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getUserTLIdsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �����û�����ʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getUserTLIds(QqTTimelinePara qqTTimelinePara);

    /**
     * �����û�����ʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getUserTLIdsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ��������<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_76">��Ѷ΢��<strong>�ҷ���ʱ��������</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getBroadcastTLIdsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getBroadcastTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getBroadcastTLIds(QqTTimelinePara qqTTimelinePara);

    /**
     * �ҷ���ʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getBroadcastTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getBroadcastTLIdsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �û��ἰʱ��������<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_78">��Ѷ΢��<strong>�û��ἰʱ��������</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getMentionsTLIdsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �û��ἰʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getMentionsTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getMentionsTLIds(QqTTimelinePara qqTTimelinePara);

    /**
     * �û��ἰʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getMentionsTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getMentionsTLIdsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ���û�����ʱ����<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_73">��Ѷ΢��<strong>���û�����ʱ����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getUsersTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ���û�����ʱ����<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUsersTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getUsersTL(QqTTimelinePara qqTTimelinePara);

    /**
     * ���û�����ʱ����<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUsersTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getUsersTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ���û�����ʱ��������<br/>
     * ���������<a href="http://open.t.qq.com/resource.php?i=1,1#7_80">��Ѷ΢��<strong>���û�����ʱ��������</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getUsersTLIdsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ���û�����ʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUsersTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getUsersTLIds(QqTTimelinePara qqTTimelinePara);

    /**
     * ���û�����ʱ��������<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUsersTLIdsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getUsersTLIdsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡvip�û�����΢����Ϣ<br/>
     * ���������<a href=
     * "http://wiki.open.t.qq.com/index.php/%E6%97%B6%E9%97%B4%E7%BA%BF/%E6%8B%89%E5%8F%96vip%E7%94%A8%E6%88%B7%E5%8F%91%E8%A1%A8%E5%BE%AE%E5%8D%9A%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3"
     * >��Ѷ΢��<strong>��ȡvip�û�����΢����Ϣ</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getVipStatusTLStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡvip�û�����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getVipStatusTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getVipStatusTL(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡvip�û�����΢����Ϣ<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getVipStatusTLStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getVipStatusTLRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �õ�ĳ��΢���ľ�����Ϣ���ַ�������
     * 
     * @param format ������Ϣ��ʽ
     * @param statusId ΢��id
     * @return
     */
    public String getStatus(String format, long statusId);

    /**
     * �õ�ĳ��΢���ľ�����Ϣ��״̬����
     * 
     * @param statusId ΢��id
     * @return
     */
    public QqTStatus getStatus(long statusId);

    /**
     * �õ�ĳ��΢���ľ�����Ϣ��QqTResponse����
     * 
     * @param statusId ΢��id
     * @return
     */
    public QqTResponse getStatusRes(long statusId);

    /**
     * ����״̬ͨ��api�������ַ���
     * 
     * @param addStatusUrl ����״̬��url
     * @param status ״̬����
     * @return
     */
    public String addStatusCommonStr(String addStatusUrl, QqTStatusInfoPara status);

    /**
     * ����״̬ͨ��api�������Ƿ������ɹ�
     * 
     * @param addStatusUrl ����״̬��url
     * @param status ״̬����
     * @return
     */
    public boolean addStatusCommon(String addStatusUrl, QqTStatusInfoPara status);

    /**
     * ����״̬ͨ��api������QqTResponse
     * 
     * @param addStatusUrl ����״̬��url
     * @param status ״̬����
     * @return
     */
    public QqTResponse addStatusCommonRes(String addStatusUrl, QqTStatusInfoPara status);

    /**
     * ����һ��΢���������ַ���
     * 
     * @param status ΢��������Ϣ
     * @return
     */
    public String addStatusStr(QqTStatusInfoPara status);

    /**
     * ����һ��΢���������Ƿ�ɹ�
     * 
     * @param status ΢��������Ϣ
     * @return
     */
    public boolean addStatus(QqTStatusInfoPara status);

    /**
     * ����һ��΢��������QqTResponse
     * 
     * @param status ΢��������Ϣ
     * @return
     */
    public QqTResponse addStatusRes(QqTStatusInfoPara status);

    /**
     * ����һ����΢���������ַ���
     * 
     * @param content ΢��������Ϣ
     * @param imagePath ͼƬ·��������ͼƬ����null
     * @return
     */
    public String addStatusStr(String content, String imagePath);

    /**
     * ����һ����΢���������Ƿ�ɹ�
     * 
     * @param content ΢��������Ϣ
     * @param imagePath ͼƬ·��������ͼƬ����null
     * @return
     */
    public boolean addStatus(String content, String imagePath);

    /**
     * ����һ����΢��������QqTResponse
     * 
     * @param content ΢��������Ϣ
     * @param imagePath ͼƬ·��������ͼƬ����null
     * @return
     */
    public QqTResponse addStatusRes(String content, String imagePath);

    /**
     * ת��һ��΢��������format�����ַ���
     * 
     * @param status ΢������
     * @return
     */
    public String repostStr(QqTStatusInfoPara status);

    /**
     * ת��һ��΢���������Ƿ�ɹ�
     * 
     * @param status ΢��������Ϣ��������ת��΢����id
     * @return
     */
    public boolean repost(QqTStatusInfoPara status);

    /**
     * ת��һ��΢��������QqTResponse
     * 
     * @param status ΢��������Ϣ��������ת��΢����id
     * @return ����QqTResponse
     */
    public QqTResponse repostRes(QqTStatusInfoPara status);

    /**
     * �ظ�һ��΢��������format�����ַ���
     * 
     * @param status ΢������
     * @return
     */
    public String replyStr(QqTStatusInfoPara status);

    /**
     * �ظ�һ��΢���������Ƿ�ɹ�
     * 
     * @param status ΢��������Ϣ��������ת��΢����id
     * @return �Ƿ�ɹ��ظ�
     */
    public boolean reply(QqTStatusInfoPara status);

    /**
     * �ظ�һ��΢��������QqTResponse
     * 
     * @param status ΢��������Ϣ��������ת��΢����id
     * @return ����QqTResponse
     */
    public QqTResponse replyRes(QqTStatusInfoPara status);

    /**
     * ����һ��΢��������format�����ַ���
     * 
     * @param status ΢������
     * @return
     */
    public String commentStr(QqTStatusInfoPara status);

    /**
     * ����һ��΢���������Ƿ�ɹ�
     * 
     * @param status ΢��������Ϣ��������ת��΢����id
     * @return �Ƿ�ɹ�����
     */
    public boolean comment(QqTStatusInfoPara status);

    /**
     * ����һ��΢��������QqTResponse
     * 
     * @param status ΢��������Ϣ��������ת��΢����id
     * @return ����QqTResponse
     */
    public QqTResponse commentRes(QqTStatusInfoPara status);

    /**
     * ��������΢��������format�����ַ���
     * 
     * @param status ΢������
     * @return
     */
    public String addMusicStatusStr(QqTStatusInfoPara status);

    /**
     * ��������΢���������Ƿ񷢱�ɹ�
     * 
     * @param status ΢������
     * @return �����Ƿ񷢱�ɹ�
     */
    public boolean addMusicStatus(QqTStatusInfoPara status);

    /**
     * ��������΢��������QqTResponse
     * 
     * @param status ΢������
     * @return ����QqTResponse
     */
    public QqTResponse addMusicStatusRes(QqTStatusInfoPara status);

    /**
     * ������Ƶ΢��������format�����ַ���
     * 
     * @param status ΢������
     * @return
     */
    public String addVideoStatusStr(QqTStatusInfoPara status);

    /**
     * ������Ƶ΢���������Ƿ񷢱�ɹ�
     * 
     * @param status ΢������
     * @return �����Ƿ񷢱�ɹ�
     */
    public boolean addVideoStatus(QqTStatusInfoPara status);

    /**
     * ������Ƶ΢��������QqTResponse
     * 
     * @param status ΢������
     * @return ����QqTResponse
     */
    public QqTResponse addVideoStatusRes(QqTStatusInfoPara status);

    /**
     * ���ĳ��΢�������ۻ�ת����Ϣͨ��api��String����
     * 
     * @param repostOrCommentFlag ��������ת����ʶ
     * @param qqTTimelinePara ʱ���߲���
     * @return
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara<br/>
     *         ����{@link QqTTimelinePara#setRepostOrCommentFlag(int)}Ϊ repostOrCommentFlag</li>
     *         <li>���� {@link QqTSdkService#getTimeLineCommonStr(String, QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public String getStatusCommentsCommonStr(int repostOrCommentFlag, QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢�������ۻ�ת����Ϣͨ��api��QqTStatus list����
     * 
     * @param repostOrCommentFlag ��������ת����ʶ
     * @param qqTTimelinePara ʱ���߲���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara<br/>
     *         ����{@link QqTTimelinePara#setFormat(String)}Ϊ {@link QqTConstant#VALUE_FORMAT_JSON}<br/>
     *         ����{@link QqTTimelinePara#setRepostOrCommentFlag(int)}Ϊ repostOrCommentFlag</li>
     *         <li>���� {@link QqTSdkService#getTimeLineCommon(String, QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getStatusCommentsCommon(int repostOrCommentFlag, QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢�������ۻ�ת����Ϣͨ��api��QqTResponse����
     * 
     * @param repostOrCommentFlag ��������ת����ʶ
     * @param qqTTimelinePara ʱ���߲���
     * @return ��QqTResponse������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara<br/>
     *         ����{@link QqTTimelinePara#setFormat(String)}Ϊ {@link QqTConstant#VALUE_FORMAT_JSON}<br/>
     *         ����{@link QqTTimelinePara#setRepostOrCommentFlag(int)}Ϊ repostOrCommentFlag</li>
     *         <li>���� {@link QqTSdkService#getTimeLineCommonRes(String, QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getStatusCommentsCommonRes(int repostOrCommentFlag, QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢����������Ϣ�������ַ���
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public String getStatusCommentsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢����������Ϣ������״̬list
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public List<QqTStatus> getStatusComments(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢����������Ϣ������QqTResponse
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public QqTResponse getStatusCommentsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢����ת����Ϣ�������ַ���
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public String getStatusRepostsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢����ת����Ϣ������״̬list
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public List<QqTStatus> getStatusReposts(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢����ת����Ϣ������QqTResponse
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public QqTResponse getStatusRepostsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢�������ۺ�ת����Ϣ�������ַ���
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public String getStatusCommentsAndRepostsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢�������ۺ�ת����Ϣ������״̬list
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public List<QqTStatus> getStatusCommentsAndReposts(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ĳ��΢�������ۺ�ת����Ϣ������QqTResponse
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return
     */
    public QqTResponse getStatusCommentsAndRepostsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡ��Ƶ��Ϣ�����ַ�����ʽ����
     * 
     * @param format ���ص����ݸ�ʽ
     * @param videoUrl ��Ƶurl
     * @return
     */
    public String getVideoInfo(String format, String videoUrl);

    /**
     * ��ȡ��Ƶ��Ϣ����QqTVideoInfo������ʽ����
     * 
     * @param videoUrl ��Ƶurl
     * @return
     */
    public QqTVideoInfo getVideoInfo(String videoUrl);

    /**
     * ��ȡ��Ƶ��Ϣ����QqTResponse������ʽ����
     * 
     * @param videoUrl ��Ƶurl
     * @return
     */
    public QqTResponse getVideoInfoRes(String videoUrl);

    /**
     * ����΢��id������ȡ΢�����ݣ������ַ���
     * 
     * @param format �������ݸ�ʽ
     * @param ids ΢��id���Զ��ŷָ�
     * @return
     */
    public String getStatusByIdsStr(String format, String ids);

    /**
     * ����΢��id������ȡ΢������
     * 
     * @param ids ΢��id���Զ��ŷָ�
     * @return
     */
    public List<QqTStatus> getStatusByIds(String ids);

    /**
     * ����΢��id������ȡ΢�����ݣ�����QqTResponse
     * 
     * @param ids ΢��id���Զ��ŷָ�
     * @return
     */
    public QqTResponse getStatusByIdsRes(String ids);

    /**
     * ����΢��id������ȡת�����ٴ�ת�����������ַ���
     * 
     * @param format �������ݸ�ʽ
     * @param ids ΢��id���Զ��ŷָ�
     * @return
     */
    public String getReRepostCountByIdsStr(String format, String ids);

    /**
     * ����΢��id������ȡת�����ٴ�ת����
     * 
     * @param ids ΢��id���Զ��ŷָ�
     * @return keyΪ΢��id��valueΪ�ٴ�ת����
     */
    public Map<Long, Integer> getReRepostCountByIds(String ids);

    /**
     * ����΢��id������ȡת�����ٴ�ת����������QqTResponse
     * 
     * @param ids ΢��id���Զ��ŷָ�
     * @return keyΪ΢��id��valueΪ�ٴ�ת����
     */
    public QqTResponse getReRepostCountByIdsRes(String ids);

    /**
     * �����������ӣ������ַ���
     * 
     * @param status ����������Ϣ
     * @return
     */
    public String addEmotionStr(QqTStatusInfoPara status);

    /**
     * �����������ӣ������Ƿ�ɹ�
     * 
     * @param status ����������Ϣ
     * @return
     */
    public boolean addEmotion(QqTStatusInfoPara status);

    /**
     * �����������ӣ�����QqTResponse
     * 
     * @param status ����������Ϣ
     * @return
     */
    public QqTResponse addEmotionRes(QqTStatusInfoPara status);

    /**
     * ��������״̬�������ղء�ɾ����ͨ��api�������ַ���
     * 
     * @param url ������url
     * @param format �������ݸ�ʽ
     * @param statusId ΢��id
     * @return
     */
    public String operateStatusCommonStr(String url, String format, long statusId);

    /**
     * ��������״̬ͨ��api�������Ƿ�����ɹ�
     * 
     * @param url ������url
     * @param statusId ΢��id
     * @return
     */
    public boolean operateStatusCommon(String url, long statusId);

    /**
     * ��������״̬ͨ��api������QqTResponse
     * 
     * @param url ������url
     * @param statusId ΢��id
     * @return
     */
    public QqTResponse operateStatusCommonRes(String url, long statusId);

    /**
     * ɾ��һ��΢��
     * 
     * @param statusId ΢��id
     * @return �Ƿ�ɹ�ɾ��
     */
    public boolean delete(long statusId);

    /**
     * ɾ��һ��΢��������QqTResponse
     * 
     * @param statusId ΢��id
     * @return
     */
    public QqTResponse deleteRes(long statusId);

    /**
     * ת������������������ַ���
     * 
     * @param format ������Ϣ��ʽ
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ���
     * @return
     */
    public String getRepostAndCommentCount(String format, String statusIds, int flag);

    /**
     * ת�����������
     * 
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ��� {@link QqTConstant#VALUE_RE_COUNT_FLAG_ALL}��ʾת�����͵�������
     *            {@link QqTConstant#VALUE_RE_COUNT_FLAG_REPOST}��ʾת������ {@link QqTConstant#VALUE_RE_COUNT_FLAG_COMMENT}
     *            ��ʾ������
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>����{@link QqTSdkService#getRepostAndCommentCount(String, String, int)}��ת��Ϊ����</li>
     *         <li>���صĶ���ֻ����statusId�Լ�commentCount��repostCount֮һ��ȫ��������flag����</li>
     *         </ul>
     */
    public List<QqTStatus> getRepostAndCommentCount(String statusIds, int flag);

    /**
     * ת���������������QqTResponse����
     * 
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ��� {@link QqTConstant#VALUE_RE_COUNT_FLAG_ALL}��ʾת�����͵�������
     *            {@link QqTConstant#VALUE_RE_COUNT_FLAG_REPOST}��ʾת������ {@link QqTConstant#VALUE_RE_COUNT_FLAG_COMMENT}
     *            ��ʾ������
     * @return ��QqTResponse����ʽ����
     *         <ul>
     *         <li>����{@link QqTSdkService#getRepostAndCommentCount(String, String, int)}��ת��Ϊ����</li>
     *         <li>���صĶ���ֻ����statusId�Լ�commentCount��repostCount֮һ��ȫ��������flag����</li>
     *         </ul>
     */
    public QqTResponse getRepostAndCommentCountRes(String statusIds, int flag);

    /**
     * ת�����͵�����
     * 
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>����{@link QqTSdkService#getRepostAndCommentCount(String, String, int)}��ת��Ϊ����</li>
     *         <li>���صĶ���ֻ����statusId��commentCount��repostCount</li>
     *         </ul>
     */
    public List<QqTStatus> getRepostAndCommentCount(String statusIds);

    /**
     * ת�����͵���������QqTResponse����
     * 
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>����{@link QqTSdkService#getRepostAndCommentCount(String, String, int)}��ת��Ϊ����</li>
     *         <li>���صĶ���ֻ����statusId��commentCount��repostCount</li>
     *         </ul>
     */
    public QqTResponse getRepostAndCommentCountRes(String statusIds);

    /**
     * ת�����������
     * 
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ���
     * @return ��map����ʽ����, keyΪ״̬id��nameΪ��������ת����
     *         <ul>
     *         <li>����{@link QqTSdkService#getRepostAndCommentCount(String, String, int)}��ת��Ϊ����</li>
     *         <li>�����ַ����õ�id�͵�������ת������Ϣ</li>
     *         </ul>
     */
    public Map<Long, Integer> getRepostOrCommentCount(String statusIds, int flag);

    /**
     * ת���������������QqTResponse����
     * 
     * @param statusIds ΢��id���Զ��ŷָ�
     * @param flag ���
     * @return ��map����ʽ����, keyΪ״̬id��nameΪ��������ת����
     *         <ul>
     *         <li>����{@link QqTSdkService#getRepostAndCommentCount(String, String, int)}��ת��Ϊ����</li>
     *         <li>�����ַ����õ�id�͵�������ת������Ϣ</li>
     *         </ul>
     */
    public QqTResponse getRepostOrCommentCountRes(String statusIds, int flag);

    /**
     * ��ȡ�Լ�����ϸ����
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E8%8E%B7%E5%8F%96%E8%87%AA%E5%B7%B1%E7%9A%84%E8%AF%A6%E7%BB%86%E8%B5%84%E6%96%99"
     * >��Ѷ΢��<strong>��ȡ�Լ�����ϸ����</strong>api</a>
     * 
     * @param format ������Ϣ��ʽ
     * @return
     */
    public String getSelfInfo(String format);

    /**
     * ��ȡ�Լ�����ϸ���ϣ�ת��ΪQqTUser
     * 
     * @return
     */
    public QqTUser getSelfInfo();

    /**
     * ��ȡ�Լ�����ϸ���ϣ�ת��ΪQqTResponse
     * 
     * @return
     */
    public QqTResponse getSelfInfoRes();

    /**
     * �����û���Ϣ�������ַ���
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E6%9B%B4%E6%96%B0%E7%94%A8%E6%88%B7%E4%BF%A1%E6%81%AF"
     * >��Ѷ΢��<strong>�����û���Ϣ</strong>api</a>
     * 
     * @param qqTUserPara �û���Ϣ
     * @return
     */
    public String updateSelfInfoStr(QqTUserPara qqTUserPara);

    /**
     * �����û���Ϣ
     * 
     * @param qqTUserPara �û���Ϣ
     * @return
     */
    public boolean updateSelfInfo(QqTUserPara qqTUserPara);

    /**
     * �����û���Ϣ������QqTResponse
     * 
     * @param qqTUserPara �û���Ϣ
     * @return
     */
    public QqTResponse updateSelfInfoRes(QqTUserPara qqTUserPara);

    /**
     * �����û�ͷ����Ϣ�������ַ���
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E6%9B%B4%E6%96%B0%E7%94%A8%E6%88%B7%E5%A4%B4%E5%83%8F%E4%BF%A1%E6%81%AF"
     * >��Ѷ΢��<strong>�����û�ͷ����Ϣ</strong>api</a>
     * 
     * @param format �������ݸ�ʽ
     * @param headImagePath ͷ��ͼƬ·��
     * @return
     */
    public String updateSelfHeadStr(String format, String headImagePath);

    /**
     * �����û�ͷ����Ϣ�������Ƿ�����ɹ�
     * 
     * @param headImagePath ͷ��ͼƬ·��
     * @return
     */
    public boolean updateSelfHead(String headImagePath);

    /**
     * �����û�ͷ����Ϣ������QqTResponse
     * 
     * @param headImagePath ͷ��ͼƬ·��
     * @return
     */
    public QqTResponse updateSelfHeadRes(String headImagePath);

    /**
     * �����û�������Ϣ�������ַ���
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E6%9B%B4%E6%96%B0%E7%94%A8%E6%88%B7%E6%95%99%E8%82%B2%E4%BF%A1%E6%81%AF"
     * >��Ѷ΢��<strong>�����û�������Ϣ</strong>api</a>
     * <ul>
     * <li>��ӽ�����Ϣ, {@link QqTUserEduPara#setFeildId(long)} feildId=1</li>
     * <li>�޸Ľ�����Ϣ��{@link QqTUserEduPara#setFeildId(long)} ��ص�feildId</li>
     * <li>ɾ��������Ϣ��{@link QqTUserEduPara#setFeildId(long)} ��ص�feildId�����������format��Ϊ��</li>
     * </ul>
     * 
     * @param qqTUserEduPara �û�������Ϣ
     * @return
     */
    public String updateSelfEduInfoStr(QqTUserEduPara qqTUserEduPara);

    /**
     * �����û�������Ϣ
     * <ul>
     * <li>��ӽ�����Ϣ, {@link QqTUserEduPara#setFeildId(long)} feildId=1</li>
     * <li>�޸Ľ�����Ϣ��{@link QqTUserEduPara#setFeildId(long)} ��ص�feildId</li>
     * <li>ɾ��������Ϣ��{@link QqTUserEduPara#setFeildId(long)} ��ص�feildId�����������format��Ϊ��</li>
     * </ul>
     * 
     * @param qqTUserEduPara �û�������Ϣ
     * @return
     */
    public boolean updateSelfEduInfo(QqTUserEduPara qqTUserEduPara);

    /**
     * �����û�������Ϣ������QqTResponse
     * <ul>
     * <li>��ӽ�����Ϣ, {@link QqTUserEduPara#setFeildId(long)} feildId=1</li>
     * <li>�޸Ľ�����Ϣ��{@link QqTUserEduPara#setFeildId(long)} ��ص�feildId</li>
     * <li>ɾ��������Ϣ��{@link QqTUserEduPara#setFeildId(long)} ��ص�feildId�����������format��Ϊ��</li>
     * </ul>
     * 
     * @param qqTUserEduPara �û�������Ϣ
     * @return
     */
    public QqTResponse updateSelfEduInfoRes(QqTUserEduPara qqTUserEduPara);

    /**
     * ��ȡ����������
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E8%8E%B7%E5%8F%96%E5%85%B6%E4%BB%96%E4%BA%BA%E8%B5%84%E6%96%99"
     * >��Ѷ΢��<strong>��ȡ����������</strong>api</a>
     * <ul>
     * <li>ֻ�����userName��ȡ��Ϣ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId��ȡ��Ϣ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format ������Ϣ��ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String getOtherUserInfo(String format, String userName, String userOpenId);

    /**
     * ��ȡ���������ϣ�ת��ΪQqTUser
     * <ul>
     * <li>ֻ�����userName��ȡ��Ϣ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId��ȡ��Ϣ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTUser getOtherUserInfo(String userName, String userOpenId);

    /**
     * ��ȡ���������ϣ�ת��ΪQqTResponse
     * <ul>
     * <li>ֻ�����userName��ȡ��Ϣ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId��ȡ��Ϣ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTResponse getOtherUserInfoRes(String userName, String userOpenId);

    /**
     * ��ȡһ���˵ļ�����
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E8%8E%B7%E5%8F%96%E4%B8%80%E6%89%B9%E4%BA%BA%E7%9A%84%E7%AE%80%E5%8D%95%E8%B5%84%E6%96%99"
     * >��Ѷ΢��<strong>��ȡһ���˵ļ�����</strong>api</a>
     * <ul>
     * <li>ֻ�����userNames��ȡ��Ϣ����userOpenIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenIds��ȡ��Ϣ����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userOpenIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param format ������Ϣ��ʽ
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public String getOtherUsersInfo(String format, String userNames, String userOpenIds);

    /**
     * ��ȡһ���˵ļ����ϣ�ת��ΪQqTUser list
     * <ul>
     * <li>ֻ�����userNames��ȡ��Ϣ����userOpenIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenIds��ȡ��Ϣ����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userOpenIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public List<QqTUser> getOtherUsersInfo(String userNames, String userOpenIds);

    /**
     * ��ȡһ���˵ļ����ϣ�ת��ΪQqTResponse
     * <ul>
     * <li>ֻ�����userNames��ȡ��Ϣ����userOpenIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenIds��ȡ��Ϣ����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userOpenIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public QqTResponse getOtherUsersInfoRes(String userNames, String userOpenIds);

    /**
     * ��֤�˻��Ƿ�Ϸ��������ַ���
     * <ul>
     * <li>ֻ�谴��userName��֤����userId����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userId��֤����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * ��ϸ�μ�<a href=
     * "http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E9%AA%8C%E8%AF%81%E8%B4%A6%E6%88%B7%E6%98%AF%E5%90%A6%E5%90%88%E6%B3%95"
     * >��Ѷ΢��<strong>��֤�˻��Ƿ�Ϸ�</strong>api</a>
     * 
     * @param format ���ص����ݸ�ʽ
     * @param userName �û���
     * @param userId �û�Id
     * @return
     */
    public String verifyAccountStr(String format, String userName, String userId);

    /**
     * ��֤�˻��Ƿ�Ϸ�
     * 
     * @param userName �û���
     * @param userId �û�Id
     * @return
     *         <ul>
     *         <li>����{@link QqTSdkService#verifyAccountStr(String, String, String)}������ж�</li>
     *         <li>�����͵����󷵻ش�������û������ڽԻ᷵��false</li>
     *         <li>�û����ڷ���true</li>
     *         </ul>
     */
    public boolean verifyAccount(String userName, String userId);

    /**
     * ��֤�˻��Ƿ�Ϸ�������QqTResponse
     * 
     * @param userName �û���
     * @param userId �û�Id
     * @return
     *         <ul>
     *         <li>����{@link QqTSdkService#verifyAccountStr(String, String, String)}</li>
     *         </ul>
     */
    public QqTResponse verifyAccountRes(String userName, String userId);

    /**
     * �õ���ĳ���й�ϵ���û���Ϣͨ��api�������ַ���
     * 
     * @param url ��ȡ��ϵ��url
     * @param qqTUserRelationPara ��ȡ��ϵ�Ĳ���
     * @return
     */
    public String getUserRelationsCommonStr(String url, QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ĳ���й�ϵ���û���Ϣͨ��api������QqTUser list
     * 
     * @param url ��ȡ��ϵ��url
     * @param qqTUserRelationPara ��ȡ��ϵ�Ĳ���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�QqTUserRelationPara������{@link QqTUserRelationPara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserRelationsCommonStr(String, QqTUserRelationPara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTUser> getUserRelationsCommon(String url, QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ĳ���й�ϵ���û���Ϣͨ��api������QqTResponse
     * 
     * @param url ��ȡ��ϵ��url
     * @param qqTUserRelationPara ��ȡ��ϵ�Ĳ���
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�QqTUserRelationPara������{@link QqTUserRelationPara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserRelationsCommonStr(String, QqTUserRelationPara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getUserRelationsCommonRes(String url, QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ĳ���й�ϵ���û�������Ϣͨ��api������String list
     * 
     * @param url ��ȡ��ϵ��url
     * @param qqTUserRelationPara ��ȡ��ϵ�Ĳ���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�QqTUserRelationPara������{@link QqTUserRelationPara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserRelationsCommonStr(String, QqTUserRelationPara)} ��ת��ΪString List</li>
     *         </ul>
     */
    public List<String> getUserRelationsNameCommon(String url, QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ĳ���й�ϵ���û�������Ϣͨ��api������QqTResponse
     * 
     * @param url ��ȡ��ϵ��url
     * @param qqTUserRelationPara ��ȡ��ϵ�Ĳ���
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�QqTUserRelationPara������{@link QqTUserRelationPara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getUserRelationsCommonStr(String, QqTUserRelationPara)} ��ת��ΪString List</li>
     *         </ul>
     */
    public QqTResponse getUserRelationsNameCommonRes(String url, QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û���Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfFansStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û���Ϣ���Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getSelfFans(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û���Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfFansStrRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfFansStr(String format, int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û���Ϣ���Զ���list��ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getSelfFans(int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û���Ϣ����QqTResponse������ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfFansRes(int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û�������Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfFansNamesStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û�������Ϣ����String list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<String> getSelfFansNames(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û�������Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfFansNamesRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û�������Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfFansNamesStr(String format, int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û�������Ϣ����String list��ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<String> getSelfFansNames(int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û�������Ϣ����QqTResponse������ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfFansNamesRes(int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û���Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfInterestedStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û���Ϣ���Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getSelfInterested(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û���Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfInterestedRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfInterestedStr(String format, int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û���Ϣ���Զ���list��ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getSelfInterested(int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û���Ϣ����QqTResponse������ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfInterestedRes(int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û�������Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfInterestedNamesStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û�������Ϣ����String list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<String> getSelfInterestedNames(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û�������Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfInterestedNamesRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û�������Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfInterestedNamesStr(String format, int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û�������Ϣ����String list��ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<String> getSelfInterestedNames(int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û�������Ϣ����QqTResponse������ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfInterestedNamesRes(int reqNumber, int startIndex);

    /**
     * �õ��Լ��������е��û���Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfBlackListStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ��������е��û���Ϣ���Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getSelfBlackList(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ��������е��û���Ϣ����QqTResponse��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfBlackListRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ��������е��û���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfBlackListStr(String format, int reqNumber, int startIndex);

    /**
     * �õ��Լ��������е��û���Ϣ���Զ���list��ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getSelfBlackList(int reqNumber, int startIndex);

    /**
     * �õ��Լ��������е��û���Ϣ����QqTResponse������ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfBlackListRes(int reqNumber, int startIndex);

    /**
     * �õ��Լ��ر��ע���û���Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfSpecialInterestedStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ��ر��ע���û���Ϣ���Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getSelfSpecialInterested(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ��ر��ע���û���Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfSpecialInterestedRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ��ر��ע���û���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfSpecialInterestedStr(String format, int reqNumber, int startIndex);

    /**
     * �õ��Լ��ر��ע���û���Ϣ���Զ���list��ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getSelfSpecialInterested(int reqNumber, int startIndex);

    /**
     * �õ��Լ��ر��ע���û���Ϣ����QqTResponse������ʽ����
     * 
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfSpecialInterestedRes(int reqNumber, int startIndex);

    /**
     * �õ������û��ķ�˿��Ϣ�����ַ�����ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getOtherUserFansStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û��ķ�˿��Ϣ���Զ���list��ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getOtherUserFans(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û��ķ�˿��Ϣ����QqTResponse���󷵻�
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getOtherUserFansRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û��ķ�˿��Ϣ�����ַ�����ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format ������Ϣ��ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getOtherUserFansStr(String format, String userName, String userOpenId, int reqNumber, int startIndex);

    /**
     * �õ������û��ķ�˿��Ϣ���Զ���list��ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getOtherUserFans(String userName, String userOpenId, int reqNumber, int startIndex);

    /**
     * �õ������û��ķ�˿��Ϣ����QqTResponse���󷵻�
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getOtherUserFansRes(String userName, String userOpenId, int reqNumber, int startIndex);

    /**
     * �õ������û���ע���û���Ϣ�����ַ�����ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getOtherUserInterestedStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û���ע���û���Ϣ���Զ���list��ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getOtherUserInterested(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û���ע���û���Ϣ����QqTResponse������ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getOtherUserInterestedRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û���ע���û���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getOtherUserInterestedStr(String format, String userName, String userOpenId, int reqNumber,
                                            int startIndex);

    /**
     * �õ������û���ע���û���Ϣ���Զ���list��ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getOtherUserInterested(String userName, String userOpenId, int reqNumber, int startIndex);

    /**
     * �õ������û���ע���û���Ϣ����QqTResponse������ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getOtherUserInterestedRes(String userName, String userOpenId, int reqNumber, int startIndex);

    /**
     * �õ������û��ر��ע���û���Ϣ�����ַ�����ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getOtherUserSpecialInterestedStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û��ر��ע���û���Ϣ���Զ���list��ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getOtherUserSpecialInterested(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û��ر��ע���û���Ϣ����QqTResponse������ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getOtherUserSpecialInterestedRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ������û��ر��ע���û���Ϣ�����ַ�����ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format ������Ϣ��ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getOtherUserSpecialInterestedStr(String format, String userName, String userOpenId, int reqNumber,
                                                   int startIndex);

    /**
     * �õ������û��ر��ע���û���Ϣ���Զ���list��ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getOtherUserSpecialInterested(String userName, String userOpenId, int reqNumber, int startIndex);

    /**
     * �õ������û��ر��ע���û���Ϣ����QqTResponse������ʽ����
     * <ul>
     * <li>userName��userOpenId����ѡһ������ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getOtherUserSpecialInterestedRes(String userName, String userOpenId, int reqNumber,
                                                        int startIndex);

    /**
     * �õ���ע�Լ����û��ļ���Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfFansSimpleInfoStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û��ļ���Ϣ���Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getSelfFansSimpleInfo(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û��ļ���Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfFansSimpleInfoRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ���ע�Լ����û��ļ���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfFansSimpleInfoStr(String format, int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û��ļ���Ϣ���Զ���list��ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getSelfFansSimpleInfo(int reqNumber, int startIndex);

    /**
     * �õ���ע�Լ����û��ļ���Ϣ����QqTResponse������ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfFansSimpleInfoRes(int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û��ļ���Ϣ�����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getSelfInterestedSimpleInfoStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û��ļ���Ϣ���Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getSelfInterestedSimpleInfo(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û��ļ���Ϣ����QqTResponse������ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getSelfInterestedSimpleInfoRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ��Լ���ע���û��ļ���Ϣ�����ַ�����ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getSelfInterestedSimpleInfoStr(String format, int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û��ļ���Ϣ���Զ���list��ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getSelfInterestedSimpleInfo(int reqNumber, int startIndex);

    /**
     * �õ��Լ���ע���û��ļ���Ϣ����QqTResponse������ʽ����
     * 
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getSelfInterestedSimpleInfoRes(int reqNumber, int startIndex);

    /**
     * �õ�������ϵ���б����ַ�����ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public String getMutualInterestedStr(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ�������ϵ���б��Զ���list��ʽ����
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public List<QqTUser> getMutualInterested(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ�������ϵ���б���QqTResponse���󷵻�
     * 
     * @param qqTUserRelationPara
     * @return
     */
    public QqTResponse getMutualInterestedRes(QqTUserRelationPara qqTUserRelationPara);

    /**
     * �õ�������ϵ���б����ַ�����ʽ����
     * 
     * @param userName �û���
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public String getMutualInterestedStr(String format, String userName, int reqNumber, int startIndex);

    /**
     * �õ�������ϵ���б��Զ���list��ʽ����
     * 
     * @param userName �û���
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public List<QqTUser> getMutualInterested(int reqNumber, String userName, int startIndex);

    /**
     * �õ�������ϵ���б���QqTResponse���󷵻�
     * 
     * @param userName �û���
     * @param format ������Ϣ��ʽ
     * @param reqNumber �������(1-30)
     * @param startIndex ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1))
     * @return
     */
    public QqTResponse getMutualInterestedRes(int reqNumber, String userName, int startIndex);

    /**
     * ��ĳ�˽�����ȡ��ĳ�ֹ�ϵͨ��api�������ַ���
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format ������Ϣ��ʽ
     * @param url ������ϵ��url
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String relationWithOtherCommonStr(String format, String url, String userName, String userOpenId);

    /**
     * ��ĳ�˽�����ȡ��ĳ�ֹ�ϵͨ��api�������Ƿ�����ɹ�
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param url ������ϵ��url
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return �Ƿ�����ɹ�
     *         <ul>
     *         <li>����{@link QqTSdkService#relationWithOtherCommonStr(String, String, String, String)}</li>
     *         </ul>
     */
    public boolean relationWithOtherCommon(String url, String userName, String userOpenId);

    /**
     * ��ĳ�˽�����ȡ��ĳ�ֹ�ϵͨ��api������QqTResponse
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param url ������ϵ��url
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return �Ƿ�����ɹ�
     *         <ul>
     *         <li>����{@link QqTSdkService#relationWithOtherCommonStr(String, String, String, String)}</li>
     *         </ul>
     */
    public QqTResponse relationWithOtherCommonRes(String url, String userName, String userOpenId);

    /**
     * ����(��ע)ĳ�ˣ������ַ���
     * <ul>
     * <li>ֻ�����userNames������ϵ����userOpenIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenIds������ϵ����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userOpenIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public String interestedInOther(String format, String userNames, String userOpenIds);

    /**
     * ����(��ע)ĳ�ˣ����ز����Ƿ�ɹ�
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userOpenIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userOpenIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public boolean interestedInOther(String userNames, String userOpenIds);

    /**
     * ����(��ע)ĳ�ˣ�����QqTResponse
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userOpenIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userOpenIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public QqTResponse interestedInOtherRes(String userNames, String userOpenIds);

    /**
     * ȡ������(��ע)ĳ�ˣ������ַ���
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String cancelInterestedInOther(String format, String userName, String userOpenId);

    /**
     * ȡ������(��ע)ĳ�ˣ����ز����Ƿ�ɹ�
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public boolean cancelInterestedInOther(String userName, String userOpenId);

    /**
     * ȡ������(��ע)ĳ�ˣ�����QqTResponse
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTResponse cancelInterestedInOtherRes(String userName, String userOpenId);

    /**
     * �ر�����(��ע)ĳ�ˣ������ַ���
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String specialInterestedInOther(String format, String userName, String userOpenId);

    /**
     * �ر�����(��ע)ĳ�ˣ����ز����Ƿ�ɹ�
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public boolean specialInterestedInOther(String userName, String userOpenId);

    /**
     * �ر�����(��ע)ĳ�ˣ�����QqTResponse
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTResponse specialInterestedInOtherRes(String userName, String userOpenId);

    /**
     * ȡ���ر�����(��ע)ĳ�ˣ������ַ���
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String cancelSpecialInterestedInOther(String format, String userName, String userOpenId);

    /**
     * ȡ���ر�����(��ע)ĳ�ˣ����ز����Ƿ�ɹ�
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public boolean cancelSpecialInterestedInOther(String userName, String userOpenId);

    /**
     * ȡ���ر�����(��ע)ĳ�ˣ�����QqTResponse
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTResponse cancelSpecialInterestedInOtherRes(String userName, String userOpenId);

    /**
     * ���ĳ���û����������������ַ���
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String addOtherToBlackList(String format, String userName, String userOpenId);

    /**
     * ���ĳ���û��������������ز����Ƿ�ɹ�
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public boolean addOtherToBlackList(String userName, String userOpenId);

    /**
     * ���ĳ���û���������������QqTResponse
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTResponse addOtherToBlackListRes(String userName, String userOpenId);

    /**
     * �Ӻ�������ɾ��ĳ���û��������ַ���
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public String deleteFromBlackList(String format, String userName, String userOpenId);

    /**
     * �Ӻ�������ɾ��ĳ���û������ز����Ƿ�ɹ�
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public boolean deleteFromBlackList(String userName, String userOpenId);

    /**
     * �Ӻ�������ɾ��ĳ���û�������QqTResponse
     * <ul>
     * <li>ֻ�����userName������ϵ����userOpenId����{@code null}���߿��ַ���</li>
     * <li>ֻ�����userOpenId������ϵ����userName����{@code null}���߿��ַ���</li>
     * <li>userName��userOpenId������һ����Ϊ�գ���ͬʱ��������userNameֵΪ��</li>
     * </ul>
     * 
     * @param userName �û���
     * @param userOpenId �û�openid
     * @return
     */
    public QqTResponse deleteFromBlackListRes(String userName, String userOpenId);

    /**
     * ����Ƿ��ҵ����ڻ��������ˣ������ַ���
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param format �������ݸ�ʽ
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @param flag ��ϵ���
     * @return
     */
    public String checkRelationWithSelf(String format, String userNames, String userOpenIds, int flag);

    /**
     * ����Ƿ��ҵ����ڻ��������ˣ������û����Լ��Ĺ�ϵ
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public List<QqTUserRelation> getIsFanAndInterested(String userNames, String userOpenIds);

    /**
     * ����Ƿ��ҵ����ڻ��������ˣ�����QqTResponse
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public QqTResponse getIsFanAndInterestedRes(String userNames, String userOpenIds);

    /**
     * ����Ƿ��ҵ����ڻ��������ˣ�����map��keyΪ�û�����valueΪtrue��false����ʾ�Ƿ������ڻ���������
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public Map<String, Boolean> getIsFanOrInterested(String userNames, String userOpenIds, int flag);

    /**
     * ����Ƿ��ҵ����ڻ��������ˣ�����QqTResponse
     * <ul>
     * <li>ֻ�谴��userNames��֤����userIds����{@code null}���߿��ַ���</li>
     * <li>ֻ�谴��userIds��֤����userNames����{@code null}���߿��ַ���</li>
     * <li>userNames��userIds������һ����Ϊ�գ���ͬʱ��������userNamesֵΪ��</li>
     * </ul>
     * 
     * @param userNames �û����ʻ����б����������","����
     * @param userOpenIds �û�openid�б����������"_"����
     * @return
     */
    public QqTResponse getIsFanOrInterestedRes(String userNames, String userOpenIds, int flag);

    /**
     * ��˽�ţ�����format�����ַ���
     * 
     * @param message ˽������
     * @return
     */
    public String sendMessageStr(QqTStatusInfoPara message);

    /**
     * ��˽�ţ������Ƿ񷢱�ɹ�
     * 
     * @param message ˽������
     * @return �����Ƿ񷢱�ɹ�
     *         <ul>
     *         <li>�˺�����ı�message������{@link QqTStatusInfoPara#setFormat(String)}Ϊ {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#sendMessageStr(QqTStatusInfoPara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public boolean sendMessage(QqTStatusInfoPara message);

    /**
     * ��˽�ţ�����QqTResponse
     * 
     * @param message ˽������
     * @return ����QqTResponse
     *         <ul>
     *         <li>�˺�����ı�message������{@link QqTStatusInfoPara#setFormat(String)}Ϊ {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#sendMessageStr(QqTStatusInfoPara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse sendMessageRes(QqTStatusInfoPara message);

    /**
     * ɾ��һ��˽��
     * 
     * @param messageId ˽��id
     * @return �Ƿ�ɹ�����
     */
    public boolean deleteMessage(long messageId);

    /**
     * ɾ��һ��˽�ţ�����QqTResponse
     * 
     * @param messageId ˽��id
     * @return
     */
    public QqTResponse deleteMessageRes(long messageId);

    /**
     * �ռ��䣬��ʾ�յ���˽���б�
     * ���������<a
     * href="http://wiki.open.t.qq.com/index.php/%E7%A7%81%E4%BF%A1%E7%9B%B8%E5%85%B3/%E6%94%B6%E4%BB%B6%E7%AE%B1"
     * >��Ѷ΢��<strong>˽���ռ���</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getReceiveMessagesStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �ռ��䣬��ʾ�յ���˽���б�
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getReceiveMessagesStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getReceiveMessages(QqTTimelinePara qqTTimelinePara);

    /**
     * �ռ��䣬��ʾ�յ���˽���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getReceiveMessagesStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getReceiveMessagesRes(QqTTimelinePara qqTTimelinePara);

    /**
     * �����䣬��ʾ������˽���б�<br/>
     * ���������<a
     * href="http://wiki.open.t.qq.com/index.php/%E7%A7%81%E4%BF%A1%E7%9B%B8%E5%85%B3/%E5%8F%91%E4%BB%B6%E7%AE%B1"
     * >��Ѷ΢��<strong>˽�ŷ�����</strong>api</a>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getSendMessagesStr(QqTTimelinePara qqTTimelinePara);

    /**
     * �����䣬��ʾ������˽���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getSendMessagesStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getSendMessages(QqTTimelinePara qqTTimelinePara);

    /**
     * �����䣬��ʾ������˽���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getSendMessagesStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getSendMessagesRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ����ͨ��api�������ַ���
     * 
     * @param url ����url
     * @param qqTSearchPara ��������
     * @return
     */
    public String searchCommonStr(String url, QqTSearchPara qqTSearchPara);

    /**
     * �����û�ͨ��api�������û��б�
     * 
     * @param url ����url
     * @param qqTSearchPara ��������
     * @return
     */
    public List<QqTUser> searchUserCommon(String url, QqTSearchPara qqTSearchPara);

    /**
     * �����û�ͨ��api������QqTResponse
     * 
     * @param url ����url
     * @param qqTSearchPara ��������
     * @return
     */
    public QqTResponse searchUserCommonRes(String url, QqTSearchPara qqTSearchPara);

    /**
     * ���ݹؼ��������û�api�������ַ���
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public String searchUserStr(QqTSearchPara qqTSearchPara);

    /**
     * ���ݹؼ��������û�api�������û��б�
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public List<QqTUser> searchUser(QqTSearchPara qqTSearchPara);

    /**
     * ���ݹؼ��������û�api������QqTResponse
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public QqTResponse searchUserRes(QqTSearchPara qqTSearchPara);

    /**
     * ���ݹؼ�������΢��api�������ַ���
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public String searchStatusStr(QqTSearchPara qqTSearchPara);

    /**
     * ���ݹؼ�������΢��api������΢���б�
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public List<QqTStatus> searchStatus(QqTSearchPara qqTSearchPara);

    /**
     * ���ݹؼ�������΢��api������QqTResponse
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public QqTResponse searchStatusRes(QqTSearchPara qqTSearchPara);

    /**
     * ���ݱ�ǩ�����û�api�������ַ���
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public String searchUserByTagStr(QqTSearchPara qqTSearchPara);

    /**
     * ���ݱ�ǩ�����û�api�������û��б�
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public List<QqTUser> searchUserByTag(QqTSearchPara qqTSearchPara);

    /**
     * ���ݱ�ǩ�����û�api������QqTResponse
     * 
     * @param qqTSearchPara ��������
     * @return
     */
    public QqTResponse searchUserByTagRes(QqTSearchPara qqTSearchPara);

    /**
     * �Ȱ�ͨ��api�������ַ���
     * 
     * @param url ���ӵ�ַ
     * @param qqTHotStatusPara ����
     * @return
     */
    public String getHotCommonStr(String url, QqTHotStatusPara qqTHotStatusPara);

    /**
     * �����Ȱ񣬷����ַ���
     * 
     * @param qqTHotStatusPara ����
     * @return
     */
    public String getHotTopicsStr(QqTHotStatusPara qqTHotStatusPara);

    /**
     * �����Ȱ񣬷���QqTTopicSimple list
     * 
     * @param qqTHotStatusPara ����
     * @return
     */
    public List<QqTTopicSimple> getHotTopics(QqTHotStatusPara qqTHotStatusPara);

    /**
     * �����Ȱ񣬷���QqTResponse
     * 
     * @param qqTHotStatusPara ����
     * @return
     */
    public QqTResponse getHotTopicsRes(QqTHotStatusPara qqTHotStatusPara);

    /**
     * ת���Ȱ񣬷����ַ���
     * 
     * @param qqTHotStatusPara ����
     * @return
     */
    public String getHotRepostsStr(QqTHotStatusPara qqTHotStatusPara);

    /**
     * ת���Ȱ񣬷���QqTStatus List
     * 
     * @param qqTHotStatusPara ����
     * @return
     */
    public List<QqTStatus> getHotReposts(QqTHotStatusPara qqTHotStatusPara);

    /**
     * ת���Ȱ񣬷���QqTResponse
     * 
     * @param qqTHotStatusPara ����
     * @return
     */
    public QqTResponse getHotRepostsRes(QqTHotStatusPara qqTHotStatusPara);

    /**
     * ������ݸ��������������ַ���
     * 
     * @param format ���ݷ��ظ�ʽ
     * @param isClear �Ƿ��ڲ鿴���ݺ����������
     * @param clearType ������Ϊtrueʱ��������յĸ�����������
     * @return
     */
    public String getUpdateInfoNumStr(String format, boolean isClear, int clearType);

    /**
     * ������ݸ�������������QqTUpdateNumInfo
     * 
     * @param isClear �Ƿ��ڲ鿴���ݺ����������
     * @param clearType ������Ϊtrueʱ��������յĸ�����������
     * @return
     */
    public QqTUpdateNumInfo getUpdateInfoNum(boolean isClear, int clearType);

    /**
     * ������ݸ�������������QqTResponse
     * 
     * @param isClear �Ƿ��ڲ鿴���ݺ����������
     * @param clearType ������Ϊtrueʱ��������յĸ�����������
     * @return
     */
    public QqTResponse getUpdateInfoNumRes(boolean isClear, int clearType);

    /**
     * �ղ�һ��΢�����Ƿ�ɹ��ղ�
     * 
     * @param statusId ΢��id
     * @return
     */
    public boolean collect(long statusId);

    /**
     * �ղ�һ��΢��������QqTResponse
     * 
     * @param statusId ΢��id
     * @return
     */
    public QqTResponse collectRes(long statusId);

    /**
     * ȡ���ղ�һ��΢�����Ƿ�ɹ�ȡ��
     * 
     * @param statusId ΢��id
     * @return
     */
    public boolean unCollect(long statusId);

    /**
     * ȡ���ղ�һ��΢��������QqTResponse
     * 
     * @param statusId ΢��id
     * @return
     */
    public QqTResponse unCollectRes(long statusId);

    /**
     * ���Ļ��⣬�Ƿ�ɹ�����
     * 
     * @param topicId ����id
     * @return
     */
    public boolean subscribeTopic(long topicId);

    /**
     * ���Ļ��⣬����QqTResponse
     * 
     * @param topicId ����id
     * @return
     */
    public QqTResponse subscribeTopicRes(long topicId);

    /**
     * ȡ�����Ļ��⣬�Ƿ�ɹ�ȡ��
     * 
     * @param topicId ����id
     * @return
     */
    public boolean unSubscribeTopic(long topicId);

    /**
     * ȡ�����Ļ��⣬����QqTResponse
     * 
     * @param topicId ����id
     * @return
     */
    public QqTResponse unSubscribeTopicRes(long topicId);

    /**
     * ��ȡ�ղص�΢���б�<br/>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getCollectStatusesStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡ�ղص�΢���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getCollectStatusesStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getCollectStatuses(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡ�ղص�΢���б�<br/>
     * 
     * @param qqTTimelinePara
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getCollectStatusesStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getCollectStatusesRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ���ݻ������Ʋ�ѯ����id<br/>
     * 
     * @param format �������ݸ�ʽ
     * @param names ���������б��Զ��ŷָ�����abc,efg
     * @return ���ַ�������ʽ����
     */
    public String getTopicIdByNamesStr(String format, String names);

    /**
     * ���ݻ������Ʋ�ѯ����id������Map
     * 
     * @param format �������ݸ�ʽ
     * @param names ���������б��Զ��ŷָ�����abc,efg
     * @return ��map���أ�keyΪid��valueΪname
     */
    public Map<String, String> getTopicIdByNames(String names);

    /**
     * ���ݻ������Ʋ�ѯ����id������QqTResponse
     * 
     * @param format �������ݸ�ʽ
     * @param names ���������б��Զ��ŷָ�����abc,efg
     * @return ����QqTResponse��dataΪmap��keyΪid��valueΪname
     */
    public QqTResponse getTopicIdByNamesRes(String names);

    /**
     * ���ݻ���id��ȡ���������Ϣ<br/>
     * 
     * @param format �������ݸ�ʽ
     * @param ids ����id�б��Զ��ŷָ�����12345,12345���15��
     * @return ���ַ�������ʽ����
     */
    public String getTopicInfoByIdsStr(String format, String ids);

    /**
     * ���ݻ���id��ȡ���������Ϣ<br/>
     * 
     * @param ids ����id�б��Զ��ŷָ�����12345,12345���15��
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>����{@link QqTSdkService#getTopicInfoByIdsStr(String, String)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getTopicInfoByIds(String ids);

    /**
     * ���ݻ���id��ȡ���������Ϣ<br/>
     * 
     * @param ids ����id�б��Զ��ŷָ�����12345,12345���15��
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>����{@link QqTSdkService#getTopicInfoByIdsStr(String, String)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getTopicInfoByIdsRes(String ids);

    /**
     * ��ȡ�Ѷ��Ļ����б�<br/>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ���ַ�������ʽ����
     */
    public String getCollectTopicsStr(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡ�Ѷ��Ļ����б�<br/>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return �Զ���list����ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getCollectTopicsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public List<QqTStatus> getCollectTopics(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ȡ�Ѷ��Ļ����б�<br/>
     * 
     * @param qqTTimelinePara ʱ���߲���
     * @return ��QqTResponse�������ʽ����
     *         <ul>
     *         <li>�˺�����ı�qqTTimelinePara������{@link QqTTimelinePara#setFormat(String)}Ϊ
     *         {@link QqTConstant#VALUE_FORMAT_JSON}</li>
     *         <li>����{@link QqTSdkService#getCollectTopicsStr(QqTTimelinePara)}��ת��Ϊ����</li>
     *         </ul>
     */
    public QqTResponse getCollectTopicsRes(QqTTimelinePara qqTTimelinePara);

    /**
     * ��ӱ�ǩ�������ַ���
     * 
     * @param format ���ص����ݸ�ʽ
     * @param tagName ��ǩ��
     * @return
     */
    public String addTag(String format, String tagName);

    /**
     * ��ӱ�ǩ�������Ƿ���ӳɹ�
     * 
     * @param tagName ��ǩ��
     * @return
     */
    public boolean addTag(String tagName);

    /**
     * ��ӱ�ǩ������QqTResponse
     * 
     * @param tagName ��ǩ��
     * @return
     */
    public QqTResponse addTagRes(String tagName);

    /**
     * ɾ����ǩ�������ַ���
     * 
     * @param format ���ص����ݸ�ʽ
     * @param tagId ��ǩid
     * @return
     */
    public String deleteTag(String format, String tagId);

    /**
     * ɾ����ǩ�������Ƿ�ɾ���ɹ�
     * 
     * @param tagId ��ǩid
     * @return
     */
    public boolean deleteTag(String tagId);

    /**
     * ɾ����ǩ������QqTResponse
     * 
     * @param tagId ��ǩid
     * @return
     */
    public QqTResponse deleteTagRes(String tagId);

    /**
     * �õ�δ��Ȩ��request token
     * 
     * @param callBackUrl
     * @return
     *         <ul>
     *         <li>map����oauth_token, oauth_token_secret, oauth_callback_confirmed</li>
     *         </ul>
     */
    public Map<String, String> getUnAuthorizedRequestToken(String callBackUrl);

    /**
     * �õ���Ȩ��request token
     * 
     * @param String query url��query����
     * @return
     *         <ul>
     *         <li>map����oauth_token, oauth_verifier</li>
     *         </ul>
     */
    public Map<String, String> getAuthorizedRequestToken(String query);

    /**
     * �õ�access token
     * 
     * @param oauthToken ��õ�oauth token
     * @param oauthVerifier ��õ�oauth verifier
     * @param requestTokenSecret �����ʱrequest tokenʱ��token secret
     * @return
     *         <ul>
     *         <li>map����oauth_token, oauth_token_secret</li>
     *         </ul>
     */
    public Map<String, String> getAccessToken(String oauthToken, String oauthVerifier, String requestTokenSecret);
}
