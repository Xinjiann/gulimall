package com.xinjian.gulimall.product.dao;

import com.xinjian.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-24 15:01:43
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
