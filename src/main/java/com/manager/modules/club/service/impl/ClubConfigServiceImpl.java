package com.manager.modules.club.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubConfigDao;
import com.manager.modules.club.entity.ClubConfigEntity;
import com.manager.modules.club.service.ClubConfigService;


@Service("clubConfigService")
public class ClubConfigServiceImpl extends ServiceImpl<ClubConfigDao, ClubConfigEntity> implements ClubConfigService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClubConfigEntity> page = this.page(
                new Query<ClubConfigEntity>().getPage(params),
                new QueryWrapper<ClubConfigEntity>()
        );

        return new PageUtils(page);
    }

}