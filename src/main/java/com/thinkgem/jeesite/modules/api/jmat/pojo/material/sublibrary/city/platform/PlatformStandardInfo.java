package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.city.platform;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;


/**
 * 平台材料标准信息(新增/更新)
 * 
 * @author  ljc
 * @version 2018-03-27
 */
@ApiModel(value = "平台材料标准信息(新增/更新)")
public class PlatformStandardInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("ID")
	private String tsiId;		// ID
	
	@ApiModelProperty("四级ID（规格）")
	private String tsiTreefour;		// 四级ID（规格）
	
	@ApiModelProperty("材料母库ID")
	private String tsiTsid;		// 材料母库ID
	
	@ApiModelProperty("类型：1瑞祥标准，2质量标准，4小样标准，8包装标准（可位运算|）")
	private Integer tsiType;		// 类型：1瑞祥标准，2质量标准，4小样标准，8包装标准（可位运算|）
	
	@ApiModelProperty("属性名称")
	private String tsiName;		// 属性名称
	
	@ApiModelProperty("属性单位ID")
	private Integer tsiUnit;		// 属性单位ID
	
	@ApiModelProperty("属性单位名称")
	private String tsiUnitname;		// 属性单位名称
	
	@ApiModelProperty("属性值")
	private String tsiValue;		// 属性值
	
	@ApiModelProperty("范围：最小")
	private String tsiStandardmin;		// 范围：最小
	
	@ApiModelProperty("范围：最大")
	private String tsiStandardmax;		// 范围：最大
	
	@ApiModelProperty("特殊要求")
	private String tsiSpecialclaim;		// 特殊要求
	
	@ApiModelProperty("检测方法")
	private String tsiDetectmethod;		// 检测方法
	
	@ApiModelProperty("外观")
	private String tsiExteriorname;		// 外观
	
	@ApiModelProperty("外观-特殊要求")
	private String tsiExteriorsc;		// 外观-特殊要求
	 
	@ApiModelProperty("外观-检测方法")
	private String tsiExteriordm;		// 外观-检测方法
	
	@ApiModelProperty("进程：0正常、1删除  （默认0）")
	private Integer tsiJincheng;		// 进程：0正常、1删除  （默认0）
	
	@ApiModelProperty("创建时间    （默认getdate()）")
	private Date tsiCreatetime;		// 创建时间    （默认getdate()）
	
	@ApiModelProperty("更新时间")
	private Date tsiUpdatetime;		// 更新时间
	
	@ApiModelProperty("继承ID（质量继承）")
	private String tsiParentid;		// 继承ID（质量继承）
	
	@ApiModelProperty("是否推送：1推送   （默认0）")
	private Integer tsiApppush;		// 是否推送：1推送   （默认0）
	
	@ApiModelProperty("档次（1:A档，2:B档，4:C档 默认:0）")
	private Integer tsiMatlevel=0;	// 档次（1:A档，2:B档，4:C档）

    @ApiModelProperty("档次标准标识(1档次保存操作0默认)")
    private Integer levelStaFlag = 0;

    @ApiModelProperty("当前操作档次")
    private Integer currentLevel;

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getLevelStaFlag() {
        return levelStaFlag;
    }

    public void setLevelStaFlag(Integer levelStaFlag) {
        this.levelStaFlag = levelStaFlag;
    }

    public PlatformStandardInfo() {
		super();
	}


	public String getTsiId() {
        if ("".equals(tsiId))
            tsiId = null;
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


	public String getTsiUnitname() {
		return tsiUnitname;
	}


	public void setTsiUnitname(String tsiUnitname) {
		this.tsiUnitname = tsiUnitname;
	}


	public String getTsiValue() {
		return tsiValue;
	}


	public void setTsiValue(String tsiValue) {
		this.tsiValue = tsiValue;
	}


	public String getTsiStandardmin() {
		return tsiStandardmin;
	}


	public void setTsiStandardmin(String tsiStandardmin) {
		this.tsiStandardmin = tsiStandardmin;
	}


	public String getTsiStandardmax() {
		return tsiStandardmax;
	}


	public void setTsiStandardmax(String tsiStandardmax) {
		this.tsiStandardmax = tsiStandardmax;
	}


	public String getTsiSpecialclaim() {
		return tsiSpecialclaim;
	}


	public void setTsiSpecialclaim(String tsiSpecialclaim) {
		this.tsiSpecialclaim = tsiSpecialclaim;
	}


	public String getTsiDetectmethod() {
		return tsiDetectmethod;
	}


	public void setTsiDetectmethod(String tsiDetectmethod) {
		this.tsiDetectmethod = tsiDetectmethod;
	}


	public String getTsiExteriorname() {
		return tsiExteriorname;
	}


	public void setTsiExteriorname(String tsiExteriorname) {
		this.tsiExteriorname = tsiExteriorname;
	}


	public String getTsiExteriorsc() {
		return tsiExteriorsc;
	}


	public void setTsiExteriorsc(String tsiExteriorsc) {
		this.tsiExteriorsc = tsiExteriorsc;
	}


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


	public Date getTsiCreatetime() {
		return tsiCreatetime;
	}


	public void setTsiCreatetime(Date tsiCreatetime) {
		this.tsiCreatetime = tsiCreatetime;
	}


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

	// --- 附加属性 ---
    private List<PlatformStandardInfo> Items;

    public List<PlatformStandardInfo> getItems() {
        return Items;
    }

    public void setItems(List<PlatformStandardInfo> items) {
        Items = items;
    }
}