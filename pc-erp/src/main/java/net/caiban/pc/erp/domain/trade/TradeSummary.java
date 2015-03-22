/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

import java.math.BigDecimal;
import java.util.Date;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
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
