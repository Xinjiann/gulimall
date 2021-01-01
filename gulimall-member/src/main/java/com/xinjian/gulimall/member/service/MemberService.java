package com.xinjian.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinjian.common.utils.PageUtils;
import com.xinjian.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author xinjianli
 * @email 963597661@qq.com
 * @date 2020-12-25 02:56:09
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

