

package com.manager.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.manager.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 系统用户
 *
 * 
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

    List<SysUserEntity> listClub();

	IPage<SysUserEntity> listClubUser(IPage<SysUserEntity> iPage, @Param("name") String username,@Param("acId") Long acId);
}
