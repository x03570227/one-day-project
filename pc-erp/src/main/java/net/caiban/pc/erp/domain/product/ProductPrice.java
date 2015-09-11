/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.format.annotation.DateTimeFormat;

import net.caiban.pc.erp.domain.BaseDomain;
import net.caiban.pc.erp.utils.jackson.CustomDateSerializer;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ProductPrice extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer productId;
	private String priceCode;
	private String priceUnitCode;
	private BigDecimal priceValue;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date gmtExpired;
	private String archived;
	private String remark;
	
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
	@JsonSerialize(using=CustomDateSerializer.class)
	public Date getGmtExpired() {
		return gmtExpired;
	}
	public void setGmtExpired(Date gmtExpired) {
		this.gmtExpired = gmtExpired;
	}
	public String getArchived() {
		return archived;
	}
	public void setArchived(String archived) {
		this.archived = archived;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
