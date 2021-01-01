package com.xinjian.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:56:07
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

