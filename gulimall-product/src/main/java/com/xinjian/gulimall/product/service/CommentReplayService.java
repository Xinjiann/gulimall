package com.xinjian.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-24 15:01:42
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

