/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.util.Date;

import net.caiban.pc.erp.domain.BaseCond;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class TradeCond extends BaseCond{

	private static final long serialVersionUID = 1L;
	
	private Long cid;
	
	private String tradeNum;
	private Integer status;
	
	private String tradeNumMatchBefore;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtCreatedMin;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtCreatedMax;
	
	private Long idMax;
	
	private String sourceDomain;
	private String sourceType;
	
	private Long pidFirst;


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

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getIdMax() {
		return idMax;
	}

	public void setIdMax(Long idMax) {
		this.idMax = idMax;
	}

	public Long getPidFirst() {
		return pidFirst;
	}

	public void setPidFirst(Long pidFirst) {
		this.pidFirst = pidFirst;
	}
}
