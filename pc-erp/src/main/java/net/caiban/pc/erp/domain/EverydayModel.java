/**
 * 
 */
package net.caiban.pc.erp.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * @author mar
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=Inclusion.NON_NULL)
public class EverydayModel extends Everyday{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer maxDayIndex;
	private BigDecimal nowDayPercent;
	
	public Integer getMaxDayIndex() {
		return maxDayIndex;
	}

	public void setMaxDayIndex(Integer maxDayIndex) {
		this.maxDayIndex = maxDayIndex;
	}

	public BigDecimal getNowDayPercent() {
		return nowDayPercent;
	}

	public void setNowDayPercent(BigDecimal nowDayPercent) {
		this.nowDayPercent = nowDayPercent;
	}

	public String getPanelClass() {
		int i = new Random().nextInt(4);
		switch (i) {
		case 0:
			return "warning";
		case 1:
			return "success";
		case 2:
			return "info";
		case 3:
			return "danger";
		default:
			return "default";
		}
	}
	
	public List<String> getTagList(){
		if(Strings.isNullOrEmpty(getTags())){
			return Lists.newArrayList();
		}
		return Lists.newArrayList(Splitter.on(",").split(getTags()));
	}
}
