/**
 * 
 */
package net.caiban.pc.event.service.events;

import java.util.Map;

/**
 * @author mays
 *
 */
public interface EventsService {

	public Integer[] changeToIntArray(String origin);
	
	public Map<String, Integer> filterAppendJoiner(String origin,
			String originId, String append);
}
