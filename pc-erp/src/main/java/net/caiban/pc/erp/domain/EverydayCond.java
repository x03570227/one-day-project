/**
 * 
 */
package net.caiban.pc.erp.domain;

import java.util.Date;

/**
 * @author mar
 *
 */
public class EverydayCond extends BaseCond {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wxOpenid;
	private String wxMsgtype;
	private Date gmtCreatedMin;
	private Date gmtCreatedMax;
	private Long excludeId;
    private Long subjectId;
    private Long uid;

	public String getWxOpenid() {
		
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}

	public String getWxMsgtype() {
		return wxMsgtype;
	}

	public void setWxMsgtype(String wxMsgtype) {
		this.wxMsgtype = wxMsgtype;
	}

	public Date getGmtCreatedMin() {
		return gmtCreatedMin;
	}

	public void setGmtCreatedMin(Date gmtCreatedMin) {
		this.gmtCreatedMin = gmtCreatedMin;
	}

	public Date getGmtCreatedMax() {
		return gmtCreatedMax;
	}

	public void setGmtCreatedMax(Date gmtCreatedMax) {
		this.gmtCreatedMax = gmtCreatedMax;
	}

	public Long getExcludeId() {
		return excludeId;
	}

	public void setExcludeId(Long excludeId) {
		this.excludeId = excludeId;
	}

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
