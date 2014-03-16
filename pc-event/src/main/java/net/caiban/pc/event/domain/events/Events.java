/**
 * 
 */
package net.caiban.pc.event.domain.events;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import net.caiban.pc.event.domain.BaseDomain;

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
	@DateTimeFormat(pattern="yyyy-MM-dd") private Date gmtStart;
	@DateTimeFormat(pattern="yyyy-MM-dd") private Date gmtEnd;
	private Double moneyBudget;
	private Double moneyFinalCoast;
	private Integer statusPublic;
	private String content;
	
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
	
	
	
}
