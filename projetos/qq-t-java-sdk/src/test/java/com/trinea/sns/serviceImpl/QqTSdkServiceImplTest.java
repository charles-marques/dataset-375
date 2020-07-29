package com.trinea.sns.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.trinea.java.common.ListUtils;
import com.trinea.sns.entity.QqTAppAndToken;
import com.trinea.sns.entity.QqTHotStatusPara;
import com.trinea.sns.entity.QqTIdAndTime;
import com.trinea.sns.entity.QqTListData;
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
import com.trinea.sns.entity.QqTUserRelationPara;
import com.trinea.sns.util.QqTConstant;

public class QqTSdkServiceImplTest extends TestCase {

    /** Ӧ��key **/
    String            QQT_APP_KEY    = "***";
    /** Ӧ��secret **/
    String            QQT_APP_SECRET = "***";
    /** �û�accesstoken **/
    String            ACCESS_TOKEN   = "***";
    /** �û�tokenSecret **/
    String            TOKEN_SECRET   = "***";
    QqTAppAndToken    qqTAppAndToken;
    QqTSdkServiceImpl qqTSdkService;

    List<QqTStatus>   statusList;

    /** �õ��Լ���һҳ������ **/
    public List<QqTStatus> getSelfFirstPage() {
        /** ��һҳ **/
        QqTTimelinePara qqTTimelinePara = new QqTTimelinePara();
        qqTTimelinePara.setPageFlag(0);
        qqTTimelinePara.setPageTime(0);
        qqTTimelinePara.setLastId(0);
        qqTTimelinePara.setPageReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        /** ����������ȡ���� **/
        qqTTimelinePara.setStatusType(QqTConstant.VALUE_STATUS_TYPE_TL_ALL);
        /** ��������΢���������� **/
        qqTTimelinePara.setContentType(QqTConstant.VALUE_CONTENT_TYPE_TL_ALL);
        QqTResponse qqTResponse = qqTSdkService.getBroadcastTLRes(qqTTimelinePara);

        if (qqTResponse != null && qqTResponse.getData() != null
            && ((QqTListData)qqTResponse.getData()).getInfo() != null) {
            return (List<QqTStatus>)((QqTListData)qqTResponse.getData()).getInfo();
        }
        return null;
    }

    protected void setUp() throws Exception {
        super.setUp();

        qqTAppAndToken = new QqTAppAndToken();
        qqTAppAndToken.setAppKey(QQT_APP_KEY);
        qqTAppAndToken.setAppSecret(QQT_APP_SECRET);
        qqTAppAndToken.setAccessToken(ACCESS_TOKEN);
        qqTAppAndToken.setTokenSecret(TOKEN_SECRET);

        qqTSdkService = new QqTSdkServiceImpl();
        qqTSdkService.setQqTAppAndToken(qqTAppAndToken);
        statusList = new ArrayList<QqTStatus>();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetBroadcastTLRes() {
        /** ��һҳ **/
        QqTTimelinePara qqTTimelinePara = new QqTTimelinePara();
        qqTTimelinePara.setPageFlag(0);
        qqTTimelinePara.setPageTime(0);
        qqTTimelinePara.setLastId(0);
        qqTTimelinePara.setPageReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        /** ����������ȡ���� **/
        qqTTimelinePara.setStatusType(QqTConstant.VALUE_STATUS_TYPE_TL_ALL);
        /** ��������΢���������� **/
        qqTTimelinePara.setContentType(QqTConstant.VALUE_CONTENT_TYPE_TL_ALL);
        QqTResponse qqTResponse = qqTSdkService.getBroadcastTLRes(qqTTimelinePara);

        List<QqTResponse> qqTResponseList = new ArrayList<QqTResponse>();
        statusList.addAll((List<QqTStatus>)((QqTListData)qqTResponse.getData()).getInfo());
        qqTResponseList.add(qqTResponse);

        /** ��һҳ **/
        while (qqTResponseList.get(qqTResponseList.size() - 1) != null
               && ((QqTListData)qqTResponseList.get(qqTResponseList.size() - 1).getData()).hastNext()) {
            qqTTimelinePara.setPageFlag(QqTConstant.VALUE_NEXT_PAGE);
            qqTTimelinePara.setPageTime(statusList.get(statusList.size() - 1).getTime());
            qqTTimelinePara.setLastId(statusList.get(statusList.size() - 1).getStatusId());
            QqTResponse nextQqTResponse = qqTSdkService.getBroadcastTLRes(qqTTimelinePara);
            if (nextQqTResponse != null && nextQqTResponse.getData() != null) {
                statusList.addAll((List<QqTStatus>)((QqTListData)nextQqTResponse.getData()).getInfo());
                qqTResponseList.add(nextQqTResponse);
                if (((List<QqTStatus>)(((QqTListData)nextQqTResponse.getData()).getInfo())).size() < QqTConstant.VALUE_PAGE_REQ_NUM) {
                    break;
                }
            } else {
                break;
            }
        }

        if (statusList != null) {
            for (QqTStatus qqtStatus : statusList) {
                qqTSdkService.delete(qqtStatus.getStatusId());
            }
        }
        assertTrue(true);
    }

    public void testDeleteRes() {

        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("����һ����ɾ��΢��Res��43");
        QqTResponse qqTResponse = qqTSdkService.addStatusRes(status);

        /** ɾ�����ӵ�΢�� **/
        if (qqTResponse.getIsOk() && qqTResponse.getData() != null) {
            assertTrue(qqTSdkService.deleteRes(((QqTIdAndTime)qqTResponse.getData()).getId()) != null);
        } else {
            assertTrue(false);
        }
    }

    public void testGetTimeLineCommonStr() {
        qqTSdkService = new QqTSdkServiceImpl();
        qqTSdkService.setQqTAppAndToken(qqTAppAndToken);
        qqTSdkService.addStatus("lala", "");
        assertTrue(true);
    }

    public void testGetTimeLineCommon() {
        assertTrue(true);
    }

    public void testGetTimeLineCommonRes() {
        assertTrue(true);
    }

    public void testGetHomeTLStr() {
        QqTTimelinePara qqTTimelinePara = new QqTTimelinePara();
        /** ���÷��ؽ����ʽ **/
        qqTTimelinePara.setFormat(QqTConstant.VALUE_FORMAT_XML);
        /** ���÷�ҳ��ʶ **/
        qqTTimelinePara.setPageFlag(0);
        /** ������ʼʱ�� **/
        qqTTimelinePara.setPageTime(0);
        /** ÿ�������¼������ **/
        qqTTimelinePara.setPageReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        /** ����������ȡ���ͣ���ȡֵQqTConstant��VALUE_STATUS_TYPE_TL_��������ȡԭ������ **/
        qqTTimelinePara.setStatusType(QqTConstant.VALUE_STATUS_TYPE_TL_ORIGINAL);
        /** ��������΢���������ͣ���ȡֵQqTConstant��VALUE_CONTENT_TYPE_TL��������ȡ���� **/
        qqTTimelinePara.setContentType(QqTConstant.VALUE_CONTENT_TYPE_TL_MUSIC);
        String homeStr = qqTSdkService.getHomeTLStr(qqTTimelinePara);
        assertTrue(homeStr != null);
    }

    public void testGetHomeTL() {
        QqTTimelinePara qqTTimelinePara = new QqTTimelinePara();
        /** ���÷�ҳ��ʶ **/
        qqTTimelinePara.setPageFlag(0);
        /** ������ʼʱ�� **/
        qqTTimelinePara.setPageTime(0);
        /** ÿ�������¼������ **/
        qqTTimelinePara.setPageReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        /** ����������ȡ���ͣ���ȡֵQqTConstant��VALUE_STATUS_TYPE_TL_�� **/
        qqTTimelinePara.setStatusType(QqTConstant.VALUE_STATUS_TYPE_TL_ALL);
        /** ��������΢���������ͣ���ȡֵQqTConstant��VALUE_CONTENT_TYPE_TL�� **/
        qqTTimelinePara.setContentType(QqTConstant.VALUE_CONTENT_TYPE_TL_ALL);
        List<QqTStatus> qqTStatusList = qqTSdkService.getHomeTL(qqTTimelinePara);

        /** �ڶ�ҳ **/
        if (qqTStatusList.size() >= qqTTimelinePara.getPageReqNum()
            && qqTStatusList.get(qqTStatusList.size() - 1) != null) {
            qqTTimelinePara.setPageFlag(QqTConstant.VALUE_NEXT_PAGE);
            qqTTimelinePara.setPageTime(qqTStatusList.get(qqTStatusList.size() - 1).getTime());
            qqTTimelinePara.setLastId(qqTStatusList.get(qqTStatusList.size() - 1).getStatusId());
            List<QqTStatus> nextPageQqTStatusList = qqTSdkService.getHomeTL(qqTTimelinePara);
        }
        assertTrue(qqTStatusList != null);
    }

    public void testGetHomeTLRes() {
        QqTTimelinePara qqTTimelinePara = new QqTTimelinePara();
        /** ���÷�ҳ��ʶ **/
        qqTTimelinePara.setPageFlag(0);
        /** ������ʼʱ�� **/
        qqTTimelinePara.setPageTime(0);
        /** ÿ�������¼������ **/
        qqTTimelinePara.setPageReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        /** ����������ȡ���ͣ���ȡֵQqTConstant��VALUE_STATUS_TYPE_TL_�� **/
        qqTTimelinePara.setStatusType(QqTConstant.VALUE_STATUS_TYPE_TL_ALL);
        /** ��������΢���������ͣ���ȡֵQqTConstant��VALUE_CONTENT_TYPE_TL�� **/
        qqTTimelinePara.setContentType(QqTConstant.VALUE_CONTENT_TYPE_TL_ALL);
        QqTResponse qqTResponse = qqTSdkService.getHomeTLRes(qqTTimelinePara);
        assertTrue(qqTResponse != null);
    }

    public void testGetPublicTLStr() {
        assertTrue(true);
    }

    public void testGetPublicTL() {
        assertTrue(true);
    }

    public void testGetPublicTLRes() {
        assertTrue(true);
    }

    public void testGetUserTLStr() {
        assertTrue(true);
    }

    public void testGetUserTL() {
        assertTrue(true);
    }

    public void testGetUserTLRes() {
        assertTrue(true);
    }

    public void testGetMentionsTLStr() {
        assertTrue(true);
    }

    public void testGetMentionsTL() {
        assertTrue(true);
    }

    public void testGetMentionsTLRes() {
        assertTrue(true);
    }

    public void testGetTopicTLStr() {
        assertTrue(true);
    }

    public void testGetTopicTL() {
        assertTrue(true);
    }

    public void testGetTopicTLRes() {
        assertTrue(true);
    }

    public void testGetBroadcastTLStr() {
        assertTrue(true);
    }

    public void testGetBroadcastTL() {
        assertTrue(true);
    }

    public void testGetSpecialTLStr() {
        assertTrue(true);
    }

    public void testGetSpecialTL() {
        assertTrue(true);
    }

    public void testGetSpecialTLRes() {
        assertTrue(true);
    }

    public void testGetAreaTLStr() {
        assertTrue(true);
    }

    public void testGetAreaTL() {
        assertTrue(true);
    }

    public void testGetAreaTLRes() {
        assertTrue(true);
    }

    public void testGetHomeTLIdsStr() {
        assertTrue(true);
    }

    public void testGetHomeTLIds() {
        assertTrue(true);
    }

    public void testGetHomeTLIdsRes() {
        assertTrue(true);
    }

    public void testGetUserTLIdsStr() {
        assertTrue(true);
    }

    public void testGetUserTLIds() {
        assertTrue(true);
    }

    public void testGetUserTLIdsRes() {
        assertTrue(true);
    }

    public void testGetBroadcastTLIdsStr() {
        assertTrue(true);
    }

    public void testGetBroadcastTLIds() {
        assertTrue(true);
    }

    public void testGetBroadcastTLIdsRes() {
        assertTrue(true);
    }

    public void testGetMentionsTLIdsStr() {
        assertTrue(true);
    }

    public void testGetMentionsTLIds() {
        assertTrue(true);
    }

    public void testGetMentionsTLIdsRes() {
        assertTrue(true);
    }

    public void testGetUsersTLStr() {
        assertTrue(true);
    }

    public void testGetUsersTL() {
        assertTrue(true);
    }

    public void testGetUsersTLRes() {
        assertTrue(true);
    }

    public void testGetUsersTLIdsStr() {
        assertTrue(true);
    }

    public void testGetUsersTLIds() {
        assertTrue(true);
    }

    public void testGetUsersTLIdsRes() {
        assertTrue(true);
    }

    public void testGetVipStatusTLStr() {
        assertTrue(true);
    }

    public void testGetVipStatusTL() {
        assertTrue(true);
    }

    public void testGetVipStatusTLRes() {
        assertTrue(true);
    }

    public void testGetStatusStringLongQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetStatusLongQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetStatusRes() {
        assertTrue(true);
    }

    public void testAddStatusCommonStr() {
        assertTrue(true);
    }

    public void testAddStatusCommon() {
        assertTrue(true);
    }

    public void testAddStatusCommonRes() {
        assertTrue(true);
    }

    public void testAddStatusStr() {
        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("��������΢��");
        /** �������ֵ�ַ **/
        status.setMusicUrl("http://201112.wma.9ku.com/file2/183/182737.mp3");
        status.setMusicAuthor("��ܿ��");
        status.setMusicTitle("ƫ��");
        assertTrue(qqTSdkService.addMusicStatusStr(status) != null);

        status.setStatusContent("������Ƶ΢��");
        /** ������Ƶ��ַ **/
        status.setVideoUrl("http://v.youku.com/v_show/id_XMjUzOTg3MDY0.html");
        assertTrue(qqTSdkService.addVideoStatusStr(status) != null);
    }

    public void testAddStatus() {
        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("����һ��΢����");
        /** ���þ��Ⱥ�γ�ȣ��ɲ����� **/
        status.setLatitude(23.4);
        status.setLongitude(110.5);
        assertTrue(qqTSdkService.addStatus(status));

        status.setStatusContent("����һ����ͼƬ΢����");
        /** �����ͼ΢��������ͼƬ·�� **/
        status.setImageFilePath("E:\\Picture\\�ഺ����\\25_56857_c91ae7a693441e5.jpg");
        assertTrue(qqTSdkService.addStatus(status));
    }

    public void testAddStatusRes() {
        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("����һ��΢��Res��");
        QqTResponse qqTResponse = qqTSdkService.addStatusRes(status);

        /** �������ӵ�״̬ **/
        if (qqTResponse.getIsOk() && qqTResponse.getData() != null) {
            /** �������۵�΢��id **/
            status.setSourceId(((QqTIdAndTime)qqTResponse.getData()).getId());
            status.setStatusContent("����΢����");
            assertTrue(qqTSdkService.comment(status));
            status.setStatusContent("�ظ�΢����");
            assertTrue(qqTSdkService.reply(status));
            status.setStatusContent("ת��΢����");
            assertTrue(qqTSdkService.repost(status));
        } else {
            assertTrue(false);
        }
    }

    public void testRepostStr() {
        assertTrue(true);
    }

    public void testRepost() {
        assertTrue(true);
    }

    public void testRepostRes() {
        assertTrue(true);
    }

    public void testReplyStr() {
        assertTrue(true);
    }

    public void testReply() {
        assertTrue(true);
    }

    public void testReplyRes() {
        assertTrue(true);
    }

    public void testCommentStr() {
        assertTrue(true);
    }

    public void testComment() {
        assertTrue(true);
    }

    public void testCommentRes() {
        assertTrue(true);
    }

    public void testAddMusicStatusStr() {
        assertTrue(true);
    }

    public void testAddMusicStatus() {
        assertTrue(true);
    }

    public void testAddMusicStatusRes() {
        assertTrue(true);
    }

    public void testAddVideoStatusStr() {
        assertTrue(true);
    }

    public void testAddVideoStatus() {
        assertTrue(true);
    }

    public void testAddVideoStatusRes() {
        assertTrue(true);
    }

    public void testGetStatusCommentsCommonStr() {
        assertTrue(true);
    }

    public void testGetStatusCommentsCommon() {
        assertTrue(true);
    }

    public void testGetStatusCommentsCommonRes() {
        assertTrue(true);
    }

    public void testGetStatusComments() {
        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("����һ��΢�������鿴�����б���22");
        QqTResponse qqTResponse = qqTSdkService.addStatusRes(status);

        /** ������������΢�����ٻ�������б� **/
        if (qqTResponse.getIsOk() && qqTResponse.getData() != null) {
            int commentCount = 2;
            for (int i = 1; i <= commentCount; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /** �������۵�΢��id **/
                status.setSourceId(((QqTIdAndTime)qqTResponse.getData()).getId());
                status.setStatusContent("�����б�����΢����" + i);
                qqTSdkService.comment(status);
            }

            /** ��������б� **/
            QqTTimelinePara qqTTimelinePara = new QqTTimelinePara();
            qqTTimelinePara.setPageFlag(0);
            qqTTimelinePara.setPageTime(0);
            qqTTimelinePara.setPageReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
            qqTTimelinePara.setTwitterId(0);
            /** ����΢��id����ʾ��ø�΢���������б� **/
            qqTTimelinePara.setRootId(((QqTIdAndTime)qqTResponse.getData()).getId());
            List<QqTStatus> qqTStatusList = qqTSdkService.getStatusComments(qqTTimelinePara);
            assertTrue(qqTStatusList.size() == commentCount);
        } else {
            assertTrue(false);
        }
    }

    public void testGetStatusCommentsRes() {
        assertTrue(true);
    }

    public void testGetStatusReposts() {
        assertTrue(true);
    }

    public void testGetStatusRepostsRes() {
        assertTrue(true);
    }

    public void testGetStatusCommentsAndReposts() {
        assertTrue(true);
    }

    public void testGetStatusCommentsAndRepostsRes() {
        assertTrue(true);
    }

    public void testGetVideoInfoStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetVideoInfoStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetVideoInfoRes() {
        assertTrue(true);
    }

    public void testGetStatusByIdsStr() {
        assertTrue(true);
    }

    public void testGetStatusByIds() {
        assertTrue(true);
    }

    public void testGetStatusByIdsRes() {
        assertTrue(true);
    }

    public void testGetReRepostCountByIdsStr() {
        assertTrue(true);
    }

    public void testGetReRepostCountByIds() {
        assertTrue(true);
    }

    public void testGetReRepostCountByIdsRes() {
        assertTrue(true);
    }

    public void testAddEmotionStr() {
        assertTrue(true);
    }

    public void testAddEmotion() {
        assertTrue(true);
    }

    public void testAddEmotionRes() {
        assertTrue(true);
    }

    public void testOperateStatusCommonStr() {
        assertTrue(true);
    }

    public void testOperateStatusCommon() {
        assertTrue(true);
    }

    public void testOperateStatusCommonRes() {
        assertTrue(true);
    }

    public void testDelete() {
        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("����һ����ɾ��΢����");
        QqTResponse qqTResponse = qqTSdkService.addStatusRes(status);

        /** ɾ�����ӵ�΢�� **/
        if (qqTResponse.getIsOk() && qqTResponse.getData() != null) {
            if (qqTSdkService.delete(((QqTIdAndTime)qqTResponse.getData()).getId())) {
                status.setStatusContent("ɾ��΢���ɹ�����");
                assertTrue(qqTSdkService.addStatus(status));
            } else {
                assertTrue(false);
            }
        } else {
            assertTrue(false);
        }
    }

    public void testGetRepostAndCommentCountStringStringIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetRepostAndCommentCountStringIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetRepostAndCommentCountResStringIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetRepostAndCommentCountStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetRepostAndCommentCountResStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetRepostOrCommentCount() {
        assertTrue(true);
    }

    public void testGetRepostOrCommentCountRes() {
        assertTrue(true);
    }

    public void testGetSelfInfoStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfInfo() {
        QqTUser qqTUser = qqTSdkService.getSelfInfo();
        assertTrue(qqTUser != null);
    }

    public void testGetSelfInfoRes() {
        assertTrue(true);
    }

    public void testUpdateSelfInfoStr() {
        assertTrue(true);
    }

    public void testUpdateSelfInfo() {
        QqTUserPara qqTUserPara = new QqTUserPara();
        qqTUserPara.setBirthDay("28");
        qqTUserPara.setBirthMonth("4");
        qqTUserPara.setBirthYear("1990");
        qqTUserPara.setIntroduction("wodexinjieshao");
        qqTUserPara.setNick("wodexinnickoo");
        qqTUserPara.setCountryCode("1");
        qqTUserPara.setCityCode("5");
        qqTUserPara.setProvinceCode("11");
        qqTUserPara.setSex(2);
        assertTrue(qqTSdkService.updateSelfInfo(qqTUserPara));
    }

    public void testUpdateSelfInfoRes() {
        assertTrue(true);
    }

    public void testUpdateSelfHeadStr() {
        assertTrue(true);
    }

    public void testUpdateSelfHead() {
        assertTrue(qqTSdkService.updateSelfHead("E:\\Picture\\�ഺ����\\25_56857_c91ae7a693441e5.jpg"));
    }

    public void testUpdateSelfHeadRes() {
        assertTrue(true);
    }

    public void testUpdateSelfEduInfoStr() {
        QqTUserEduPara qqTUserEduPara = new QqTUserEduPara();
        /** �޸Ľ�����Ϣ,setFeildId��ص�feildId **/
        qqTUserEduPara.setFeildId(24037);
        qqTUserEduPara.setYear("2007");
        qqTUserEduPara.setSchoolId(12264);
        qqTUserEduPara.setDepartmentId(25555);
        qqTUserEduPara.setLevel(5);
        assertTrue(qqTSdkService.updateSelfEduInfo(qqTUserEduPara));

        /** ɾ��������Ϣ **/
        // FIXME ɾ������bug
        qqTUserEduPara.setFeildId(24037);
        assertTrue(qqTSdkService.updateSelfEduInfo(qqTUserEduPara));

        /** ���ӽ�����Ϣ,setFeildId feildId=1 **/
        qqTUserEduPara.setFeildId(1);
        qqTUserEduPara.setYear("2011");
        qqTUserEduPara.setSchoolId(12264);
        qqTUserEduPara.setDepartmentId(25555);
        /** 1��ʾСѧ��2��ʾ���У�3��ʾ���У�4��ʾ��ѧ��5��ʾ˶ʿ��6��ʾ��ʿ **/
        qqTUserEduPara.setLevel(6);
        assertTrue(qqTSdkService.updateSelfEduInfo(qqTUserEduPara));
    }

    public void testUpdateSelfEduInfo() {
        assertTrue(true);
    }

    public void testUpdateSelfEduInfoRes() {
        assertTrue(true);
    }

    public void testGetOtherUserInfoStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetOtherUserInfo() {
        String userName = "wenzhang";
        QqTUser qqTUser = qqTSdkService.getOtherUserInfo(userName, null);
        assertTrue(qqTUser != null);
    }

    public void testGetOtherUsersInfo() {
        String userName = "wenzhang,mayili007,li_nian";
        List<QqTUser> qqTUserList = qqTSdkService.getOtherUsersInfo(userName, null);
        assertTrue(qqTUserList != null);
    }

    public void testGetOtherUserInfoRes() {
        assertTrue(true);
    }

    public void testVerifyAccountStr() {
        assertTrue(true);
    }

    public void testVerifyAccount() {
        assertTrue(qqTSdkService.verifyAccount("wenzhang", null));
        assertTrue(qqTSdkService.verifyAccount("li_nian", null));
        assertFalse(qqTSdkService.verifyAccount("mayili007lalalalaallalalala", null));
    }

    public void testVerifyAccountRes() {
        assertTrue(true);
    }

    public void testGetUserRelationsCommonStr() {
        assertTrue(true);
    }

    public void testGetUserRelationsCommon() {
        assertTrue(true);
    }

    public void testGetUserRelationsCommonRes() {
        assertTrue(true);
    }

    public void testGetUserRelationsNameCommon() {
        assertTrue(true);
    }

    public void testGetUserRelationsNameCommonRes() {
        assertTrue(true);
    }

    public void testGetSelfFansStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfFansIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfFansRes() {
        assertTrue(true);
    }

    public void testGetSelfFansNamesStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfFansNamesIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfFansNamesStr() {
        QqTUserRelationPara qqTUserRelationPara = new QqTUserRelationPara();
        qqTUserRelationPara.setFormat(QqTConstant.VALUE_FORMAT_JSON);
        qqTUserRelationPara.setReqNumber(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTUserRelationPara.setStartIndex(0);
        String myFansStr = qqTSdkService.getSelfFansNamesStr(qqTUserRelationPara);
        assertTrue(myFansStr != null);
    }

    public void testGetSelfInterestedStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfInterested() {
        QqTUserRelationPara qqTUserRelationPara = new QqTUserRelationPara();
        qqTUserRelationPara.setReqNumber(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTUserRelationPara.setStartIndex(0);
        List<QqTUser> qqTUserList = qqTSdkService.getSelfInterested(qqTUserRelationPara);

        /** �ڶ�ҳ **/
        if (qqTUserList.size() >= qqTUserRelationPara.getReqNumber() && qqTUserList.get(qqTUserList.size() - 1) != null) {
            /** ���õڶ�ҳ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1)) **/
            qqTUserRelationPara.setStartIndex(QqTConstant.VALUE_PAGE_REQ_NUM);
            List<QqTUser> nextqqTUserList = qqTSdkService.getSelfInterested(qqTUserRelationPara);
            assertTrue(nextqqTUserList != null);
        } else {
            assertTrue(qqTUserList != null);
        }
    }

    public void testGetSelfInterestedRes() {
        QqTUserRelationPara qqTUserRelationPara = new QqTUserRelationPara();
        qqTUserRelationPara.setReqNumber(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTUserRelationPara.setStartIndex(0);
        QqTResponse qqTResponse = qqTSdkService.getSelfInterestedRes(qqTUserRelationPara);
        assertTrue(qqTResponse != null);
    }

    public void testGetOtherUserFans() {
        QqTUserRelationPara qqTUserRelationPara = new QqTUserRelationPara();
        qqTUserRelationPara.setReqNumber(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTUserRelationPara.setStartIndex(0);
        /** �����û��ʻ�����ע�ⲻ����ʾ������http://t.qq.com/li_nianΪ�� **/
        qqTUserRelationPara.setUserName("li_nian");
        QqTResponse qqTResponse = qqTSdkService.getOtherUserFansRes(qqTUserRelationPara);

        /** �ڶ�ҳ **/
        if (qqTResponse.getData() != null) {
            /** ���QqTResponse�е��û��б���Ϣ **/
            List<QqTUser> qqTUserList = (List<QqTUser>)((QqTListData)qqTResponse.getData()).getInfo();
            if (qqTUserList.size() >= qqTUserRelationPara.getReqNumber()
                && qqTUserList.get(qqTUserList.size() - 1) != null) {
                /** ���õڶ�ҳ��ʼλ��(��һҳ��0���������·�ҳ����:reqnum*(page-1)) **/
                qqTUserRelationPara.setStartIndex(QqTConstant.VALUE_PAGE_REQ_NUM);
                QqTResponse nextQqTResponse = qqTSdkService.getOtherUserFansRes(qqTUserRelationPara);
                assertTrue(nextQqTResponse != null);
            } else {
                assertTrue(qqTResponse != null);
            }
        } else {
            assertTrue(false);
        }
    }

    public void testGetOtherUserFansRes() {
        QqTUserRelationPara qqTUserRelationPara = new QqTUserRelationPara();
        qqTUserRelationPara.setReqNumber(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTUserRelationPara.setStartIndex(0);
        qqTUserRelationPara.setUserName("li_nian");
        QqTResponse qqTResponse = qqTSdkService.getOtherUserFansRes(qqTUserRelationPara);
        assertTrue(qqTResponse != null);
    }

    public void testGetSelfInterestedNamesStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfInterestedNamesIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfInterestedNamesRes() {
        assertTrue(true);
    }

    public void testGetSelfBlackListStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfBlackListIntIntQqTAppAndToken() {
        QqTUserRelationPara qqTUserRelationPara = new QqTUserRelationPara();
        qqTUserRelationPara.setReqNumber(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTUserRelationPara.setStartIndex(0);
        List<QqTUser> qqTUserList = qqTSdkService.getSelfBlackList(qqTUserRelationPara);
        assertTrue(qqTUserList != null);
    }

    public void testGetSelfBlackListRes() {
        assertTrue(true);
    }

    public void testGetSelfSpecialInterestedStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfSpecialInterestedIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfSpecialInterestedRes() {
        assertTrue(true);
    }

    public void testGetOtherUserInterestedStringStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetOtherUserInterestedIntStringIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetOtherUserInterestedRes() {
        assertTrue(true);
    }

    public void testGetOtherUserSpecialInterestedStringStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetOtherUserSpecialInterestedIntStringIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetOtherUserSpecialInterestedRes() {
        assertTrue(true);
    }

    public void testGetSelfFansSimpleInfoStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfFansSimpleInfoIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfFansSimpleInfoRes() {
        assertTrue(true);
    }

    public void testGetSelfInterestedSimpleInfoStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfInterestedSimpleInfoIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetSelfInterestedSimpleInfoRes() {
        assertTrue(true);
    }

    public void testGetMutualInterestedStringStringIntIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetMutualInterestedIntStringIntQqTAppAndToken() {
        assertTrue(true);
    }

    public void testGetMutualInterestedRes() {
        assertTrue(true);
    }

    public void testRelationWithOtherCommonStr() {
        assertTrue(true);
    }

    public void testRelationWithOtherCommon() {
        assertTrue(true);
    }

    public void testRelationWithOtherCommonRes() {
        assertTrue(true);
    }

    public void testInterestedInOtherStr() {
        assertTrue(true);
    }

    public void testInterestedInOther() {
        assertTrue(qqTSdkService.interestedInOther("wenzhang,li_nian,mayili007", null));
    }

    public void testInterestedInOtherRes() {
        assertTrue(true);
    }

    public void testCancelInterestedInOtherStr() {
        assertTrue(qqTSdkService.cancelInterestedInOther(QqTConstant.VALUE_FORMAT_XML, "wenzhang", null) != null);
    }

    public void testCancelInterestedInOther() {
        assertTrue(qqTSdkService.cancelInterestedInOther("wenzhang", null));
    }

    public void testCancelInterestedInOtherRes() {
        assertTrue(qqTSdkService.cancelInterestedInOther("wenzhang", null));
    }

    public void testSpecialInterestedInOtherStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testSpecialInterestedInOther() {
        assertTrue(qqTSdkService.specialInterestedInOther("mayili007", null));
    }

    public void testSpecialInterestedInOtherRes() {
        assertTrue(true);
    }

    public void testCancelSpecialInterestedInOtherStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testCancelSpecialInterestedInOtherStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testCancelSpecialInterestedInOtherRes() {
        assertTrue(true);
    }

    public void testAddOtherToBlackListStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testAddOtherToBlackList() {
        assertTrue(qqTSdkService.addOtherToBlackList("mayili007", null));
    }

    public void testAddOtherToBlackListRes() {
        assertTrue(qqTSdkService.addOtherToBlackListRes("mayili007", null) != null);
    }

    public void testDeleteFromBlackListStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testDeleteFromBlackListStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testDeleteFromBlackListRes() {
        assertTrue(true);
    }

    public void testCheckRelationWithSelf() {
        assertTrue(true);
    }

    public void testGetIsFanAndInterested() {
        assertTrue(true);
    }

    public void testGetIsFanAndInterestedRes() {
        assertTrue(true);
    }

    public void testGetIsFanOrInterested() {
        assertTrue(true);
    }

    public void testGetIsFanOrInterestedRes() {
        assertTrue(true);
    }

    public void testSendMessageStr() {
        assertTrue(true);
    }

    public void testSendMessage() {
        assertTrue(true);
    }

    public void testSendMessageRes() {
        assertTrue(true);
    }

    public void testDeleteMessage() {
        assertTrue(true);
    }

    public void testDeleteMessageRes() {
        assertTrue(true);
    }

    public void testGetReceiveMessagesStr() {
        assertTrue(true);
    }

    public void testGetReceiveMessages() {
        assertTrue(true);
    }

    public void testGetReceiveMessagesRes() {
        assertTrue(true);
    }

    public void testGetSendMessagesStr() {
        assertTrue(true);
    }

    public void testGetSendMessages() {
        assertTrue(true);
    }

    public void testGetSendMessagesRes() {
        assertTrue(true);
    }

    public void testSearchCommonStr() {
        assertTrue(true);
    }

    public void testSearchUserCommon() {
        assertTrue(true);
    }

    public void testSearchUserCommonRes() {
        assertTrue(true);
    }

    public void testSearchUserStr() {
        assertTrue(true);
    }

    public void testSearchUser() {
        QqTSearchPara qqTSearchPara = new QqTSearchPara();
        qqTSearchPara.setKeyword("iphone");
        qqTSearchPara.setPage(1);
        qqTSearchPara.setPageSize(QqTConstant.VALUE_PAGE_REQ_NUM);
        List<QqTUser> qqTUserList = qqTSdkService.searchUser(qqTSearchPara);
        assertTrue(qqTUserList != null);
    }

    public void testSearchUserRes() {
        assertTrue(true);
    }

    public void testSearchStatusStr() {
        assertTrue(true);
    }

    public void testSearchStatus() {
        QqTSearchPara qqTSearchPara = new QqTSearchPara();
        qqTSearchPara.setKeyword("iphone");
        qqTSearchPara.setPage(1);
        qqTSearchPara.setPageSize(QqTConstant.VALUE_PAGE_REQ_NUM);
        List<QqTStatus> qqTStatusList = qqTSdkService.searchStatus(qqTSearchPara);
        assertTrue(qqTStatusList != null);
    }

    public void testSearchStatusRes() {
        assertTrue(true);
    }

    public void testSearchUserByTagStr() {
        assertTrue(true);
    }

    public void testSearchUserByTag() {
        QqTSearchPara qqTSearchPara = new QqTSearchPara();
        qqTSearchPara.setKeyword("ƻ��");
        qqTSearchPara.setPage(1);
        qqTSearchPara.setPageSize(QqTConstant.VALUE_PAGE_REQ_NUM);
        List<QqTUser> qqTUserList = qqTSdkService.searchUserByTag(qqTSearchPara);
        assertTrue(qqTUserList != null);
    }

    public void testSearchUserByTagRes() {
        assertTrue(true);
    }

    public void testGetHotCommonStr() {
        assertTrue(true);
    }

    public void testGetHotTopics() {
        QqTHotStatusPara qqTHotStatusPara = new QqTHotStatusPara();
        qqTHotStatusPara.setReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTHotStatusPara.setLastPosition(0);
        /**
         * 1 ��������2 �����ؼ��� 3 �������Ͷ���
         **/
        qqTHotStatusPara.setType(Integer.toString(1));
        List<QqTTopicSimple> hotTopicsList = qqTSdkService.getHotTopics(qqTHotStatusPara);
        assertTrue(hotTopicsList != null);
    }

    public void testGetHotTopicsRes() {
        assertTrue(true);
    }

    public void testGetHotReposts() {
        QqTHotStatusPara qqTHotStatusPara = new QqTHotStatusPara();
        qqTHotStatusPara.setReqNum(QqTConstant.VALUE_PAGE_REQ_NUM);
        qqTHotStatusPara.setLastPosition(0);
        /**
         * 0x1-���ı� 0x2-������ 0x4ͼƬ 0x8-����Ƶ
         * ������ȡ���������ʹ��|����(0x1|0x2)�õ�3����ʱtype=3���ɣ������ʾ��ȡ��������
         **/
        qqTHotStatusPara.setType(Integer.toString(QqTConstant.VALUE_CONTENT_TYPE_TL_LINK));
        List<QqTStatus> qqTStatus = qqTSdkService.getHotReposts(qqTHotStatusPara);
        assertTrue(qqTStatus != null);
    }

    public void testGetHotRepostsRes() {
        assertTrue(true);
    }

    public void testGetUpdateInfoNumStr() {
        assertTrue(true);
    }

    public void testGetUpdateInfoNum() {
        /** ����clearType����ӦQqTConstant.VALUE_CLEAR_TYPE_�� **/
        QqTUpdateNumInfo qqTUpdateNumInfo = qqTSdkService.getUpdateInfoNum(true,
                                                                           QqTConstant.VALUE_CLEAR_TYPE_PRIVATE_MSG);
        assertTrue(qqTUpdateNumInfo != null);
    }

    public void testGetUpdateInfoNumRes() {
        assertTrue(true);
    }

    public void testCollect() {
        QqTStatusInfoPara status = new QqTStatusInfoPara();
        status.setStatusContent("����һ�����ղ�΢����");
        QqTResponse qqTResponse = qqTSdkService.addStatusRes(status);

        /** �ղ����ӵ�΢�� **/
        if (qqTResponse.getIsOk() && qqTResponse.getData() != null) {
            assertTrue(qqTSdkService.collect(((QqTIdAndTime)qqTResponse.getData()).getId()));
        } else {
            assertTrue(false);
        }
    }

    public void testCollectRes() {
        List<QqTStatus> qqtStatusList = getSelfFirstPage();
        if (!ListUtils.isEmpty(qqtStatusList)) {
            QqTResponse qqTResponse = qqTSdkService.collectRes(qqtStatusList.get(0).getStatusId());
            assertTrue(qqTResponse.getIsOk());
        } else {
            assertTrue(true);
        }
    }

    public void testUnCollect() {
        List<QqTStatus> qqtStatusList = getSelfFirstPage();
        if (qqtStatusList != null) {
            for (QqTStatus qqtStatus : qqtStatusList) {
                qqTSdkService.unCollect(qqtStatus.getStatusId());
            }
        }
    }

    public void testUnCollectRes() {
        assertTrue(true);
    }

    public void testSubscribeTopic() {
        assertTrue(true);
    }

    public void testSubscribeTopicRes() {
        assertTrue(true);
    }

    public void testUnSubscribeTopic() {
        assertTrue(true);
    }

    public void testUnSubscribeTopicRes() {
        assertTrue(true);
    }

    public void testGetCollectStatusesStr() {
        assertTrue(true);
    }

    public void testGetCollectStatuses() {
        assertTrue(true);
    }

    public void testGetCollectStatusesRes() {
        assertTrue(true);
    }

    public void testGetCollectTopicsStr() {
        assertTrue(true);
    }

    public void testGetCollectTopics() {
        assertTrue(true);
    }

    public void testGetCollectTopicsRes() {
        assertTrue(true);
    }

    public void testGetTopicIdByNamesStr() {
        assertTrue(true);
    }

    public void testGetTopicIdByNames() {
        /** ���ݻ������б����������б��Զ��ŷָ� **/
        Map<String, String> topicIdAndName = qqTSdkService.getTopicIdByNames("Ԭ������,��֭Դ�¼�,iphone");
        assertTrue(topicIdAndName != null);
    }

    public void testGetTopicIdByNamesRes() {
        assertTrue(true);
    }

    public void testGetTopicInfoByIdsStr() {
        assertTrue(true);
    }

    public void testGetTopicInfoByIds() {
        /** �ȵõ�����id **/
        Map<String, String> topicIdAndName = qqTSdkService.getTopicIdByNames("Ԭ������,��֭Դ�¼�,iphone");

        if (topicIdAndName != null) {
            /** ����id�б��Զ��ŷָ� **/
            List<QqTStatus> qqtStatusList = qqTSdkService.getTopicInfoByIds(ListUtils.join(new ArrayList<String>(
                                                                                                                 topicIdAndName.keySet())));
            assertTrue(qqtStatusList != null);
        } else {
            assertTrue(false);
        }
    }

    public void testGetTopicInfoByIdsRes() {
        assertTrue(true);
    }

    public void testAddTagStringStringQqTAppAndToken() {
        assertTrue(true);
    }

    public void testAddTag() {
        assertTrue(qqTSdkService.addTag("����׿"));
    }

    public void testAddTagRes() {
        assertTrue(true);
    }

    public void testDeleteTagStringLongQqTAppAndToken() {
        assertTrue(true);
    }

    public void testDeleteTag() {
        /** ɾ���Լ���tag���Ȼ�ȡ�Լ������ϣ�����ȡ��tag id **/
        QqTUser qqTUser = qqTSdkService.getSelfInfo();
        if (qqTUser != null && qqTUser.getTagMap() != null && qqTUser.getTagMap().size() > 0) {
            /** ɾ��tag **/
            for (Map.Entry<String, String> tag : qqTUser.getTagMap().entrySet()) {
                qqTSdkService.deleteTag(tag.getKey());
            }
        } else {
            assertTrue(false);
        }
    }

    public void testDeleteTagRes() {
        assertTrue(true);
    }

    public void testGetUnAuthorizedRequestToken() {
        assertTrue(true);
    }

    public void testGetAuthorizedRequestToken() {
        assertTrue(true);
    }

    public void testGetAccessToken() {
        assertTrue(true);
    }

}
