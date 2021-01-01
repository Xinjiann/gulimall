package com.xinjian.gulimall.order.dao;

import com.xinjian.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 03:09:23
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
