package com.manager.modules.club.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 社团成员
 * 
 * @author ""
 * @email ""
 * @date 2023-03-30 10:11:06
 */
@Data
@TableName("club_user")
public class ClubUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 联系电话
	 */
	private String tel;
	/**
	 * 所属学院
	 */
	private String academyName;
	/**
	 * 所属社团
	 */
	private Long clubId;
	/**
	 * 社团名
	 */
	private String clubName;
	/**
	 * 状态
	 */
	private Integer status;

}
