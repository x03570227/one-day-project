/**
 * 
 */
package net.caiban.pc.event.service.events;

import java.util.Map;

import net.caiban.pc.event.domain.events.Events;

/**
 * @author mays
 *
 */
public interface EventsService {

	public Integer[] changeToIntArray(String origin);
	
	public Map<String, Integer> filterAppendJoiner(String origin,
			String originId, String append);
	
	public void initGmt(Events events, String gmtStartStr, String gmtEndStr);
	
	public Integer saveEvent(Events event);
}
