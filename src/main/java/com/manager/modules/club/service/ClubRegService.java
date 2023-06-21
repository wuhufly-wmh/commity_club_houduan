package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubRegEntity;

import java.util.Map;

/**
 * 注册申请
 *
 * @author ""
 * @email ""
 * @date 2023-03-30 17:12:27
 */
public interface ClubRegService extends IService<ClubRegEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveInfo(ClubRegEntity clubReg);

    void success(Long id);

    void reject(Long id);
}

