/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 分类表Entity
 * @author ljc
 * @version 2018-04-12
 */
public class TypeInfo extends DataEntity<TypeInfo> {
	
	private static final long serialVersionUID = 1L;
	private String tiId;		// ID
	private Integer tiType;		// 类型：1平台图片分类，2平台置顶分类
	private String tiTitle;		// 标题
	private String tiTitleen;		// 英文标题
	private String tiDescription;		// 描述
	private Integer tiPhototype;		// 照片类型（可用在前台版块区分上） 暂时无用
	private String tiUploaddescription;		// 上传描述
	private Integer tiStatus;		// 进程：0正常、1删除  （默认0）
	private Date tiTime;		// 时间（可用作排序）    （默认getdate()）
	
	public TypeInfo() {
		super();
	}

	public TypeInfo(String id){
		super(id);
	}

	public String getTiId() {
		return tiId;
	}

	public void setTiId(String tiId) {
		this.tiId = tiId;
	}
	
	public Integer getTiType() {
		return tiType;
	}

	public void setTiType(Integer tiType) {
		this.tiType = tiType;
	}
	
	@Length(min=0, max=50, message="标题长度必须介于 0 和 50 之间")
	public String getTiTitle() {
		return tiTitle;
	}

	public void setTiTitle(String tiTitle) {
		this.tiTitle = tiTitle;
	}
	
	@Length(min=0, max=50, message="英文标题长度必须介于 0 和 50 之间")
	public String getTiTitleen() {
		return tiTitleen;
	}

	public void setTiTitleen(String tiTitleen) {
		this.tiTitleen = tiTitleen;
	}
	
	@Length(min=0, max=500, message="描述长度必须介于 0 和 500 之间")
	public String getTiDescription() {
		return tiDescription;
	}

	public void setTiDescription(String tiDescription) {
		this.tiDescription = tiDescription;
	}
	
	public Integer getTiPhototype() {
		return tiPhototype;
	}

	public void setTiPhototype(Integer tiPhototype) {
		this.tiPhototype = tiPhototype;
	}
	
	@Length(min=0, max=500, message="上传描述长度必须介于 0 和 500 之间")
	public String getTiUploaddescription() {
		return tiUploaddescription;
	}

	public void setTiUploaddescription(String tiUploaddescription) {
		this.tiUploaddescription = tiUploaddescription;
	}
	
	public Integer getTiStatus() {
		return tiStatus;
	}

	public void setTiStatus(Integer tiStatus) {
		this.tiStatus = tiStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTiTime() {
		return tiTime;
	}

	public void setTiTime(Date tiTime) {
		this.tiTime = tiTime;
	}
	
}