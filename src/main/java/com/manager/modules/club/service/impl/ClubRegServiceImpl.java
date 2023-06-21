package com.manager.modules.club.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.manager.common.utils.ShiroUtils;
import com.manager.modules.club.entity.ClubInfoEntity;
import com.manager.modules.club.entity.RoleEnum;
import com.manager.modules.club.service.ClubInfoService;
import com.manager.modules.sys.service.SysUserRoleService;
import com.mchange.lang.ShortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manager.common.utils.PageUtils;
import com.manager.common.utils.Query;

import com.manager.modules.club.dao.ClubRegDao;
import com.manager.modules.club.entity.ClubRegEntity;
import com.manager.modules.club.service.ClubRegService;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;


@Service("clubRegService")
public class ClubRegServiceImpl extends ServiceImpl<ClubRegDao, ClubRegEntity> implements ClubRegService {

    @Autowired
    private ClubInfoService clubInfoService;

    @Resource
    private SysUserRoleService sysUserRoleService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String clubName = (String) params.get("key");

        Long acId = ShiroUtils.getUserEntity().getAcId();

        Set<String> roleIdList = sysUserRoleService.queryRoleIdList(ShiroUtils.getUserEntity().getUserId())
                .stream()
                .map(String::valueOf)
                .collect(Collectors.toSet());


        RoleEnum adminEnum = RoleEnum.getAdminEnum(roleIdList);
        List<Long> ids = null;
        switch (adminEnum) {
            case ADMIN:
                break;
            case NORMAL:
                LambdaQueryWrapper<ClubInfoEntity> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(ClubInfoEntity::getAcademyId, acId);
                List<ClubInfoEntity> list = clubInfoService.list(wrapper);
                if (CollectionUtils.isEmpty(list)) {
                    return new PageUtils(new ArrayList<>(), 0, 10, 1);
                }
                ids = list.stream().map(ClubInfoEntity::getId).collect(Collectors.toList());
            case USER:
                break;
            default:
                throw new RuntimeException("错误的枚举类型");
        }

        QueryWrapper<ClubRegEntity> wrapper = new QueryWrapper<ClubRegEntity>()
                .like(StringUtils.hasText(clubName), "club_name", clubName)
                .orderByDesc("create_time");
        if (!CollectionUtils.isEmpty(ids)) {
            wrapper.in("club_id", ids);
        }


        IPage<ClubRegEntity> page = this.page(
                new Query<ClubRegEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void saveInfo(ClubRegEntity clubReg) {
        ClubRegEntity regInfo = baseMapper.selectByYear(clubReg.getClubId());
        if (regInfo != null) {
            regInfo.setUrl(clubReg.getUrl());
            baseMapper.updateById(clubReg);
        } else {
            clubReg.setCreateTime(new Date());
            clubReg.setStatus(0);
            baseMapper.insert(clubReg);
            // 状态审核中
            ClubInfoEntity clubInfo = clubInfoService.getById(clubReg.getClubId());
            clubInfo.setRstatus(3);
            clubInfoService.updateById(clubInfo);
        }
    }

    @Override
    public void success(Long id) {
        ClubRegEntity reg = getById(id);
        reg.setStatus(1);
        this.updateById(reg);
        ClubInfoEntity clubInfo = clubInfoService.getById(reg.getClubId());
        clubInfo.setRstatus(1);
        clubInfoService.updateById(clubInfo);
    }

    @Override
    public void reject(Long id) {
        ClubRegEntity reg = getById(id);
        reg.setStatus(1);
        this.updateById(reg);
        ClubInfoEntity clubInfo = clubInfoService.getById(reg.getClubId());
        clubInfo.setRstatus(2);
        clubInfoService.updateById(clubInfo);
    }

}