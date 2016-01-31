/**
 * 
 */
package net.caiban.pc.erp.domain.product;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import net.caiban.pc.erp.domain.BaseCond;

/**
 * @author mays
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class ProductCond extends BaseCond{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String name;
	private String categoryCode;
	private Long cid;
	private String statusLife;
	
	private String categoryCodeMatchBefore; //前匹配
	private String nameMatchBefore; //前匹配
	private String nameFuzzy; //按名称模糊查询
	private String codeMatchBefore;
	
	private Long productId;
	private String archived;
	
	private Long groupId;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getStatusLife() {
		return statusLife;
	}

	public void setStatusLife(String statusLife) {
		this.statusLife = statusLife;
	}

	public String getCategoryCodeMatchBefore() {
		return categoryCodeMatchBefore;
	}

	public void setCategoryCodeMatchBefore(String categoryCodeMatchBefore) {
		this.categoryCodeMatchBefore = categoryCodeMatchBefore;
	}

	public String getNameMatchBefore() {
		return nameMatchBefore;
	}

	public void setNameMatchBefore(String nameMatchBefore) {
		this.nameMatchBefore = nameMatchBefore;
	}

	public String getNameFuzzy() {
		return nameFuzzy;
	}

	public void setNameFuzzy(String nameFuzzy) {
		this.nameFuzzy = nameFuzzy;
	}

	public String getCodeMatchBefore() {
		return codeMatchBefore;
	}

	public void setCodeMatchBefore(String codeMatchBefore) {
		this.codeMatchBefore = codeMatchBefore;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getArchived() {
		return archived;
	}

	public void setArchived(String archived) {
		this.archived = archived;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}
