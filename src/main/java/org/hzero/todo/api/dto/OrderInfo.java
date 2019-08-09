package org.hzero.todo.api.dto;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.choerodon.mybatis.domain.AuditDomain;
import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;

/**
 * @program: hzero-order-24520-dd->OrderInfo
 * @description: 订单信息类
 * @author: 胡超男
 * @create: 2019-08-06 15:19
 **/
@ExcelSheet(zh = "订单明细", en = "Order Detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderInfo extends AuditDomain {
    @Id
    private Long id;
    //订单编号
    @ExcelColumn(zh = "订单号", en = "orderNumber")
    private String orderNumber;
    //公司名称
    @ExcelColumn(zh = "公司名称", en = "companyName")
    public String companyName;
    //客户名称
    @ExcelColumn(zh = "客户名称", en = "customerName")
    private String customerName;
    //订单日期
    @ExcelColumn(zh = "订单日期", en = "creationDate")
    private Date orderDate;
    //订单状态
    @ExcelColumn(zh = "订单状态", en = "orderStatus")
    private String orderStatus;
    //订单金额
    @ExcelColumn(zh = "订单金额", en = "totalAmount")
    private BigDecimal totalAmount;

//    public Date getCreationDate() {
//        return this.creationDate;
//    }
//
//    public void setCreationDate(Date creationDate) {
//        this.creationDate = creationDate;
//    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", companyName='" + companyName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
