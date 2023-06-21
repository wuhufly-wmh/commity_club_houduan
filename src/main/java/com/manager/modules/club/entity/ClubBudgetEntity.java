package com.manager.modules.club.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社团经费
 * 
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
@Data
@TableName("club_budget")
public class ClubBudgetEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 社团ID
	 */
	private Long clubId;
	/**
	 * 社团名称
	 */
	private String clubName;
	/**
	 * 申请金额
	 */
	private BigDecimal price;
	/**
	 * 申请时间
	 */
	private Date createTime;
	/**
	 * 用途
	 */
	private String application;
	/**
	 * 状态
	 */
	private Integer status;

}
