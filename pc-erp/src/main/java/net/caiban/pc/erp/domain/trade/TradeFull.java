/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author mays
 *
 */
@Deprecated
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class TradeFull implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Trade trade;
	private TradeDefine define;
	
	public TradeFull(){
		
	}
	
	public TradeFull(Trade trade, TradeDefine define){
		this.trade = trade;
		this.define = define;
	}
	
	public Trade getTrade() {
		return trade;
	}
	public void setTrade(Trade trade) {
		this.trade = trade;
	}
	public TradeDefine getDefine() {
		return define;
	}
	public void setDefine(TradeDefine define) {
		this.define = define;
	}
	
}
