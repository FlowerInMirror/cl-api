package com.thinkgem.jeesite.modules.web.mms.web.common;

import com.thinkgem.jeesite.common.fsdf.FastDFSUtils;
import com.thinkgem.jeesite.common.web.Constants;
import com.wordnik.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 *
 * 材料管理系统-上传控制器
 * @author  ljc
 * @version 1.0
 * @createTime 2018-7-13 14:49:34
 */
@Controller
@RequestMapping(value = "mms_upload-web")
public class MMSUploadController {

    // 上传图片
    /**
     * @author ljc
     * @createTime 2018-8-11 13:58:38
     */
    @RequestMapping(value = "pics")
    @ApiOperation(value="MMS:上传图片",httpMethod="GET",notes="body:url 图片地址<br/>@author  ljc<br/>@version 2018-7-14 12:13:06<br/>")
    public void uploadFck(HttpServletRequest request, HttpServletResponse response) throws Exception{

        // 文件请求接收对象
        MultipartRequest mr = (MultipartRequest)request;

        // 上传文件Map
        Map<String, MultipartFile> fileMap = mr.getFileMap();

        // 图片集
        Set<Map.Entry<String, MultipartFile>> entrySet = fileMap.entrySet();

        // 遍历上传
        for (Map.Entry<String, MultipartFile> entry : entrySet) {

            // 上传图片
            MultipartFile pic = entry.getValue();

            //保存在分布式文件系统中
            String path = FastDFSUtils.uploadPic(pic.getBytes(), new Date().getTime() + pic.getOriginalFilename(), pic.getSize());

            // 响应上传文件路径
            JSONObject jo = new JSONObject();
            jo.put("url", Constants.IMG_URL + path);
            jo.put("error", 0);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(jo.toString());
        }
    }

}
