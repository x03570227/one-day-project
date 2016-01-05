/**
 * 
 */
package net.caiban.pc.erp.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author mar
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class Everyday extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

}
