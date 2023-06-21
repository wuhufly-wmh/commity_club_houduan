package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubBudgetEntity;

import java.util.Map;

/**
 * 社团经费
 *
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
public interface ClubBudgetService extends IService<ClubBudgetEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils select(Map<String, Object> params);

    void updateWithNum(ClubBudgetEntity clubBudget);
}

