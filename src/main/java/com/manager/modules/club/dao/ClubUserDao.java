package com.manager.modules.club.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manager.modules.club.entity.ClubUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 社团成员
 * 
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
@Mapper
public interface ClubUserDao extends BaseMapper<ClubUserEntity> {

    IPage<ClubUserEntity> select(IPage<ClubUserEntity> iPage, @Param("name") String name,@Param("id") Long userId);
}
