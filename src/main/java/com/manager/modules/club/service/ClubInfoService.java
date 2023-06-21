package com.manager.modules.club.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manager.common.utils.PageUtils;
import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.entity.PieVo;

import java.util.List;
import java.util.Map;

/**
 * 社团信息
 *
 * @author ""
 * @email ""
 * @date 2023-03-29 20:59:18
 */
public interface ClubInfoService extends IService<ClubInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    ClubInfoEntity getByUser();

    PageUtils queryPageAc(Map<String, Object> params);

    List<PieVo> pie();

    void saveInfo(ClubInfoEntity clubInfo);

}

