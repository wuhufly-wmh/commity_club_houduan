package com.manager.modules.club.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社团注册/审核控制
 * 
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
@Data
@TableName("club_config")
public class ClubConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 注册开关0关1开
	 */
	private Integer rstatus;
	/**
	 * 审核开关0关1开
	 */
	private Integer sstatus;

}
