package com.manager.modules.club.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manager.modules.club.entity.ClubBudgetEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 社团经费
 * 
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
@Mapper
public interface ClubBudgetDao extends BaseMapper<ClubBudgetEntity> {

    IPage<ClubBudgetEntity> select(IPage<ClubBudgetEntity> page,@Param("id") Long userId);

    IPage<ClubBudgetEntity> page(IPage<ClubBudgetEntity> page,@Param("clubName") String clubName,@Param("acId") Long acId);
}
