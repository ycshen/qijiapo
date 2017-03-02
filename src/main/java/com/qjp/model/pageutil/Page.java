package com.qjp.model.pageutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qjp.entity.UserEntity;
import com.qjp.util.UserUtils;

public class Page<T> {
	private Integer page = 1;// 页码，默认是第一页
	private Integer size = 10;// 每页显示的记录数，默认是15
	private Integer count=0;// 总记录数
	private Integer pageCount=0;// 总页数
	private List<T> items;// 对应的当前页记录
	private Boolean isPage = true;
	private Map<String, Object> params = new HashMap<>();// 其他的参数我们把它分装成一个Map对象
	private String roleType = "3"; //数据权限类型 3-默认查看本人以及下属的数据  2-本部门以及下级部门的数据 1-全部数据
	private String userId; //登录人的userId
	private String departmentId;
	private String companyId;
	
	public String getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;

		int pageCount = count / size;
		if (count % size > 0)
			pageCount++;
		this.setPageCount(pageCount);
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
/*	@Override
	public String toString() {
		int lastItemIndex = page * size;
		if(lastItemIndex>count) lastItemIndex = count;
		return String.format("当前第%d-%d项 第%d/%d页 共%d项 每页显示%d项", (page - 1) * size + 1, lastItemIndex, page, pageCount,
				count, size);
	}*/
	
	public void init(HttpServletRequest request){
		UserEntity user = UserUtils.getLoginUser(request);
		this.setCompanyId(user.getCompanyId().toString());
		this.setUserId(user.getId().toString());
		this.setDepartmentId(user.getDepartmentId().toString());
		this.setRoleType(UserUtils.getRoleType(request));
	}
}
