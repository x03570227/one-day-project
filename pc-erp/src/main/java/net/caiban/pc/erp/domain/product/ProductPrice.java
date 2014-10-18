/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import java.math.BigDecimal;
import java.util.Date;

import net.caiban.pc.erp.domain.BaseDomain;

/**
 * @author mays
 *
 */
public class ProductPrice extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer productId;
	private String priceCode;
	private String priceUnitCode;
	private BigDecimal priceValue;
	private Date gmtExpired;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getPriceCode() {
		return priceCode;
	}
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	public String getPriceUnitCode() {
		return priceUnitCode;
	}
	public void setPriceUnitCode(String priceUnitCode) {
		this.priceUnitCode = priceUnitCode;
	}
	public BigDecimal getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(BigDecimal priceValue) {
		this.priceValue = priceValue;
	}
	public Date getGmtExpired() {
		return gmtExpired;
	}
	public void setGmtExpired(Date gmtExpired) {
		this.gmtExpired = gmtExpired;
	}
	
}
