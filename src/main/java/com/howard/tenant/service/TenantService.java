package com.howard.tenant.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.howard.base.model.Pagination;
import com.howard.base.service.BaseService;
import com.howard.tenant.model.Tenant;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月20日 下午2:34:03  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public interface TenantService extends BaseService<Tenant, Integer> {
	/**
	 * 启用禁用操作
	 * @param isEnable 是否启用
	 * @param idList 管理员ID集合
	 * @return
	 */
	public void enable(Boolean isEnable, List<Integer> idList);
	
	/**
	 * 重置密码
	 * @param password 初始化密码(已加密)
	 * @param idList 
	 */
	public void resetPassword(String password, List<Integer> idList);

	/**
	 * 根据登录名和应用ID查询分页列表
	 * @param account 登录名
	 * @param appId 应用ID
	 * @param pageNo 分页起始
	 * @param pageSize 分页记录数
	 * @return
	 */
	public Pagination<Tenant> findPaginationByAccount(String account, Integer appId, Pagination<Tenant> p);
	
	/**
	 * 根据登录名和应用ID查询
	 * @param account 登录名
	 * @param appId 应用ID
	 * @return
	 */
	public Tenant findByAccount(String account);
	
	/**
	 * 更新密码
	 * 
	 * @param id 用户ID
	 * @param newPassword  新密码
	 * @return
	 */
	public void updatePassword(Integer id, String newPassword);
	
	
}
