package com.thinkgem.jeesite.modules.api.jmat.pojo.commons;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer totalRecord;// 总记录数

	private List<T> result;// 每一页的数据

	public PageBean() {
		super();
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PageBean [totalRecord=" + totalRecord + ", result=" + result + "]";
	}

}
