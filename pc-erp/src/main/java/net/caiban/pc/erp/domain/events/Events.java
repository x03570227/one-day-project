/**
 * 
 */
package net.caiban.pc.erp.domain.events;

import java.util.Date;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class Events extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7265038251765569140L;
	
	private Integer uid;
	private String name;
	//@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date gmtStart;
	//@DateTimeFormat(pattern="yyyy-MM-dd") 
	private Date gmtEnd;
	private Double moneyBudget;
	private Double moneyFinalCoast;
	private Integer statusPublic;
	private String content;
	private String picSmall;
	private String picNormal;
	private String picLarger;
	private String picBanner;
	private String location;
	private String locationMapx;
	private String locationMapy;
	private String clearStatus;
	
	public final static int STATUS_PUBLIC = 1;
	public final static int STATUS_DEFAULT = 0;
	
	
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
	public Double getMoneyBudget() {
		return moneyBudget;
	}
	public void setMoneyBudget(Double moneyBudget) {
		this.moneyBudget = moneyBudget;
	}
	public Double getMoneyFinalCoast() {
		return moneyFinalCoast;
	}
	public void setMoneyFinalCoast(Double moneyFinalCoast) {
		this.moneyFinalCoast = moneyFinalCoast;
	}
	public Integer getStatusPublic() {
		return statusPublic;
	}
	public void setStatusPublic(Integer statusPublic) {
		this.statusPublic = statusPublic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicSmall() {
		return picSmall;
	}
	public void setPicSmall(String picSmall) {
		this.picSmall = picSmall;
	}
	public String getPicNormal() {
		return picNormal;
	}
	public void setPicNormal(String picNormal) {
		this.picNormal = picNormal;
	}
	public String getPicLarger() {
		return picLarger;
	}
	public void setPicLarger(String picLarger) {
		this.picLarger = picLarger;
	}
	public String getPicBanner() {
		return picBanner;
	}
	public void setPicBanner(String picBanner) {
		this.picBanner = picBanner;
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
	public String getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
	}
	
	
	
}
