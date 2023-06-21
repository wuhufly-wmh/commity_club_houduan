package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubConfigEntity;

import java.util.Map;

/**
 * 社团注册/审核控制
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
public interface ClubConfigService extends IService<ClubConfigEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

