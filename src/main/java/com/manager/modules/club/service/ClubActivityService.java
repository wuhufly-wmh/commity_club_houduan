package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubActivityEntity;

import java.util.List;
import java.util.Map;

/**
 * 社团活动
 *
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
public interface ClubActivityService extends IService<ClubActivityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils select(Map<String, Object> params);

    void updateWithNum(ClubActivityEntity clubActivity);

    List<ClubActivityEntity> selectAll();

    PageUtils queryPageAll(Map<String, Object> params);
}

