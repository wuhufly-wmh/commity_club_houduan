package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubExamEntity;

import java.util.Map;

/**
 * 注册申请
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 17:12:27
 */
public interface ClubExamService extends IService<ClubExamEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveInfo(ClubExamEntity clubExam);

    PageUtils select(Map<String, Object> params);
}

