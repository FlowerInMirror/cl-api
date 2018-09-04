package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 平台搜索词(删除/更新)
 * @author  ljc
 * @version 2018-03-28
 */
@ApiModel(value = "材料搜索|用途(删除/更新)")
public class PlatformSearchTerm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("四级科目ID")
	private String treeFourID;
	
	@ApiModelProperty("类型：1搜索，2用途，4搜索自动生成内容（可位运算|）")
	private int type;
	
	@ApiModelProperty("类型/内容(1搜索，2用途)")
	private List<Map<Integer, String>> items = new ArrayList<Map<Integer,String>>() ;

	public String getTreeFourID() {
		return treeFourID;
	}

	public void setTreeFourID(String treeFourID) {
		this.treeFourID = treeFourID;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<Map<Integer, String>> getItems() {
		return items;
	}

	public void setItems(List<Map<Integer, String>> items) {
		this.items = items;
	}
	
	
}
