/**
 * 
 */
package net.caiban.pc.erp.domain.calendar;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author parox
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class EventsNotify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date gmtCreated;
	private Date gmtModified;
	private Integer uid;
	private Long eventsId;
	private Integer offsetSec;
	private Date gmtNotify;
	private Short notifyType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getGmtCreated() {
		return gmtCreated;
	}
	public void setGmtCreated(Date gmtCreated) {
		this.gmtCreated = gmtCreated;
	}
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Long getEventsId() {
		return eventsId;
	}
	public void setEventsId(Long eventsId) {
		this.eventsId = eventsId;
	}
	public Integer getOffsetSec() {
		return offsetSec;
	}
	public void setOffsetSec(Integer offsetSec) {
		this.offsetSec = offsetSec;
	}
	public Date getGmtNotify() {
		return gmtNotify;
	}
	public void setGmtNotify(Date gmtNotify) {
		this.gmtNotify = gmtNotify;
	}
	public Short getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(Short notifyType) {
		this.notifyType = notifyType;
	}
	
	
}
