<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div id="main_header" class="header-fixed j_outerHeight">
    <h1 id="logo" class="j_leftnav"><a href="${basePath}/public-web/to/index"><i></i><span>${fns:getConfig('productName')}</span></a></h1>
    <!--顶部导航 开始-->
    <div id="topnav" class="clear">
        <ul class="fl">
            <li>
                <a href="javascript:;" data-rippleria="" class="setup_nav setupbtn rippleria-dark" style="overflow: hidden; display: block;">
                    <div class="rippleria-wrap">
                        <i></i>
                    </div>
                </a>
            </li>
            <li class="dropdown">
                <a href="javascript:;" data-rippleria="" class="setup_layout setupbtn rippleria-dark" style="overflow: hidden; display: block;">
                    <div class="rippleria-wrap">
                        <i></i>
                    </div>
                </a>
                <div class="dropdown-menu">
                    <i class="dropdown-adorn"></i>
                    <div class="panel-heading">用户选项</div>
                    <div class="list-group">
                        <div class="list-group-item">
                            <p>菜单样式</p>
                            <div class="radio-group">
                                <label class="setup-layout-lable" data-direction="left">
                                    <input type="radio" name="direction" checked="checked">左侧
                                </label>
                                <label class="setup-layout-lable" data-direction="top">
                                    <input type="radio" name="direction">上面
                                </label>
                            </div>
                        </div>
                        <div class="list-group-item" id="j_fixed_all">
                            <p>导航样式</p>
                            <div class="radio-group fixed-check" id="j-fixed-header" data-check="true"><i></i>固定头部</div>
                            <div class="radio-group fixed-check" id="j-fixed-nav" data-check="true"><i></i>固定导航菜单</div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="dropdown">
                <a href="javascript:;" data-rippleria="" class="setup_skin setupbtn rippleria-dark" style="overflow: hidden; display: block;">
                    <div class="rippleria-wrap">
                        <i></i>
                    </div>
                </a>
                <div class="dropdown-menu">
                    <i class="dropdown-adorn"></i>
                    <div class="panel-heading">颜色应用</div>
                    <div class="panel-body">
                        <div class="row clear">
                            <div class="col-md-4 col-item-header relative fl">
                                <span class="skin-item logo-bg-c000000 active"></span>
                                <span class="skin-item logo-bg-c009688"></span>
                                <span class="skin-item logo-bg-c8bc34a"></span>
                                <span class="skin-item logo-bg-c00bcd4"></span>
                                <span class="skin-item logo-bg-cffca28"></span>
                                <span class="skin-item logo-bg-cf44336"></span>
                            </div>
                            <div class="col-md-4 col-item-nav relative fl">
                                <span class="skin-item bar-bg-cffffff"></span>
                                <span class="skin-item bar-bg-c009688"></span>
                                <span class="skin-item bar-bg-c8bc34a"></span>
                                <span class="skin-item bar-bg-c00bcd4"></span>
                                <span class="skin-item bar-bg-cffca28"></span>
                                <span class="skin-item bar-bg-cf44336"></span>
                            </div>
                            <div class="col-md-4 col-item-bar relative fl">
                                <span class="skin-item header-bg-c000000"></span>
                                <span class="skin-item header-bg-c009688"></span>
                                <span class="skin-item header-bg-c8bc34a"></span>
                                <span class="skin-item header-bg-c00bcd4"></span>
                                <span class="skin-item header-bg-cffca28"></span>
                                <span class="skin-item header-bg-cf44336"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <div class="user-div">
            <%--当前登录用户信息--%>
            <a href="javascript:;"><i></i><span class="mr8">-</span><span class="mr4">${user.bumenName}</span>·<span class="mr4 ml4">${user.xinming}</span>-<span class="ml4">${user.cardNumber}</span></a>
        </div>
        <div class="eventDiv fr hide" data-value="1"><i class="fl"></i><span class="fl ml5">事件执行</span><span class="fl ml5 eventCount">0</span></div>
        <div class="eventDiv fr hide" data-value="2"><i class="fl"></i><span class="fl ml5 s_tit">池子</span></div>
        <div class="custom-remark dis-il-block ml50 mr50 fr">
            <p></p>
        </div>
    </div>
    <!--顶部导航 结束-->
</div>