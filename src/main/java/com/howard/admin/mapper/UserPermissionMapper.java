package com.howard.admin.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.howard.admin.model.UserPermission;
import com.howard.base.mapper.BaseMapper;

/**
 * 角色权限映射持久化接口
 * 
 * @author Sunny
 */
public interface UserPermissionMapper extends BaseMapper<UserPermission, Integer> {
	
	public List<UserPermission> findByUserId(@Param("userId") Integer userId);
	
	public int deleteByPermissionIds(@Param("idList") List<Integer> idList);
	
	public int deleteByUserIds(@Param("idList") List<Integer> idList);
}
