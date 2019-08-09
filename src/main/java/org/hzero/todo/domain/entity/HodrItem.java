package org.hzero.todo.domain.entity;/**
 * Created by hcn on 2019/8/5.
 */

import java.sql.Date;
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
 * @program: hzero-order-24520->HodrItem
 * @description: 物料信息
 * @author: 胡超男
 * @create: 2019-08-05 14:12
 **/
@ApiModel("物料信息")
@ModifyAudit
@VersionAudit
@Table(name = "hodr_item_24520") //表映射
@JsonInclude(JsonInclude.Include.NON_NULL) //数据返回前端时，排除为空的字段
public class HodrItem extends AuditDomain {
    @Id
    @GeneratedValue
    private Long itemId;
    @Length(max = 60)
    @NotBlank
    @ApiModelProperty("物料编码")
    private String itemCode;
    @Length(max = 60)
    @NotBlank
    @ApiModelProperty("物料单位")
    private String itemUom;
    @NotBlank
    @ApiModelProperty("物料描述")
    private String itemDescription;
    @NotBlank
    @ApiModelProperty("可销售标识")
    private Integer saleableFlag;
    @ApiModelProperty("生效起始时间")
    private Date startActiveDate;
    @ApiModelProperty("生效结束时间")
    private Date endActiveDate;
    @NotBlank
    @ApiModelProperty("启用标识")
    private Integer enabledFlag;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemUom() {
        return itemUom;
    }

    public void setItemUom(String itemUom) {
        this.itemUom = itemUom;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Integer getSaleableFlag() {
        return saleableFlag;
    }

    public void setSaleableFlag(Integer saleableFlag) {
        this.saleableFlag = saleableFlag;
    }

    public Date getStartActiveDate() {
        return startActiveDate;
    }

    public void setStartActiveDate(Date startActiveDate) {
        this.startActiveDate = startActiveDate;
    }

    public Date getEndActiveDate() {
        return endActiveDate;
    }

    public void setEndActiveDate(Date endActiveDate) {
        this.endActiveDate = endActiveDate;
    }

    public Integer getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(Integer enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    @Override
    public String toString() {
        return "HodrItem{" +
                "itemId=" + itemId +
                ", itemCode='" + itemCode + '\'' +
                ", itemUom='" + itemUom + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", saleableFlag=" + saleableFlag +
                ", startActiveDate=" + startActiveDate +
                ", endActiveDate=" + endActiveDate +
                ", enabledFlag=" + enabledFlag +
                '}';
    }
}
