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

import net.caiban.pc.erp.config.AppConst;
import net.caiban.utils.DateUtil;

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
	private String pageTitle;
	
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
	
	

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
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
	
	public String getGmtCreatedStr(){
		if(getGmtCreated()==null){
			return null;
		}
		return DateUtil.toString(getGmtCreated(), AppConst.DATE_FORMAT_DATE);
	}
	
	public String getImageUrl(){
		if(Strings.isNullOrEmpty(getWxPicurl())){
			return null;
		}
		
		if(getWxPicurl().startsWith("http")){
			return getWxPicurl();
		}
		
		return UpyunNamespaceEnum.IMAGE.getHost()+getWxPicurl();
	}
}
