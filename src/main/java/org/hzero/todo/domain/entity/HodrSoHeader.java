package org.hzero.todo.domain.entity;
/**
 * Created by hcn on 2019/8/5.
 */

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.choerodon.mybatis.domain.AuditDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * @program: hzero-order-24520->hodrSoHeader
 * @description: 销售订单头信息
 * @author: 胡超男
 * @create: 2019-08-05 14:23
 **/
@ApiModel("销售订单头信息")
@ModifyAudit
@VersionAudit
@Table(name = "hodr_so_header_24520") //表映射
@JsonInclude(JsonInclude.Include.NON_NULL) //数据返回前端时，排除为空的字段
public class HodrSoHeader extends AuditDomain {
    @Id
    @GeneratedValue
    private Long soHeaderId;
    @Length(max = 60)
    @NotBlank
    @ApiModelProperty("订单编码")
    private String orderNumber;
    @NotBlank
    @ApiModelProperty("公司ID")
    private Long companyId;
    @NotBlank
    @ApiModelProperty("订单日期")
    private Date orderDate;
    @Length(max = 30)
    @NotBlank
    @ApiModelProperty("订单状态")
    private String orderStatus;
    @NotBlank
    @ApiModelProperty("客户ID")
    private Long customerId;

    @Transient
    @ApiModelProperty("订单总金额")
    private BigDecimal totalAmount;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public void setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "HodrSoHeader{" +
                "soHeaderId=" + soHeaderId +
                ", orderNumber='" + orderNumber + '\'' +
                ", companyId=" + companyId +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
