/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 地区(人事管理)Entity
 * @author ljc
 * @version 2018-03-29
 */
public class RsglDiQu extends DataEntity<RsglDiQu> {
	
	private static final long serialVersionUID = 1L;
	private Integer dqId;		// 地区ID
	private String dqName;		// 地区名字
	private String dqCode;		// 地区编码
	private Integer dqLishu;		// dq_lishu
	private Date dqTime;		// dq_time
	private Integer dqJingcheng;		// dq_jingcheng
	private String dqQuhao;		// dq_quhao
	private Integer dqOldid;		// dq_oldid
	private Date lasteditdate;		// lasteditdate
	private Date creationdate;		// creationdate
	private Integer tbDiqu;		// tb_diqu
	private String wpsUsestate;		// wps_usestate
	
	public RsglDiQu() {
		super();
	}

	public RsglDiQu(String id){
		super(id);
	}

	@NotNull(message="地区ID不能为空")
	public Integer getDqId() {
		return dqId;
	}

	public void setDqId(Integer dqId) {
		this.dqId = dqId;
	}
	
	@Length(min=0, max=50, message="地区名字长度必须介于 0 和 50 之间")
	public String getDqName() {
		return dqName;
	}

	public void setDqName(String dqName) {
		this.dqName = dqName;
	}
	
	@Length(min=0, max=50, message="地区编码长度必须介于 0 和 50 之间")
	public String getDqCode() {
		return dqCode;
	}

	public void setDqCode(String dqCode) {
		this.dqCode = dqCode;
	}
	
	public Integer getDqLishu() {
		return dqLishu;
	}

	public void setDqLishu(Integer dqLishu) {
		this.dqLishu = dqLishu;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDqTime() {
		return dqTime;
	}

	public void setDqTime(Date dqTime) {
		this.dqTime = dqTime;
	}
	
	public Integer getDqJingcheng() {
		return dqJingcheng;
	}

	public void setDqJingcheng(Integer dqJingcheng) {
		this.dqJingcheng = dqJingcheng;
	}
	
	@Length(min=0, max=10, message="dq_quhao长度必须介于 0 和 10 之间")
	public String getDqQuhao() {
		return dqQuhao;
	}

	public void setDqQuhao(String dqQuhao) {
		this.dqQuhao = dqQuhao;
	}
	
	public Integer getDqOldid() {
		return dqOldid;
	}

	public void setDqOldid(Integer dqOldid) {
		this.dqOldid = dqOldid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLasteditdate() {
		return lasteditdate;
	}

	public void setLasteditdate(Date lasteditdate) {
		this.lasteditdate = lasteditdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}
	
	public Integer getTbDiqu() {
		return tbDiqu;
	}

	public void setTbDiqu(Integer tbDiqu) {
		this.tbDiqu = tbDiqu;
	}
	
	@Length(min=0, max=50, message="wps_usestate长度必须介于 0 和 50 之间")
	public String getWpsUsestate() {
		return wpsUsestate;
	}

	public void setWpsUsestate(String wpsUsestate) {
		this.wpsUsestate = wpsUsestate;
	}
	
}