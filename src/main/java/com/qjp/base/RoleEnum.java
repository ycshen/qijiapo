package com.qjp.base;
/** 
 * <p>Project: MyBase</p> 
 * <p>Title: RoleEnum.java</p> 
 * <p>Description: TODO</p> 
 * <p>Copyright (c) 2017 xjw Consultancy Services</p>
 * <p>All Rights Reserved.</p>
 * @author <a href="mailto:shenyuchuan@itiaoling.com">申鱼川</a>
 */
public enum RoleEnum {
	ADMIN(1, "默认管理员"),
	DEP(2, "默认经理用户"), 
	USR(3, "默认普通用户");
	
	private Integer roleId; 
	private String roleName;
	private RoleEnum(Integer roleId, String roleName)  
    {  
        this.roleId = roleId;  
        this.roleName = roleName;  
    }
	
	public Integer getRoleId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	
	public static String getRoleName(Integer roleId) {
		for (RoleEnum role : RoleEnum.values()) {
			if(roleId == role.getRoleId()){
				return role.getRoleName();
			}
		}
		
		return null;
	}
	
	public static RoleEnum getRoleEnum(Integer roleId) {
		for (RoleEnum role : RoleEnum.values()) {
			if(roleId == role.getRoleId()){
				return role;
			}
		}
		
		return null;
	}

}

