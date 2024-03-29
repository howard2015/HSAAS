package com.howard.admin.service;

import java.util.List;

import com.howard.admin.model.RolePermission;
import com.howard.base.service.BaseService;

/**
 * 角色权限映射服务接口
 * 
 * @author Sunny
 */
public interface RolePermissionService extends BaseService<RolePermission, Integer> {
	
	/**
	 * 根据角色ID查询映射
	 * @param roleId 角色ID
	 * @return
	 */
	public List<RolePermission> findByRoleId(Integer roleId);
	
	/**
	 * 根据角色ID给角色授权
	 * @param roleId 角色ID
	 * @param list 角色权限映射集合
	 * @return
	 */
	public void allocate(Integer roleId, List<RolePermission> list);
	
	/**
	 * 根据权限ID集合删除映射
	 * @param idList 权限ID集合
	 * @return
	 */
	public void deleteByPermissionIds(List<Integer> idList);
	
	/**
	 * 根据角色ID集合删除映射
	 * @param idList 角色ID集合
	 * @return
	 */
	public void deleteByRoleIds(List<Integer> idList);
}
