/**
 * 
 */
package net.caiban.pc.erp.domain;

import java.io.Serializable;


/**
 * @author mays
 *
 */
public class BaseCond implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer limit;

	public Integer getLimit() {
		if(limit==null){
			limit = Pager.DEFAULT_LIMIT;
		}
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
	
}
