/**
 * 
 */
package net.caiban.pc.erp.event;

import org.apache.log4j.Logger;

import com.google.common.eventbus.Subscribe;

/**
 * @author parox
 *
 */
public class EventListener {

	final static Logger LOG = Logger.getLogger(EventListener.class);
	
	@Subscribe
	public void onEvent(CreateProductIndexEvent event){
		try {
			event.excute();
		} catch (EventException e) {
			e.printStackTrace();
		}
	}
	
	@Subscribe
	public void onEvent(UpdateProductIndexEvent event){
		try {
			event.excute();
		} catch (EventException e) {
			e.printStackTrace();
		}
	}
}
