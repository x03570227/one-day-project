/**
 * 
 */
package net.caiban.pc.erp.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;


/**
 * @author parox
 *
 */
public class EventUtil {

	static EventBus eventBus;

	public static void initAsync(){
		ExecutorService threadPool = Executors.newCachedThreadPool();
		eventBus =new AsyncEventBus(threadPool);
		eventBus.register(new EventListener());
	}

	public static EventBus getEventBus(){
		return eventBus;
	}

}
