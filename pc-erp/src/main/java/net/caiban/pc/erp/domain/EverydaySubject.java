package net.caiban.pc.erp.domain;

/**
 * Created by mar on 16/5/3.
 */
public class EverydaySubject extends BaseDomain {

    private Long uid;
    private String title;
    private  String content;
    private String wxOpenid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }
}
