/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料地区信息Entity
 * @author ljc
 * @version 2018-03-16
 */
public class TreeCityInfo extends DataEntity<TreeCityInfo> {
	
	private static final long serialVersionUID = 1L;
	private String tciId;		// ID
	private String tciTreeid;		// 树ID
	private Integer tciCityid;		// 地区ID
	private Integer tciPageindex;		// 第几页   （默认0）
	private Integer tciPagenum;		// 第几号   （默认0）
	private Date tciToptime;		// 置顶日期
	private Integer tciHomehoststate;		// 是否首页推荐：1推荐  （默认0） 暂时搁置
	private Integer tciStatus;		// 进程：0正常、1删除  （默认0）
	private Date tciCreatetime;		// 创建时间    （默认getdate()）
	private Date tciUpdatetime;		// 更新时间
	
	public TreeCityInfo() {
		super();
	}

	public TreeCityInfo(String id){
		super(id);
	}

	public String getTciId() {
		return tciId;
	}

	public void setTciId(String tciId) {
		this.tciId = tciId;
	}
	
	public String getTciTreeid() {
		return tciTreeid;
	}

	public void setTciTreeid(String tciTreeid) {
		this.tciTreeid = tciTreeid;
	}
	
	public Integer getTciCityid() {
		return tciCityid;
	}

	public void setTciCityid(Integer tciCityid) {
		this.tciCityid = tciCityid;
	}
	
	public Integer getTciPageindex() {
		return tciPageindex;
	}

	public void setTciPageindex(Integer tciPageindex) {
		this.tciPageindex = tciPageindex;
	}
	
	public Integer getTciPagenum() {
		return tciPagenum;
	}

	public void setTciPagenum(Integer tciPagenum) {
		this.tciPagenum = tciPagenum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTciToptime() {
		return tciToptime;
	}

	public void setTciToptime(Date tciToptime) {
		this.tciToptime = tciToptime;
	}
	
	public Integer getTciHomehoststate() {
		return tciHomehoststate;
	}

	public void setTciHomehoststate(Integer tciHomehoststate) {
		this.tciHomehoststate = tciHomehoststate;
	}
	
	public Integer getTciStatus() {
		return tciStatus;
	}

	public void setTciStatus(Integer tciStatus) {
		this.tciStatus = tciStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTciCreatetime() {
		return tciCreatetime;
	}

	public void setTciCreatetime(Date tciCreatetime) {
		this.tciCreatetime = tciCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTciUpdatetime() {
		return tciUpdatetime;
	}

	public void setTciUpdatetime(Date tciUpdatetime) {
		this.tciUpdatetime = tciUpdatetime;
	}
	
}