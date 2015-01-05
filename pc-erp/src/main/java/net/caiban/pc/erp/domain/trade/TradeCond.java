/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.util.Date;

import net.caiban.pc.erp.domain.BaseCond;

/**
 * @author mays
 *
 */
public class TradeCond extends BaseCond{

	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	
	private String tradeNum;
	private Integer status;
	
	private String tradeNumMatchBefore;
	private Date gmtCreatedMin;
	private Date gmtCreatedMax;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getTradeNumMatchBefore() {
		return tradeNumMatchBefore;
	}

	public void setTradeNumMatchBefore(String tradeNumMatchBefore) {
		this.tradeNumMatchBefore = tradeNumMatchBefore;
	}

	public String getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getGmtCreatedMin() {
		return gmtCreatedMin;
	}

	public void setGmtCreatedMin(Date gmtCreatedMin) {
		this.gmtCreatedMin = gmtCreatedMin;
	}

	public Date getGmtCreatedMax() {
		return gmtCreatedMax;
	}

	public void setGmtCreatedMax(Date gmtCreatedMax) {
		this.gmtCreatedMax = gmtCreatedMax;
	}
	
}
