/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.entity;

import com.thinkgem.jeesite.common.utils.StringUtils;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 材料标准信息Entity
 * @author ljc
 * @version 2018-03-19
 */
public class TreeStandardItme extends DataEntity<TreeStandardItme> {
	
	private static final long serialVersionUID = 1L;
	private String tsiId;		// ID
	private String tsiTreefour;		// 四级ID（规格）
	private String tsiTsid;		// 材料母库ID
	private Integer tsiType;		// 类型：1瑞祥标准，2质量标准，4小样标准，8包装标准（可位运算|）
	private String tsiName;		// 属性名称
	private Integer tsiUnit;		// 属性单位ID
	private String tsiUnitname;		// 属性单位名称
	private String tsiValue;		// 属性值
	private String tsiStandardmin;		// 范围：最小
	private String tsiStandardmax;		// 范围：最大
	private String tsiSpecialclaim;		// 特殊要求 1值
	private String tsiDetectmethod;		// 检测方法 2值
	private String tsiExteriorname;		// 外观 <属性1>  -- '检验标准'
	private String tsiExteriorsc;		// 外观-特殊要求 <属性2> -- '检验方法'
	private String tsiExteriordm;		// 外观-检测方法
	private Integer tsiJincheng;		// 进程：0正常、1删除  （默认0）
	private Date tsiCreatetime;		// 创建时间    （默认getdate()）
	private Date tsiUpdatetime;		// 更新时间
	private String tsiParentid;		// 继承ID（质量继承）
	private Integer tsiApppush;		// 是否推送：1推送   （默认0）
	private Integer tsiMatlevel;		// 档次（1:A档，2:B档，4:C档）
	
	public TreeStandardItme() {
		super();
	}

	public TreeStandardItme(String id){
		super(id);
	}

	public String getTsiId() {
		return tsiId;
	}

	public void setTsiId(String tsiId) {
		this.tsiId = tsiId;
	}
	
	public String getTsiTreefour() {
		return tsiTreefour;
	}

	public void setTsiTreefour(String tsiTreefour) {
		this.tsiTreefour = tsiTreefour;
	}
	
	public String getTsiTsid() {
		return tsiTsid;
	}

	public void setTsiTsid(String tsiTsid) {
		this.tsiTsid = tsiTsid;
	}
	
	public Integer getTsiType() {
		return tsiType;
	}

	public void setTsiType(Integer tsiType) {
		this.tsiType = tsiType;
	}
	
	@Length(min=0, max=50, message="属性名称长度必须介于 0 和 50 之间")
	public String getTsiName() {
		return tsiName;
	}

	public void setTsiName(String tsiName) {
		this.tsiName = tsiName;
	}
	
	public Integer getTsiUnit() {
		return tsiUnit;
	}

	public void setTsiUnit(Integer tsiUnit) {
		this.tsiUnit = tsiUnit;
	}
	
	@Length(min=0, max=10, message="属性单位名称长度必须介于 0 和 10 之间")
	public String getTsiUnitname() {
		return tsiUnitname;
	}

	public void setTsiUnitname(String tsiUnitname) {
		this.tsiUnitname = tsiUnitname;
	}
	
	@Length(min=0, max=100, message="属性值长度必须介于 0 和 100 之间")
	public String getTsiValue() {
		return tsiValue;
	}

	public void setTsiValue(String tsiValue) {
		this.tsiValue = tsiValue;
	}
	
	@Length(min=0, max=10, message="范围：最小长度必须介于 0 和 10 之间")
	public String getTsiStandardmin() {
	    if ("".equals(tsiStandardmin))
            tsiStandardmin = null;
		return tsiStandardmin;
	}

	public void setTsiStandardmin(String tsiStandardmin) {
		this.tsiStandardmin = tsiStandardmin;
	}
	
	@Length(min=0, max=10, message="范围：最大长度必须介于 0 和 10 之间")
	public String getTsiStandardmax() {
	    if ("".equals(tsiStandardmax))
            tsiStandardmax = null;
		return tsiStandardmax;
	}

	public void setTsiStandardmax(String tsiStandardmax) {
		this.tsiStandardmax = tsiStandardmax;
	}
	
	@Length(min=0, max=2000, message="特殊要求长度必须介于 0 和 2000 之间")
	public String getTsiSpecialclaim() {
		return tsiSpecialclaim;
	}

	public void setTsiSpecialclaim(String tsiSpecialclaim) {
		this.tsiSpecialclaim = tsiSpecialclaim;
	}
	
	@Length(min=0, max=2000, message="检测方法长度必须介于 0 和 2000 之间")
	public String getTsiDetectmethod() {
		return tsiDetectmethod;
	}

	public void setTsiDetectmethod(String tsiDetectmethod) {
		this.tsiDetectmethod = tsiDetectmethod;
	}
	
	@Length(min=0, max=100, message="外观长度必须介于 0 和 100 之间")
	public String getTsiExteriorname() {
		return tsiExteriorname;
	}

	public void setTsiExteriorname(String tsiExteriorname) {
		this.tsiExteriorname = tsiExteriorname;
	}
	
	@Length(min=0, max=2000, message="外观-特殊要求长度必须介于 0 和 2000 之间")
	public String getTsiExteriorsc() {
		return tsiExteriorsc;
	}

	public void setTsiExteriorsc(String tsiExteriorsc) {
		this.tsiExteriorsc = tsiExteriorsc;
	}
	
	@Length(min=0, max=2000, message="外观-检测方法长度必须介于 0 和 2000 之间")
	public String getTsiExteriordm() {
		return tsiExteriordm;
	}

	public void setTsiExteriordm(String tsiExteriordm) {
		this.tsiExteriordm = tsiExteriordm;
	}
	
	public Integer getTsiJincheng() {
		return tsiJincheng;
	}

	public void setTsiJincheng(Integer tsiJincheng) {
		this.tsiJincheng = tsiJincheng;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTsiCreatetime() {
		return tsiCreatetime;
	}

	public void setTsiCreatetime(Date tsiCreatetime) {
		this.tsiCreatetime = tsiCreatetime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTsiUpdatetime() {
		return tsiUpdatetime;
	}

	public void setTsiUpdatetime(Date tsiUpdatetime) {
		this.tsiUpdatetime = tsiUpdatetime;
	}
	
	public String getTsiParentid() {
		return tsiParentid;
	}

	public void setTsiParentid(String tsiParentid) {
		this.tsiParentid = tsiParentid;
	}
	
	public Integer getTsiApppush() {
		return tsiApppush;
	}

	public void setTsiApppush(Integer tsiApppush) {
		this.tsiApppush = tsiApppush;
	}
	
	public Integer getTsiMatlevel() {
		return tsiMatlevel;
	}

	public void setTsiMatlevel(Integer tsiMatlevel) {
		this.tsiMatlevel = tsiMatlevel;
	}

	// ========== 附加属性
    private int loopStatus = 1;// 回路状态:1.完成(默认)/0.未完成

    private Integer platformStaLevel; //标准总档次标识

    public int getLoopStatus() {
        // 获取时设值回路状态 校验:属性值未null时,设值回路状态 0.未完成
        if (StringUtils.isBlank(tsiValue))
            loopStatus = 0;
        return loopStatus;
    }
    public void setLoopStatus(int loopStatus) {
        this.loopStatus = loopStatus;
    }

    public Integer getPlatformStaLevel() {
        return platformStaLevel;
    }

    public void setPlatformStaLevel(Integer platformStaLevel) {
        this.platformStaLevel = platformStaLevel;
    }
}