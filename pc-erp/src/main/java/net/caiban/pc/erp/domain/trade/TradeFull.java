/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.io.Serializable;

/**
 * @author mays
 *
 */
public class TradeFull implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Trade trade;
	private TradeDefine define;
	
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
