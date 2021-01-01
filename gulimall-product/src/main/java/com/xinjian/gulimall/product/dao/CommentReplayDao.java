package com.xinjian.gulimall.product.dao;

import com.xinjian.gulimall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-24 15:01:42
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
