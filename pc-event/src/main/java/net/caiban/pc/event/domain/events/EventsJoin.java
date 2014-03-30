/**
 * 
 */
package net.caiban.pc.event.domain.events;

import net.caiban.pc.event.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class EventsJoin extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private Integer eventsId;
	private Double moneyPrepaid;
	private Integer yesOrNo;
	private String clearStatus;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getEventsId() {
		return eventsId;
	}
	public void setEventsId(Integer eventsId) {
		this.eventsId = eventsId;
	}
	public Double getMoneyPrepaid() {
		return moneyPrepaid;
	}
	public void setMoneyPrepaid(Double moneyPrepaid) {
		this.moneyPrepaid = moneyPrepaid;
	}
	public Integer getYesOrNo() {
		return yesOrNo;
	}
	public void setYesOrNo(Integer yesOrNo) {
		this.yesOrNo = yesOrNo;
	}
	public String getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
	}
	
	
}
