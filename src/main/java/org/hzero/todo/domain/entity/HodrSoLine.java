package org.hzero.todo.domain.entity;
/**
 * Created by hcn on 2019/8/5.
 */

import java.math.BigDecimal;
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

/**
 * @program: hzero-order-24520->hodrSoLine
 * @description: 销售订单行信息
 * @author: 胡超男
 * @create: 2019-08-05 14:29
 **/
@ApiModel("销售订单行信息")
@ModifyAudit
@VersionAudit
@Table(name = "hodr_so_line_24520") //表映射
@JsonInclude(JsonInclude.Include.NON_NULL) //数据返回前端时，排除为空的字段
public class HodrSoLine extends AuditDomain {
    @Id
    @GeneratedValue
    private Long soLineId;
    @NotBlank
    @ApiModelProperty("订单头ID")
    private Long soHeaderId;
    @NotBlank
    @ApiModelProperty("行号")
    private Integer lineNumber;
    @NotBlank
    @ApiModelProperty("产品ID")
    private Long itemId;
    @NotBlank
    @ApiModelProperty("数量")
    private BigDecimal orderQuantity;
    @NotBlank
    @ApiModelProperty("产品单位")
    private String orderQuantityUom;
    @NotBlank
    @ApiModelProperty("销售单价")
    private BigDecimal unitSellingPrice;
    @ApiModelProperty("备注")
    private String description;
    @ApiModelProperty("附加信息1")
    private String addition1;
    @ApiModelProperty("附加信息2")
    private String addition2;
    @ApiModelProperty("附加信息3")
    private String addition3;
    @ApiModelProperty("附加信息4")
    private String addition4;
    @ApiModelProperty("附加信息5")
    private String addition5;

    @Transient
    @ApiModelProperty("行金额")
    private BigDecimal lineAmount;

    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }

    public Long getSoLineId() {
        return soLineId;
    }

    public void setSoLineId(Long soLineId) {
        this.soLineId = soLineId;
    }

    public Long getSoHeaderId() {
        return soHeaderId;
    }

    public void setSoHeaderId(Long soHeaderId) {
        this.soHeaderId = soHeaderId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    public void setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
    }

    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddition1() {
        return addition1;
    }

    public void setAddition1(String addition1) {
        this.addition1 = addition1;
    }

    public String getAddition2() {
        return addition2;
    }

    public void setAddition2(String addition2) {
        this.addition2 = addition2;
    }

    public String getAddition3() {
        return addition3;
    }

    public void setAddition3(String addition3) {
        this.addition3 = addition3;
    }

    public String getAddition4() {
        return addition4;
    }

    public void setAddition4(String addition4) {
        this.addition4 = addition4;
    }

    public String getAddition5() {
        return addition5;
    }

    public void setAddition5(String addition5) {
        this.addition5 = addition5;
    }

    @Override
    public String toString() {
        return "hodrSoLine{" +
                "soLineId=" + soLineId +
                ", soHeaderId=" + soHeaderId +
                ", lineNumber=" + lineNumber +
                ", itemId=" + itemId +
                ", orderQuantity=" + orderQuantity +
                ", orderQuantityUom='" + orderQuantityUom + '\'' +
                ", unitSellingPrice=" + unitSellingPrice +
                ", description='" + description + '\'' +
                ", addition1='" + addition1 + '\'' +
                ", addition2='" + addition2 + '\'' +
                ", addition3='" + addition3 + '\'' +
                ", addition4='" + addition4 + '\'' +
                ", addition5='" + addition5 + '\'' +
                '}';
    }
}
