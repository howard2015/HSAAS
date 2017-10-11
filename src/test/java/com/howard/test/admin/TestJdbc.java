package com.howard.test.admin;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.common.collect.Maps;
import com.howard.admin.model.LoginLog;
import com.howard.admin.model.Role;
import com.howard.admin.service.LoginLogService;
import com.howard.admin.service.PermissionService;
import com.howard.admin.service.RoleService;
import com.howard.admin.service.SettingsService;
import com.howard.admin.service.UserService;
import com.howard.base.model.Pagination;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月20日 下午2:29:44  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:/config/spring-ctx.xml")
public class TestJdbc {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private LoginLogService loginLogService;
	
	@Autowired
	private SettingsService settingsService;
	
	/**
	 * 
	 */
	@Test
	public  void testJdbc() {
		System.out.println(userService);
		userService.updatePassword(3, "123456");
	}
	
	/**
	 * 
	 */
	@Test
	public  void testRole() {
		List<Role> dataList = roleService.selectRolesByUser(2);
		System.out.println(dataList);
	}
	
	/**
	 * 
	 */
	@Test
	public  void testPermission() {
		List<String> dataList = permissionService.findPermissionsByRoleId(1);
		System.out.println(dataList);
		
//		List<NavNode> nodeList  = permissionService.getNavMenu(2);
//		System.out.println(JSONObject.toJSONString(nodeList));
	}
	
	@Test
	public void testLog() {
		Pagination<LoginLog> page = new Pagination<LoginLog>(1, 8);
		page = loginLogService.findByAllPagination(page);
		System.out.println(page);
	}
	
	@Test
	public void testSettings() {
		Map<String, String> map = Maps.newHashMap();
		map.put("mailsend", "2");
		settingsService.update(map);
		System.out.println(settingsService.getValueByKey("mailsend"));
	}
}
