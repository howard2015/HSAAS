package com.howard.admin.model;

import com.howard.base.model.PersistentObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色
 * 
 * @author Sunny
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends PersistentObject {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 564115576254799088L;

	/** 名称 */
	private String name;
	
	/** 编码 */
	private String code;
	
	/** 排序 */
	private Integer sort = Integer.valueOf(1);
	
	/** 描述 */
	private String description;
	
	/** 是否启用 */
	private Boolean isEnable = true;
	
	private String type ;
	
	private String tenantId ;
	
	/** 以下为显示辅助参数 */
	private Boolean isChecked = Boolean.valueOf(false);
}
