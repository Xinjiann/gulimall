package com.xinjian.gulimall.order.dao;

import com.xinjian.gulimall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 03:09:21
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
