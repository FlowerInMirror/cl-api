/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料母库照片Entity
 * @author ljc
 * @version 2018-04-12
 */
public class TreeStandardPhoto extends DataEntity<TreeStandardPhoto> {
	
	private static final long serialVersionUID = 1L;
	private String tspId;		// ID
	private String tspTreefour;		// 四级ID（规格）
	private String tspTsid;		// 材料母库ID|材料ID(子库)
	private Integer tspClass;		// 分类：1母库，2子库
	private Integer tspType;		// 图片类型：1品牌LOGO，2品牌型号，3材料对比图，4产品主图，5材料资质，6小样图，7产品效果图，8品质细节图，9材料工艺
	private String tspPhotourl;		// 图片地址
	private Integer tspParaid;		// 参数ID（针对对比图）
	private String tspBrandid;		// 标准ID（针对对比图）
	private Integer tspMian;		// 是否主图：1主图    （默认0）
	private String tspTitle;		// 备注1（标题）
	private String tspContent1;		// 备注2（内容）
	private String tspContent2;		// 备注3（内容）
	private Integer tspJincheng;		// 进程：0正常、1删除  （默认0）
	private Date tspCreatetime;		// 创建时间    （默认getdate()）
	private Date tspUpdatetime;		// 更新时间
	private Integer tspMatlevel;		// 档次（1:A档，2:B档，4:C档）（可位运算|）
	private Integer tspCityid;		// 地区ID
	
	public TreeStandardPhoto() {
		super();
	}

	public TreeStandardPhoto(String id){
		super(id);
	}

	public String getTspId() {
		return tspId;
	}

	public void setTspId(String tspId) {
		this.tspId = tspId;
	}
	
	public String getTspTreefour() {
		return tspTreefour;
	}

	public void setTspTreefour(String tspTreefour) {
		this.tspTreefour = tspTreefour;
	}
	
	public String getTspTsid() {
		return tspTsid;
	}

	public void setTspTsid(String tspTsid) {
		this.tspTsid = tspTsid;
	}
	
	public Integer getTspClass() {
		return tspClass;
	}

	public void setTspClass(Integer tspClass) {
		this.tspClass = tspClass;
	}
	
	public Integer getTspType() {
		return tspType;
	}

	public void setTspType(Integer tspType) {
		this.tspType = tspType;
	}
	
	@Length(min=0, max=255, message="图片地址长度必须介于 0 和 255 之间")
	public String getTspPhotourl() {
		return tspPhotourl;
	}

	public void setTspPhotourl(String tspPhotourl) {
		this.tspPhotourl = tspPhotourl;
	}
	
	public Integer getTspParaid() {
		return tspParaid;
	}

	public void setTspParaid(Integer tspParaid) {
		this.tspParaid = tspParaid;
	}
	
	public String getTspBrandid() {
		return tspBrandid;
	}

	public void setTspBrandid(String tspBrandid) {
		this.tspBrandid = tspBrandid;
	}
	
	public Integer getTspMian() {
		return tspMian;
	}

	public void setTspMian(Integer tspMian) {
		this.tspMian = tspMian;
	}
	
	@Length(min=0, max=255, message="备注1（标题）长度必须介于 0 和 255 之间")
	public String getTspTitle() {
		return tspTitle;
	}

	public void setTspTitle(String tspTitle) {
		this.tspTitle = tspTitle;
	}
	
	@Length(min=0, max=2000, message="备注2（内容）长度必须介于 0 和 2000 之间")
	public String getTspContent1() {
		return tspContent1;
	}

	public void setTspContent1(String tspContent1) {
		this.tspContent1 = tspContent1;
	}
	
	@Length(min=0, max=2000, message="备注3（内容）长度必须介于 0 和 2000 之间")
	public String getTspContent2() {
		return tspContent2;
	}

	public void setTspContent2(String tspContent2) {
		this.tspContent2 = tspContent2;
	}
	
	public Integer getTspJincheng() {
		return tspJincheng;
	}

	public void setTspJincheng(Integer tspJincheng) {
		this.tspJincheng = tspJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTspCreatetime() {
		return tspCreatetime;
	}

	public void setTspCreatetime(Date tspCreatetime) {
		this.tspCreatetime = tspCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTspUpdatetime() {
		return tspUpdatetime;
	}

	public void setTspUpdatetime(Date tspUpdatetime) {
		this.tspUpdatetime = tspUpdatetime;
	}
	
	public Integer getTspMatlevel() {
		return tspMatlevel;
	}

	public void setTspMatlevel(Integer tspMatlevel) {
		this.tspMatlevel = tspMatlevel;
	}
	
	public Integer getTspCityid() {
		return tspCityid;
	}

	public void setTspCityid(Integer tspCityid) {
		this.tspCityid = tspCityid;
	}
	
}