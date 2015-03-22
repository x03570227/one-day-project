/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.util.Date;

import net.caiban.pc.erp.domain.BaseCond;

import org.springframework.format.annotation.DateTimeFormat;

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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtCreatedMin;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtCreatedMax;
	
	private Integer idMax;
	
	private String sourceDomain;
	private String sourceType;

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

	public Integer getIdMax() {
		return idMax;
	}

	public void setIdMax(Integer idMax) {
		this.idMax = idMax;
	}
	
}
