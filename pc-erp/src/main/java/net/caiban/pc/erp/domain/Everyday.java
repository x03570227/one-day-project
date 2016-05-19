/**
 * 
 */
package net.caiban.pc.erp.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author mar
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class Everyday extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wxOpenid;
	private String url;
	private String content;
	private String tags;
	private Integer dayIndex;
	private Integer dayItemIndex;
	private String wxMsgid;
	
	private String wxMsgtype;
	private String wxPicurl;
	private String wxMediaid;
	private String wxThumbMediaId;
	private Double wxLx;
	private Double wxLy;
	private String wxScale;
	private String wxLabel;
	private String wxTitle;
	private String wxDescription;

    private Long uid;
    private Long subjectId;

    private Integer subjectIndex;
	
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getDayIndex() {
		return dayIndex;
	}
	public void setDayIndex(Integer dayIndex) {
		this.dayIndex = dayIndex;
	}
	public Integer getDayItemIndex() {
		return dayItemIndex;
	}
	public void setDayItemIndex(Integer dayItemIndex) {
		this.dayItemIndex = dayItemIndex;
	}
	public String getWxMsgid() {
		return wxMsgid;
	}
	public void setWxMsgid(String wxMsgid) {
		this.wxMsgid = wxMsgid;
	}
	public String getWxMsgtype() {
		return wxMsgtype;
	}
	public void setWxMsgtype(String wxMsgtype) {
		this.wxMsgtype = wxMsgtype;
	}
	public String getWxPicurl() {
		return wxPicurl;
	}
	public void setWxPicurl(String wxPicurl) {
		this.wxPicurl = wxPicurl;
	}
	public String getWxMediaid() {
		return wxMediaid;
	}
	public void setWxMediaid(String wxMediaid) {
		this.wxMediaid = wxMediaid;
	}
	public String getWxThumbMediaId() {
		return wxThumbMediaId;
	}
	public void setWxThumbMediaId(String wxThumbMediaId) {
		this.wxThumbMediaId = wxThumbMediaId;
	}
	public String getWxScale() {
		return wxScale;
	}
	public void setWxScale(String wxScale) {
		this.wxScale = wxScale;
	}
	public String getWxLabel() {
		return wxLabel;
	}
	public void setWxLabel(String wxLabel) {
		this.wxLabel = wxLabel;
	}
	public String getWxTitle() {
		return wxTitle;
	}
	public void setWxTitle(String wxTitle) {
		this.wxTitle = wxTitle;
	}
	public String getWxDescription() {
		return wxDescription;
	}
	public void setWxDescription(String wxDescription) {
		this.wxDescription = wxDescription;
	}
	public Double getWxLx() {
		return wxLx;
	}
	public void setWxLx(Double wxLx) {
		this.wxLx = wxLx;
	}
	public Double getWxLy() {
		return wxLy;
	}
	public void setWxLy(Double wxLy) {
		this.wxLy = wxLy;
	}

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSubjectIndex() {
        return subjectIndex;
    }

    public void setSubjectIndex(Integer subjectIndex) {
        this.subjectIndex = subjectIndex;
    }
}
