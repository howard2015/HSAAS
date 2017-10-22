package com.howard.admin.controller.houtai;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.howard.admin.model.Role;
import com.howard.admin.service.RoleService;
import com.howard.base.controller.BaseController;
import com.howard.base.model.Pagination;
import com.howard.base.model.ResponseResult;
import com.howard.base.model.TablePageResult;

/**  
 * <pre>
 * Description
 * Copyright:	Copyright (c)2017
 * Company:		Sunny
 * Author:		Administrator
 * Version: 	1.0
 * Create at:	2017年6月23日 上午8:48:33  
 *  
 * Modification History:  
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------  
 * 
 * </pre>
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	/**
	 * 角色业务对象
	 */
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:role:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/role/list";
	}
	
	/**
	 * 保存或编辑角色
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		Role role;
		if (id == null) {
			role = new Role();
		} else {
			role = roleService.get(id);
		}
		model.addAttribute("vo", role);
		return "/role/edit";
	}
	
	/**
	 * 分页请求
	 * 
	 * @param kw
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public TablePageResult page(HttpServletRequest req,Integer draw) {
		String kw = req.getParameter("kw");
		String type = req.getParameter("type");
		String curpage = req.getParameter("start");
		if (Strings.isNullOrEmpty(curpage)) {
			curpage = "0";
		}
		int start = Integer.parseInt(curpage);
		if (start <= 0) {
			start = 0;
		}
		String curlg = req.getParameter("length");
		if (Strings.isNullOrEmpty(curlg)) {
			curlg = "1";
		}
		int length = Integer.parseInt(curlg);
		if (length <=  0) {
			length = 8;
		}
		Pagination<Role> page  = new Pagination<Role>((start / length) + 1, length);
		page = roleService.findPaginationByName(kw,type, page);
		return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
	}
	
	/**
	 * 是否启用
	 * 
	 * @param ids
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/enable", method = RequestMethod.POST)
	public @ResponseBody ResponseResult enable(String ids, Boolean isEnable) {
		roleService.enable(isEnable, getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}
	
	/**
	 * 保存角色
	 * 
	 * @param id
	 * @param name
	 * @param sort
	 * @param description
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseResult save(Integer id, String name, String code, Integer sort, String description, Boolean isEnable) {
		Role role;
		if (id == null) {
			role = new Role();
			role.setType("0");
		} else {
			role = roleService.get(id);
		}
		role.setName(name);
		role.setSort(sort);
		role.setCode(code);
		role.setDescription(description);
		role.setIsEnable(isEnable);
		roleService.save(role);
		return ResponseResult.createSuccessResult();
	}
	
	/**
	 * 角色删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseResult delete(String ids) {
		roleService.deleteById(getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}

}
