/**
 * 
 */
package net.caiban.pc.erp.persist.events;

import net.caiban.pc.erp.domain.events.Events;

/**
 * @author mays
 *
 */
public interface EventsMapper {

	public Integer save(Events events);
	
}
