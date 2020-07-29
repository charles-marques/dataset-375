package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * ��Ѷ΢���û���Ϣ
 * http://open.t.qq.com/resource.php?i=1,1#9_30
 * 
 * @author Trinea 2011-10-8 ����11:44:53
 */
public class QqTUser implements Serializable {

    private static final long    serialVersionUID = 3298635316340763420L;

    private long                 userId;                                 // �û�id
    private String               userName;                               // �û���
    private String               userScreenName;                         // �û��ǳ�
    private String               userDescription;                        // ���˽���
    private String               iconUrl;                                // ͷ��ͼƬ��ַ

    /** �û��Ա� 1�� 2 Ů 0δ֪ **/
    private String               sex;
    private String               birthYear;                              // ������
    private String               birthMonth;                             // ������
    private String               birthDay;                               // ������

    private long                 followersCount;                         // ������
    private long                 friendsCount;                           // ����������
    private long                 statusesCount;                          // ΢����

    private Map<String, String>  tagMap;                                 // ���˱�ǩ��keyΪ��ǩid��nameΪ��ǩ��
    private List<QqTUserEduInfo> EduInfoList;                            // ѧ����Ϣ

    private String               location;                               // ���������ڵ�
    private String               countryCode;                            // ������(����ʱ����һ��)
    private String               provinceCode;                           // ʡ����(����ʱ����һ��)
    private String               cityCode;                               // ������(����ʱ����һ��)

    private boolean              isMyInterested;                         // �Ƿ�����������
    private boolean              isVip;                                  // �Ƿ���֤�û�
    private boolean              isEnt;                                  // �Ƿ���ҵ����
    private String               verifyInfo;                             // ��֤��Ϣ

    private List<QqTStatus>      latestStatusList;                       // �û����΢���б�

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * �û��Ա� 1�У� 2Ů�� ������ʾδ֪
     * 
     * @param sex ��ʾ�Ա������
     *            <ul>
     *            <li>������ʱ��Ů��ʾ</li>
     *            </ul>
     */
    public void setSex(int sex) {
        if (sex == 1) {
            this.sex = "��";
        } else if (sex == 2) {
            this.sex = "Ů";
        } else {
            this.sex = "δ֪";
        }
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public long getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(long followersCount) {
        this.followersCount = followersCount;
    }

    public long getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(long friendsCount) {
        this.friendsCount = friendsCount;
    }

    public long getStatusesCount() {
        return statusesCount;
    }

    public void setStatusesCount(long statusesCount) {
        this.statusesCount = statusesCount;
    }

    public Map<String, String> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, String> tagMap) {
        this.tagMap = tagMap;
    }

    public List<QqTUserEduInfo> getEduInfoList() {
        return EduInfoList;
    }

    public void setEduInfoList(List<QqTUserEduInfo> eduInfoList) {
        EduInfoList = eduInfoList;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setMyInterested(boolean isMyInterested) {
        this.isMyInterested = isMyInterested;
    }

    public boolean isMyInterested() {
        return isMyInterested;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean isVip) {
        this.isVip = isVip;
    }

    public boolean isEnt() {
        return isEnt;
    }

    public void setEnt(boolean isEnt) {
        this.isEnt = isEnt;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public void setLatestStatusList(List<QqTStatus> latestStatusList) {
        this.latestStatusList = latestStatusList;
    }

    public List<QqTStatus> getLatestStatusList() {
        return latestStatusList;
    }
}
