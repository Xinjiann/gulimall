package com.xinjian.gulimall.coupon.dao;

import com.xinjian.gulimall.coupon.entity.CouponSpuRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:48:21
 */
@Mapper
public interface CouponSpuRelationDao extends BaseMapper<CouponSpuRelationEntity> {
	
}
