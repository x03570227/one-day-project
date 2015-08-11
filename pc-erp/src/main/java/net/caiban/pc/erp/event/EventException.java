/**
 * 
 */
package net.caiban.pc.erp.event;

/**
 * @author mays
 *
 */
public class EventException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventException(){
		super();
	}
	
	public EventException(String msg){
		super(msg);
	}
	
	public EventException(String msg, Throwable cause){
		super(msg, cause);
	}
	
}
