/**
 * 
 */
package net.caiban.pc.erp.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @author mar
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class Everyday extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wxOpenid;
	private String url;
	private String content;
	private String tags;
	private Integer dayIndex;
	private Integer dayItemIndex;
	
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Integer getDayIndex() {
		return dayIndex;
	}
	public void setDayIndex(Integer dayIndex) {
		this.dayIndex = dayIndex;
	}
	public Integer getDayItemIndex() {
		return dayItemIndex;
	}
	public void setDayItemIndex(Integer dayItemIndex) {
		this.dayItemIndex = dayItemIndex;
	}
	
	

}
