package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢���޸��û���Ϣ����
 * http://wiki.open.t.qq.com/index.php/%E5%B8%90%E6%88%B7%E7%9B%B8%E5%85%B3/%E6%9B%B4%E6%96%B0%E7%94%A8%E6%88%B7%E4%BF%
 * A1%E6%81%AF
 * 
 * @author Trinea 2011-10-29 ����05:02:15
 */
public class QqTUserPara implements Serializable {

    private static final long serialVersionUID    = 3298635316340763420L;

    /** �������ݵĸ�ʽ **/
    private String            format;

    /** �ǳ� **/
    private String            nick;

    /** �û��Ա� 1�� 2 Ů 0δ֪ **/
    private int               sex;

    /** ������1990-���� **/
    private String            birthYear;

    /** ������ 1-12 **/
    private String            birthMonth;

    /** ������ 1-31 **/
    private String            birthDay;

    /**
     * ������
     * ����μ� http://open.t.qq.com/bbs/forum.php?mod=viewthread&tid=9684&extra=page%3D1
     **/
    private String            countryCode;

    /**
     * ʡ����
     * ����μ� http://open.t.qq.com/bbs/forum.php?mod=viewthread&tid=9684&extra=page%3D1
     **/
    private String            provinceCode;

    /**
     * ������
     * ����μ� http://open.t.qq.com/bbs/forum.php?mod=viewthread&tid=9684&extra=page%3D1
     **/
    private String            cityCode;

    /** ���˽��� **/
    private String            introduction;

    /** Ĭ��ֵ **/
    private static String     defaultFormat       = "";
    private static String     defaultNick         = "";
    private static int        defaultSex          = -1;
    private static String     defaultBirthYear    = "";
    private static String     defaultBirthMonth   = "";
    private static String     defaultBirthDay     = "";
    private static String     defaultCountryCode  = "";
    private static String     defaultProvinceCode = "";
    private static String     defaultCityCode     = "";
    private static String     defaultIntroduction = "";

    public QqTUserPara(){
        super();

        this.format = defaultFormat;
        this.nick = defaultNick;
        this.sex = defaultSex;
        this.birthYear = defaultBirthYear;
        this.birthMonth = defaultBirthMonth;
        this.birthDay = defaultBirthDay;
        this.countryCode = defaultCountryCode;
        this.provinceCode = defaultProvinceCode;
        this.cityCode = defaultCityCode;
        this.introduction = defaultIntroduction;
    }

    /**
     * ���û���Ϣ��������ת��Ϊapi��Ҫ��map
     * 
     * @return
     */
    public Map<String, String> getParasMap() {
        Map<String, String> parasMap = new HashMap<String, String>();
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_FORMAT, format);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_NICK, nick);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_BIRTH_YEAR, birthYear);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_BIRTH_MONTH, birthMonth);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_BIRTH_DAY, birthDay);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_COUNTRY_CODE, countryCode);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_PROVINCE_CODE, provinceCode);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_CITY_CODE, cityCode);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_INTRODUCTION, introduction);
        if (sex >= 0) {
            parasMap.put(QqTConstant.PARA_SEX, Integer.toString(sex));
        }
        return parasMap;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
