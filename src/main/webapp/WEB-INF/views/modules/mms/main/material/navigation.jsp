<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div id="nav" class="j_leftnav nav-fixed" data-height="365">
    <div class="nav-wrapper">
        <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
            <div class="nav-wrapper-content" style="overflow: hidden; width: auto; height: 100%;">

                <div class="rolechange" style="display: block;">
                    <p class="col-md-12 Pacitve">材料</p>
                </div>

                <ul class="nav-ul clear ">
                    <li>
                        <a class="rippleria-dark" data-rippleria="" style="overflow: hidden; display: block;">
                            <div class="rippleria-wrap">
                                <div class="rippleria-wrap">
                                    <a href="javascript:" class="circlemark circlemark-lightRed">样</a>
                                    <em class="small-icon-logo small-icon-num15"></em>
                                    <strong>后台</strong>
                                    <i class="icon-down"></i>
                                </div>
                            </div>
                        </a>
                        <ul class="sub-2-nav" style="display: none;">
                            <li id="1" class="relative">
                                <a href="/Backstage/TreeManagerList" class="rippleria-dark" onclick="getMenu('2', 1, '1')">
                                    <div class="rippleria-wrap">
                                        <em class="icon-circle"></em>
                                        <strong>科目树</strong>
                                    </div>
                                </a>
                            </li>
                            <li id="2" class="relative">
                                <a href="/Backstage/TreeStandardList" class="rippleria-dark"



                                   onclick="getMenu('2', 1, '2')">
                                    <div class="rippleria-wrap">
                                        <em class="icon-circle"></em>
                                        <strong>母库</strong>
                                    </div>
                                </a>
                            </li>
                            <li id="3" class="relative">
                                <a href="/Backstage/UnitParameter" class="rippleria-dark"
                                   onclick="getMenu('2', 1, '3')">
                                    <div class="rippleria-wrap">
                                        <em class="icon-circle"></em>
                                        <strong>单位参数设置</strong>
                                    </div>
                                </a>
                            </li>
                            <li id="4" class="relative">
                                <a href="/Material/MaterialSynchronize" class="rippleria-dark"
                                   onclick="getMenu('2', 1, '4')">
                                    <div class="rippleria-wrap">
                                        <em class="icon-circle"></em>
                                        <strong>同步库</strong>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </li>
                    <li id="5" class="active">
                        <a href="/mat-web/index" data-rippleria="" class="rippleria-dark" onclick="getMenu('2', 1, '5')"
                           style="overflow: hidden; display: block;">
                            <div class="rippleria-wrap">
                                <em class="small-icon-logo small-icon-num5"></em>

                                <strong>子库</strong></div>
                        </a>
                    </li>
                    <li id="13">
                        <a href="http://s.wenes.com/Shopping/Index" target="_blank" data-rippleria=""
                           class="rippleria-dark" onclick="getMenu('2', 1, '13')"
                           style="overflow: hidden; display: block;">
                            <div class="rippleria-wrap"><em
                                    class="small-icon-logo small-icon-num9"></em><strong>平台</strong></div>
                        </a>
                    </li>
                </ul>

            </div>
            <div class="slimScrollBar"
                 style="background: rgb(0, 0, 0); width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 6px; z-index: 99; right: 1px; height: 729px;"></div>
            <div class="slimScrollRail"
                 style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
        </div>
    </div>
</div>

