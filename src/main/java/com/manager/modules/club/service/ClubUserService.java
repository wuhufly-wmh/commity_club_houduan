package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 社团成员
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
public interface ClubUserService extends IService<ClubUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils select(Map<String, Object> params);

    void saveInfo(ClubUserEntity clubUser);

    void removeWithNum(List<Long> asList);
}

