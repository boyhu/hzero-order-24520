package org.hzero.todo.domain.entity;
/**
 * Created by hcn on 2019/8/5.
 */

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @program: hzero-order-24520->HodrCompany
 * @description: 公司实体类
 * @author: 胡超男
 * @create: 2019-08-05 12:02
 **/
@ApiModel("公司信息")
@ModifyAudit
@VersionAudit
@Table(name = "hodr_company_24520") //表映射
@JsonInclude(JsonInclude.Include.NON_NULL) //数据返回前端时，排除为空的字段
public class HodrCompany extends AuditDomain {
    @Id
    @GeneratedValue
    public Long companyId;
    @Length(max = 60)
    @NotBlank
    @ApiModelProperty("公司编号")
    public String companyNumber;
    @NotBlank
    @ApiModelProperty("公司名称")
    public String companyName;
    @NotBlank
    @ApiModelProperty("启用标识")
    public Integer enabledFlag;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Override
    public String toString() {
        return "HodrCompany{" +
                "companyId=" + companyId +
                ", companyNumber='" + companyNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", enabledFlag=" + enabledFlag +
                '}';
    }
}
