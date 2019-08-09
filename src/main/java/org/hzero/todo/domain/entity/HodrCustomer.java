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
 * @program: hzero-order-24520->HodrCustomer
 * @description: 客户信息
 * @author: 胡超男
 * @create: 2019-08-05 14:07
 **/
@ApiModel("客户信息")
@ModifyAudit
@VersionAudit
@Table(name = "hodr_customer_24520") //表映射
@JsonInclude(JsonInclude.Include.NON_NULL) //数据返回前端时，排除为空的字段
public class HodrCustomer extends AuditDomain {
    @Id
    @GeneratedValue
    private Long customerId;
    @Length(max = 60)
    @NotBlank
    @ApiModelProperty("客户编号")
    private String customerNumber;
    @NotBlank
    @ApiModelProperty("客户名称")
    private String customerName;
    @NotBlank
    @ApiModelProperty("公司ID")
    private Long companyId;
    @NotBlank
    @ApiModelProperty("启用标识")
    private Integer enabledFlag;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Override
    public String toString() {
        return "HodrCustomer{" +
                "customerId=" + customerId +
                ", customerNumber='" + customerNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", companyId=" + companyId +
                ", enabledFlag=" + enabledFlag +
                '}';
    }
}
