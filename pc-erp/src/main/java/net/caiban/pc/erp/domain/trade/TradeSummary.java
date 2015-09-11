/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.math.BigDecimal;
import java.util.Date;

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
public class TradeSummary extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal totalFee;
	private Integer num;
	private Date gmtScan;
	
	public BigDecimal getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Date getGmtScan() {
		return gmtScan;
	}
	public void setGmtScan(Date gmtScan) {
		this.gmtScan = gmtScan;
	}
	
	
}
