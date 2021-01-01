package com.xinjian.gulimall.coupon.dao;

import com.xinjian.gulimall.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:48:22
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
