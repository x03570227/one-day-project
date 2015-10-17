/**
 * 
 */
package net.caiban.pc.erp.domain.calendar;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author parox
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class CalendarSubscribe extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private Integer calendarId;
	private String accessLevel;
	private Short showInList;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	public String getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	public Short getShowInList() {
		return showInList;
	}
	public void setShowInList(Short showInList) {
		this.showInList = showInList;
	}
	

}
