/**
 * Copyright 2010 YYSoft
 * All right reserved.
 * Created on 2010-3-19
 */
package net.caiban.pc.erp.domain;

import java.util.List;

import com.google.common.base.Strings;
import net.caiban.pc.erp.enums.DirEnum;

/**
 * @author Mays (x03570227@gmail.com)
 * 
 */
public class Pager<E> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	public static final String DIR_DESC="desc";
//	public static final String DIR_ASC="asc";
	
	public final static int DEFAULT_START = 0;//默认起始记录位置
	public final static int DEFAULT_LIMIT = 20;//默认页面大小
	
	private Integer totals;// 总行数
	private Integer start;// 页起始记录
	private Integer limit;//页面大小（每页最大显示的数据条数)
	private String sort;//排序字段名称
	private String dir;//排序规则［asc/desc］
	private List<E> records;
	
	private String sortColumn;
	
	public Integer getTotals() {
		return totals;
	}
	public void setTotals(Integer totals) {
		this.totals = totals;
	}
	public Integer getStart() {
		if(start==null){
			start=DEFAULT_START;
		}
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		if(limit==null){
			limit = DEFAULT_LIMIT;
		}
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		
		if (Strings.isNullOrEmpty(dir)) {
			return DirEnum.DESC.name();
		}

		for (DirEnum d : DirEnum.values()) {
			if (dir.equalsIgnoreCase(d.name())) {
				return dir;
			}
		}

		return DirEnum.DESC.name();
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public List<E> getRecords() {
		return records;
	}
	public void setRecords(List<E> records) {
		this.records = records;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}
	
	public Integer getPreStart(){
		int prestart = getStart() - getLimit();
		if(prestart<=0){
			return 0;
		}
		return prestart;
	}
	
	public Integer getNextStart(){
		int nextstart = getStart()+getLimit();
		if(nextstart>=getTotals()){
			return getStart();
		}
		return nextstart;
		
	}


}
