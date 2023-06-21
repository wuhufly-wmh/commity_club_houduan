package com.manager.modules.club.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社团活动
 * 
 * @author ""
 * @email ""
 * @date 2023-03-29 22:00:19
 */
@Data
@TableName("club_activity")
public class ClubActivityEntity implements Serializable {
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
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动时间
	 */
	private Date activityTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 活动地点
	 */
	private String address;
	/**
	 * 活动人数
	 */
	private Integer activityNum;
	/**
	 * 申请时间
	 */
	private Date createTime;
	/**
	 * 申请资料
	 */
	private String url;
	/**
	 * 状态
	 */
	private Integer status;

}
