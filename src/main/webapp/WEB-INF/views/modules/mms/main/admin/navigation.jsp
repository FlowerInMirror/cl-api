<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"  %>
<div id="nav" class="j_leftnav nav-fixed" data-height="365">
    <div class="nav-wrapper">
        <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
            <div class="nav-wrapper-content" style="overflow: hidden; width: auto; height: 100%;">
                <%--主导航标题--%>
                <div class="rolechange" style="display: block;">
                    <p class="col-md-12 Pacitve">材料</p>
                </div>

                <%--材料导航内容--%>
                <ul class="nav-ul clear" style="display: block;">
                    <%--子库--%>
                    <li id="1">
                        <a href="${basePath}/mat-web/index" data-rippleria="" class="rippleria-dark" onclick="getMenu('2', 0, '1')" style="overflow: hidden; display: block;">
                            <div class="rippleria-wrap">
                                <em style="margin-right: 20px">库</em>
                                <strong>子库</strong>
                            </div>
                        </a>
                    </li>
                    <%--主营后台--%>
                    <li id="2">
                        <a href="${basePath}/mat-web/major"  data-rippleria="" class="rippleria-dark" onclick="getMenu('2', 0, '2')" style="overflow: hidden; display: block;">
                            <div class="rippleria-wrap">
                                <em style="margin-right: 20px">主</em>
                                <strong>主营管理</strong></div>
                        </a>
                    </li>
                    <%--小样管理--%>
                    <li id="3" >
                        <a  href="${basePath}/mat-web/sample" data-rippleria class="rippleria-dark" onclick="getMenu('2', 0, '3')">
                            <em style="margin-right: 20px">样</em>
                            <strong>小样管理</strong>
                        </a>
                    </li>
                    <%--老材料--%>
                    <li id="4" >
                        <a target="_blank" href="http://mat.gc.rx/Login/JumpLogin?CardNo=${user.cardNumber}&PassWord=${user.ciphertext}&jump=1" data-rippleria class="rippleria-dark" onclick="getMenu('2', 0, '4')">
                            <em style="margin-right: 20px">旧</em>
                            <strong>OLD材料</strong>
                        </a>
                    </li>
                </ul>

            </div>
            <div class="slimScrollBar" style="background: rgb(206, 206, 206); width: 2px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 6px; z-index: 99; right: 1px; height: 729px;"></div>
            <div class="slimScrollRail" style="width: 2px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
        </div>
    </div>
</div>