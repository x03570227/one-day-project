/**
 * 
 */
package net.caiban.pc.erp.service.events;

import java.util.Map;

import net.caiban.pc.erp.domain.events.Events;

/**
 * @author mays
 *
 */
public interface EventsService {

	public Integer[] changeToIntArray(String origin);
	
	public Map<String, Long> filterAppendJoiner(String origin,
			String originId, String append);
	
	public void initGmt(Events events, String gmtStartStr, String gmtEndStr);
	
	public Long saveEvent(Events event);
	
	public void appendJoiner(Integer eventId, String inviteAccountId);
}
