<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/mms/commons/plug-in/taglib.jsp" %>
<style>
    .layerRtb{
        height:100%;}
</style>
<div class="tc-main">
    <div class="tc-center fl">
        <div class="clearfix">
            <div class="tc-center-tabW">
                <ul class="clearfix uiTab9 j_uiTab9">
                    <li class="uiTab9-active">状态</li>
                </ul>
            </div>
        </div>
        <div class="pr10">
            <%--状态--%>
            <div class="uiTab9Con" id="">
                <div class="analyItem">
                    <p class="analyItemTit tx-center">状态</p>
                    <div class="analyItemCon clearfix">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tc-right">
        <div class="tc-righttop-tab j-tc-righttop-tab">

        </div>
        <div class="tc-right-top clearfix">
            <div class="fl needdealt">
                <ul>
                    <li class="bgRed">待办一</li>
                    <li class="bgOrange">待办二</li>
                    <li class="bgGreen">待办三</li>
                </ul>
            </div>
            <!-- 待办弹窗S -->
            <div class="tc-taskDiv">
                <div class="clearfix">
                    <p class="fl">• 标题长度自行调整...</p>
                    <i class="fr rig_close">×</i>
                </div>
            </div>
            <!-- 待办弹窗E -->
            <div class="visitbox thinScroll">
                <%--<div class="visit" id="j-visit">--%>
                    <%--<div class="visi-list tx-left">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-name">营</span>--%>
                            <%--<span class="visi-date">07-19 14:55</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt">--%>
                            <%--主案设计师系统静态页面已经制作完毕，已经交付开发。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
               <%----%>
                <%--</div>--%>

            </div>
        </div>
        <div class="tc-right-bottom pa10 relative">
            <div class="tc-right-bottom-left relative">
                <ul class="topdaily">
                    <li>
                        <a class="topdailybtn" href="javascript:">预留</a>
                    </li>
                    <li>
                        <a class="topdailybtn" href="javascript:">预留</a>
                    </li>
                    <li>
                        <a class="topdailybtn" href="javascript:">预留</a>
                    </li>
                </ul>
                <div class="dailyrgt-botradio hide">
                    <div class="mb10">
                        <label class="uiRadio12 cGreen">
                            <input value="1" type="radio" name="a" class="uiRadio12-input">正常</label>
                        <label class="uiRadio12 cOrange">
                            <input value="2" type="radio" name="a" class="uiRadio12-input">异常</label>
                        <label class="uiRadio12 cRed">
                            <input value="3" type="radio" name="a" class="uiRadio12-input">问题</label>
                    </div>
                </div>
                <div class="dailyrgt-divZi">
                    <p class="tx-center">先解决业务问题，才有资格说管理。前两个因素完成之后，再反馈系统问题。</p>
                </div>
                <div class="visi-text-content hide">
                    <textarea placeholder="请输入回访内容" class="hf-textarea mb10"></textarea>
                    <input class="hf-submit" type="submit" value="提交" />
                </div>

            </div>
            <div class="EventpenaltyDiv">
                <a href="javascrpit:;" class="EventpenaltyBtn">预留</a>
                <a href="javascrpit:;" class="EventpenaltyBtn evaluateBtn">评价标签</a>
            </div>
        </div>

        <!--评价标签S-->
        <div class="layerRtb layerRtb-eval">
            <div class="plr10">
                <div class="clearfix layerRtb-head">
                    <p class="fl"></p>
                    <i class="fr rig_close">×</i>
                </div>
                <table class="uiTable mt10">
                    <tr>
                        <th width="15%">序号</th>
                        <th width="15%">12px</th>
                        <th width="15%">#333</th>
                        <th width="30%">背景颜色#f4f4f4 高度36px</th>
                        <th>操作</th>
                    </tr>
                </table>
                <div class="layerRtbEval-scroll">
                    <table class="uiTable">
                        <tr>
                            <td width="15%">1</td>
                            <td width="15%">12px</td>
                            <td width="15%">#666</td>
                            <td width="30%">背景颜色#ffff 高度34px</td>
                            <td>
                                <input type="button" value="编辑" class="uiBtn-blue uiBtn-small" />
                                <input type="button" value="删除" class="uiBtn-red uiBtn-small" />
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>12px</td>
                            <td>#666</td>
                            <td>背景颜色#ffff 高度34px</td>
                            <td>
                                <input type="button" value="编辑" class="uiBtn-blue uiBtn-small" />
                                <input type="button" value="删除" class="uiBtn-red uiBtn-small" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="layerRtb-foot">
                <h2 class="uiTitle2">
                    <i class="uiTitle-icon"></i>
                    评价标签
                </h2>
                <div class="pl10">
                    <div class="clearfix mt20">
                        <span class="fl lh28">类型：</span>
                        <select class="fl width120">
                            <option>薪酬反馈</option>
                        </select>
                    </div>
                    <div class="clearfix mt10">
                        <span class="fl lh28">标签：</span>
                        <input type="text" class="form-control width120 fl" />
                    </div>
                    <div class="clearfix mt10">
                        <span class="fl lh28">内容：</span>
                        <textarea class="layerRtb-foot-textarea form-control fl"></textarea>
                    </div>
                </div>
                <div class="tx-center mt10">
                    <input type="submit" class="hf-submit" />
                </div>
            </div>
        </div>
        <!--评价标签E-->
    </div>
</div>
<div class="tc-main hide" id="tc-main-city">
    <div class="tc-center fl">
        <div class="clearfix">
            <div class="tc-center-tabW">
                <ul class="clearfix uiTab9 j_uiTab9">
                    <li class="uiTab9-active">状态</li>
                    <li>标准</li>
                    <li>材料</li>
                    <li>位置</li>
                </ul>
            </div>
        </div>
        <div id="SampleTcCenter" class="pr10">
            <%--状态--%>
            <div class="uiTab9Con" id="SampleStatus"></div>
            <%--标准--%>
            <div class="uiTab9Con dis-none" id="SampleStandard"></div>
            <%--材料--%>
            <div class="uiTab9Con dis-none" id="SampleMaterial"></div>
            <%--位置--%>
            <div class="uiTab9Con dis-none" id="SamplePosition"></div>
        </div>
    </div>
    <div class="tc-right">
        <div class="tc-righttop-tab j-tc-righttop-tab">

        </div>
        <div class="tc-right-top clearfix">
            <%--<div class="fl needdealt">--%>
                <%--<ul>--%>
                    <%--<li class="bgRed">待办一</li>--%>
                    <%--<li class="bgOrange">待办二</li>--%>
                    <%--<li class="bgGreen">待办三</li>--%>
                <%--</ul>--%>
            <%--</div>--%>
            <%--<!-- 待办弹窗S -->--%>
            <%--<div class="tc-taskDiv">--%>
                <%--<div class="clearfix">--%>
                    <%--<p class="fl">• 标题长度自行调整...</p>--%>
                    <%--<i class="fr rig_close">×</i>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<!-- 待办弹窗E -->--%>
            <%--<div class="visitbox thinScroll">--%>
                <%--<div class="visit" id="j-visit">--%>
                    <%--<div class="visi-list tx-left">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-name">营</span>--%>
                            <%--<span class="visi-date">07-19 14:55</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt">--%>
                            <%--主案设计师系统静态页面已经制作完毕，已经交付开发。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<div class="visi-list tx-right">--%>
                        <%--<div class="visi-listtop">--%>
                            <%--<span class="visi-date mr5">07-19 15:55</span><span class="visi-name">团</span>--%>
                        <%--</div>--%>
                        <%--<p class="visi-listtxt cBlue">--%>
                            <%--今日新增任务：3个，均有必须近期前端制作；--%>
                            <%--<br>在执行任务：5个，均为正常状态；--%>
                            <%--<br>完成任务:2个,其中有个新人未提交验收，明天教一下系统怎么使用，另外一个验收合格。--%>
                        <%--</p>--%>
                    <%--</div>--%>
                    <%--<!----%>
                <%--作者：offline--%>
                <%--时间：2017-08-01--%>
                <%--描述--%>

            <%--<div class="visi-list tx-left">--%>
                <%--<div class="visi-listtop">--%>
                    <%--<span class="visi-name">师</span>--%>
                    <%--<span class="visi-date">07-19 17:55</span>--%>
                <%--</div>--%>
                <%--<p class="visi-listtxt cGreen">--%>
                        <%--1、查出的问题，解决方案是什么要明确，不要只说做了什么，要体现解决了什么！--%>
    <%--2、标准提案什么时候能测试通过交付一版？--%>
                <%--</p>--%>
            <%--</div>--%>

            <%--<div class="visi-list tx-left">--%>
                <%--<div class="visi-listtop">--%>
                    <%--<span class="visi-name">军</span>--%>
                    <%--<span class="visi-date">07-19 18:55</span>--%>
                <%--</div>--%>
                <%--<p class="visi-listtxt cOrange">--%>
                    <%--不要只有数量，具体说明下任务内容，新增的任务业务是否了解清晰？完成的任务验收指标和情况需明确--%>
                <%--</p>--%>
            <%--</div>--%>
               <%---->--%>
                <%--</div>--%>

            <%--</div>--%>
        </div>
        <div class="tc-right-bottom pa10 relative">
            <div class="tc-right-bottom-left relative">
                <ul class="topdaily">
                    <li>
                        <a class="topdailybtn" href="javascript:">预留</a>
                    </li>
                    <li>
                        <a class="topdailybtn" href="javascript:">预留</a>
                    </li>
                    <li>
                        <a class="topdailybtn" href="javascript:">预留</a>
                    </li>
                </ul>
                <div class="dailyrgt-botradio hide">
                    <div class="mb10">
                        <label class="uiRadio12 cGreen">
                            <input value="1" type="radio" name="a" class="uiRadio12-input">正常</label>
                        <label class="uiRadio12 cOrange">
                            <input value="2" type="radio" name="a" class="uiRadio12-input">异常</label>
                        <label class="uiRadio12 cRed">
                            <input value="3" type="radio" name="a" class="uiRadio12-input">问题</label>
                    </div>
                </div>
                <div class="dailyrgt-divZi">
                    <p class="tx-center">先解决业务问题，才有资格说管理。前两个因素完成之后，再反馈系统问题。</p>
                </div>
                <div class="visi-text-content hide">
                    <textarea placeholder="请输入回访内容" class="hf-textarea mb10"></textarea>
                    <input class="hf-submit" type="submit" value="提交" />
                </div>

            </div>
            <div class="EventpenaltyDiv">
                <a href="javascrpit:;" class="EventpenaltyBtn">预留</a>
                <a href="javascrpit:;" class="EventpenaltyBtn evaluateBtn">评价标签</a>
            </div>
        </div>

        <!--评价标签S-->
        <div class="layerRtb layerRtb-eval">
            <div class="plr10">
                <div class="clearfix layerRtb-head">
                    <p class="fl"></p>
                    <i class="fr rig_close">×</i>
                </div>
                <table class="uiTable mt10">
                    <tr>
                        <th width="15%">序号</th>
                        <th width="15%">12px</th>
                        <th width="15%">#333</th>
                        <th width="30%">背景颜色#f4f4f4 高度36px</th>
                        <th>操作</th>
                    </tr>
                </table>
                <div class="layerRtbEval-scroll">
                    <table class="uiTable">
                        <tr>
                            <td width="15%">1</td>
                            <td width="15%">12px</td>
                            <td width="15%">#666</td>
                            <td width="30%">背景颜色#ffff 高度34px</td>
                            <td>
                                <input type="button" value="编辑" class="uiBtn-blue uiBtn-small" />
                                <input type="button" value="删除" class="uiBtn-red uiBtn-small" />
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>12px</td>
                            <td>#666</td>
                            <td>背景颜色#ffff 高度34px</td>
                            <td>
                                <input type="button" value="编辑" class="uiBtn-blue uiBtn-small" />
                                <input type="button" value="删除" class="uiBtn-red uiBtn-small" />
                            </td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="layerRtb-foot">
                <h2 class="uiTitle2">
                    <i class="uiTitle-icon"></i>
                    评价标签
                </h2>
                <div class="pl10">
                    <div class="clearfix mt20">
                        <span class="fl lh28">类型：</span>
                        <select class="fl width120">
                            <option>薪酬反馈</option>
                        </select>
                    </div>
                    <div class="clearfix mt10">
                        <span class="fl lh28">标签：</span>
                        <input type="text" class="form-control width120 fl" />
                    </div>
                    <div class="clearfix mt10">
                        <span class="fl lh28">内容：</span>
                        <textarea class="layerRtb-foot-textarea form-control fl"></textarea>
                    </div>
                </div>
                <div class="tx-center mt10">
                    <input type="submit" class="hf-submit" />
                </div>
            </div>
        </div>
        <!--评价标签E-->
        <%--"=======================三段========================="--%>
        <!--三段弹出页面S-->
        <div class="layerRtb layerRtb-threecolumn hide positionAlert pa10">

        </div>
        <!--三段弹出页面E-->
        <!--删除列-->
        <div class="comfirmAlertLie hide pa20 fz14">
            <p class="mt20">是否确认删除此列？</p>
        </div>
        <!--删除层-->
        <div class="comfirmAlertCeng hide pa20 fz14">
            <p class="mt20">是否确认删除此层？</p>
        </div>
    </div>
</div>

