/**
 * 
 */
package net.caiban.pc.erp.domain.events;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class EventsItemCoast extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer uid;
	private Integer eventsId;
	private Integer eventsItemId;
	private Double coast;
	
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
	public Integer getEventsItemId() {
		return eventsItemId;
	}
	public void setEventsItemId(Integer eventsItemId) {
		this.eventsItemId = eventsItemId;
	}
	public Double getCoast() {
		return coast;
	}
	public void setCoast(Double coast) {
		this.coast = coast;
	}
	
}
