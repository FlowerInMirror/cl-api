/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 项目|人员回访记录V_VisitEntity
 * @author  ljc
 * @version 2018-7-25 11:24:19
 */
public class Visit extends DataEntity<Visit> {
	
	private static final long serialVersionUID = 1L;

	private Integer vId;		// ID
	private Integer vType;		// 回访类型：1项目，2材料商，3配送单,100材料子库,200集团主案
	private Integer vOperattype;		// 操作人类型：1集团，2地方
	private String vPiid;		// 项目ID
	private Integer vUserid;		// 材料商ID
	private Integer vMark;		// 标记：1正常，2异常，3问题
	private String vContent;		// 回访内容
	private String vKahao;		// 回访人
	private Integer vJincheng;		// 进程：0正常、1删除  （默认0）
	private Date vCreatetime;		// 创建时间    （默认getdate()）
	private Date vUpdatetime;		// 更新时间
	
	public Visit() {
		super();
	}

	public Visit(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getVId() {
		return vId;
	}

	public void setVId(Integer vId) {
		this.vId = vId;
	}

	public Integer getVType() {
		return vType;
	}

	public void setVType(Integer vType) {
		this.vType = vType;
	}

	public Integer getVOperattype() {
		return vOperattype;
	}

	public void setVOperattype(Integer vOperattype) {
		this.vOperattype = vOperattype;
	}

	@Length(min=0, max=36, message="项目ID长度必须介于 0 和 36 之间")
	public String getVPiid() {
		return vPiid;
	}

	public void setVPiid(String vPiid) {
		this.vPiid = vPiid;
	}

	public Integer getVUserid() {
		return vUserid;
	}

	public void setVUserid(Integer vUserid) {
		this.vUserid = vUserid;
	}

	public Integer getVMark() {
		return vMark;
	}

	public void setVMark(Integer vMark) {
		this.vMark = vMark;
	}

	public String getVContent() {
		return vContent;
	}

	public void setVContent(String vContent) {
		this.vContent = vContent;
	}

	@Length(min=0, max=50, message="回访人长度必须介于 0 和 50 之间")
	public String getVKahao() {
		return vKahao;
	}

	public void setVKahao(String vKahao) {
		this.vKahao = vKahao;
	}

	public Integer getVJincheng() {
		return vJincheng;
	}

	public void setVJincheng(Integer vJincheng) {
		this.vJincheng = vJincheng;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVCreatetime() {
		return vCreatetime;
	}

	public void setVCreatetime(Date vCreatetime) {
		this.vCreatetime = vCreatetime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVUpdatetime() {
		return vUpdatetime;
	}

	public void setVUpdatetime(Date vUpdatetime) {
		this.vUpdatetime = vUpdatetime;
	}

}