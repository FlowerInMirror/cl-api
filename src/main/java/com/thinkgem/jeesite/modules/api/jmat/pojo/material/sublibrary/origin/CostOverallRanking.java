package com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.origin;

import java.io.Serializable;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 成本整体排名
 * 
 * @author ljc
 * @createTime 2018-04-04 14:13:58
 */
@ApiModel(value = "成本整体排名")
public class CostOverallRanking implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 本月排名
	 */
	@ApiModelProperty("本月排名")
	private Integer cmRanking;
	/**
	 * 本月城市ID
	 */
	@ApiModelProperty("本月城市ID")
	private Integer cmCityID;
	/**
	 * 城市名称
	 */
	@ApiModelProperty("城市名称")
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * 本月材料总价
	 */
	@ApiModelProperty("本月材料总价")
	private Double cmTotalPrice;
	/**
	 * 上月排名
	 */
	@ApiModelProperty("上月排名")
	private Integer lmRanking;
	/**
	 * 上月城市ID
	 */
	@ApiModelProperty("上月城市ID")
	private Integer lmCityID;
	/**
	 * 上月材料
	 */
	@ApiModelProperty("上月材料")
	private Double lmTotalPrice;
	/**
	 * 近期涨幅
	 */
	@ApiModelProperty("近期涨幅")
	private Double recentGains;
	/**
	 * 近期排名
	 */
	@ApiModelProperty("近期排名")
	private Integer recentRanking;

	public Double getRecentGains() {
		return recentGains;
	}

	public void setRecentGains(Double recentGains) {
		this.recentGains = recentGains;
	}

	public Integer getRecentRanking() {
		return recentRanking;
	}

	public void setRecentRanking(Integer recentRanking) {
		this.recentRanking = recentRanking;
	}

	public Integer getCmRanking() {
		return cmRanking;
	}

	public void setCmRanking(Integer cmRanking) {
		this.cmRanking = cmRanking;
	}

	public Integer getCmCityID() {
		return cmCityID;
	}

	public void setCmCityID(Integer cmCityID) {
		this.cmCityID = cmCityID;
	}

	public Double getCmTotalPrice() {
		return cmTotalPrice;
	}

	public void setCmTotalPrice(Double cmTotalPrice) {
		this.cmTotalPrice = cmTotalPrice;
	}

	public Integer getLmRanking() {
		return lmRanking;
	}

	public void setLmRanking(Integer lmRanking) {
		this.lmRanking = lmRanking;
	}

	public Integer getLmCityID() {
		return lmCityID;
	}

	public void setLmCityID(Integer lmCityID) {
		this.lmCityID = lmCityID;
	}

	public Double getLmTotalPrice() {
		return lmTotalPrice;
	}

	public void setLmTotalPrice(Double lmTotalPrice) {
		this.lmTotalPrice = lmTotalPrice;
	}

}
