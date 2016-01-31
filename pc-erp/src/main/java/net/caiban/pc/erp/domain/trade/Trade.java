/**
 * 
 */
package net.caiban.pc.erp.domain.trade;

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
public class Trade extends BaseDomain {

	private static final long serialVersionUID = 1L;
	
	public enum STATUS{
		DEFAULT(0), DONE(-1);
		private Integer key;
		
		STATUS(Integer key){
			this.key=key;
		}
		public Integer getKey(){
			return key;
		}
	}
	
	private Long cid;
	private Long pidFirst;
	private String tradeNum;
	private String sourceDomain;
	private String sourceType;
	private Integer status;
	
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

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Long getPidFirst() {
		return pidFirst;
	}

	public void setPidFirst(Long pidFirst) {
		this.pidFirst = pidFirst;
	}
}
