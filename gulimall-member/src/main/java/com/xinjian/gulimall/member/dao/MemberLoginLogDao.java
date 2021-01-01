package com.xinjian.gulimall.member.dao;

import com.xinjian.gulimall.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:56:07
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
