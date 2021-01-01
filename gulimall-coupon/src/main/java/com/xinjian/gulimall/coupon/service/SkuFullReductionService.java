package com.xinjian.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:48:17
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

