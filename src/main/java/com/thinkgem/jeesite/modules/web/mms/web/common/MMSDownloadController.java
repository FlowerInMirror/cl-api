package com.thinkgem.jeesite.modules.web.mms.web.common;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 *
 * 材料管理系统-下载控制器
 * @author  ljc
 * @version 1.0
 * @createTime 2018-7-13 14:49:34
 */
@Controller
@RequestMapping(value = "mms_download-web")
public class MMSDownloadController extends BaseController {

    // ---------- 子库列表相关下载 begin  ----------

    /**
     * 打印软件与模版下载
     * @author      ljc
     * @createTime 2018-7-14 12:13:06
     */
    @RequestMapping(value = {"matreial/list/label_print/software_course"})
    @ApiOperation(value="子库列表:打印软件与模版下载",httpMethod="GET",notes="子库列表:打印软件与模版下载@author  ljc<br/>@version 2018-7-14 12:13:06<br/>")
    public void templetAndTutorialDownload (HttpServletRequest request, HttpServletResponse response) {

        StringBuilder stringBuilder = new StringBuilder();
        String path = null;

        // 1. 获取项目根路径
        String projectBasePath = request.getSession().getServletContext().getRealPath("/");

        // 2. 硬解码 目录&文件名称
        String mmsFileBaseDir = Global.getConfig("mms.file.basedir"); // 系统文件基础路径
        String fileName = Global.getConfig("mms.sublibrary.list.filename"); // 子库列表下载文件名称

        // 3. 设值下载文件路径
        path = stringBuilder.append(projectBasePath).append(mmsFileBaseDir).append("/").append(fileName).toString();

        // 4. 向浏览器发送文件下载，支持断点续传
        FileUtils.downFile(new File(path),request,response,"打印软件与教程.zip");
    }

}
