package com.manager.modules.club.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 注册申请
 * 
 * @author ""
 * @email ""
 * @date 2023-03-30 17:12:27
 */
@Data
@TableName("club_reg")
public class ClubRegEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 社团名
	 */
	private String clubName;
	/**
	 * 社团ID
	 */
	private Integer clubId;
	/**
	 * 注册资料
	 */
	private String url;
	/**
	 * 申请时间
	 */
	private Date createTime;
	/**
	 * 审核状态
	 */
	private Integer status;

}
