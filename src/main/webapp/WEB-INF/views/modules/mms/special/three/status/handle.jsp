<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="plr10">
    <div class="clearfix layerRtb-head pb10 mr10">
        <p class="fl"></p>
        <i class="fr rig_close">×</i>
    </div>
    <div class="layerRtb-scroll thinScroll overflow plr10">
        <div class="analyItem">
            <p class="analyItemTit tx-center">科目一</p>
            <div class="analyItemCon">
            </div>
        </div>
        <div class="analyItem">
            <p class="analyItemTit tx-center">科目二</p>
            <div class="analyItemCon">
            </div>
        </div>
        <div class="analyItem">
            <p class="analyItemTit tx-center">科目三</p>
            <div class="analyItemCon">
            </div>
        </div>
    </div>
    <div class="layerRtb-bottom borT-scroll plr10 pt10 pb5">
        <div class="analyItem">
            <p class="analyItemTit tx-center">综合</p>
            <div class="analyItemCon">
            </div>
        </div>
        <div class="clearfix">
            <div class="col-md-7">
                <h2 id="handle" class="uiTitle2 mb10 choice-item">
                    <i class="uiTitle-icon"></i>
                    处理
                    <span handtype="1" title="在输入框填写回访 提交即可"
                          class="choice-span choice-span3 mr0 fz12 choice-spanfirst cGreen">正常</span>
                    <span handtype="2" title="在输入框填写回访 提交即可"
                          class="choice-span choice-span3 mr0 fz12 choice-spanfirst cOrange">异常</span>
                    <span handtype="3" title="在输入框填写回访 提交即可"
                          class="choice-span choice-span3 mr0 fz12 choice-spanfirst cRed">问题</span>
                    <span handtype="4" title="在输入框填写回访 提交即可"
                          class="choice-span choice-span3 mr0 fz12 choice-spanfirst">下架</span>
                </h2>
                <div class="clearfix pb10 pr10 pt10"><textarea id="handleContent" class="evaluate-textarea"
                                                               style="width: 100%;height: 66px;padding: 4px 8px;"></textarea>
                </div>
            </div>
            <div class="col-md-5 fl operate-bottomDiv EventBanner operate-bottomR pt20 pl10">
                <div class="pt10 tx-center" style="padding: 96px 0px 10px;"><input type="button" value="提交"
                                                                                   id=""
                                                                                   class="uiBtn-normal uiBtn-blue  operate-submit">
                </div>
            </div>
        </div>
    </div>
</div>

