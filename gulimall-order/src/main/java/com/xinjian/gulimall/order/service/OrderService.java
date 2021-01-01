package com.xinjian.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 03:09:23
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

