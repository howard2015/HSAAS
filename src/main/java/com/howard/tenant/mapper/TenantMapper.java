package com.howard.tenant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.howard.base.mapper.BaseMapper;
import com.howard.base.model.Pagination;
import com.howard.tenant.model.Tenant;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月20日 下午2:30:50  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */  
public interface TenantMapper extends BaseMapper<Tenant, Integer> {
	
	/**
	 * @param isEnable
	 * @param idList
	 * @return
	 */
	public int enable(@Param("isEnable") Boolean isEnable, @Param("idList") List<Integer> idList);
	
	/**
	 * @param password
	 * @param idList
	 * @return
	 */
	public int resetPassword(@Param("password") String password, @Param("idList") List<Integer> idList);

	/**
	 * @param account
	 * @param appId
	 * @param p
	 * @return
	 */
	public List<Tenant> findPaginationByAccount(@Param("account") String account, @Param("appId") Integer appId, Pagination<Tenant> p);
	
	/**
	 * @param account
	 * @return
	 */
	public Tenant findByAccount(@Param("account") String account);
}
