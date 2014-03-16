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
public class EventsItem extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6170998229931840832L;
	
	private Integer uid;
	private Integer eventId;
	private String name;
	private Double moneyFinalCoast;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") private Date gmtHappen;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoneyFinalCoast() {
		return moneyFinalCoast;
	}
	public void setMoneyFinalCoast(Double moneyFinalCoast) {
		this.moneyFinalCoast = moneyFinalCoast;
	}
	public Date getGmtHappen() {
		return gmtHappen;
	}
	public void setGmtHappen(Date gmtHappen) {
		this.gmtHappen = gmtHappen;
	}
	
}
