/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.api.project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

//(message="合同类型 1个人,2公司3.代理商;
//        (message="&quot;类型;
//        (message="身高;
//        (message="固定工人;
//        (message="代班;
//        (min=0,max=50,message="项目面积;
//        (message="保证金(元);
//        (message="试用人员项目数;
//        (message="状态 :1 临时人员,2 正式人员, 3.停工,4 待离职审批;

/**
 * 项目人事(新)Entity
 * @author ljc
 * @version 2018-06-21
 */
public class PmUserInfoFull extends DataEntity<PmUserInfoFull> {
	
	private static final long serialVersionUID = 1L;
	private Integer uid;		// 主键
	private Integer userType;		// 分类:1 项目经理,2分项经理,3材料商,4,临时材料商
	private Integer major;		// 专业 :1装修,2弱电,3家具,4钢结构,5消防,6空调
	private Integer contractType;		// 合同类型 1个人,2公司3.代理商(临时材料商)
	private String userName;		// 姓名/业务代表姓名
	private Integer userLevel;		// &quot;类型(级别) A,B,C,D 分别对应 1,2,3,4 &quot;
	private Integer qualityLevel;		// &quot;标识调级趋势,如果调级成功则和级别字段相同,如果调级时,条件不够,则显示将要调整的趋势,     A,B,C,D 分别对应 1,2,3,4 &quot;
	private Integer sex;		// 性别 1男 0女
	private Integer nation;		// 民族 字典表取
	private Date birthday;		// 出生日期
	private String mobile;		// 联系方式
	private Integer maritalStatus;		// 婚姻状况 1 未婚 2已婚 3离异 4 丧偶
	private String nowAddress;		// 现住址
	private Integer selfJob;		// 自身工种
	private String referrer;		// 推荐人
	private String idcard;		// 身份证号
	private Integer userHeight;		// 身高(cm)
	private Integer emergencyRelation;		// 紧急联系人关系ion
	private String emergencyName;		// 紧急联系人姓名
	private String emergencyMobile;		// 紧急联系人电话
	private String registerPlace;		// 户籍所在地
	private String bankAccountName;		// 开户名
	private String bankAccount;		// 银行账号
	private Integer bankType;		// 开户行 来源于字典表
	private String bankAddress;		// 开户行地址
	private Date inductionDate;		// 入职时间
	private Integer inductionArea;		// 入职地区:质保金及入职资料交到的地区
	private Integer education;		// 学历 字典表
	private String educationHistory;		// 培训经历
	private String educationCertificate;		// 所获证书
	private String loginAccount;		// 登录账号
	private String loginPasspord;		// 登录密码
	private Integer workerNum;		// 固定工人(个)
	private Integer workerLeaderNum;		// 代班(个)
	private Double workerYear;		// 工人工作年限
	private Double leadTeamYear;		// 带领团队年限
	private String proMaxName;		// 参与金额最大项目名称
	private String proMaxAddress;		// 项目地址
	private String proMaxArea;		// 项目面积(平方米)
	private Integer proType;		// 项目类型:办公 餐饮,等,来源于字典表
	private Integer proMaxMoney;		// 项目工程造价:区间范围,来源于字典表
	private Integer canVisit;		// 是否可参观
	private Integer knownWay;		// 从何途径了解公司 :字典表
	private String firstImpression;		// 对公司第一印象
	private String lastLeavingReason;		// 上家公司离职原因
	private String compName;		// 公司名称
	private Double compRegisterMoney;		// 公司注册资金(万元)
	private Date compRegisterDate;		// 公司注册日期
	private String compAddress;		// 公司地址
	private String compBusinessLicense;		// 营业执照号
	private String compTaxId;		// 税务登记号
	private String compLegalPerson;		// 法人
	private String compLegalCardno;		// 法人身份证号
	private String compLegalMobile;		// 法人电话
	private String compLegalPersonEmail;		// 法人邮箱
	private String compBankAccount;		// 对公账号
	private Integer compBankType;		// 对公账号开户行 字典
	private BigDecimal depositCash;		// 保证金(元)(最终金额,由财务变动时通知修改)
	private Integer depositBackState;		// 质保金返还状态
	private Date depositExpectBackDate;		// 预计质保金返还日期
	private Date depositActualBackDate;		// 实际质保金返还日期
	private String continuedCharge;		// continued_charge
	private String leavingReason;		// 本次离职原因
	private Date leavingDate;		// leaving_date
	private Integer isBlacklist;		// 是否黑名单 0 不是   1 是
	private Integer proTestCount;		// 试用人员项目数(默认2个)
	private Integer userState;		// 状态 :1 临时人员,2 正式人员, 3.停工,4 待离职审批(  正式人员先经过集团审批批),51临时待离职, 52正式待离职,6,已离职未提档 7,已离职
	private String photoServerUrl;		// 个人照片服务器地址
	private String photoFileUrl;		// 个人照片文件名
	private Date createtime;		// 创建时间
	private String createuser;		// 创建人
	private Date updatetime;		// 修改时间
	private String updateuser;		// 修改人
	private Integer isGuidang;		// 是否归档 0 未   1 已归档
	private Date leavingTime;		// 本次离职时间
	private Integer userLevelNext;		// user_level_next
	private Integer tempOriginalLevel;		// temp_original_level
	private Date tempLevelDeadline;		// temp_level_deadline
	private Integer isFreeze;		// is_freeze
	private Integer groupIsok;		// group_isok
	private String photoArtServerUrl;		// 个人艺术照片服务器地址
	private String photoArtFileUrl;		// 个人艺术照片文件名
	private Integer achievementLevel;		// achievement_level
	private String userTag;		// 人员标记
	private Integer notDisplayInSgpt;		// 项目经理是否在施工平台显示: 1 不显示 ,其他显示
	private Integer photoCheckstate;		// photo_checkstate
	private Integer photoArtCheckstate;		// photo_art_checkstate
	private String appCardno;		// APP人事卡号
	private Integer userTitleLevel;		// 职称等级 V1 V2 V3
	private Integer dataSources;		// 来源：0 工程，1APP注册，2招聘人员，3分享进入 （默认0）
	private Integer starlevel;		// 星级
	private Integer paidType;		// 支付类型：1银行卡，2对公账户  （默认1）
	private String compBankAccountName;		// 对公账户开户名
	private Integer counselorlevel;		// 顾问等级 0 非顾问
	private Integer syntheticgrade;		// 临时级别  对应pm_userinfo_level表的levelcode
	private Integer tempsyntheticgrade;		// 临时级别
	private Integer nameCheckstate;		// 姓名审核状态  0未审核 1审核通过 2打回
	private Integer idcardCheckstate;		// 身份证审核状态  0未审核 1审核通过 2打回
	private Integer bankAmountCheckstate;		// 银行卡号审核状态  0未审核 1审核通过 2打回
	private Integer bankTypeCheckstate;		// 银行卡类型审核状态  0未审核 1审核通过 2打回
	private Integer areaType;		// 面积类型 1：200以下  2：200-500 3：500-1200 4：1200以上
	private Integer projectMoneyType;		// 1：10以下  2：10-30 3：30-50  4：50-70 5：70-100 6：100-200 7：200以上
	private Integer userJoinState;		// 人员加盟状态 0（未加盟）、1（分单未交加盟费用）、2（已有成交项目并扣部分加盟费）、3（加盟费已交齐）
	private String userJoinAmount;		// 加盟费
	private Integer deviationType;		// deviation_type
	private Integer characterType;		// character_type
	private Integer customerDeviationType;		// customer_deviation_type
	private Integer customerCharacterType;		// customer_character_type
	private Integer customerAreaType;		// customer_area_type
	private Integer customerBudgetType;		// customer_budget_type
	private Integer customerProType;		// customer_pro_type
	private Integer msgstate;		// 资料状态：1 Z2;2 Z3;30(待培训)、31(已培训未通过) P; 4 Z4; 5 正常
	private Integer stagestate;		// 阶段状态：1 J2; 2 J3; 3 P; 4 J4; 5 正常
	private String approvedesc;		// 认证-操作内容
	private String temporarydesc;		// 临时-操作内容
	private String officialdesc;		// 正式-操作内容
	private Date userJoinDate;		// 加盟时间
	private Integer receivingOrderState;		// 项目经理接单状态：0可接单，1不可接单
	private Integer channel;		// 渠道 1.百度 2。头条 3.58同城 4.其他
	private String wechat;		// 微信号
	private Integer experience;		// 经验 1有 2无
	private Integer zsrsUid;		// 回调uid
	private String province;		// 省
	private String city;		// 市
	private String pmCard;		// 业务员卡号
	private Integer agreementtype;		// 合同类型
	private String joinmoney;		// 加盟费
	private Date agreementstarttime;		// 合同生效时间
	private Date agreementendtime;		// 合同截止时间
	private Date signInTime;		// 签到时间
	private String plateNumber;		// 车牌号
	private Integer accommodation;		// 是否住宿：1否，0是
	private Integer intentionStatus;		// 合作意向书状态（0默认，1同意）
	private Date intentionTime;		// 合作意向书时间
	private String rsAppId;		// 大院人事主键
	private String proTypeNew;		// 项目经理新擅长
	private String personMotto;		// 个人箴言
	private Integer isTuijian;		// 0首页推荐 ，1不推荐，2项目经理列表推荐
	private Long bespokeNum;		// 预约数
	private Date tuijianTime;		// 推荐时间
	private Integer returnGuest;		// 回头客
	private Integer integral;		// 积分
	private Integer traditionLevel;		// 传统级别
	private Integer joinLevel;		// 加盟级别
	private Integer sgptUserScore;		// 施工平台项目经理总分
	
	public PmUserInfoFull() {
		super();
	}

	public PmUserInfoFull(String id){
		super(id);
	}

	@NotNull(message="主键不能为空")
	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	@NotNull(message="分类:1 项目经理,2分项经理,3材料商,4,临时材料商不能为空")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@NotNull(message="专业 :1装修,2弱电,3家具,4钢结构,5消防,6空调不能为空")
	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}
	
//	@代理商(临时材料商)不能为空")
	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}
	
	@Length(min=1, max=50, message="姓名/业务代表姓名长度必须介于 1 和 50 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@NotNull(message="&quot;类型(级别) A,B,C,D 分别对应 1,2,3,4 &quot;不能为空")
	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	
	@NotNull(message="&quot;标识调级趋势,如果调级成功则和级别字段相同,如果调级时,条件不够,则显示将要调整的趋势,     A,B,C,D 分别对应 1,2,3,4 &quot;不能为空")
	public Integer getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(Integer qualityLevel) {
		this.qualityLevel = qualityLevel;
	}
	
	@NotNull(message="性别 1男 0女不能为空")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@NotNull(message="民族 字典表取不能为空")
	public Integer getNation() {
		return nation;
	}

	public void setNation(Integer nation) {
		this.nation = nation;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=20, message="联系方式长度必须介于 0 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@NotNull(message="婚姻状况 1 未婚 2已婚 3离异 4 丧偶不能为空")
	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	@Length(min=0, max=50, message="现住址长度必须介于 0 和 50 之间")
	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	
	@NotNull(message="自身工种不能为空")
	public Integer getSelfJob() {
		return selfJob;
	}

	public void setSelfJob(Integer selfJob) {
		this.selfJob = selfJob;
	}
	
	@Length(min=0, max=50, message="推荐人长度必须介于 0 和 50 之间")
	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	
	@Length(min=0, max=30, message="身份证号长度必须介于 0 和 30 之间")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@NotNull(message="身高(cm)不能为空")
	public Integer getUserHeight() {
		return userHeight;
	}

	public void setUserHeight(Integer userHeight) {
		this.userHeight = userHeight;
	}
	
	@NotNull(message="紧急联系人关系ion不能为空")
	public Integer getEmergencyRelation() {
		return emergencyRelation;
	}

	public void setEmergencyRelation(Integer emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}
	
	@Length(min=0, max=50, message="紧急联系人姓名长度必须介于 0 和 50 之间")
	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}
	
	@Length(min=0, max=50, message="紧急联系人电话长度必须介于 0 和 50 之间")
	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}
	
	@Length(min=0, max=50, message="户籍所在地长度必须介于 0 和 50 之间")
	public String getRegisterPlace() {
		return registerPlace;
	}

	public void setRegisterPlace(String registerPlace) {
		this.registerPlace = registerPlace;
	}
	
	@Length(min=0, max=50, message="开户名长度必须介于 0 和 50 之间")
	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	
	@Length(min=0, max=50, message="银行账号长度必须介于 0 和 50 之间")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@NotNull(message="开户行 来源于字典表不能为空")
	public Integer getBankType() {
		return bankType;
	}

	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}
	
	@Length(min=0, max=50, message="开户行地址长度必须介于 0 和 50 之间")
	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="入职时间不能为空")
	public Date getInductionDate() {
		return inductionDate;
	}

	public void setInductionDate(Date inductionDate) {
		this.inductionDate = inductionDate;
	}
	
	@NotNull(message="入职地区:质保金及入职资料交到的地区不能为空")
	public Integer getInductionArea() {
		return inductionArea;
	}

	public void setInductionArea(Integer inductionArea) {
		this.inductionArea = inductionArea;
	}
	
	@NotNull(message="学历 字典表不能为空")
	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}
	
	@Length(min=0, max=200, message="培训经历长度必须介于 0 和 200 之间")
	public String getEducationHistory() {
		return educationHistory;
	}

	public void setEducationHistory(String educationHistory) {
		this.educationHistory = educationHistory;
	}
	
	@Length(min=0, max=200, message="所获证书长度必须介于 0 和 200 之间")
	public String getEducationCertificate() {
		return educationCertificate;
	}

	public void setEducationCertificate(String educationCertificate) {
		this.educationCertificate = educationCertificate;
	}
	
	@Length(min=0, max=50, message="登录账号长度必须介于 0 和 50 之间")
	public String getLoginAccount() {
		return loginAccount;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	
	@Length(min=0, max=50, message="登录密码长度必须介于 0 和 50 之间")
	public String getLoginPasspord() {
		return loginPasspord;
	}

	public void setLoginPasspord(String loginPasspord) {
		this.loginPasspord = loginPasspord;
	}
	
	@NotNull(message="固定工人(个)不能为空")
	public Integer getWorkerNum() {
		return workerNum;
	}

	public void setWorkerNum(Integer workerNum) {
		this.workerNum = workerNum;
	}
	
	@NotNull(message="代班(个)不能为空")
	public Integer getWorkerLeaderNum() {
		return workerLeaderNum;
	}

	public void setWorkerLeaderNum(Integer workerLeaderNum) {
		this.workerLeaderNum = workerLeaderNum;
	}
	
	@NotNull(message="工人工作年限不能为空")
	public Double getWorkerYear() {
		return workerYear;
	}

	public void setWorkerYear(Double workerYear) {
		this.workerYear = workerYear;
	}
	
	@NotNull(message="带领团队年限不能为空")
	public Double getLeadTeamYear() {
		return leadTeamYear;
	}

	public void setLeadTeamYear(Double leadTeamYear) {
		this.leadTeamYear = leadTeamYear;
	}
	
	@Length(min=0, max=50, message="参与金额最大项目名称长度必须介于 0 和 50 之间")
	public String getProMaxName() {
		return proMaxName;
	}

	public void setProMaxName(String proMaxName) {
		this.proMaxName = proMaxName;
	}
	
	@Length(min=0, max=50, message="项目地址长度必须介于 0 和 50 之间")
	public String getProMaxAddress() {
		return proMaxAddress;
	}

	public void setProMaxAddress(String proMaxAddress) {
		this.proMaxAddress = proMaxAddress;
	}
	
	@Length(min=0, max=50, message="项目面积(平方米)长度必须介于 0 和 50 之间")
	public String getProMaxArea() {
		return proMaxArea;
	}

	public void setProMaxArea(String proMaxArea) {
		this.proMaxArea = proMaxArea;
	}
	
	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}
	
	public Integer getProMaxMoney() {
		return proMaxMoney;
	}

	public void setProMaxMoney(Integer proMaxMoney) {
		this.proMaxMoney = proMaxMoney;
	}
	
	public Integer getCanVisit() {
		return canVisit;
	}

	public void setCanVisit(Integer canVisit) {
		this.canVisit = canVisit;
	}
	
	public Integer getKnownWay() {
		return knownWay;
	}

	public void setKnownWay(Integer knownWay) {
		this.knownWay = knownWay;
	}
	
	@Length(min=0, max=200, message="对公司第一印象长度必须介于 0 和 200 之间")
	public String getFirstImpression() {
		return firstImpression;
	}

	public void setFirstImpression(String firstImpression) {
		this.firstImpression = firstImpression;
	}
	
	@Length(min=0, max=200, message="上家公司离职原因长度必须介于 0 和 200 之间")
	public String getLastLeavingReason() {
		return lastLeavingReason;
	}

	public void setLastLeavingReason(String lastLeavingReason) {
		this.lastLeavingReason = lastLeavingReason;
	}
	
	@Length(min=0, max=50, message="公司名称长度必须介于 0 和 50 之间")
	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}
	
	public Double getCompRegisterMoney() {
		return compRegisterMoney;
	}

	public void setCompRegisterMoney(Double compRegisterMoney) {
		this.compRegisterMoney = compRegisterMoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCompRegisterDate() {
		return compRegisterDate;
	}

	public void setCompRegisterDate(Date compRegisterDate) {
		this.compRegisterDate = compRegisterDate;
	}
	
	@Length(min=0, max=50, message="公司地址长度必须介于 0 和 50 之间")
	public String getCompAddress() {
		return compAddress;
	}

	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}
	
	@Length(min=0, max=50, message="营业执照号长度必须介于 0 和 50 之间")
	public String getCompBusinessLicense() {
		return compBusinessLicense;
	}

	public void setCompBusinessLicense(String compBusinessLicense) {
		this.compBusinessLicense = compBusinessLicense;
	}
	
	@Length(min=0, max=50, message="税务登记号长度必须介于 0 和 50 之间")
	public String getCompTaxId() {
		return compTaxId;
	}

	public void setCompTaxId(String compTaxId) {
		this.compTaxId = compTaxId;
	}
	
	@Length(min=0, max=50, message="法人长度必须介于 0 和 50 之间")
	public String getCompLegalPerson() {
		return compLegalPerson;
	}

	public void setCompLegalPerson(String compLegalPerson) {
		this.compLegalPerson = compLegalPerson;
	}
	
	@Length(min=0, max=50, message="法人身份证号长度必须介于 0 和 50 之间")
	public String getCompLegalCardno() {
		return compLegalCardno;
	}

	public void setCompLegalCardno(String compLegalCardno) {
		this.compLegalCardno = compLegalCardno;
	}
	
	@Length(min=0, max=50, message="法人电话长度必须介于 0 和 50 之间")
	public String getCompLegalMobile() {
		return compLegalMobile;
	}

	public void setCompLegalMobile(String compLegalMobile) {
		this.compLegalMobile = compLegalMobile;
	}
	
	@Length(min=0, max=50, message="法人邮箱长度必须介于 0 和 50 之间")
	public String getCompLegalPersonEmail() {
		return compLegalPersonEmail;
	}

	public void setCompLegalPersonEmail(String compLegalPersonEmail) {
		this.compLegalPersonEmail = compLegalPersonEmail;
	}
	
	@Length(min=0, max=50, message="对公账号长度必须介于 0 和 50 之间")
	public String getCompBankAccount() {
		return compBankAccount;
	}

	public void setCompBankAccount(String compBankAccount) {
		this.compBankAccount = compBankAccount;
	}
	
	public Integer getCompBankType() {
		return compBankType;
	}

	public void setCompBankType(Integer compBankType) {
		this.compBankType = compBankType;
	}
	
	@NotNull(message="保证金(元)(最终金额,由财务变动时通知修改)不能为空")
	public BigDecimal getDepositCash() {
		return depositCash;
	}

	public void setDepositCash(BigDecimal depositCash) {
		this.depositCash = depositCash;
	}
	
	public Integer getDepositBackState() {
		return depositBackState;
	}

	public void setDepositBackState(Integer depositBackState) {
		this.depositBackState = depositBackState;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDepositExpectBackDate() {
		return depositExpectBackDate;
	}

	public void setDepositExpectBackDate(Date depositExpectBackDate) {
		this.depositExpectBackDate = depositExpectBackDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDepositActualBackDate() {
		return depositActualBackDate;
	}

	public void setDepositActualBackDate(Date depositActualBackDate) {
		this.depositActualBackDate = depositActualBackDate;
	}
	
	@Length(min=0, max=1000, message="continued_charge长度必须介于 0 和 1000 之间")
	public String getContinuedCharge() {
		return continuedCharge;
	}

	public void setContinuedCharge(String continuedCharge) {
		this.continuedCharge = continuedCharge;
	}
	
	@Length(min=0, max=200, message="本次离职原因长度必须介于 0 和 200 之间")
	public String getLeavingReason() {
		return leavingReason;
	}

	public void setLeavingReason(String leavingReason) {
		this.leavingReason = leavingReason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}
	
	@NotNull(message="是否黑名单 0 不是   1 是不能为空")
	public Integer getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(Integer isBlacklist) {
		this.isBlacklist = isBlacklist;
	}
	
	@NotNull(message="试用人员项目数(默认2个)不能为空")
	public Integer getProTestCount() {
		return proTestCount;
	}

	public void setProTestCount(Integer proTestCount) {
		this.proTestCount = proTestCount;
	}
	
//	@停工,4 待离职审批(  正式人员先经过集团审批批),51临时待离职, 52正式待离职,6,已离职未提档 7,已离职不能为空")
	public Integer getUserState() {
		return userState;
	}

	public void setUserState(Integer userState) {
		this.userState = userState;
	}
	
	@Length(min=0, max=500, message="个人照片服务器地址长度必须介于 0 和 500 之间")
	public String getPhotoServerUrl() {
		return photoServerUrl;
	}

	public void setPhotoServerUrl(String photoServerUrl) {
		this.photoServerUrl = photoServerUrl;
	}
	
	@Length(min=0, max=500, message="个人照片文件名长度必须介于 0 和 500 之间")
	public String getPhotoFileUrl() {
		return photoFileUrl;
	}

	public void setPhotoFileUrl(String photoFileUrl) {
		this.photoFileUrl = photoFileUrl;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=10, message="创建人长度必须介于 0 和 10 之间")
	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="修改时间不能为空")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Length(min=0, max=10, message="修改人长度必须介于 0 和 10 之间")
	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	
	public Integer getIsGuidang() {
		return isGuidang;
	}

	public void setIsGuidang(Integer isGuidang) {
		this.isGuidang = isGuidang;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLeavingTime() {
		return leavingTime;
	}

	public void setLeavingTime(Date leavingTime) {
		this.leavingTime = leavingTime;
	}
	
	public Integer getUserLevelNext() {
		return userLevelNext;
	}

	public void setUserLevelNext(Integer userLevelNext) {
		this.userLevelNext = userLevelNext;
	}
	
	public Integer getTempOriginalLevel() {
		return tempOriginalLevel;
	}

	public void setTempOriginalLevel(Integer tempOriginalLevel) {
		this.tempOriginalLevel = tempOriginalLevel;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTempLevelDeadline() {
		return tempLevelDeadline;
	}

	public void setTempLevelDeadline(Date tempLevelDeadline) {
		this.tempLevelDeadline = tempLevelDeadline;
	}
	
	public Integer getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Integer isFreeze) {
		this.isFreeze = isFreeze;
	}
	
	public Integer getGroupIsok() {
		return groupIsok;
	}

	public void setGroupIsok(Integer groupIsok) {
		this.groupIsok = groupIsok;
	}
	
	@Length(min=0, max=500, message="个人艺术照片服务器地址长度必须介于 0 和 500 之间")
	public String getPhotoArtServerUrl() {
		return photoArtServerUrl;
	}

	public void setPhotoArtServerUrl(String photoArtServerUrl) {
		this.photoArtServerUrl = photoArtServerUrl;
	}
	
	@Length(min=0, max=500, message="个人艺术照片文件名长度必须介于 0 和 500 之间")
	public String getPhotoArtFileUrl() {
		return photoArtFileUrl;
	}

	public void setPhotoArtFileUrl(String photoArtFileUrl) {
		this.photoArtFileUrl = photoArtFileUrl;
	}
	
	public Integer getAchievementLevel() {
		return achievementLevel;
	}

	public void setAchievementLevel(Integer achievementLevel) {
		this.achievementLevel = achievementLevel;
	}
	
	@Length(min=0, max=100, message="人员标记长度必须介于 0 和 100 之间")
	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}
	
	public Integer getNotDisplayInSgpt() {
		return notDisplayInSgpt;
	}

	public void setNotDisplayInSgpt(Integer notDisplayInSgpt) {
		this.notDisplayInSgpt = notDisplayInSgpt;
	}
	
	public Integer getPhotoCheckstate() {
		return photoCheckstate;
	}

	public void setPhotoCheckstate(Integer photoCheckstate) {
		this.photoCheckstate = photoCheckstate;
	}
	
	public Integer getPhotoArtCheckstate() {
		return photoArtCheckstate;
	}

	public void setPhotoArtCheckstate(Integer photoArtCheckstate) {
		this.photoArtCheckstate = photoArtCheckstate;
	}
	
	@Length(min=0, max=50, message="APP人事卡号长度必须介于 0 和 50 之间")
	public String getAppCardno() {
		return appCardno;
	}

	public void setAppCardno(String appCardno) {
		this.appCardno = appCardno;
	}
	
	public Integer getUserTitleLevel() {
		return userTitleLevel;
	}

	public void setUserTitleLevel(Integer userTitleLevel) {
		this.userTitleLevel = userTitleLevel;
	}
	
	public Integer getDataSources() {
		return dataSources;
	}

	public void setDataSources(Integer dataSources) {
		this.dataSources = dataSources;
	}
	
	public Integer getStarlevel() {
		return starlevel;
	}

	public void setStarlevel(Integer starlevel) {
		this.starlevel = starlevel;
	}
	
	public Integer getPaidType() {
		return paidType;
	}

	public void setPaidType(Integer paidType) {
		this.paidType = paidType;
	}
	
	@Length(min=0, max=50, message="对公账户开户名长度必须介于 0 和 50 之间")
	public String getCompBankAccountName() {
		return compBankAccountName;
	}

	public void setCompBankAccountName(String compBankAccountName) {
		this.compBankAccountName = compBankAccountName;
	}
	
	public Integer getCounselorlevel() {
		return counselorlevel;
	}

	public void setCounselorlevel(Integer counselorlevel) {
		this.counselorlevel = counselorlevel;
	}
	
	public Integer getSyntheticgrade() {
		return syntheticgrade;
	}

	public void setSyntheticgrade(Integer syntheticgrade) {
		this.syntheticgrade = syntheticgrade;
	}
	
	public Integer getTempsyntheticgrade() {
		return tempsyntheticgrade;
	}

	public void setTempsyntheticgrade(Integer tempsyntheticgrade) {
		this.tempsyntheticgrade = tempsyntheticgrade;
	}
	
	public Integer getNameCheckstate() {
		return nameCheckstate;
	}

	public void setNameCheckstate(Integer nameCheckstate) {
		this.nameCheckstate = nameCheckstate;
	}
	
	public Integer getIdcardCheckstate() {
		return idcardCheckstate;
	}

	public void setIdcardCheckstate(Integer idcardCheckstate) {
		this.idcardCheckstate = idcardCheckstate;
	}
	
	public Integer getBankAmountCheckstate() {
		return bankAmountCheckstate;
	}

	public void setBankAmountCheckstate(Integer bankAmountCheckstate) {
		this.bankAmountCheckstate = bankAmountCheckstate;
	}
	
	public Integer getBankTypeCheckstate() {
		return bankTypeCheckstate;
	}

	public void setBankTypeCheckstate(Integer bankTypeCheckstate) {
		this.bankTypeCheckstate = bankTypeCheckstate;
	}
	
	public Integer getAreaType() {
		return areaType;
	}

	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
	
	public Integer getProjectMoneyType() {
		return projectMoneyType;
	}

	public void setProjectMoneyType(Integer projectMoneyType) {
		this.projectMoneyType = projectMoneyType;
	}
	
	public Integer getUserJoinState() {
		return userJoinState;
	}

	public void setUserJoinState(Integer userJoinState) {
		this.userJoinState = userJoinState;
	}
	
	public String getUserJoinAmount() {
		return userJoinAmount;
	}

	public void setUserJoinAmount(String userJoinAmount) {
		this.userJoinAmount = userJoinAmount;
	}
	
	public Integer getDeviationType() {
		return deviationType;
	}

	public void setDeviationType(Integer deviationType) {
		this.deviationType = deviationType;
	}
	
	public Integer getCharacterType() {
		return characterType;
	}

	public void setCharacterType(Integer characterType) {
		this.characterType = characterType;
	}
	
	public Integer getCustomerDeviationType() {
		return customerDeviationType;
	}

	public void setCustomerDeviationType(Integer customerDeviationType) {
		this.customerDeviationType = customerDeviationType;
	}
	
	public Integer getCustomerCharacterType() {
		return customerCharacterType;
	}

	public void setCustomerCharacterType(Integer customerCharacterType) {
		this.customerCharacterType = customerCharacterType;
	}
	
	public Integer getCustomerAreaType() {
		return customerAreaType;
	}

	public void setCustomerAreaType(Integer customerAreaType) {
		this.customerAreaType = customerAreaType;
	}
	
	public Integer getCustomerBudgetType() {
		return customerBudgetType;
	}

	public void setCustomerBudgetType(Integer customerBudgetType) {
		this.customerBudgetType = customerBudgetType;
	}
	
	public Integer getCustomerProType() {
		return customerProType;
	}

	public void setCustomerProType(Integer customerProType) {
		this.customerProType = customerProType;
	}
	
	public Integer getMsgstate() {
		return msgstate;
	}

	public void setMsgstate(Integer msgstate) {
		this.msgstate = msgstate;
	}
	
	public Integer getStagestate() {
		return stagestate;
	}

	public void setStagestate(Integer stagestate) {
		this.stagestate = stagestate;
	}
	
	@Length(min=0, max=100, message="认证-操作内容长度必须介于 0 和 100 之间")
	public String getApprovedesc() {
		return approvedesc;
	}

	public void setApprovedesc(String approvedesc) {
		this.approvedesc = approvedesc;
	}
	
	@Length(min=0, max=100, message="临时-操作内容长度必须介于 0 和 100 之间")
	public String getTemporarydesc() {
		return temporarydesc;
	}

	public void setTemporarydesc(String temporarydesc) {
		this.temporarydesc = temporarydesc;
	}
	
	@Length(min=0, max=100, message="正式-操作内容长度必须介于 0 和 100 之间")
	public String getOfficialdesc() {
		return officialdesc;
	}

	public void setOfficialdesc(String officialdesc) {
		this.officialdesc = officialdesc;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUserJoinDate() {
		return userJoinDate;
	}

	public void setUserJoinDate(Date userJoinDate) {
		this.userJoinDate = userJoinDate;
	}
	
	public Integer getReceivingOrderState() {
		return receivingOrderState;
	}

	public void setReceivingOrderState(Integer receivingOrderState) {
		this.receivingOrderState = receivingOrderState;
	}
	
	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
	@Length(min=0, max=255, message="微信号长度必须介于 0 和 255 之间")
	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	
	public Integer getZsrsUid() {
		return zsrsUid;
	}

	public void setZsrsUid(Integer zsrsUid) {
		this.zsrsUid = zsrsUid;
	}
	
	@Length(min=0, max=255, message="省长度必须介于 0 和 255 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=255, message="市长度必须介于 0 和 255 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=255, message="业务员卡号长度必须介于 0 和 255 之间")
	public String getPmCard() {
		return pmCard;
	}

	public void setPmCard(String pmCard) {
		this.pmCard = pmCard;
	}
	
	public Integer getAgreementtype() {
		return agreementtype;
	}

	public void setAgreementtype(Integer agreementtype) {
		this.agreementtype = agreementtype;
	}
	
	public String getJoinmoney() {
		return joinmoney;
	}

	public void setJoinmoney(String joinmoney) {
		this.joinmoney = joinmoney;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAgreementstarttime() {
		return agreementstarttime;
	}

	public void setAgreementstarttime(Date agreementstarttime) {
		this.agreementstarttime = agreementstarttime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAgreementendtime() {
		return agreementendtime;
	}

	public void setAgreementendtime(Date agreementendtime) {
		this.agreementendtime = agreementendtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}
	
	@Length(min=0, max=255, message="车牌号长度必须介于 0 和 255 之间")
	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public Integer getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Integer accommodation) {
		this.accommodation = accommodation;
	}
	
	public Integer getIntentionStatus() {
		return intentionStatus;
	}

	public void setIntentionStatus(Integer intentionStatus) {
		this.intentionStatus = intentionStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getIntentionTime() {
		return intentionTime;
	}

	public void setIntentionTime(Date intentionTime) {
		this.intentionTime = intentionTime;
	}
	
	@Length(min=0, max=500, message="大院人事主键长度必须介于 0 和 500 之间")
	public String getRsAppId() {
		return rsAppId;
	}

	public void setRsAppId(String rsAppId) {
		this.rsAppId = rsAppId;
	}
	
	@Length(min=0, max=255, message="项目经理新擅长长度必须介于 0 和 255 之间")
	public String getProTypeNew() {
		return proTypeNew;
	}

	public void setProTypeNew(String proTypeNew) {
		this.proTypeNew = proTypeNew;
	}
	
	@Length(min=0, max=255, message="个人箴言长度必须介于 0 和 255 之间")
	public String getPersonMotto() {
		return personMotto;
	}

	public void setPersonMotto(String personMotto) {
		this.personMotto = personMotto;
	}
	
	public Integer getIsTuijian() {
		return isTuijian;
	}

	public void setIsTuijian(Integer isTuijian) {
		this.isTuijian = isTuijian;
	}
	
	public Long getBespokeNum() {
		return bespokeNum;
	}

	public void setBespokeNum(Long bespokeNum) {
		this.bespokeNum = bespokeNum;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTuijianTime() {
		return tuijianTime;
	}

	public void setTuijianTime(Date tuijianTime) {
		this.tuijianTime = tuijianTime;
	}
	
	public Integer getReturnGuest() {
		return returnGuest;
	}

	public void setReturnGuest(Integer returnGuest) {
		this.returnGuest = returnGuest;
	}
	
	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	
	public Integer getTraditionLevel() {
		return traditionLevel;
	}

	public void setTraditionLevel(Integer traditionLevel) {
		this.traditionLevel = traditionLevel;
	}
	
	public Integer getJoinLevel() {
		return joinLevel;
	}

	public void setJoinLevel(Integer joinLevel) {
		this.joinLevel = joinLevel;
	}
	
	public Integer getSgptUserScore() {
		return sgptUserScore;
	}

	public void setSgptUserScore(Integer sgptUserScore) {
		this.sgptUserScore = sgptUserScore;
	}
	
}