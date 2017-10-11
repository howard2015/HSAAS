package com.howard.admin.model;

import com.howard.base.model.PersistentObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员角色映射
 * 
 * @author Sunny
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserRole extends PersistentObject {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 4942358338145288018L;

	/** 管理员ID */
	private Integer userId;
	
	/** 角色ID */
	private Integer roleId;
}
