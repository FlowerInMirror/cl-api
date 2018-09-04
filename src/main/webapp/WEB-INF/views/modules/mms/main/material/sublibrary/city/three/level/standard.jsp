<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>

<!--标准 开始-->
<div id="editMatLevelSta" class="pb10" v-cloak >
    <div class="info_tit clearfix ViewBoxCha">
        <h2 class="uiTitle2 personnelDetail-title fl slight_blue ml10">
            <i class="uiTitle-icon"></i><span class="ml10">质量标准</span>
        </h2>
    </div>
    <div class="">

        <div class="materialpicture materialTopNavConm">
            <div class=" pt10 plr10">
                <%--档次质量标准遍历 开始--%>
                <div v-for="(levelStaItem, index) in matLevelSta.levelStaItems" class="analyItem" :data-tsiid="levelStaItem.tsiId" :data-parent="levelStaItem.tsiParentid" :data-level="levelStaItem.tsiMatlevel">
                    <p v-show="levelStaItem.tsiParentid != '00000000-0000-0000-0000-000000000000'" class="analyItemTit tx-center">统一质量标准</p>
                    <p v-show="levelStaItem.tsiParentid == '00000000-0000-0000-0000-000000000000'" class="analyItemTit tx-center">{{levelStaItem.tsiMatlevel | levelFlagTOABC(levelStaItem.tsiMatlevel)}}档质量标准</p>
                    <div class="analyItemCon relative">
                        <div class="analyItemConText">
                        <p class="info-look">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80" :title="levelStaItem.tsiName">{{levelStaItem.tsiName == null || levelStaItem.tsiName == '' ? '--' : levelStaItem.tsiName | convertOverlengthStrings(5)}}</span>
                            <span class="fl" style="width:80%">
                                <span>{{levelStaItem.tsiValue == null ? '--' : levelStaItem.tsiValue}} {{levelStaItem.tsiUnitname}}</span>
                                <span v-show="levelStaItem.tsiStandardmin != null || levelStaItem.tsiStandardmax != null" >合格范围({{levelStaItem.tsiUnitname == ''? '-' : levelStaItem.tsiUnitname}}):{{levelStaItem.tsiStandardmin != null ? levelStaItem.tsiStandardmin : '∞'}}～{{levelStaItem.tsiStandardmax != null ? levelStaItem.tsiStandardmax : '∞'}}</span>
                            </span>
                        </p>
                        <p class="info-edit hide">
                            <span class="cLightGray pr8 justify_span justify_span_w80">
                                {{levelStaItem.tsiName | convertOverlengthStrings(5)}}
                                <input type="text" class="width80 txtStandardName hide" :value="levelStaItem.tsiName" />
                            </span>
                            <input type="text" class="width60 txtStandardValue" :value="levelStaItem.tsiValue" data-input="true" />
                            <span class="pr5">{{levelStaItem.tsiUnitname}}</span>
                            <select class="ddlStandardUnit mt3f hide">
                                <option selected="" :value="levelStaItem.tsiUnit">{{levelStaItem.tsiUnitname}}</option>
                            </select>
                            <span>（合格范围：<span><input type="text" class="width60 ddlStandardMIN" :value="levelStaItem.tsiStandardmin" /></span> - <span><input type="text" class="width60 ddlStandardMAX" :value="levelStaItem.tsiStandardmax" /></span>）</span>
                            <select class="ddlStandardMatLevel mt3f hide">
                                <option selected="" :value="levelStaItem.tsiMatlevel">??</option>
                            </select>
                        </p>
                        <p class="clearfix mt5 ">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">{{levelStaItem.tsiExteriorname}}</span>
                            <span class="fl" style="width:80%">{{levelStaItem.tsiSpecialclaim}}</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80"><input type="text" class="width80 fl txtExteriorName" :value="levelStaItem.tsiExteriorname" placeholder="检验方法一" data-input="false"></span>
                            <input type="text" class="width360 fl txtStandardPC" data-input="false" :value="levelStaItem.tsiSpecialclaim" placeholder="">
                        </p>
                        <p class="clearfix mt5 ">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80">{{levelStaItem.tsiExteriorsc}}</span>
                            <span class="fl" style="width:80%">{{levelStaItem.tsiDetectmethod}}</span>
                        </p>
                        <p class="clearfix mt5 hide">
                            <span class="cLightGray pr8 fl justify_span justify_span_w80"><input type="text" class="width80 fl txtExteriorSC" :value="levelStaItem.tsiExteriorsc" placeholder="检验方法二" data-input="false"></span>
                            <textarea rows="3" class="width360 fl txtStandardDM" data-input="false"></textarea>
                        </p>

                        <div class="dis-il-block imgbox imgbox1">
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}" />
                        </div>
                        <div class="dis-il-block imgbox  hide imgbox2">
                            <img class="save_img btnSaveStandardDetectNode" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}" />
                        </div>

                        <%--回路积分项--%>
                        <div class="judge-div">
                            <input type="text" :class="levelStaItem.loopStatus == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                            <input type="text" :class="levelStaItem.loopStatus == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                            <strong class="fl score cGreen fz14 pl5">{{levelStaItem.loopStatus}}分</strong>
                        </div>
                    </div>
                    </div>
                </div>

                <%--为空展示>质量标准--%>
                <div v-show="matLevelSta.levelStaItems == ''" class="analyItem">
                    <p  class="analyItemTit tx-center">质量标准</p>
                    <div class="analyItemCon relative">
                        无
                    </div>
                </div>

                <%--对比图遍历 开始--%>
                <div v-for="(contrastItem, index) in matLevelSta.contrastItems" class="analyItem">
                    <p class="analyItemTit tx-center">对比图{{index + 1 | numToStr(index + 1)}}</p>
                    <div class="analyItemCon relative">
                        <p class="cRed lh20"></p>
                        <ul class="prodiagram clearfix">

                            <%--有图--%>
                            <li v-show="contrastItem.mcaPhotoURL != '' && contrastItem.mcaPhotoURL != null" :data-tspid="contrastItem.tspID" data-type="3" :data-paraid="contrastItem.mcaID" :data-matlevel="contrastItem.levelFlag">
                                <h4 class="h4title mb10">{{contrastItem.mcaName}}</h4>
                                <!--左侧图片-->
                                <div class="fl brandlogopic_new">
                                    <img :src="contrastItem.mcaPhotoURL" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="jvascript:" class="upagain_link"><input type="file" accept=".png,.jpg,.jpeg,image/png,image/jpg,image/jpeg" class="fileone"></a>
                                                <a href="javascript:" class="enlarge_link" :data-src="contrastItem.mcaPhotoURL" data-imgname=""></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                                <!--右侧图片-->
                                <div class="f2 brandlogopic_new" :title="'参数：'+ contrastItem.mcaPara + '\n描述：' + contrastItem.mcaDescribe">
                                    <img :src="contrastItem.consultPhotoURL == null ? '${ctxStatic}/images/pic/zan_nopic.png' : contrastItem.consultPhotoURL" />
                                    <!--图片遮罩和放大按钮 开始-->
                                    <div class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="javascript:" class="enlarge_link" :data-src="contrastItem.consultPhotoURL" :data-imgname="'参数：' + contrastItem.mcaPara + '；描述：' + contrastItem.mcaDescribe"></a>
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>
                                <!--参数/描述-->
                                <div class="prodiagramOpera_new relative pt5">
                                    <div class="mr10">
                                        <div class="clearfix mb10">
                                            <span class="fl lh28 width50">参数</span>
                                            <div class="ml50 lh28 overflowhid info-look">
                                                {{contrastItem.mcaPara}}
                                            </div>
                                            <div class="ml50 overflowhid info-edit hide">
                                                <input type="text" class="col-md-12 txtTitle" :value="contrastItem.mcaPara" :zz-old="contrastItem.mcaPara" />
                                            </div>
                                        </div>
                                        <div class="clearfix">
                                            <span class="fl lh28 width50">描述</span>
                                            <div class="ml50 lh28 overflowhid info-look prodiagramOperaLeft_new_cont">
                                                {{contrastItem.mcaDescribe}}
                                            </div>
                                            <div class="ml50 overflowhid info-edit hide">
                                                <textarea name="" rows="4" class="col-md-12 h52 txtContent" :zz-old="contrastItem.mcaDescribe">{{contrastItem.mcaDescribe}}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>

                            <%--无图--%>
                            <li v-show="contrastItem.mcaPhotoURL == '' || contrastItem.mcaPhotoURL == null" data-type="3" :data-paraid="contrastItem.mcaID" :data-matlevel="contrastItem.levelFlag">
                                <h4 class="h4title mb10">{{contrastItem.mcaName}}</h4>

                                <!-- 左侧图片 -->
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
                                            </div>
                                        </div>
                                    </div>
                                    <!--图片遮罩和放大按钮 结束-->
                                </div>

                                <!--右侧图片-->
                                <div class="f2 brandlogopic_new" :title="'参数：'+ contrastItem.mcaPara + '\n描述：' + contrastItem.mcaDescribe">
                                    <img :src="contrastItem.consultPhotoURL == null ? '${ctxStatic}/images/pic/zan_nopic.png' : contrastItem.consultPhotoURL" />
                                    <div   class="uiImgUpload-mark">
                                        <div class="uiImgUpload-mark-bg"></div>
                                        <div class="uiImgUpload-mark-link">
                                            <div class="dis_il_block">
                                                <a href="javascript:" class="enlarge_link" :data-src="contrastItem.consultPhotoURL" :data-imgname="'参数：' + contrastItem.mcaPara + '；描述：' + contrastItem.mcaDescribe"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!--中部图片参数-->
                                <div class="prodiagramOpera_new relative pt5">
                                    <div class="prodiagramOperaLeft_new">
                                        <div class="clearfix mb10">
                                            <span class="fl lh28 width50">参数</span>
                                            <div class="ml50 lh28 overflowhid info-look">
                                                --
                                            </div>
                                            <div class="ml50 overflowhid info-edit hide">
                                                <input type="text" class="col-md-12 txtTitle" value="" />
                                            </div>
                                        </div>
                                        <div class="clearfix">
                                            <span class="fl lh28 width50">描述</span>
                                            <div class="ml50 lh28 overflowhid info-look">
                                                --
                                            </div>
                                            <div class="ml50 overflowhid info-edit hide">
                                                <textarea name="" rows="4" class="col-md-12 h52 txtContent"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </li>

                            <%--下侧回路标识--%>
                            <div class="clearfix" >
                                <div class="fr">
                                <input type="text" :class="contrastItem.loopStatus == 1 ? 'fl judge-span judge-yes active' : 'fl judge-span judge-yes'">
                                <input type="text" :class="contrastItem.loopStatus == 0 ? 'fl judge-span judge-no active' : 'fl judge-span judge-no'">
                                <strong class="fl score cGreen fz14 pl5">{{contrastItem.loopStatus}}分</strong>
                                </div>
                            </div>
                        </ul>

                        <div class="dis-il-block imgbox imgbox1">
                            <img class="img_edit" src="${ctxStatic}/images/pic/edit_img.png?${verStatic}" />
                        </div>
                        <div class="dis-il-block imgbox hide imgbox2">
                            <img class="save_img" src="${ctxStatic}/images/pic/save_img.png?${verStatic}" id="btnSavePhotoToCompared" />
                            <img class="goback_img" src="${ctxStatic}/images/pic/goback_img.png?${verStatic}" />
                        </div>
                    </div>
                </div>

                <%--为空展示>对比图--%>
                <div v-show="matLevelSta.contrastItems == ''" class="analyItem">
                    <p class="analyItemTit tx-center">对比图</p>
                    <div class="analyItemCon relative">
                        无
                    </div>
                </div>

            </div>
        </div>
    </div>

    <%--综合项--%>
    <div class="divMaterialLoadAlert_bottom pt10 plr10 ViewBoxCha boldTopLine">
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
                <p class="fl col-md-8 cRed">
                    <span class="cLightGray pr8 cRed">完成所有质量标准属性值录入/完成所有对比项图片上传,对比图片描述录入 | 注：质量标准全国通用 （图片要求：尺寸300*300px；大小：2M以内；格式：jpg；）</span>
                    <strong class="plr5 cRed"></strong>
                </p>
                <p class="fl col-md-4"><span class="cLightGray pr8">得分</span><strong class="fz14 cGreen">{{matLevelSta.totalScore}}分</strong></p>
                <a v-show="matLevelSta.levelStaLoopStatus == 1" href="javascript:" class="circlemark circlemark-green">完</a>
                <a v-show="matLevelSta.levelStaLoopStatus == 0" href="javascript:" class="circlemark circlemark-lightRed">未</a>
            </div>
        </div>
    </div>

    <!--标准 结束-->
    <input type="hidden" id="hidTSIID" />
    <input type="hidden" id="hidTSIType" value="1" />
</div>

<%--VUE2.0 开始--%>
<script>
    var editMatLevelStaVue = new Vue({
        el: '#editMatLevelSta',
        data: {
            matLevelSta: {}// 材料基础信息
        },
        created() {
            this.buildMatLevelStaData();// 档次 标准 数据
        },
        filters: {
        },
        methods: {
            // 档次 标准 参数
            buildMatLevelStaData: function () {
                var _this = this;
                var treeFourID = $("#hidTreeID").val();
                var cityID = $("#hidCityID").val();
                var levelFlag = $("#hidMatLevel").val();
                $.ajax({
                    url: basePath + '/sublibrary-api/city_three_section/level/standard',
                    type: 'GET',
                    dataType: 'json',
                    data: {treeFourID,cityID,levelFlag},
                    success: function (res) {
                        _this.matLevelSta = res.body;
                    },
                    error: function (err) {alert("操作出错！");}
                });
            }
        }
    });
</script>



<script>
    //清空数据
    function ClearStandard() {
    }
    $(function () {
        setTimeout(function () {
            initImageUpload();//绑定上传
            PhotoRelated();//绑定事件'
        },250);
        $(document).on("click", ".img_edit", function () {
            var divObj = $(this).closest("div.analyItem");
            var tsiID = $(divObj).data("tsiid");
            if (tsiID) {
                $("#hidTSIID").val(tsiID);
            }
            var unit = $(divObj).attr("zz-unit");
            if (unit) {
                $(divObj).find(".ddlStandardUnit").val(unit);
            }
            var blevel = $(divObj).attr("zz-blevel");
            if (blevel) {
                $(divObj).find(".ddlStandardMatLevel").val(blevel);
            }

            $(divObj).find(".info-look").hide();
            $(divObj).find(".info-edit").show();

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
        $(document).on("click", ".img_add", function () {
            $(".divNewStandardItem").css("display","table");
        });
        //滚动条高度计算
        var theight = $("body").height() - $(".uiTitle2 ").height() - $(".divMaterialLoadAlert_bottom mb20").outerHeight() - 120;
        $(".divMaterialLoadAlert_topm ").height(theight).slimScroll({
            height: theight,
            borderRadius: "0px"
        });
    });
</script>