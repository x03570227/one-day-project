/**
 * 
 */
package net.caiban.pc.erp.domain.calendar;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author parox
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class Events implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer uid;
	private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtStart;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtEnd;
	private Integer startTime;
	private Integer endTime;
	private String content;
	private Integer calendarId;
	private String location;
	private String locationMapx;
	private String locationMapy;
	private String rule;
	private Long seriesId;
	private String color;
	private Short repeatOver;
	private Date repeatUntil;
	private Short idle;
	private Date gmtCreated;
	private Date gmtModified;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getGmtStart() {
		return gmtStart;
	}
	public void setGmtStart(Date gmtStart) {
		this.gmtStart = gmtStart;
	}
	public Date getGmtEnd() {
		return gmtEnd;
	}
	public void setGmtEnd(Date gmtEnd) {
		this.gmtEnd = gmtEnd;
	}
	public Integer getStartTime() {
		return startTime;
	}
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}
	public Integer getEndTime() {
		return endTime;
	}
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCalendarId() {
		return calendarId;
	}
	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLocationMapx() {
		return locationMapx;
	}
	public void setLocationMapx(String locationMapx) {
		this.locationMapx = locationMapx;
	}
	public String getLocationMapy() {
		return locationMapy;
	}
	public void setLocationMapy(String locationMapy) {
		this.locationMapy = locationMapy;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Long getSeriesId() {
		return seriesId;
	}
	public void setSeriesId(Long seriesId) {
		this.seriesId = seriesId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Short getRepeatOver() {
		return repeatOver;
	}
	public void setRepeatOver(Short repeatOver) {
		this.repeatOver = repeatOver;
	}
	public Date getRepeatUntil() {
		return repeatUntil;
	}
	public void setRepeatUntil(Date repeatUntil) {
		this.repeatUntil = repeatUntil;
	}
	public Short getIdle() {
		return idle;
	}
	public void setIdle(Short idle) {
		this.idle = idle;
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
	
	
}
