package com.xinjian.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-24 15:01:43
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();
}

