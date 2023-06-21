package com.manager.modules.club.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.manager.common.utils.ShiroUtils;
import com.manager.modules.aca.entity.AcademyEntity;
import com.manager.modules.aca.service.AcademyService;
import com.manager.modules.club.entity.PieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubInfoDao;
import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.service.ClubInfoService;
import org.springframework.util.StringUtils;


@Service("clubInfoService")
public class ClubInfoServiceImpl extends ServiceImpl<ClubInfoDao, ClubInfoEntity> implements ClubInfoService {

    @Autowired
    private AcademyService academyService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String)params.get("key");
        Long acId = (Long) params.get("acId");
        IPage<ClubInfoEntity> page = this.page(
                new Query<ClubInfoEntity>().getPage(params),
                new QueryWrapper<ClubInfoEntity>()
                        .eq(acId != null, "academy_id", acId)
                        .like(StringUtils.hasText(name),"name", name)
        );

        return new PageUtils(page);
    }

    /**
     * 社团信息
     * @return
     */
    @Override
    public ClubInfoEntity getByUser() {
        return this.getOne(new LambdaQueryWrapper<ClubInfoEntity>().eq(ClubInfoEntity::getOwnerId, ShiroUtils.getUserId()));
    }

    @Override
    public PageUtils queryPageAc(Map<String, Object> params) {
        String name = (String)params.get("key");
        Long acId = ShiroUtils.getUserEntity().getAcId();
        IPage<ClubInfoEntity> page = this.page(
                new Query<ClubInfoEntity>().getPage(params),
                new QueryWrapper<ClubInfoEntity>()
                        .eq("status", 0)
                        .like(StringUtils.hasText(name),"name", name)
                        .eq("academy_id", acId)
        );

        return new PageUtils(page);

    }

    @Override
    public List<PieVo> pie() {
        return baseMapper.selectPie();
    }

    @Override
    public void saveInfo(ClubInfoEntity clubInfo) {
        clubInfo.setCreateTime(new Date());
        clubInfo.setAcademyId(ShiroUtils.getUserEntity().getAcId());
        AcademyEntity academy = academyService.getById(ShiroUtils.getUserEntity().getAcId());
        clubInfo.setAcademyName(academy.getLabel());
        this.save(clubInfo);
    }

}