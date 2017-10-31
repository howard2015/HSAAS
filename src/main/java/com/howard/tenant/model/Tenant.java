package com.howard.tenant.model;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.howard.base.model.PersistentObject;


/**
 * 租户
 * 
 * @author Sunny
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Tenant extends PersistentObject {
	
	/** 序列化ID */
	private static final long serialVersionUID = 1106412532325860697L;
	
	/** 登录名 */
	private String account;
	
	/** 密码 */
	@JSONField(serialize = false)
	private String password;
	
	/** 姓名 */
	@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
	private String name;
	
	/** 公司 */
	@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
	private String company;
	
	/** 联系人 */
	@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
	private String contactor;
	
	/** 地址 */
	@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
	private String address;
	
	/** 联系电话 */
	@JSONField(serialzeFeatures = SerializerFeature.WriteMapNullValue)
	private String telphone;
	
	/** 状态 */
	private Integer state = Integer.valueOf(0);
	
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	/** 是否启用 */
	private Boolean isEnable;
	

}
