package com.manager.modules.club.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.manager.common.utils.ShiroUtils;
import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.service.ClubInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubActivityDao;
import com.manager.modules.club.entity.ClubActivityEntity;
import com.manager.modules.club.service.ClubActivityService;


@Service("clubActivityService")
public class ClubActivityServiceImpl extends ServiceImpl<ClubActivityDao, ClubActivityEntity> implements ClubActivityService {

    @Autowired
    private ClubInfoService clubInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("key");
        Long acId = ShiroUtils.getUserEntity().getAcId();
        IPage<ClubActivityEntity> page = baseMapper.page(
                new Query<ClubActivityEntity>().getPage(params),
                name,acId

        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils select(Map<String, Object> params) {
        IPage<ClubActivityEntity> iPage = new Query<ClubActivityEntity>().getPage(params);
        IPage<ClubActivityEntity> page = baseMapper.select(iPage, ShiroUtils.getUserId());
        return new PageUtils(page);

    }

    @Override
    public void updateWithNum(ClubActivityEntity clubActivity) {
        this.updateById(clubActivity);
        if (clubActivity.getStatus() == 1) {
            // 同意，活动次数+1
            ClubInfoEntity clubInfo = clubInfoService.getById(clubActivity.getClubId());
            clubInfo.setAcNum(clubInfo.getAcNum() + 1);
            clubInfoService.updateById(clubInfo);
        }
    }

    @Override
    public List<ClubActivityEntity> selectAll() {
        return baseMapper.selectAll(ShiroUtils.getUserId());
    }

    @Override
    public PageUtils queryPageAll(Map<String, Object> params) {
        String name = (String)params.get("key");
        IPage<ClubActivityEntity> page = baseMapper.pageAll(
                new Query<ClubActivityEntity>().getPage(params),
                name

        );

        return new PageUtils(page);
    }

}