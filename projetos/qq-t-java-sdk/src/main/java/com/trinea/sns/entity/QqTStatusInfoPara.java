package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.java.common.StringUtils;
import com.trinea.sns.util.QqTConstant;

/**
 * ������ת��������΢��ʱ�Ķ���
 * 
 * @author Trinea 2011-9-23 ����01:37:07
 */
public class QqTStatusInfoPara implements Serializable {

    private static final long serialVersionUID     = -177267939556171256L;

    /** �������ݵĸ�ʽ **/
    private String            format;
    /** �������ݣ���isForwardΪtrue�����ʾת������ **/
    private String            statusContent;
    /** ͼƬ·�� **/
    private String            imageFilePath;
    /** ���ȣ���Ч��Χ��-180.0��+180.0��+��ʾ������ **/
    private double            longitude;
    /** γ�ȣ���Ч��Χ��-90.0��+90.0��+��ʾ��γ�� **/
    private double            latitude;
    /** �ͻ���ip **/
    private String            clientIp;
    /** ԭ΢��id������һ����΢��ʱΪ�� **/
    private long              sourceId;
    /** ���ֵ�ַ **/
    private String            musicUrl;
    /** ������ **/
    private String            musicTitle;
    /** �ݳ��� **/
    private String            musicAuthor;
    /** ��Ƶ��ַ **/
    private String            videoUrl;
    /** �������ͣ�0��1��2��3��4�� **/
    private int               signType;

    /** Ĭ��ֵ **/
    private static String     defaultFormat        = "";
    private static String     defaultStatusContent = "";
    private static String     defaultImageFilePath = "";
    private static double     defaultLongitude     = 360;
    private static double     defaultLatitude      = 360;
    private static String     defaultClientIp      = "127.0.0.1";
    private static String     defaultMusicUrl      = "";
    private static String     defaultMusicTitle    = "";
    private static String     defaultMusicAuthor   = "";
    private static String     defaultVideoUrl      = "";
    private static long       defaultSourceId      = -1;
    private static int        defaultSignType      = -1;

    public QqTStatusInfoPara(){
        super();

        format = defaultFormat;
        statusContent = defaultStatusContent;
        imageFilePath = defaultImageFilePath;
        longitude = defaultLongitude;
        latitude = defaultLatitude;
        clientIp = defaultClientIp;
        musicUrl = defaultMusicUrl;
        musicTitle = defaultMusicTitle;
        musicAuthor = defaultMusicAuthor;
        videoUrl = defaultVideoUrl;
        sourceId = defaultSourceId;
        signType = defaultSignType;
    }

    /**
     * ��΢������ת��Ϊapi��Ҫ��map
     * 
     * @return
     */
    public Map<String, String> getParasMap() {
        Map<String, String> parasMap = new HashMap<String, String>();
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_FORMAT, format);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_CONTENT, statusContent, "");
        // MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_PICTURE, imageFilePath);
        if (longitude <= 180 && longitude >= -180) {
            parasMap.put(QqTConstant.PARA_LONGITUDE, Double.toString(longitude));
        }
        if (latitude <= 90 && latitude >= -90) {
            parasMap.put(QqTConstant.PARA_LATITUDE, Double.toString(latitude));
        }
        if (sourceId >= 0) {
            parasMap.put(QqTConstant.PARA_REPLY_ID, Long.toString(sourceId));
        }
        if (signType >= 0) {
            parasMap.put(QqTConstant.PARA_SIGN_TYPE, Integer.toString(signType));
        }
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_CLIENT_IP, clientIp);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_MUSIC_URL, musicUrl);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_MUSIC_TITLE, musicTitle);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_MUSIC_AUTHOR, musicAuthor);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_VIDEO_URL, videoUrl);
        return parasMap;
    }

    /**
     * ���ڷ�����΢���Ƿ�Ϸ�
     * 
     * @return
     */
    public boolean isNewValid() {
        return !StringUtils.isEmpty(statusContent);
    }

    /**
     * ����ת��������΢���Ƿ�Ϸ�
     * 
     * @return
     */
    public boolean isRepostOrCommentValid() {
        return !(StringUtils.isEmpty(statusContent) || sourceId < 0);
    }

    /**
     * �Ƿ����ͼƬ������imageFilePath���ж�
     * 
     * @return
     *         <ul>
     *         <li>��imageFilePathΪnull����ַ������򲻰������������</li>
     *         </ul>
     */
    public boolean isContainImage() {
        return !StringUtils.isEmpty(imageFilePath);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStatusContent() {
        return statusContent;
    }

    public void setStatusContent(String statusContent) {
        this.statusContent = statusContent;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
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

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getSignType() {
        return signType;
    }

    public void setSignType(int signType) {
        this.signType = signType;
    }
}
