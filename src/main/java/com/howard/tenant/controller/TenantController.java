package com.howard.tenant.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.howard.admin.model.User;
import com.howard.admin.service.UserService;
import com.howard.base.controller.BaseController;
import com.howard.base.exception.ValidateException;
import com.howard.base.model.Pagination;
import com.howard.base.model.ResponseResult;
import com.howard.base.model.TablePageResult;
import com.howard.base.provider.PasswordProvider;
import com.howard.tenant.model.Tenant;
import com.howard.tenant.service.TenantService;


/**
 * @author Sunny
 */
@Controller
@RequestMapping("/tenant/user")
public class TenantController extends BaseController {

	/**
	 * 用户业务对象
	 */
	@Autowired
	private TenantService tenantService;
	
	/**
	 * 默认密码
	 */
	@Value("${system.init.password}")
	private String password;

	/**
	 * 用户列表首页
	 * 
	 * @param model
	 * @return
	 */
	@RequiresPermissions("tenant:user:list")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		return "/tenant/list";
	}

	/**
	 * 编辑用户
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Integer id, Model model) {
		Tenant user;
		if (id == null) {
			user = new Tenant();
		} else {
			user = tenantService.get(id);
		}
		model.addAttribute("vo", user);
		return "/user/edit";
	}
	
	/**
	 * 用户分页
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public TablePageResult page(HttpServletRequest req, Integer draw) {
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
		
		Pagination<Tenant> page = new Pagination<Tenant>();
		page = tenantService.findPaginationByAccount(kw, null, new Pagination<Tenant>((start / length) + 1, length));
		return TablePageResult.createSuccessResult(page.getList(), page.getRowCount(), draw + 1);
	}
	
	/**
	 * 保存或更新用户
	 * 
	 * @param id
	 * @param account
	 * @param password
	 * @param isEnable
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody ResponseResult save(Integer id, String account, String password, String name, String company, String contactor, String address, String telphone, Boolean isEnable) {
		Tenant user;
		if (id == null) {
			if (Strings.isNullOrEmpty(password)) {
				throw new ValidateException("密码不能为空");
			}
			user = new Tenant();
			user.setCreateTime(new Date());
		} else {
			user = tenantService.get(id);
		}
		user.setAccount(account);
		if (!Strings.isNullOrEmpty(password)) {
			user.setPassword(PasswordProvider.encrypt(password));
		}
		user.setName(name);
		user.setCompany(company);
		user.setContactor(contactor);
		user.setAddress(address);
		user.setTelphone(telphone);
		user.setState(1);
		user.setIsEnable(isEnable);
		tenantService.save(user);
		return ResponseResult.createSuccessResult();
	}
	
	
	/**
	 * 重置密码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody ResponseResult resetPassword(String ids) {
		tenantService.resetPassword(PasswordProvider.encrypt(password), getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
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
		tenantService.enable(isEnable, getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}

 
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody ResponseResult delete(String ids) {
		tenantService.deleteById(getAjaxIds(ids));
		return ResponseResult.createSuccessResult();
	}
}