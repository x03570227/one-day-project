/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class Trade extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private Integer pidFirst;
	private String tradeNum;
	private String sourceDomain;
	private String sourceType;
	private Integer status;
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getPidFirst() {
		return pidFirst;
	}
	public void setPidFirst(Integer pidFirst) {
		this.pidFirst = pidFirst;
	}
	public String getTradeNum() {
		return tradeNum;
	}
	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}
	public String getSourceDomain() {
		return sourceDomain;
	}
	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
