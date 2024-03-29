package com.howard.admin.controller.houtai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.howard.admin.model.Permission;
import com.howard.admin.model.RolePermission;
import com.howard.admin.model.UserPermission;
import com.howard.admin.service.PermissionService;
import com.howard.admin.service.RolePermissionService;
import com.howard.admin.service.UserPermissionService;
import com.howard.base.controller.BaseController;
import com.howard.base.layerui.NavNode;
import com.howard.base.model.ResponseResult;

/**
 * @author Sunny
 */
@Controller
@RequestMapping("/admin/permission")
public class PermissionController extends BaseController {

	/**
	 * 权限业务对象
	 */
	@Autowired
	private PermissionService permissionService;
	
	/**
	 * 角色与权限关联业务对象
	 */
	@Autowired
	private RolePermissionService rolePermissionService;
	
	/**
	 * 用户与权限关联业务对象
	 */
	@Autowired
	private UserPermissionService userPermissionService;
	

	/**
	 * 树列表
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sys:permission:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/permission/list";
	}

	/**
	 * 权限全部节点加载
	 * 
	 * @param name
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/nodes", method = RequestMethod.POST)
	public @ResponseBody List<Permission> nodes(String name, Boolean isEnable) {
		List<Permission> list = permissionService.findByName(name, isEnable);
		Permission permission = new Permission();
		permission.setId(null);
		permission.setPId(-1);
		permission.setName("HSAAS权限管理系统");
		list.add(0, permission);
		return list;
	}

	/**
	 * 保存菜单
	 * 
	 * @param id
	 * @param parentId
	 * @param icon
	 * @param name
	 * @param url
	 * @param sort
	 * @param isMenu
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseResult save(Integer id,
			Integer pId,
			String icon,
			String name,
			String permission,
			String url,
			Integer sort,
			Boolean isMenu,
			Boolean isEnable,
			Boolean type,
			String tenantId) {
		Permission domain;
		if (id == null) {
			domain = new Permission();
		} else {
			domain = permissionService.get(id);
		}
		domain.setPId(pId);
		domain.setIcon(icon);
		domain.setName(name);
		domain.setPermission(permission);
		domain.setUrl(url);
		domain.setSort(sort);
		domain.setIsMenu(isMenu);
		domain.setIsEnable(isEnable);
		domain.setType(type);
		domain.setTenantId(tenantId);
		permissionService.save(domain);
		return ResponseResult.createSuccessResult().setMessage("保存成功");
	}

	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseResult delete(Integer id) {
		permissionService.deletePermission(id);
		return ResponseResult.createSuccessResult().setMessage("删除成功");
	}
	
	/**
	 * 编辑权限
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Integer pId, Model model) {
		Permission permission;
		if (id == null) {
			permission = new Permission();
			permission.setPId(pId);
		} else {
			permission = permissionService.get(id);
		}
		
		model.addAttribute("vo", permission);
		return "/permission/edit";
	}
	
	
	/**
	 * 角色授权
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/allocate", method = RequestMethod.GET)
	public String allocate(Integer roleId, Model model) {
		model.addAttribute("roleId", roleId);
		model.addAttribute("permissions", rolePermissionService.findByRoleId(roleId));
		return "/role/permission-allocate";
	}
	
	
	/**
	 * 角色管理权限保存
	 * 
	 * @param roleId 角色id
	 * @param permissionIds 权限ids
	 * @return
	 */
	@RequestMapping(value = "/allocateSave", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult allocateSave(Integer roleId, String permissionIds) {
		List<Integer> idList = getAjaxIds(permissionIds);
		List<RolePermission> list = new ArrayList<RolePermission>();
		Integer permissionId;
		for (Iterator<Integer> i$ = idList.iterator(); i$.hasNext(); list.add(new RolePermission(roleId, permissionId))) {
			permissionId = i$.next();
		}
		rolePermissionService.allocate(roleId, list);
		return ResponseResult.createSuccessResult().setMessage("授权成功");
	}
	
	/**
	 * 用户授权
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/allocateUser", method = RequestMethod.GET)
	public String allocateUser(Integer userId, Model model) {
		model.addAttribute("userId", userId);
		model.addAttribute("permissions", userPermissionService.findByUserId(userId));
		return "/user/permission-allocate";
	}
	
	
	/**
	 * 用户管理权限保存
	 * 
	 * @param userId 用户id
	 * @param permissionIds 权限ids
	 * @return
	 */
	@RequestMapping(value = "/allocateUserSave", method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult allocateUserSave(Integer userId, String permissionIds) {
		List<Integer> idList = getAjaxIds(permissionIds);
		List<UserPermission> list = new ArrayList<UserPermission>();
		Integer permissionId;
		for (Iterator<Integer> i$ = idList.iterator(); i$.hasNext(); list.add(new UserPermission(userId, permissionId))) {
			permissionId = i$.next();
		}
		userPermissionService.allocate(userId, list);
		return ResponseResult.createSuccessResult().setMessage("授权成功");
	}
	
	/**
	 * 角色管理权限保存
	 * 
	 * @param roleId 角色id
	 * @param permissionIds 权限ids
	 * @return
	 */
	@RequestMapping(value = "/nav", method = RequestMethod.POST)
	@ResponseBody
	public List<NavNode> nav(Integer uId) {
		return permissionService.getNavMenu(uId);
	}
}