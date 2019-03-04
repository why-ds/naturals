package com.naturals.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageVO {
	private static final int DEFAULT_SIZE = 10;
	private static final int DEFAULT_MAX_SIZE = 50;

	private int page;
	private int size;
	
	private String keyword;
	private String type;
	private String sdate;
	private String edate;

	public PageVO() {
		this.page = 1;
		this.size = DEFAULT_SIZE;
	}
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return  edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page < 0 ? 1 : page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {

		this.size = size < DEFAULT_SIZE || size > DEFAULT_MAX_SIZE ? DEFAULT_SIZE : size;
	}

	// ... = varargs  
	public Pageable makePageable(int direction, String... proops) {
		Sort.Direction dir = direction  == 0 ? Sort.Direction.DESC : Sort.Direction.ASC;
		
		return PageRequest.of(this.page -1, this.size, dir, proops);
	}

}
