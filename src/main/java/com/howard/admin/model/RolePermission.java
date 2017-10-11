package com.howard.admin.model;

import com.howard.base.model.PersistentObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * 角色权限映射
 * 
 * @author Sunny
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolePermission extends PersistentObject {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 2817362249993235590L;

	/** 应用ID */
	@NonNull
	private Integer roleId;
	
	/** 权限ID */
	@NonNull
	private Integer permissionId;
}
