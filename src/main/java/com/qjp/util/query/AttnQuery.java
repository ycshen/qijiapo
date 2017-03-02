package com.qjp.util.query;

import com.qjp.entity.AttnEntity;
import com.qjp.model.pageutil.Page;

/**
 * Created by fengyue on 2017/2/28.
 */
public class AttnQuery extends Page<AttnEntity> {

    private Long companyId;
    private Long departmentId;
    private Long userId;
    private String provinceId;
    private String cityId;
    private String areaId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
