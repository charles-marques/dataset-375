package com.trinea.sns.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.trinea.java.common.MapUtils;
import com.trinea.sns.util.QqTConstant;

/**
 * ��Ѷ΢��������Ϣ����
 * http://wiki.open.t.qq.com/index.php/%E6%90%9C%E7%B4%A2%E7%9B%B8%E5%85%B3
 * 
 * @author Trinea 2011-11-5 ����03:34:40
 */
public class QqTSearchPara implements Serializable {

    private static final long serialVersionUID = -989824436462485204L;

    /** �������ݵĸ�ʽ **/
    private String            format;

    /** �����ؼ��� **/
    private String            keyword;

    /** ÿҳ��С **/
    private int               pageSize;

    /** ҳ�� **/
    private int               page;

    /** Ĭ��ֵ **/
    private static String     defaultFormat    = "";
    private static String     defaultKeyword   = "";
    private static int        defaultPageSize  = -1;
    private static int        defaultPage      = -1;

    public QqTSearchPara(){
        super();

        this.format = defaultFormat;
        this.keyword = defaultKeyword;
        this.pageSize = defaultPageSize;
        this.page = defaultPage;
    }

    /**
     * ��������Ϣ��������ת��Ϊapi��Ҫ��map
     * 
     * @return
     */
    public Map<String, String> getParasMap() {
        Map<String, String> parasMap = new HashMap<String, String>();
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_FORMAT, format);
        MapUtils.putMapNotEmptyValue(parasMap, QqTConstant.PARA_KEYWORD, keyword);
        if (pageSize >= 0) {
            parasMap.put(QqTConstant.PARA_PAGE_SIZE, Integer.toString(pageSize));
        }
        if (page >= 0) {
            parasMap.put(QqTConstant.PARA_PAGE, Integer.toString(page));
        }
        return parasMap;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
