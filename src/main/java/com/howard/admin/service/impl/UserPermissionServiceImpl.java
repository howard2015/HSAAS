package com.howard.admin.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.howard.admin.mapper.UserPermissionMapper;
import com.howard.admin.model.UserPermission;
import com.howard.admin.service.UserPermissionService;
import com.howard.base.service.impl.BaseServiceImpl;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2014  
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午10:05:46  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("userPermissionService")
public class UserPermissionServiceImpl extends BaseServiceImpl<UserPermissionMapper, UserPermission, Integer> implements UserPermissionService {
	
	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(UserPermissionMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#allocate(java.lang.Integer, java.util.List)
	 */
	@Transactional
	public void allocate(Integer userId, List<UserPermission> list) {
		mapper.deleteByUserIds(Arrays.asList(userId));
		super.save(list);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#findByRoleId(java.lang.Integer)
	 */
	public List<UserPermission> findByUserId(Integer userId) {
		return mapper.findByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#deleteByPermissionIds(java.util.List)
	 */
	public void deleteByPermissionIds(List<Integer> idList) {
		mapper.deleteByPermissionIds(idList);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.RolePermissionService#deleteByRoleIds(java.util.List)
	 */
	public void deleteByUserIds(List<Integer> idList) {
		mapper.deleteByUserIds(idList);
	}
}
