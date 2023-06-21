package com.manager.modules.aca.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学院
 * 
 * @author ""
 * @email ""
 * @date 2023-03-30 10:14:35
 */
@Data
@TableName("academy")
public class AcademyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long value;
	/**
	 * 学院名
	 */
	private String label;

}
