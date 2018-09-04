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
 * 材料处理表Entity
 * @author ljc
 * @version 2018-07-26
 */
public class MaterialHandle extends DataEntity<MaterialHandle> {
	
	private static final long serialVersionUID = 1L;
	private Integer vmhId;		// vmh_id
	private String vmhMainobject;		// 处理主对象
	private String vmhAuxiliaryobject;		// 处理辅助对象
	private String vmhContent;		// 处理内容
	private Integer vmhType;		// 处理类型：1正常，2问题，3异常,4.下架
	private String vmhUsercard;		// 处理人卡号
	private Integer vmhUsertype;		// 处理人类型
	private Integer vmhCityid;		// 处理城市ID
	private Integer vmhStatus;		// 进程状态：0正常、1删除  （默认0）
	private Date vmhCreatetime;		// 创建时间    （默认getdate()）
	private Date vmhUpdatetime;		// 更新时间
	
	public MaterialHandle() {
		super();
	}

	public MaterialHandle(String id){
		super(id);
	}

	@NotNull(message="vmh_id不能为空")
	public Integer getVmhId() {
		return vmhId;
	}

	public void setVmhId(Integer vmhId) {
		this.vmhId = vmhId;
	}
	
	public String getVmhMainobject() {
		return vmhMainobject;
	}

	public void setVmhMainobject(String vmhMainobject) {
		this.vmhMainobject = vmhMainobject;
	}
	
	@Length(min=0, max=-1, message="处理辅助对象长度必须介于 0 和 -1 之间")
	public String getVmhAuxiliaryobject() {
		return vmhAuxiliaryobject;
	}

	public void setVmhAuxiliaryobject(String vmhAuxiliaryobject) {
		this.vmhAuxiliaryobject = vmhAuxiliaryobject;
	}
	
	public String getVmhContent() {
		return vmhContent;
	}

	public void setVmhContent(String vmhContent) {
		this.vmhContent = vmhContent;
	}
	
	public Integer getVmhType() {
		return vmhType;
	}

	public void setVmhType(Integer vmhType) {
		this.vmhType = vmhType;
	}
	
	@Length(min=0, max=-1, message="处理人卡号长度必须介于 0 和 -1 之间")
	public String getVmhUsercard() {
		return vmhUsercard;
	}

	public void setVmhUsercard(String vmhUsercard) {
		this.vmhUsercard = vmhUsercard;
	}
	
	public Integer getVmhUsertype() {
		return vmhUsertype;
	}

	public void setVmhUsertype(Integer vmhUsertype) {
		this.vmhUsertype = vmhUsertype;
	}
	
	public Integer getVmhCityid() {
		return vmhCityid;
	}

	public void setVmhCityid(Integer vmhCityid) {
		this.vmhCityid = vmhCityid;
	}
	
	public Integer getVmhStatus() {
		return vmhStatus;
	}

	public void setVmhStatus(Integer vmhStatus) {
		this.vmhStatus = vmhStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVmhCreatetime() {
		return vmhCreatetime;
	}

	public void setVmhCreatetime(Date vmhCreatetime) {
		this.vmhCreatetime = vmhCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVmhUpdatetime() {
		return vmhUpdatetime;
	}

	public void setVmhUpdatetime(Date vmhUpdatetime) {
		this.vmhUpdatetime = vmhUpdatetime;
	}
	
}