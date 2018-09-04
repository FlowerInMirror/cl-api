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
 * 子库更新审核表Entity
 * @author ljc
 * @version 2018-05-09
 */
public class MaterialChangeHistory extends DataEntity<MaterialChangeHistory> {
	
	private static final long serialVersionUID = 1L;
	private Integer mchId;		// ID
	private String mchMid;		// 材料ID
	private Integer mchUsertype;		// 操作人类型：1地方，2集团
	private Integer mchCheckstate;		// 审核状态：0待审核，1通过，2打回,3作废（集团已修改）  （默认0）
	private String mchBrand;		// 品牌名称
	private String mchBrandremark;		// 品牌描述
	private Integer mchHoststate;		// 是否推荐：1推荐    （默认-1）
	private Integer mchHomehoststate;		// 是否首页推荐：1推荐  （默认-1）
	private Integer mchMattype;		// 材料类型：1品牌材料，2瑞祥专供材料    （默认-1）
	private String mchBrandtype;		// 型号
	private Double mchCost;		// 成本价
	private Double mchQuotes;		// 报价
	private Double mchInstall;		// 安装价
	private String mchBrandphoto;		// 品牌照片
	private String mchTypephoto;		// 型号照片
	private String mchMatphoto;		// 材料资质照片（,隔开）
	private String mchRemark;		// 打回备注
	private Date mchCreatetime;		// 创建时间    （默认getdate()）
	private Date mchUpdatetime;		// 更新时间    （默认getdate()）
	private String mchJincheng;		// 进程：0正常、1删除  （默认0）
	
	public MaterialChangeHistory() {
		super();
	}

	public MaterialChangeHistory(String id){
		super(id);
	}

	@NotNull(message="ID不能为空")
	public Integer getMchId() {
		return mchId;
	}

	public void setMchId(Integer mchId) {
		this.mchId = mchId;
	}
	
	public String getMchMid() {
		return mchMid;
	}

	public void setMchMid(String mchMid) {
		this.mchMid = mchMid;
	}
	
	public Integer getMchUsertype() {
		return mchUsertype;
	}

	public void setMchUsertype(Integer mchUsertype) {
		this.mchUsertype = mchUsertype;
	}
	
	public Integer getMchCheckstate() {
		return mchCheckstate;
	}

	public void setMchCheckstate(Integer mchCheckstate) {
		this.mchCheckstate = mchCheckstate;
	}
	
	@Length(min=0, max=50, message="品牌名称长度必须介于 0 和 50 之间")
	public String getMchBrand() {
		return mchBrand;
	}

	public void setMchBrand(String mchBrand) {
		this.mchBrand = mchBrand;
	}
	
	@Length(min=0, max=2000, message="品牌描述长度必须介于 0 和 2000 之间")
	public String getMchBrandremark() {
		return mchBrandremark;
	}

	public void setMchBrandremark(String mchBrandremark) {
		this.mchBrandremark = mchBrandremark;
	}
	
	public Integer getMchHoststate() {
		return mchHoststate;
	}

	public void setMchHoststate(Integer mchHoststate) {
		this.mchHoststate = mchHoststate;
	}
	
	public Integer getMchHomehoststate() {
		return mchHomehoststate;
	}

	public void setMchHomehoststate(Integer mchHomehoststate) {
		this.mchHomehoststate = mchHomehoststate;
	}
	
	public Integer getMchMattype() {
		return mchMattype;
	}

	public void setMchMattype(Integer mchMattype) {
		this.mchMattype = mchMattype;
	}
	
	@Length(min=0, max=50, message="型号长度必须介于 0 和 50 之间")
	public String getMchBrandtype() {
		return mchBrandtype;
	}

	public void setMchBrandtype(String mchBrandtype) {
		this.mchBrandtype = mchBrandtype;
	}
	
	public Double getMchCost() {
		return mchCost;
	}

	public void setMchCost(Double mchCost) {
		this.mchCost = mchCost;
	}
	
	public Double getMchQuotes() {
		return mchQuotes;
	}

	public void setMchQuotes(Double mchQuotes) {
		this.mchQuotes = mchQuotes;
	}
	
	public Double getMchInstall() {
		return mchInstall;
	}

	public void setMchInstall(Double mchInstall) {
		this.mchInstall = mchInstall;
	}
	
	@Length(min=0, max=255, message="品牌照片长度必须介于 0 和 255 之间")
	public String getMchBrandphoto() {
		return mchBrandphoto;
	}

	public void setMchBrandphoto(String mchBrandphoto) {
		this.mchBrandphoto = mchBrandphoto;
	}
	
	@Length(min=0, max=255, message="型号照片长度必须介于 0 和 255 之间")
	public String getMchTypephoto() {
		return mchTypephoto;
	}

	public void setMchTypephoto(String mchTypephoto) {
		this.mchTypephoto = mchTypephoto;
	}
	
	@Length(min=0, max=-1, message="材料资质照片（,隔开）长度必须介于 0 和 -1 之间")
	public String getMchMatphoto() {
		return mchMatphoto;
	}

	public void setMchMatphoto(String mchMatphoto) {
		this.mchMatphoto = mchMatphoto;
	}
	
	@Length(min=0, max=2000, message="打回备注长度必须介于 0 和 2000 之间")
	public String getMchRemark() {
		return mchRemark;
	}

	public void setMchRemark(String mchRemark) {
		this.mchRemark = mchRemark;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMchCreatetime() {
		return mchCreatetime;
	}

	public void setMchCreatetime(Date mchCreatetime) {
		this.mchCreatetime = mchCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMchUpdatetime() {
		return mchUpdatetime;
	}

	public void setMchUpdatetime(Date mchUpdatetime) {
		this.mchUpdatetime = mchUpdatetime;
	}
	
	@Length(min=0, max=50, message="进程：0正常、1删除  （默认0）长度必须介于 0 和 50 之间")
	public String getMchJincheng() {
		return mchJincheng;
	}

	public void setMchJincheng(String mchJincheng) {
		this.mchJincheng = mchJincheng;
	}
	
}