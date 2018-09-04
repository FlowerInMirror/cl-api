/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料搜索|用途Entity
 * @author ljc
 * @version 2018-03-28
 */
public class SearchItem extends DataEntity<SearchItem> {
	
	private static final long serialVersionUID = 1L;
	private Integer seId;		// ID
	private String seTreefour;		// 四级ID（规格）
	private String seTsid;		// 材料母库ID
	private String seContent;		// 内容
	private Integer seType;		// 类型：1搜索，2用途，4搜索自动生成内容
	private Integer seJincheng;		// 进程：0正常、1删除  （默认0）
	private Date seCreatetime;		// 创建时间    （默认getdate()）
	private Date seUpdatetime;		// 更新时间
	
	public SearchItem() {
		super();
	}

	public SearchItem(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getSeId() {
		return seId;
	}

	public void setSeId(Integer seId) {
		this.seId = seId;
	}
	
	public String getSeTreefour() {
		return seTreefour;
	}

	public void setSeTreefour(String seTreefour) {
		this.seTreefour = seTreefour;
	}
	
	public String getSeTsid() {
		return seTsid;
	}

	public void setSeTsid(String seTsid) {
		this.seTsid = seTsid;
	}
	
	@Length(min=0, max=255, message="内容长度必须介于 0 和 255 之间")
	public String getSeContent() {
		return seContent;
	}

	public void setSeContent(String seContent) {
		this.seContent = seContent;
	}
	
	public Integer getSeType() {
		return seType;
	}

	public void setSeType(Integer seType) {
		this.seType = seType;
	}
	
	public Integer getSeJincheng() {
		return seJincheng;
	}

	public void setSeJincheng(Integer seJincheng) {
		this.seJincheng = seJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSeCreatetime() {
		return seCreatetime;
	}

	public void setSeCreatetime(Date seCreatetime) {
		this.seCreatetime = seCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSeUpdatetime() {
		return seUpdatetime;
	}

	public void setSeUpdatetime(Date seUpdatetime) {
		this.seUpdatetime = seUpdatetime;
	}
	
}