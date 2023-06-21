package com.manager.modules.club.dao;

import com.manager.modules.club.entity.ClubInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manager.modules.club.entity.PieVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 社团信息
 * 
 * @author ""
 * @email ""
 * @date 2023-03-29 20:59:18
 */
@Mapper
public interface ClubInfoDao extends BaseMapper<ClubInfoEntity> {

    List<PieVo> selectPie();

}
