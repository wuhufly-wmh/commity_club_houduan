package com.manager.modules.club.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社团信息
 * 
 * @author ""
 * @email ""
 * @date 2023-03-29 20:59:18
 */
@Data
@TableName("club_info")
public class ClubInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 社团名称
	 */
	private String name;
	/**
	 * 负责人ID
	 */
	private Long ownerId;
	/**
	 * 负责人姓名
	 */
	private String ownerUser;
	/**
	 * 社团状态
	 */
	private Integer status;
	/**
	 * 注册状态
	 */
	private Integer rstatus;
	/**
	 * 挂靠学院ID
	 */
	private Long academyId;
	/**
	 * 挂靠学院
	 */
	private String academyName;
	/**
	 * 社团简介
	 */
	private String introduce;
	/**
	 * 成立时间
	 */
	private Date createTime;
	/**
	 * 活动次数
	 */
	private Integer acNum;
	/**
	 * 经费
	 */
	private BigDecimal budget;
	/**
	 * 成员人数
	 */
	private Integer perNum;

}
