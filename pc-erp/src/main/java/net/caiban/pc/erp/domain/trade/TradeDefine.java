/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class TradeDefine extends BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tradeId;
	private String details;
	
	public Integer getTradeId() {
		return tradeId;
	}
	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

}
