package com.manager.modules.club.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manager.modules.club.entity.ClubActivityEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社团活动
 * 
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
@Mapper
public interface ClubActivityDao extends BaseMapper<ClubActivityEntity> {

    IPage<ClubActivityEntity> select(IPage<ClubActivityEntity> iPage,@Param("id") Long userId);

    List<ClubActivityEntity> selectAll(@Param("id") Long userId);

    IPage<ClubActivityEntity> page(IPage<ClubActivityEntity> page,@Param("name") String name,@Param("acId") Long acId);

    IPage<ClubActivityEntity> pageAll(IPage<ClubActivityEntity> page, String name);
}
