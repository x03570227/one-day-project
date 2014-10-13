/**
 * 
 */
package net.caiban.pc.event.persist.events;

import net.caiban.pc.event.domain.events.Events;

/**
 * @author mays
 *
 */
public interface EventsMapper {

	public Integer save(Events events);
	
}
