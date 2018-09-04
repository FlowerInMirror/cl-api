package com.thinkgem.jeesite.common.fsdf;

import org.apache.commons.io.FilenameUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.util.Date;

/**
 * FastDFSUtils 工具类
 * @author ljc
 * @createTime 2018-8-11 13:46:36
 */
public class FastDFSUtils {

	//上传
    /**
     * @author ljc
     * @param  pic    图片字节组
     * @param  name   原始文件名
     * @param  size   文件大小
     * @return String 上传文件路径
     * @createTime 2018-8-11 13:54:35
     */
	public static String uploadPic(byte[] pic,String name,long size) throws Exception{
		
		ClassPathResource resource = new ClassPathResource("fdfs_client.conf");
		//配置文件
		ClientGlobal.init(resource.getClassLoader().getResource("fdfs_client.conf").getPath());
		//创建Tracker客户端
		TrackerClient trackerClient = new TrackerClient();
		//连接tracker (地址：保存Storage的地址）
		TrackerServer trackerServer = trackerClient.getConnection();
		
		StorageServer storageServer = null;
		//创建Storage客户端
		StorageClient1 storageClient1 = new StorageClient1(trackerServer,storageServer);
		//上传图片
		String ext = FilenameUtils.getExtension(name);
		
		NameValuePair[] meta_list = new NameValuePair[3];
		meta_list[0] = new NameValuePair("filename",name);
		meta_list[1] = new NameValuePair("fileext",ext);
		meta_list[2] = new NameValuePair("filesize",String.valueOf(size));
		
		String path = storageClient1.upload_file1(pic, ext, meta_list);

		// 处理图片名称
        path = path.substring(path.indexOf("/",path.indexOf("/") + 1)).replace("/","");
        return path;
	}
}
