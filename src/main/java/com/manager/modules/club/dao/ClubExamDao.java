package com.manager.modules.club.dao;

import com.manager.modules.club.entity.ClubExamEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 注册申请
 * 
 * @author ""
 * @email ""
 * @date 2023-03-30 17:12:27
 */
@Mapper
public interface ClubExamDao extends BaseMapper<ClubExamEntity> {

    ClubExamEntity selectByYear(@Param("clubId") Integer clubId);

}
