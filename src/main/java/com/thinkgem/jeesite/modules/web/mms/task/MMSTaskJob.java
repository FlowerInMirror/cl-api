package com.thinkgem.jeesite.modules.web.mms.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.web.Constants;
import com.thinkgem.jeesite.modules.api.jmat.dao.SublibraryDao;
import com.thinkgem.jeesite.modules.api.jmat.pojo.material.sublibrary.list.QGMatLoopList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 材料管理系统任务作业
 * @author ljc
 * @createTime 2018-8-5 19:09:40
 */
@Service("mmsTaskJob")
@Transactional(readOnly = true)
public class MMSTaskJob {
    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * REDIS 键前缀
     */
    public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix");

    /**
     * 平台-子库
     */
    @Autowired
    private SublibraryDao sublibraryDao;

    /**
     * 子库列表:设置全国回路材料列表到Redis缓存
     * @author     ljc
     * @createTime 2018-8-5 19:11:55
     * @cron 从1日开始每1天执行一次
     */
    public void setQGLoopMatListCache() throws JsonProcessingException {
        long startTime = System.currentTimeMillis();
        // 未命中  从数据库获取子库起始页一段列表
        List<QGMatLoopList> qgLoopMatList = sublibraryDao.getQGLoopMatList();
        // 保存    Redis中缓存并设置保存时限三天
        JedisUtils.set(KEY_PREFIX + Constants.QG_LOOP_MAT_LIST, new ObjectMapper().writeValueAsString(qgLoopMatList),60 * 60 * 24 * 3);
        long endTime = System.currentTimeMillis();
        logger.debug("子库列表:设置全国回路材料列表到Redis缓存,程序运行时间"+ (endTime - startTime) + "ms");
    }

    /**
     * 测试定时器任务
     * @author ljc
     * @createTime 2018-8-5 19:30:42
     */
    public void testJob() {
        logger.debug("测试任务开始了...一秒执行一次");
    }

}
