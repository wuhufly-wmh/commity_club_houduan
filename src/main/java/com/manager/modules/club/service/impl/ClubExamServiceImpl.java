package com.manager.modules.club.service.impl;

import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.service.ClubInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubExamDao;
import com.manager.modules.club.entity.ClubExamEntity;
import com.manager.modules.club.service.ClubExamService;
import org.springframework.util.StringUtils;


@Service("clubExamService")
public class ClubExamServiceImpl extends ServiceImpl<ClubExamDao, ClubExamEntity> implements ClubExamService {

    @Autowired
    private ClubInfoService clubInfoService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String clubName = (String) params.get("key");
        IPage<ClubExamEntity> page = this.page(
                new Query<ClubExamEntity>().getPage(params),
                new QueryWrapper<ClubExamEntity>()
                        .like(StringUtils.hasText(clubName),"club_name",clubName)
        );

        return new PageUtils(page);
    }

    @Override
    public void saveInfo(ClubExamEntity clubExam) {
        ClubExamEntity club = baseMapper.selectByYear(clubExam.getClubId());
        if (club != null) {
            club.setUrl(clubExam.getUrl());
            club.setStatus(0);
            baseMapper.updateById(clubExam);
        } else {
            clubExam.setStatus(0);
            clubExam.setCreateTime(new Date());
            baseMapper.insert(clubExam);
        }
    }

    @Override
    public PageUtils select(Map<String, Object> params) {
        ClubInfoEntity clubInfo = clubInfoService.getByUser();
        Long clubId = 0L;
        if (clubInfo != null) {
            clubId = clubInfo.getId();
        }
        IPage<ClubExamEntity> page = this.page(
                new Query<ClubExamEntity>().getPage(params),
                new QueryWrapper<ClubExamEntity>()
                        .eq("club_id", clubId)
        );

        return new PageUtils(page);
    }

}