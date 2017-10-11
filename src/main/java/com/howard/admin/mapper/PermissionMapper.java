package com.howard.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.howard.admin.model.Permission;
import com.howard.base.mapper.BaseMapper;


/**
 * 权限持久化接口
 * 
 * @author Sunny
 */
public interface PermissionMapper extends BaseMapper<Permission, Integer> {
	
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	public List<Permission> findByName(@Param("name") String name, @Param("isEnable") Boolean isEnable);
	
	public List<String> findPermissionsByRoleId(@Param("roleId") Integer roleId);
	
	public List<Permission> findListByUserId(@Param("userId") Integer userId);
}