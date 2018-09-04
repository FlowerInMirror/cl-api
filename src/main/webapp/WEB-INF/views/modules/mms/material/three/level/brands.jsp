<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .switchcra{width:80px;height:30px;line-height:30px;position: relative;border-radius: 30px;margin-top:32px;}
    .switchcra span{position: absolute;width:30px;height:30px;border-radius: 50%;}
    .switchcra-off{background:#e3e3e3;padding: 0 0 0 10px;}
    .switchcra-off span{background:#cfcfcf;right:0;top:0;}
    .switchcra-on{background:#67e66c;text-align: right;padding-right:15px;
        color: #fff;}
    .switchcra-on span{background:#fff;left:0;top:0;}
    .ml80{margin-left:80px;}
</style>
<div id="editMatLevelBrands"  v-cloak >
    <%--品牌展示 开始--%>
    <div class="brandsShowBox plr10">
        <div class="brandsShow clearfix mt10 relative">
            <a href="javascript:" class="brandsSet"></a>
            <div class="mall_slide_box fl relative">
                <ul class="clearfix">
                    <li style="position: absolute; left: 0px; top: 0px;"><img class="db_100 show_img" :src="firstBrandMainPhotos.branMainPhotoURL == null || firstBrandMainPhotos.branMainPhotoURL == '' ? '${ctxStatic}/images/pic/zan_nopic.png' : firstBrandMainPhotos.branMainPhotoURL" alt=""></li>
                </ul>
            </div>
            <div class="slide_img_info relative">
                <p class="f3 fz16 mb14 lh28 mt10"><span>{{mallBrand.matName}}</span><span class="ml10">{{mallBrand.matSpec}}</span></p>
                <p><span class="Model">{{matLevelBrands.mCode}}</span></p>

                <span class="cRed ml15 slidePrice">￥{{matLevelBrands.mQuotesPrice | holdTwoDecimal(matLevelBrands.mQuotesPrice )}}</span>
                <div class="classification clearfix">
                    <p class="lh32 fz14 ">当前选择型号<strong class="fz14 cce2222">【{{matLevelBrands.mBrandType}}】</strong></p>
                    <ul class="clearfix">
                        <li v-for="(item, index) in matLevelBrands.branTypePhotos" :title="item.branTypePhotoName"><img class="Optshow_img" :data-src="item.branTypePhotoURL" :src="item.branTypePhotoURL == null || item.branTypePhotoURL == '' ? '${ctxStatic}/images/pic/zan_nopic.png' : item.branTypePhotoURL" alt=""></li>
                    </ul>

                </div>
                <!-- 档品牌 开始 -->
                <div class="brand_lab">
                    <div class="brand_lab_list clearfix">
                        <span class="brand_lab_list_tit brand_lab_list_titA fl">{{matLevelBrands.mLevel | levelFlagTOABC(matLevelBrands.mLevel)}}档</span>
                        <ul class="fl">
                            <li class="relative fl " style="font-size: 0;">
                                <a href="javascript:" class="" :title="matLevelBrands.brandName"><img :src="matLevelBrands.brandLOGOPhotoURL == null || matLevelBrands.brandLOGOPhotoURL == ''? '${ctxStatic}/images/pic/zan_nopic.png' : matLevelBrands.brandLOGOPhotoURL" alt=""></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- 各档品牌 结束 -->
                <span class="erwm2 brandQRCode" :data-matid="matLevelBrands.matID"></span>
            </div>
        </div>
        <%--品牌主图遍历开始--%>
        <div class=" brandsShowSmall clearfix pt10">
            <div v-for="(item, index) in matLevelBrands.branMainPhotos" :class="index == 0 ? 'borderAll fl mr10 Opt_model tb-selected' : 'borderAll fl mr10 Opt_model'">
                <a href="javascript:" class="db_100 ">
                    <img class="Opt_img" :src="item.branMainPhotoURL" :data-src="item.branMainPhotoURL" alt="">
                </a>
            </div>
        </div>
    </div>
    <!--品牌编辑 开始-->
    <div class="hide divMaterialLoadAlert_box">
        <%--编辑项 --%>
        <div class="divMaterialLoadAlert_topm  pr10 pl10">
            <h2 class="uiTitle2 platit relative">
                <i class="uiTitle-icon"></i>
                品牌详情
                <a href="javascript:" class="gobacktoalert"></a>
            </h2>
            <div class=" materialTopNavConm pr10">


                <div class="SearchCon pt10 materialpicture materialpictureNoCount pr10">

                    <!--品牌信息 开始-->
                    <div class="analyItem pinpai_analyItem ">
                        <p class="analyItemTit tx-center">品牌信息</p>
                        <div class="analyItemCon relative">
                            <p class="info-look fl col-md-4">
                                <span class="cLightGray pr8 fl">品牌名称</span>
                                <span class="txtBrandName">{{matLevelBrands.brandName | isBlankToPlaceholder(matLevelBrands.brandName)}}</span>
                            </p>
                            <p class="info-edit fl col-md-4 hide">
                                <span class="cLightGray pr8 fl">品牌名称</span>
                                <input type="text" class="width130" id="txtBrandName" :value="matLevelBrands.brandName | isBlankToPlaceholder(matLevelBrands.brandName)">
                            </p>
                            <p class="info-look fl col-md-8">
                                <span class="cLightGray pr8 fl">品牌描述</span>
                                <span class="txtBrandRemark">{{matLevelBrands.brandDescription | isBlankToPlaceholder(matLevelBrands.brandDescription)}} </span>
                            </p>
                            <p class="info-edit fl col-md-8 hide">
                                <span class="cLightGray pr8 fl">品牌描述</span>
                                <input type="text" class="width270" id="txtBrandRemark" :value="matLevelBrands.brandDescription | isBlankToPlaceholder(matLevelBrands.brandDescription)" placeholder="描述">
                            </p>
                            <p class="lh20 p_tip1 dis-il-block tx-center" style="width:126px;">
                                品牌LOGO
                                <%--<span data-pos="top" class="roundtips">?</span>--%>
                            </p>
                            <p class="lh20 p_tip2 dis-il-block tx-center" style="width:126px;">
                                产品主图
                                <%--<span data-pos="top" class="roundtips">?</span>--%>
                            </p>
                            <ul class="brandlogo clearfix">
                                <li v-show="matLevelBrands.brandLOGOPhotoURL != null && matLevelBrands.brandLOGOPhotoURL != ''" :data-tspid="matLevelBrands.brandLOGOPhotoID" data-type="1">
                                    <div class="brandlogopic">
                                        <img :src="matLevelBrands.brandLOGOPhotoURL" />
                                        <div class="uiImgUpload-mark">
                                            <div class="uiImgUpload-mark-bg"></div>
                                            <div class="uiImgUpload-mark-link">
                                                <div class="dis_il_block">
                                                    <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                                    <a href="javascript:" class="enlarge_link" :data-src="matLevelBrands.brandLOGOPhotoURL" data-imgname=""></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>

                                <li v-show="matLevelBrands.brandLOGOPhotoURL == null || matLevelBrands.brandLOGOPhotoURL == ''" :data-matid="matLevelBrands.matID" :data-matlevel="matLevelBrands.mLevel" data-type="1">
                                    <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth126 relative">
                                        <a href="javascript:" class="uiImgUpload-first">
                                            <input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" value="" class="file fileone">
                                            <em class="bgIcon file-ico"></em>
                                        </a>
                                        <img src="" />
                                        <div class="uiImgUpload-mark">
                                            <div class="uiImgUpload-mark-bg"></div>
                                            <div class="uiImgUpload-mark-link">
                                                <div class="dis_il_block">
                                                    <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                                    <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <%--产品主图遍历开始--%>
                                <li v-for="(item, index) in matLevelBrands.branMainPhotos" :data-tspid="item.branMainPhotoID" data-type="4">
                                    <div class="brandlogopic brandlogopic_new">
                                        <img :src="item.branMainPhotoURL" />
                                        <!--图片遮罩和放大按钮 开始-->
                                        <div class="uiImgUpload-mark">
                                            <div class="uiImgUpload-mark-bg"></div>
                                            <div class="uiImgUpload-mark-link">
                                                <div class="dis_il_block">
                                                    <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                                    <a href="javascript:" class="enlarge_link" :data-src="item.branMainPhotoURL" data-imgname=""></a>
                                                    <a href="javascript:" class="delect_link"></a>
                                                </div>
                                            </div>
                                        </div>
                                        <!--图片遮罩和放大按钮 结束-->
                                    </div>
                                </li>
                                <%--添加产品主图不限制--%>
                                <li data-type="4" :data-matid="matLevelBrands.matID" :data-matlevel="matLevelBrands.mLevel">
                                    <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative">
                                        <a href="javascript:">
                                            <input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" value="" class="file filemore">
                                            <em class="bgIcon file-ico"></em>
                                        </a>

                                    </div>

                                </li>
                            </ul>
                            <div class="dis-il-block imgbox imgbox1">
                                <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png" />
                            </div>
                            <div class="dis-il-block imgbox  hide imgbox2">
                                <img class="save_img btnSaveBrandSupOption" src="${ctxStatic}/images/pic/save_img.png" data-type="1" />
                                <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png" />
                            </div>
                            <%--回路积分项--%>
                            <div class="judge-div">
                                <input type="text" :class="matLevelBrands.brandInfoLoop == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                                <input type="text" :class="matLevelBrands.brandInfoLoop == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                                <strong class="fl score cGreen fz14 pl5">{{matLevelBrands.brandInfoLoop}}分</strong>
                            </div>
                        </div>
                    </div>
                    <!--品牌信息 结束-->
                    <!--品牌型号 开始-->
                    <div class="analyItem divbrandtype xinghao_analyItem ">
                        <p class="analyItemTit tx-center">型号信息</p>
                        <div class="analyItemCon relative clearfix">
                            <div class="analyItemConText">
                                <p class="cRed lh20">图片要求：尺寸180*180px；大小：2M以内；格式：jpg；</p>
                                <ul class="brandmodel_new clearfix mb10">
                                    <%--型号信息遍历开始--%>
                                    <%--情况1:有图片--%>
                                    <li  v-for="(item, index) in matLevelBrands.branTypePhotos" :data-tspid="item.branTypePhotoID" :data-matid="matLevelBrands.matID" data-type="2">

                                        <div  class="brandlogopic">
                                            <img :src="item.branTypePhotoURL == '' || item.branTypePhotoURL == null ? '${ctxStatic}/images/pic/zan_nopic.png' : item.branTypePhotoURL" />
                                            <!--图片遮罩和放大按钮 开始-->
                                            <div class="uiImgUpload-mark">
                                                <div class="uiImgUpload-mark-bg"></div>
                                                <div class="uiImgUpload-mark-link">
                                                    <div class="dis_il_block">
                                                        <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                                        <a href="javascript:" class="enlarge_link" :data-src="item.branTypePhotoURL"  data-imgname=""></a>
                                                        <a href="javascript:" class="delect_link"></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--图片遮罩和放大按钮 结束-->
                                        </div>

                                        <span :class="item.branTypePhotoName == '' || item.branTypePhotoName == null ? 'mt5 info-look cRed' : 'mt5 info-look'" :title="item.branTypePhotoName" :data-spanname="index" >{{index == 0 ? (matLevelBrands.mBrandType == '' ? '未录入' : matLevelBrands.mBrandType) : (item.branTypePhotoName  == '' ? '未录入' : item.branTypePhotoName ) }}</span>
                                        <input type="text" class="col-md-12 txtTitle mt5 info-edit hide" :data-inputname="index" :value="index == 0 ? matLevelBrands.mBrandType : item.branTypePhotoName" placeholder="型号名称" />
                                    </li>
                                    <%--情况2:没图片--%>
                                    <li v-for="(item, index) in matLevelBrands.branTypePhotoAddArr" :data-matid="matLevelBrands.matID" :data-matlevel="matLevelBrands.mLevel" data-type="2">
                                        <div class="fl uiImgUpload uiImgUpload-gblock uiImgUpload1 uploadwidth130 relative">
                                            <a href="javascript:" class="uiImgUpload-first">
                                                <input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" value="" class="file fileone">
                                                <em class="bgIcon file-ico"></em>
                                            </a>
                                            <img src="" />
                                            <!--图片遮罩和放大按钮 开始-->
                                            <div class="uiImgUpload-mark">
                                                <div class="uiImgUpload-mark-bg"></div>
                                                <div class="uiImgUpload-mark-link">
                                                    <div class="dis_il_block">
                                                        <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                                        <a href="javascript:" class="enlarge_link" data-src="" data-imgname=""></a>
                                                        <a v-show="index >= 2" href="javascript:" class="delect_link"></a>
                                                    </div>
                                                </div>
                                            </div>
                                            <!--图片遮罩和放大按钮 结束-->
                                        </div>

                                        <input type="text" class="col-md-12 txtTitle mt5 info-edit hide" :value="index == 0 && matLevelBrands.branTypePhotoSize == 0 ? matLevelBrands.mBrandType : null" placeholder="型号名称" />
                                        <span class="mt5 info-look"><span v-show="index == 0 && matLevelBrands.branTypePhotoSize == 0" >{{matLevelBrands.mBrandType}}</span><span v-show="index != 0 || matLevelBrands.branTypePhotoSize != 0" class="cRed">未录入</span></span>
                                    </li>
                                </ul>

                                <p class="info-look fl col-md-4">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">是否主推</span>
                                    <span class="ddlHost">{{matLevelBrands.mHostState == 1 ? '是' : '否'}}</span>
                                </p>
                                <p class="info-edit fl col-md-4 hide">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">是否主推</span>
                                    <select class="width60" id="ddlHost">
                                        <option  value="1"  >是</option>
                                        <option  value="0"  >否</option>
                                    </select>
                                </p>
                                <p class="info-look fl col-md-8">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">材料类型</span>
                                    <span class="ddlMatType">{{matLevelBrands.mMatType == 1 ? '品牌材料' : '瑞祥专供材料' }}</span>
                                </p>
                                <p class="info-edit fl col-md-8 hide">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">材料类型</span>
                                    <select class="width120 nopaddlr" id="ddlMatType">
                                        <option value="1" >品牌材料</option>
                                        <option value="2" >瑞祥专供材料</option>
                                    </select>
                                </p>
                                <%--隐藏未启用--%>
                                <p class="hide fl col-md-8">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">品牌型号</span>
                                    <input type="text" class="width150" id="txtBrandType" :value="matLevelBrands.mBrandType">
                                </p>
                                <p class="hide fl col-md-4">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">是否推荐首页</span>
                                    <span>{{matLevelBrands.mHomeHostState == 1 ? '是' : '否'}}</span>
                                </p>
                                <p class=" hide fl col-md-4">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">是否推荐首页</span>
                                    <select class="width60" id="ddlHomeHost">
                                        <option  value="1" :selected="matLevelBrands.mHomeHostState == 1 ? 'selected' : 'false'">是</option>
                                        <option  value="0" :selected="matLevelBrands.mHomeHostState != 1 ? 'selected' : 'false'">否</option>
                                    </select>
                                </p>
                                <p class="fl col-md-4 hide">
                                    <span class="cLightGray pr8 justify_span justify_span_w80">材料编号</span>
                                    <span>{{matLevelBrands.mCode}}</span>
                                </p>

                                <%--操作按钮--%>
                                <div class="dis-il-block imgbox imgbox1 ">
                                    <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png" />
                                </div>
                                <div class="dis-il-block imgbox hide  imgbox2">
                                    <img class="save_img btnSaveBrandSupOption" src="${ctxStatic}/images/pic/save_img.png" data-type="2" />
                                    <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png" />
                                </div>

                                <%--回路积分项--%>
                                <div class="judge-div">
                                    <input type="text" :class="matLevelBrands.typeInfoLoop == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                                    <input type="text" :class="matLevelBrands.typeInfoLoop == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                                    <strong class="fl score cGreen fz14 pl5">{{matLevelBrands.typeInfoLoop}}分</strong>
                                </div>
                            </div></div>
                    </div>
                    <!--品牌型号 结束-->
                    <!--小样 开始-->
                    <div class="analyItem">
                        <p class="analyItemTit tx-center">小样信息</p>
                        <div class="analyItemCon relative">
                            <div class="clearfix">

                                <div class="fl">
                                    <h2 class="mb10" style="font-size: 15px;letter-spacing: 2px;">{{mallBrand.matName}}</h2>
                                    <span class="dis_block lh22">品牌：{{matLevelBrands.brandName}}</span>
                                    <span class="dis_block lh22">规格：{{mallBrand.matSpec}}</span>
                                    <span class="dis_block lh22">编码：{{matLevelBrands.mCode}}</span>
                                </div>
                                <div id="brandQRCode" class="fl mt5 ml80"></div>

                                <%--<a href="javascript:" class="switchcra switchcra-on fr hide">需要<span></span></a>--%>
                                <%--<a href="javascript:" class="switchcra switchcra-off fr hide">不需要<span></span></a>--%>
                            </div>
                        </div>
                    </div>
                    <!--小样 结束-->
                    <%--价格 开始--%>
                    <div class="analyItem">
                        <p class="analyItemTit tx-center">价格</p>
                        <div class="analyItemCon relative">
                            <p class="info-look fl col-md-4">
                                <span class="cLightGray pr8 justify_span justify_span_w80">报价</span>
                                <span>￥{{matLevelBrands.mQuotesPrice | holdTwoDecimal(matLevelBrands.mQuotesPrice)}}</span>
                            </p>
                            <p class="info-editaaaa hide fl col-md-4">
                                <span class="cLightGray pr8 justify_span justify_span_w80">报价</span>
                                <input type="text" class="width60" id="txtQuotesPrice" :value="matLevelBrands.mQuotesPrice">
                            </p>
                            <p class="info-look fl col-md-4">
                                <span class="cLightGray pr8 justify_span justify_span_w80">成本</span>
                                <span>￥{{matLevelBrands.mCostPrice | holdTwoDecimal(matLevelBrands.mCostPrice)}}</span>
                            </p>
                            <p class="info-editaaaa hide fl col-md-4">
                                <span class="cLightGray pr8 justify_span justify_span_w80">成本</span>
                                <input type="text" class="width60" id="txtCostPrice" :value="matLevelBrands.mCostPrice">
                            </p>
                            <%--成品类展示 安装价--%>
                            <p v-show="matLevelBrands.classify == 1" class="info-look fl col-md-4 ">
                                <span class="cLightGray pr8 justify_span justify_span_w80">安装/加工费</span>
                                <span>￥{{matLevelBrands.mInstallPrice | holdTwoDecimal(matLevelBrands.mInstallPrice)}}</span>
                            </p>
                            <p class=" hide fl col-md-4">
                                <span class="cLightGray pr8 justify_span justify_span_w80">安装/加工费</span>
                                <input type="text" class="width60" id="txtInstallPrice" :value="matLevelBrands.mInstallPrice">
                            </p>

                        </div>
                    </div>
                    <!--供货商 开始-->
                    <div class="analyItem">
                        <p class="analyItemTit tx-center">供货商</p>
                        <div class="analyItemCon relative">
                            <p v-for="(item, index) in matLevelBrands.suppliers" class="fl col-md-4">
                                <span class="cLightGray pr8" :title="'联系电话：' + item.userMobile">{{item.userName}}</span>
                                <span v-if="(matLevelBrands.mCostPrice + matLevelBrands.mInstallPrice) < item.userPrice" class="cRed">{{item.userPrice | holdTwoDecimal(item.userPrice)}}</span>
                                <span  v-else-if="(matLevelBrands.mCostPrice + matLevelBrands.mInstallPrice) > item.userPrice" class="cGreen">{{item.userPrice | holdTwoDecimal(item.userPrice)}}</span>
                                <span v-else >{{item.userPrice | holdTwoDecimal(item.userPrice)}}</span>
                            </p>
                            <p v-show="matLevelBrands.suppliersSize == 0" class="fl col-md-4"><span class="cRed">无供货商</span></p>

                            <%--回路积分项--%>
                            <div  class="judge-div" >
                                <input type="text" :class="matLevelBrands.suppliersSize != 0 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                                <input type="text" :class="matLevelBrands.suppliersSize == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                                <strong class="fl score cGreen fz14 pl5">{{matLevelBrands.suppliersSize}}分</strong>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--综合项--%>
        <div class="divMaterialLoadAlert_bottom pt10 plr10 boldTopLine">

            <%--综合项开始--%>
            <div class="analyItem">
                <p class="analyItemTit tx-center">综合</p>
                <div class="analyItemCon">
                    <p class="fl col-md-8 cRed">
                        <span class="cLightGray pr8 cRed">品牌名称录入,品牌LOGO上传,产品主图至少上传一张/型号图与型号名称至少录入一项</span>
                        <strong class="plr5 cRed"></strong>
                    </p>
                    <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matLevelBrands.totalLoopScroe}}分</strong></p>
                    <a v-show="matLevelBrands.brandInfoLoop == 1 && matLevelBrands.typeInfoLoop == 1 " href="javascript:" class="circlemark circlemark-green">1</a>
                    <a v-show="matLevelBrands.brandInfoLoop == 0 || matLevelBrands.typeInfoLoop == 0 " href="javascript:" class="circlemark circlemark-lightRed">0.5</a>
                </div>
            </div>
            <div class="tx-center mt10 pb5">
                <input v-show="matLevelBrands.mUpdateState != 1" type="button" value="入库" class="uiBtn-small uiBtn-blue btnBrandNamePerfectState">
                <input v-show="matLevelBrands.firstFlag != 1" type="button" value="删除" class="uiBtn-small uiBtn-red btnBrandDelete">
                <input type="button" value="应用" class="uiBtn-small uiBtn-blue btnBrandNameApply">
            </div>
            <div class="tx-center mt5 clearfix pb5 hide">
                <input type="button" value="进入编辑" class="uiBtn-small uiBtn-blue btnOQAUpdate">
            </div>
        </div>
    </div>
</div>

<script>
    $(function(){

        // 小样需求开关
        $(document).on("click",".switchcra",function(){
            var matID = $("#hidMatID").val();
            if($(this).hasClass("switchcra-off")) {
                // 需要
                $(this).hide();
                $(".switchcra-on").show();
                $.get(basePath + '/material-api/update/sample_type', { matID: matID, sampleType: 1 },
                    function(data){
                    });
            }else{
                // 不需要
                $(this).hide();
                $(".switchcra-off").show();
                $.get(basePath + '/material-api/update/sample_type', { matID: matID, sampleType: 2 },
                    function(data){
                    });
            }

        });


        setTimeout(function () {
            // 生成二维码
            $(".brandQRCode").each(function (index, domEle) {
                var matID = $(domEle).data('matid');
                var cityID = $("#hidCityID").val();
                // 生成规则如下
                var url = 'http://c.rxjyzx.com/SD/'+matID+'/'+cityID;
                // 例:http://c.rxjyzx.com/SD/33A923C1-E74B-4911-AFFB-C52DE38347BD/12
                $(domEle).attr("data-href", url);
                $(domEle).qrcode({
                    width: 60,
                    height: 60,
                    correctLevel: 0,
                    background: "#efeef3", //背景颜色
                    foreground: "black", //前景颜色
                    text: url
                });
            });
        },100);

        $(document).on("click",".brandsShowBox",function(){
            $(".brandsShowBox").hide();
            $(".divMaterialLoadAlert_box").show();
            countAlert();

        });

        $(document).on("click",".brandsSet",function(){
            $(".brandsShowBox").hide();
            $(".divMaterialLoadAlert_box").show();
            countAlert();

        });
        $(document).on("click",".gobacktoalert",function(){
            $(".brandsShowBox").show();
            $(".divMaterialLoadAlert_box").hide();

        });

        $(document).on("click",".Opt_model",function (event) {
            $(".classification ul li").removeClass("tb-selected");
            $(".Opt_model").removeClass("tb-selected");
            $(this).addClass("tb-selected");
            var new_src1 = $(this).find(".Opt_img").attr("src");
            $(".mall_slide_box .show_img").attr("src", new_src1);
            stopEventBubble(event);
        })
        $(document).on("click",".classification ul li",function (event) {
            $(".Opt_model").removeClass("tb-selected");
            $(".classification ul li").removeClass("tb-selected");
            $(this).addClass("tb-selected");
            var new_src1 = $(this).find(".Optshow_img").attr("src");
            $(".mall_slide_box .show_img").attr("src", new_src1);
            stopEventBubble(event);
        });
        $(document).on("click",".brand_lab li",function(event){
            stopEventBubble(event);
        });


    })
</script>
<script>
    //滚动条高度计算
    function countAlert(){
        var theight = $(window).height()-$("#main_header").outerHeight(true) -$(".divMaterialLoadAlert-con .platit").outerHeight(true) - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 10;
        $(".divMaterialLoadAlert_topm").height(theight).slimScroll({
            height: theight,
            borderRadius: "0px"
        });
        $(".divMaterialLoadAlert_topm").closest(".slimScrollDiv").height(theight);
        $(window).resize(function () {
            var theight = $(window).height()-$("#main_header").outerHeight(true) -$(".divMaterialLoadAlert-con .platit").outerHeight(true) - $(".divMaterialLoadAlert_bottom").outerHeight(true) - 10;
            $(".divMaterialLoadAlert_topm").height(theight).slimScroll({
                height: theight,
                borderRadius: "0px"
            });
            $(".divMaterialLoadAlert_topm").closest(".slimScrollDiv").height(theight);
        })
    }
    //清空数据
    function ClearStandard() {
    }

    $(function () {
        //加载上传控件
        setTimeout(function () {
            //二维码
            createBrandQRcoed('brandQRCode',$("#hidMatID").val(),$("#hidCityID").val());

            countAlert();
            $(".materialpicture .brandlogopic,.brandlogo .brandlogopic").each(function () {
                $(this).hover(function () {
                    $(this).find(".uiImgUpload-mark").show();
                }, function () {
                    $(this).find(".uiImgUpload-mark").hide();
                });
            });
            // 主图 鼠标悬停事件
            $(".materialpicture .brandlogopic_new").each(function () {
                $(this).hover(function () {
                    $(this).find(".uiImgUpload-mark").show();
                }, function () {
                    $(this).find(".uiImgUpload-mark").hide();
                });
            });
            $(document).on("click",".enlarge_link",function(){
                var i=$(this).index();
                var thisclick = this;
                var thisId = "preview_" + i;
                var curId = "CurImg_" + i;
                //图片放大
                rxued.images.enSingleLarge(thisclick, thisId, curId);
                //点击旋转按钮
                rxued.images.rotateBtnClick(thisId, curId);
                //关闭图片放大弹出层
                rxued.images.closeImgAlert(thisId);
                //1:1
                rxued.images.oneToone(thisId, curId);
            });

            initImageUpload();
            initImageUploadAddItems();
            //气泡提示信息
            rxued.alert.tips(".p_tip1 span", {
                oEvent: 'mouseover',
                content: '图片要求：尺寸126*80px；大小：2M以内；格式：jpg；'
            })
            rxued.alert.tips(".p_tip2 span", {
                oEvent: 'mouseover',
                content: '图片要求：尺寸370*370px；大小：2M以内；格式：jpg；'
            })
        },300);



        $(document).on("hover", ".brandlogopic brandlogopic_new", function () {
            $(".dis_il_block").show();
        }, function () {
            $(".dis_il_block").hide();
        });

        $(document).on("click", ".img_edit", function () {
            var divObj = $(this).closest("div.analyItem");

            $(divObj).find(".info-look").not(".grounding").hide();
            $(divObj).find(".info-edit").not(".grounding").show();
            // 校验 小样状态是否为 1有,确定是否展示上架状态下拉选
            var ddlSampleStatus = $("#ddlSampleStatus").val();
            if (ddlSampleStatus == 1){
                $(divObj).find(".info-look").hide();
                $(divObj).find(".info-edit").show();
            }

            $(this).parent(".imgbox").hide();
            $(this).parent(".imgbox").next(".imgbox").show();
        });

        $(document).on("click", ".goback_img", function () {
            var divObj = $(this).closest("div.analyItem");
            $(divObj).find(".info-look").show();
            $(divObj).find(".info-edit").hide();

            $(this).parents(".analyItemCon").find(".imgbox1").show();
            $(this).parent(".imgbox2").hide();
        });

        // 数据框内容改变时间 型号信息型号名称
        $(document).on("change","input[data-inputname]",function (i) {
            var n = $(this).attr('data-inputname');
            $("span[data-spanname="+n+"]").removeClass('cRed');
            $("span[data-spanname="+n+"]").html($(this).val());
        });

        // 页面 保存按钮触发事件
        $(document).on("click ", ".save_img", function () {
            // 隐藏 保存/返回按钮 展示编辑按钮
            var divObj = $(this).closest("div.analyItem");
            $(divObj).find(".info-look").show();
            $(divObj).find(".info-edit").hide();

            $(this).parents(".analyItemCon").find(".imgbox1").show();
            $(this).parent(".imgbox2").hide();

            // 点击保存 同步回显
            // 品牌信息-品牌名称
            $(".txtBrandName").html($("#txtBrandName").val());
            // 品牌信息-品牌描述
            $(".txtBrandRemark").html($("#txtBrandRemark").val());

            // 型号信息-是否主推
            $(".ddlHost").html($("#ddlHost").val() == 1 ? '是' : '否');
            // 型号信息-材料类型
            $(".ddlMatType").html($("#ddlMatType").val()  == 1 ? '品牌材料' : '瑞祥专供材料');

        });
    });

    function CutStr(str) {
        if (str.length >= 6) {
            str = '<span class="justify_span justify_span_w70" title="' + str + '">' + str.substring(0, 5) + '</span>';
        }
        else {
            str = '<span class="justify_span justify_span_w70">' + str + '</span>';
        }
        return str;
    }

</script>

<%--VUE2.0 开始--%>
<script>
    var editMatLevelBrandsVue = new Vue({
        el: '#editMatLevelBrands',
        data: {
            matLevelBrands: {}, // 档次 品牌项 数据
            mallBrand:{}, // 商城品牌
            firstBrandMainPhotos:{} // 第一个品牌主图
        },
        created() {
            this.buildMatLevelBrandsData();// 档次 品牌项 数据
        },
        methods: {
            // 档次 品牌项 参数
            buildMatLevelBrandsData: function () {
                var _this = this;
                var matID = $("#hidMatID").val();// 材料ID
                var firstFlag = $("#hidLevelBrandsFirstFlag").val();// B档第一个标识 1.为B档第一个;0.不为B档第一个.
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/level/brands',
                    type: 'GET',
                    dataType: 'json',
                    data: {matID,firstFlag},
                    success: function (res) {
                        // 作用域赋值
                        _this.matLevelBrands = res.body;
                        _this.mallBrand = res.body.mallBrand;
                        _this.firstBrandMainPhotos = res.body.firstBrandMainPhotos;

                        // 二段数据同步
                        $("div[data-matid="+matID+"] .lbl_material_brand_name").html(res.body.brandName);
                        $("div[data-matid="+matID+"] .lbl_material_brand_type").html(res.body.mBrandType);

                        // 型号信息
                        $("#ddlHost").val(res.body.mHostState);
                        var mMatType = res.body.mMatType;
                        $("#ddlMatType").val(mMatType == 0 ? 2 : mMatType);

                        // 小样
                        // 小样类型：需要，不需要  （默认1）
                        var sampleStatus = res.body.mSampleType;
                        if (sampleStatus == 1){
                            $(".switchcra-on").show();
                        } else {
                            $(".switchcra-off").show();
                        }

                        // 设置回路总积分 品牌积分 型号积分法 小样积分（2分满分）供货商积分
                        _this.matLevelBrands.totalLoopScroe = _this.matLevelBrands.brandInfoLoop + _this.matLevelBrands.typeInfoLoop + (_this.matLevelBrands.suppliersSize > 0 ? 1 : 0) ;

                    },
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>




