package com.howard.admin.service;

import java.util.List;

import com.howard.admin.model.RolePermission;
import com.howard.admin.model.UserPermission;
import com.howard.base.service.BaseService;

/**
 * 用户权限映射服务接口
 * 
 * @author Sunny
 */
public interface UserPermissionService extends BaseService<UserPermission, Integer> {
	
	/**
	 * 根据用户ID查询映射
	 * @param roleId 用户ID
	 * @return
	 */
	public List<UserPermission> findByUserId(Integer userId);
	
	/**
	 * 根据用户ID给用户授权
	 * @param roleId 用户ID
	 * @param list 用户权限映射集合
	 * @return
	 */
	public void allocate(Integer userId, List<UserPermission> list);
	
	/**
	 * 根据权限ID集合删除映射
	 * @param idList 权限ID集合
	 * @return
	 */
	public void deleteByPermissionIds(List<Integer> idList);
	
	/**
	 * 根据用户ID集合删除映射
	 * @param idList 用户ID集合
	 * @return
	 */
	public void deleteByUserIds(List<Integer> idList);
}
