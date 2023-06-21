package com.manager.modules.club.service.impl;

import com.manager.common.utils.ShiroUtils;
import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.service.ClubInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubBudgetDao;
import com.manager.modules.club.entity.ClubBudgetEntity;
import com.manager.modules.club.service.ClubBudgetService;
import org.springframework.util.StringUtils;


@Service("clubBudgetService")
public class ClubBudgetServiceImpl extends ServiceImpl<ClubBudgetDao, ClubBudgetEntity> implements ClubBudgetService {

    @Autowired
    private ClubInfoService clubInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String clubName = (String) params.get("key");
        IPage<ClubBudgetEntity> page = baseMapper.page(
                new Query<ClubBudgetEntity>().getPage(params)
                , clubName, ShiroUtils.getUserEntity().getAcId()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils select(Map<String, Object> params) {
        IPage<ClubBudgetEntity> page = new Query<ClubBudgetEntity>().getPage(params);
        IPage<ClubBudgetEntity> pages = baseMapper.select(page, ShiroUtils.getUserId());
        return new PageUtils(pages);
    }

    @Override
    public void updateWithNum(ClubBudgetEntity clubBudget) {
        this.updateById(clubBudget);
        clubBudget = this.getById(clubBudget.getId());
        if (clubBudget.getStatus() == 1) {
            // 审批通过
            ClubInfoEntity clubInfo = clubInfoService.getById(clubBudget.getClubId());
            clubInfo.setBudget(clubInfo.getBudget().add(clubBudget.getPrice()));
            clubInfoService.updateById(clubInfo);

        }
    }

}