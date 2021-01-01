package com.xinjian.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 03:09:21
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

