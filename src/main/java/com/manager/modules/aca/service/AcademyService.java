package com.manager.modules.aca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.aca.entity.AcademyEntity;

import java.util.Map;

/**
 * 学院
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 10:14:35
 */
public interface AcademyService extends IService<AcademyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

