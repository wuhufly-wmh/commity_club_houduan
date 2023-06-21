package com.manager.modules.club.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.manager.common.utils.ShiroUtils;
import com.manager.modules.club.dao.ClubInfoDao;
import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.service.ClubInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubUserDao;
import com.manager.modules.club.entity.ClubUserEntity;
import com.manager.modules.club.service.ClubUserService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


@Service("clubUserService")
public class ClubUserServiceImpl extends ServiceImpl<ClubUserDao, ClubUserEntity> implements ClubUserService {


    @Autowired
    private ClubInfoService clubInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("key");
        Long acId = ShiroUtils.getUserEntity().getAcId();
        LambdaQueryWrapper<ClubInfoEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ClubInfoEntity::getAcademyId,acId);
        List<ClubInfoEntity> list = clubInfoService.list(wrapper);
        if (CollectionUtils.isEmpty(list)){
            return new PageUtils(new ArrayList<>(),0,10,1);
        }
        List<Long> ids = list.stream().map(ClubInfoEntity::getId).collect(Collectors.toList());
        IPage<ClubUserEntity> page = this.page(
                new Query<ClubUserEntity>().getPage(params),
                new QueryWrapper<ClubUserEntity>()
                        .eq("status", 0)
                        .like(StringUtils.hasText(name), "name", name)
                        .in("club_id",ids)
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils select(Map<String, Object> params) {
        IPage<ClubUserEntity> iPage = new Query<ClubUserEntity>().getPage(params);
        String name = (String) params.get("key");
        IPage<ClubUserEntity> page = baseMapper.select(iPage, name,ShiroUtils.getUserId());

        return new PageUtils(page);
    }

    /**
     * 添加成员
     * @param clubUser
     */
    @Override
    public void saveInfo(ClubUserEntity clubUser) {
        ClubInfoEntity clubInfo = clubInfoService.getByUser();
        clubUser.setClubId(clubInfo.getId());
        clubUser.setClubName(clubInfo.getName());
        clubUser.setStatus(0);
        clubInfo.setPerNum(clubInfo.getPerNum() + 1);
        clubInfoService.updateById(clubInfo);
        this.save(clubUser);
    }

    @Override
    public void removeWithNum(List<Long> asList) {
        ClubInfoEntity clubInfo = clubInfoService.getByUser();
        clubInfo.setPerNum(clubInfo.getPerNum() - asList.size());
        clubInfoService.updateById(clubInfo);
        this.removeByIds(asList);
    }

}