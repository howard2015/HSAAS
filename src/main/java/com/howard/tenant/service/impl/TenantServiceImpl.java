package com.howard.tenant.service.impl;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.howard.admin.mapper.LoginLogMapper;
import com.howard.admin.mapper.UserMapper;
import com.howard.admin.model.LoginLog;
import com.howard.admin.model.User;
import com.howard.admin.service.SettingsService;
import com.howard.admin.service.UserOnlineService;
import com.howard.admin.service.UserService;
import com.howard.base.contants.ResponseCode;
import com.howard.base.model.Pagination;
import com.howard.base.model.ResponseResult;
import com.howard.base.provider.PasswordProvider;
import com.howard.base.service.impl.BaseServiceImpl;
import com.howard.tenant.mapper.TenantMapper;
import com.howard.tenant.model.Tenant;
import com.howard.tenant.service.TenantService;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		lenovo
 * Version:		1.0  
 * Create at:	2017年6月22日 下午9:40:54  
 *  
 * 修改历史:
 * 日期    作者    版本  修改描述
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
@Service("tenantService")
public class TenantServiceImpl extends BaseServiceImpl<TenantMapper, Tenant, Integer> implements TenantService {
	
	
	/* (non-Javadoc)
	 * @see com.belling.base.service.impl.BaseServiceImpl#setMapper(com.belling.base.mapper.BaseMapper)
	 */
	@Autowired
	public void setMapper(TenantMapper mapper) {
		this.mapper = mapper;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#enable(java.lang.Boolean, java.util.List)
	 */
	@Override
	public void enable(Boolean isEnable, List<Integer> idList) {
		verifyRows(mapper.enable(isEnable, idList), idList.size(), "管理员数据库更新失败");
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#resetPassword(java.lang.String, java.util.List)
	 */
	@Override
	public void resetPassword(String password, List<Integer> idList) {
		verifyRows(mapper.resetPassword(password, idList), idList.size(), "管理员密码数据库重置失败");
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#findPaginationByAccount(java.lang.String, java.lang.Integer, com.belling.base.model.Pagination)
	 */
	@Override
	public Pagination<Tenant> findPaginationByAccount(String account, Integer appId, Pagination<Tenant> p) {
		mapper.findPaginationByAccount(account, appId, p);
		return p;
	}

	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#findByAccount(java.lang.String)
	 */
	@Override
	public Tenant findByAccount(String account) {
		return mapper.findByAccount(account);
	}
	
	/* (non-Javadoc)
	 * @see com.belling.admin.service.UserService#updatePassword(java.lang.Integer, java.lang.String)
	 */
	@Override
	@Transactional
	public void updatePassword(Integer id, String newPassword) {
		Tenant tenant = get(id);
		tenant.setPassword(PasswordProvider.encrypt(newPassword));
		update(tenant);
	}

}
