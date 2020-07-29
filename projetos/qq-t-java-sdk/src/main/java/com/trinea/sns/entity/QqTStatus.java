package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢����Ϣ
 * 
 * @author Trinea 2011-10-8 ����11:45:09
 */
public class QqTStatus implements Serializable {

    private static final long serialVersionUID = -361015223160572717L;

    private QqTUser           user;                                   // ��������Ϣ
    private long              time;                                   // ����ʱ�䣬����Ϊ��λ

    private long              statusId;                               // ����id
    private String            statusContent;                          // �������ݣ���isForwardΪtrue�����ʾת������
    private String            statusOrigiContent;                     // �������ݣ���isForwardΪtrue�����ʾת������
    /** 1-ԭ������2-ת�ء�3-˽�� 4-�ظ� 5-�ջ� 6-�ἰ 7-���� **/
    private int               statusType;                             // ״̬����
    /** ΢��״̬ 0-������1-ϵͳɾ�� 2-����� 3-�û�ɾ�� 4-��ɾ�� **/
    private int               statusStatus;                           // ״̬״̬
    private int               commentCount;                           // ������Ŀ
    private String            sourceType;                             // ��Դ����iphone��android����ҳ

    /** ͼƬ��Ϣ image:["",""] **/
    private boolean           isContainImage;                         // �Ƿ����ͼƬ
    private String[]          imageUrl;                               // ͼƬ�б�

    /** video ��Ϣ video:{picurl:""",player:"",realurl:"",shorturl:"",title:""} **/
    private boolean           isContainVideo;                         // �Ƿ������Ƶ
    private String            videoImageUrl;                          // ��ƵͼƬ��ַ
    private String            videoPlayerUrl;                         // ��Ƶ��������ַ
    private String            videoActualUrl;                         // ��Ƶʵ�ʵ�ַ
    private String            videoShortUrl;                          // ��Ƶ�̵�ַ
    private String            videoTitle;                             // ��Ƶ����

    /** ��Ƶ��Ϣ��music:{author:"",url:"",title:""} **/
    private boolean           isContainMusic;
    private String            musicAuthor;                            // �ݳ���
    private String            musicUrl;                               // ���ֵ�ַ
    private String            musicTitle;                             // ����

    private int               repostCount;                            // ת����Ŀ
    private boolean           isContainSource;                        // �Ƿ����ԭ΢�������������ۡ�ת����
    private QqTStatus         sourceStatus;                           // ���isRepostΪtrue������ԭstatus��Ϣ

    private long              colloctNum;                             // ����ʾ����ʱ��ָ���ղش���
    private long              tweetNum;                               // ����ʾ����ʱ��ָ�û�����΢������

    public QqTUser getUser() {
        return user;
    }

    public void setUser(QqTUser user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public long getStatusId() {
        return statusId;
    }

    public void setStatusId(long statusId) {
        this.statusId = statusId;
    }

    public String getStatusOrigiContent() {
        return statusOrigiContent;
    }

    public void setStatusOrigiContent(String statusContent) {
        this.statusOrigiContent = statusContent;
    }

    public int getStatusType() {
        return statusType;
    }

    public void setStatusType(int statusType) {
        this.statusType = statusType;
    }

    public int getStatusStatus() {
        return statusStatus;
    }

    public void setStatusStatus(int statusStatus) {
        this.statusStatus = statusStatus;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public boolean isContainImage() {
        return isContainImage;
    }

    public void setContainImage(boolean isContainImage) {
        this.isContainImage = isContainImage;
    }

    /**
     * ͨ��imageUrl�����Ƿ�Ϊ���ж��Ƿ����ͼƬ
     */
    public void setContainImageFromImageUrl() {
        this.isContainImage = (imageUrl != null && imageUrl.length > 0);
    }

    public String[] getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String[] imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isContainVideo() {
        return isContainVideo;
    }

    public void setContainVideo(boolean isContainVideo) {
        this.isContainVideo = isContainVideo;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public String getVideoPlayerUrl() {
        return videoPlayerUrl;
    }

    public void setVideoPlayerUrl(String videoPlayerUrl) {
        this.videoPlayerUrl = videoPlayerUrl;
    }

    public String getVideoActualUrl() {
        return videoActualUrl;
    }

    public void setVideoActualUrl(String videoActualUrl) {
        this.videoActualUrl = videoActualUrl;
    }

    public String getVideoShortUrl() {
        return videoShortUrl;
    }

    public void setVideoShortUrl(String videoShortUrl) {
        this.videoShortUrl = videoShortUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public boolean isContainMusic() {
        return isContainMusic;
    }

    public void setContainMusic(boolean isContainMusic) {
        this.isContainMusic = isContainMusic;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public boolean isContainSource() {
        return isContainSource;
    }

    public void setContainSource(boolean isRepost) {
        this.isContainSource = isRepost;
    }

    public int getRepostCount() {
        return repostCount;
    }

    public void setRepostCount(int repostCount) {
        this.repostCount = repostCount;
    }

    public QqTStatus getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(QqTStatus sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public long getColloctNum() {
        return colloctNum;
    }

    public void setColloctNum(long colloctNum) {
        this.colloctNum = colloctNum;
    }

    public long getTweetNum() {
        return tweetNum;
    }

    public void setTweetNum(long tweetNum) {
        this.tweetNum = tweetNum;
    }
}
