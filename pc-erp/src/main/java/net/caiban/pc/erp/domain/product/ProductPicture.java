/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author parox
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ProductPicture extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
