package com.howard.admin.service;

import java.util.List;

import com.howard.admin.model.Permission;
import com.howard.base.layerui.NavNode;
import com.howard.base.service.BaseService;

/**
 * 权限服务接口
 * 
 * @author Sunny
 */
public interface PermissionService extends BaseService<Permission, Integer> {

	/**
	 * 根据名称和应用ID查询
	 * @param name 权限名称
	 * @param appId 应用ID
	 * @return
	 */
	public List<Permission> findByName(String name, Boolean isEnable);
	
	/**
	 * 删除权限
	 * @param id 权限ID
	 * @return
	 */
	public void deletePermission(Integer id);
	
	/**
	 * 根据角色查询权限
	 * 
	 * @param roleId 角色ID
	 * @return
	 */
	public List<String> findPermissionsByRoleId(Integer roleId);
	
	/**
	 * 根据用户查询权限
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	public List<String> findPermissionsByUserId(Integer userId);
	
	
	/**
	 * 查询用户权限
	 * 
	 * @param userId 用户ID
	 * @return
	 */
	public List<Permission> findListByUserId(Integer userId);
	
	
	/**
	 * 转成LayerUI的NAV数据格式
	 * 
	 * @param userId
	 * @return
	 */
	public List<NavNode> getNavMenu(Integer userId);
}
