/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.jmat.web;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.web.Constants;
import com.thinkgem.jeesite.modules.api.jmat.dao.CityToMatCityDao;
import com.thinkgem.jeesite.modules.api.jmat.dao.UnitDao;
import com.thinkgem.jeesite.modules.api.jmat.entity.CityToMatCity;
import com.thinkgem.jeesite.modules.api.jmat.entity.Unit;
import com.thinkgem.jeesite.modules.api.jmat.pojo.commons.InformationBody;
import com.thinkgem.jeesite.modules.api.jmat.service.ParameterService;
import com.thinkgem.jeesite.modules.api.jmat.service.TreeService;
import com.thinkgem.jeesite.modules.api.jmat.service.UnitService;
import com.thinkgem.jeesite.modules.api.project.dao.RsglDiQuDao;
import com.thinkgem.jeesite.modules.api.project.entity.RsglDiQu;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用Controller
 * @author   ljc
 * @version  2018-03-30 
 */
@Controller
@RequestMapping(value = "common-api")
@Api(value="公用Controller",description="公用API")
public class CommonController extends BaseController  implements InitializingBean {
	//Redis 键前缀
	public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix");
	//城市集合
    private List<Map<String,Object>> citys ;

	//---服务对象---
	@Autowired//材料单位
	private UnitService<Unit> unitService;
	@Autowired//材料参数
	private ParameterService parameterService;
	@Autowired//科目树
    private TreeService treeService;
	
	//---数据访问对象---
	@Autowired//地区(工程管理-原人事)
	private RsglDiQuDao rsglDiQuDao;
	@Autowired//地区关联
	private CityToMatCityDao cityToMatCityDao;
	@Autowired//单位
	private UnitDao unitDao;

    /**
     * 获取科目树名称集API
     * @author     ljc
     * @version    2018-5-8 16:35:46
     * @param      treeParentID        科目树父级ID
     * @return     InformationBody     响应数据:科目树名称/科目树ID
     */
    @RequestMapping(value="/tree_names")
    @ApiOperation(value="获取科目树名称集API",httpMethod="GET",notes="获取科目树名称集,通过科目树父级ID(可选)<br/>@author  ljc<br/>@version 2018-5-8<br/>body:科目树名称 treeName,科目树ID treeID")
    public @ResponseBody InformationBody treeNames(
            @ApiParam(required = false, value="科目树父级ID",name="treeParentID")@RequestParam(required = false) String treeParentID){
        if ("".equals(treeParentID))
            treeParentID = null;
        return treeService.findTreeNamesByParentID(treeParentID);
    }

	/**
	 * 获取全部地区API
	 * @author   ljc
	 * @version  2018-03-30
	 * @return   InformationBody 响应数据:地区List[{地区ID cityID,地区名称 cityName,地区编码 cityCode}... ].
	 */
	@RequestMapping(value={"citys"})
	@ApiOperation(value="获取全部地区API",httpMethod="GET",notes="获取全部地区<br/>@author ljc<br/>@version 2018-03-30<br/>body:<br/>"
			+ "城市 cityArray:[{地区ID cityID,地区名称 cityName,地区编码 cityCode}... ]<br/>",response=InformationBody.class)
	public @ResponseBody InformationBody getAllCity(){
		InformationBody informationBody = new InformationBody();
		try {
 			//设置响应体
 			informationBody.setBody(citys);
 			//设置响应状态
			informationBody.setStatusCode(0);
			informationBody.setStatusMsg("成功");
			logger.debug("子库地区新增标准接口d,调用成功.");
		} catch (Exception e) {
			informationBody.setStatusCode(-1);
			informationBody.setStatusMsg("失败");
			logger.error("子库地区新增标准接口,调用失败!",e);
		}
		return informationBody;
	}
	
	
	/**
	 * 获取全部材料单位API
	 * @author   ljc
	 * @version  2018-03-30 
	 * @return   InformationBody  响应数据 [{unitID 单位ID,unitName 单位名称}... ]
	 */
	@RequestMapping(value={"units"})
	@ApiOperation(value="获取全部材料单位API",httpMethod="GET",notes="获取全部材料单位.<br/>"
			+ "@author  ljc<br/>@version 2018-03-30<br/>body:<br/>"
			+ "[{unitID 单位ID,unitName 单位名称}... ]",response=InformationBody.class)
	public@ResponseBody InformationBody getAllUnit(){
		return unitService.getAllUnit();
	}
	
	/**
	 * 获取全部材料参数API
	 * @author   ljc
	 * @version  2018-03-30
	 * @return   InformationBody  响应数据 [{paraId 参数ID,paraName 参数名称,paraUnitname 参数单位}... ]
	 */
	@RequestMapping(value={"/parameters"})
	@ApiOperation(value="获取全部材料参数API",httpMethod="GET",notes="获取全部材料参数.<br/>"
			+ "@author  ljc<br/>@version 2018-03-30<br/>body:<br/>"
			+ "[{paraId 参数ID,paraName 参数名称,paraUnitname 参数单位}... ]",response=InformationBody.class)
	public@ResponseBody InformationBody getAllParameter(){
		return parameterService.getAllParameter();
	}
	
	
	//-------------------- 公用方法 --------------------
	/**
	 * 通过城市ID,获取城市名称.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-04-04 16:42:52
	 * @param      cityID                 城市ID
	 * @return     String                 城市名称
	 */
	public String getCityNameByCityID(Integer cityID){
		for (Map<String, Object> map : citys) {
			Integer cid = (Integer)map.get("cityID");
			if (null != cityID && cid == cityID) {
				return String.valueOf(map.get("cityName"));
			}
		}
		return null;
	}
	
	/**
	 * 查找单位集合,通过Redis键.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-04-04 16:30:08
	 * @param      unitsStr	                                         从Reids缓存中获取单位JSON的Key
	 * @return     List<Map<String, Object>>  单位集合
	 */
	public List<Map<String, Object>> findUnitArrayByReidsKey(String unitsStr) {
		List<Map<String, Object>> unitArray;
		if(null == unitsStr){
			unitArray =	new ArrayList<Map<String, Object>>();
			List<Unit> units = unitDao.getAllUnit();//全部单位
			for (Unit unit : units) {
				Map<String, Object> unitMap = new HashMap<String,Object>();
				unitMap.put("unitID", unit.getUnitId());//单位ID
				unitMap.put("unitName", unit.getUnitName());//单位名称
				unitArray.add(unitMap);
			}
			JedisUtils.set(KEY_PREFIX + Constants.UNIT,JSON.toJSONString(unitArray),60 * 60 * 24);
			logger.debug("设置单位到Redis缓存中.有效期:" + 60 * 60 * 24);
		}else {
			unitArray = JSONArray.fromObject(unitsStr);
			logger.debug("Redis缓存中获取单位.");
		}
		return unitArray;
	}
	
	/**
	 * 查找城市集合,通过Redis键.
	 * @author     ljc
	 * @version    1.0
	 * @createTime 2018-04-04 16:30:08
	 * @param      citysStr	                                         从Reids缓存中获取城市JSON的Key
	 * @return     List<Map<String, Object>>  城市集合
	 */
	public  List<Map<String, Object>> findCityArrayByReidsKey(String citysStr) {
		List<Map<String, Object>> cityArray;
		if (null == citysStr) {
			cityArray = new ArrayList<Map<String, Object>>();
			List<CityToMatCity> cityToMatCitys = cityToMatCityDao.getAllCityToMatCity();//全部地区关联

			DynamicDataSource.setCurrentLookupKey("dataSourceGC");//切换"工程"数据源

			List<RsglDiQu> rsglDiQus = rsglDiQuDao.getAllRsglDiQu();//全部地区(工程数据源)
			DynamicDataSource.clearCustomerType();//清除数据源类型
			DynamicDataSource.setCurrentLookupKey("dataSourceCL");//切回"材料"默认数据源

			for (RsglDiQu rsglDiQu : rsglDiQus) {
				boolean flag = true;
				for (CityToMatCity cityToMatCity : cityToMatCitys) {
					if (rsglDiQu.getDqId()==cityToMatCity.getCcCity()){
						flag=false;
						break;
					}
				}
				if (flag) {
					Map<String, Object> cityMap = new HashMap<String,Object>();
					cityMap.put("cityID", rsglDiQu.getDqId());//地区ID
					cityMap.put("cityName",rsglDiQu.getDqName());//地区名称
					cityMap.put("cityCode", rsglDiQu.getDqCode());//地区编码
					cityArray.add(cityMap);
				}
			}
			JedisUtils.set(KEY_PREFIX + Constants.AREA,JSON.toJSONString(cityArray),60 * 60 * 24);
			logger.debug("设置城市到Redis缓存中.有效期:" + 60 * 60 * 24);
		}else{
			cityArray = JSONArray.fromObject(citysStr);
			logger.debug("Redis缓存中获取城市.");
		}
		return cityArray;
	}

    @Override
    public void afterPropertiesSet() throws Exception {
        citys = findCityArrayByReidsKey(JedisUtils.get(KEY_PREFIX + Constants.AREA));
    }

    public List<Map<String, Object>> getCitys() {
        return citys;
    }

    public List<RsglDiQu> getRsglDiQu(){
		return rsglDiQuDao.getAllRsglDiQu();//全部地区(工程数据源);
	}
}