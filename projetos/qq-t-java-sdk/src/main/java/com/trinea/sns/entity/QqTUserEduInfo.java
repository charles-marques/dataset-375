package com.trinea.sns.entity;

import java.io.Serializable;

/**
 * ��Ѷ΢���û�������Ϣ
 * 
 * @author Trinea 2011-10-25 ����11:47:49
 */
public class QqTUserEduInfo implements Serializable {

    private static final long serialVersionUID = -623843187140208710L;

    private long              id;                                     // ѧ����¼id
    private String            year;                                   // ��ѧ��
    private long              schoolId;                               // ѧУid
    private long              departmentId;                           // Ժϵid
    private int               level;                                  // ѧ������

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
