package com.manager.modules.aca.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.aca.dao.AcademyDao;
import com.manager.modules.aca.entity.AcademyEntity;
import com.manager.modules.aca.service.AcademyService;
import org.springframework.util.StringUtils;


@Service("academyService")
public class AcademyServiceImpl extends ServiceImpl<AcademyDao, AcademyEntity> implements AcademyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("key");
        IPage<AcademyEntity> page = this.page(
                new Query<AcademyEntity>().getPage(params),
                new QueryWrapper<AcademyEntity>()
                        .like(StringUtils.hasText(name), "label", name)
        );

        return new PageUtils(page);
    }

}