package com.xinjian.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.coupon.entity.HomeSubjectSpuEntity;

import java.util.Map;

/**
 * 专题商品
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:48:20
 */
public interface HomeSubjectSpuService extends IService<HomeSubjectSpuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

