/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目材料表Entity
 * @author ljc
 * @version 2018-07-26
 */
public class ProInfoMaterial extends DataEntity<ProInfoMaterial> {
	
	private static final long serialVersionUID = 1L;
	private String pmId;		// ID
	private String pmPiid;		// 项目ID
	private String pmRwdid;		// 任务单ID
	private String pmMatid;		// 材料ID
	private Integer pmMattype;		// 材料类型：1固定材料
	private Double pmMatprice;		// 材料价格（最后一次配送的价格）     默认最低价
	private Integer pmUserid;		// 材料商ID（最后一次配送的材料商）  默认0
	private Double pmTotalcount;		// 分配量
	private Double pmAlreadycount;		// 已下单量  (默认0)
	private Double pmFloatingrate;		// 浮动率 （默认20）
	private String pmMatname;		// 材料名称
	private String pmUnitname;		// 材料单位
	private Integer pmJincheng;		// 进程：0正常、1删除  （默认0）
	private Date pmCreatetime;		// 创建时间    （默认getdate()）
	private Date pmUpdatetime;		// 更新时间
	private Integer pmPushtoapp;		// 是否推送APP：1推送 （默认0）
	private Integer pmFormtype;		// 形成组合：1固定，2临时，4增项 (位运算)
	private Double pmOldcount;		// 原量
	private Double pmSplitcount;		// 拆分量（临时项拆分）
	private Double pmPlusminuscount;		// 增减量（增减项）
	
	public ProInfoMaterial() {
		super();
	}

	public ProInfoMaterial(String id){
		super(id);
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}
	
	public String getPmPiid() {
		return pmPiid;
	}

	public void setPmPiid(String pmPiid) {
		this.pmPiid = pmPiid;
	}
	
	@Length(min=0, max=50, message="任务单ID长度必须介于 0 和 50 之间")
	public String getPmRwdid() {
		return pmRwdid;
	}

	public void setPmRwdid(String pmRwdid) {
		this.pmRwdid = pmRwdid;
	}
	
	public String getPmMatid() {
		return pmMatid;
	}

	public void setPmMatid(String pmMatid) {
		this.pmMatid = pmMatid;
	}
	
	public Integer getPmMattype() {
		return pmMattype;
	}

	public void setPmMattype(Integer pmMattype) {
		this.pmMattype = pmMattype;
	}
	
	public Double getPmMatprice() {
		return pmMatprice;
	}

	public void setPmMatprice(Double pmMatprice) {
		this.pmMatprice = pmMatprice;
	}
	
	public Integer getPmUserid() {
		return pmUserid;
	}

	public void setPmUserid(Integer pmUserid) {
		this.pmUserid = pmUserid;
	}
	
	public Double getPmTotalcount() {
		return pmTotalcount;
	}

	public void setPmTotalcount(Double pmTotalcount) {
		this.pmTotalcount = pmTotalcount;
	}
	
	public Double getPmAlreadycount() {
		return pmAlreadycount;
	}

	public void setPmAlreadycount(Double pmAlreadycount) {
		this.pmAlreadycount = pmAlreadycount;
	}
	
	public Double getPmFloatingrate() {
		return pmFloatingrate;
	}

	public void setPmFloatingrate(Double pmFloatingrate) {
		this.pmFloatingrate = pmFloatingrate;
	}
	
	@Length(min=0, max=50, message="材料名称长度必须介于 0 和 50 之间")
	public String getPmMatname() {
		return pmMatname;
	}

	public void setPmMatname(String pmMatname) {
		this.pmMatname = pmMatname;
	}
	
	@Length(min=0, max=50, message="材料单位长度必须介于 0 和 50 之间")
	public String getPmUnitname() {
		return pmUnitname;
	}

	public void setPmUnitname(String pmUnitname) {
		this.pmUnitname = pmUnitname;
	}
	
	public Integer getPmJincheng() {
		return pmJincheng;
	}

	public void setPmJincheng(Integer pmJincheng) {
		this.pmJincheng = pmJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPmCreatetime() {
		return pmCreatetime;
	}

	public void setPmCreatetime(Date pmCreatetime) {
		this.pmCreatetime = pmCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPmUpdatetime() {
		return pmUpdatetime;
	}

	public void setPmUpdatetime(Date pmUpdatetime) {
		this.pmUpdatetime = pmUpdatetime;
	}
	
	public Integer getPmPushtoapp() {
		return pmPushtoapp;
	}

	public void setPmPushtoapp(Integer pmPushtoapp) {
		this.pmPushtoapp = pmPushtoapp;
	}
	
	public Integer getPmFormtype() {
		return pmFormtype;
	}

	public void setPmFormtype(Integer pmFormtype) {
		this.pmFormtype = pmFormtype;
	}
	
	public Double getPmOldcount() {
		return pmOldcount;
	}

	public void setPmOldcount(Double pmOldcount) {
		this.pmOldcount = pmOldcount;
	}
	
	public Double getPmSplitcount() {
		return pmSplitcount;
	}

	public void setPmSplitcount(Double pmSplitcount) {
		this.pmSplitcount = pmSplitcount;
	}
	
	public Double getPmPlusminuscount() {
		return pmPlusminuscount;
	}

	public void setPmPlusminuscount(Double pmPlusminuscount) {
		this.pmPlusminuscount = pmPlusminuscount;
	}
	
}