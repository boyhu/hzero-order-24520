package org.hzero.todo.api.dto;/**
 * Created by hcn on 2019/8/6.
 */

import java.math.BigDecimal;

import org.hzero.export.annotation.ExcelColumn;
import org.hzero.export.annotation.ExcelSheet;

/**
 * @program: hzero-order-24520-dd->OrderDetailInfo
 * @description: 订单明细信息
 * @author: 胡超男
 * @create: 2019-08-06 17:50
 **/
@ExcelSheet(zh = "订单明细", en = "Order Detail")
public class OrderDetailInfo extends OrderInfo{
    //行号
    @ExcelColumn(zh = "行号", en = "lineNumber")
    private Integer lineNumber;
    //物料编码
    @ExcelColumn(zh = "物料编码", en = "itemCode")
    private String itemCode;
    //物料描述
    @ExcelColumn(zh = "物料描述", en = "itemDescription")
    private  String itemDescription;
    //产品单位
    @ExcelColumn(zh = "产品单位", en = "orderQuantityUom")
    private String orderQuantityUom;
    //数量
    @ExcelColumn(zh = "数量", en = "orderQuantity")
    private BigDecimal orderQuantity;
    //销售单价
    @ExcelColumn(zh = "销售单价", en = "unitSellingPrice")
    private BigDecimal unitSellingPrice;
    //行金额
    @ExcelColumn(zh = "行金额", en = "lineAmount")
    private BigDecimal lineAmount;
    //备注
    @ExcelColumn(zh = "备注", en = "description")
    private String description;

    private String addition1;
    private String addition2;
    private String addition3;
    private String addition4;
    private String addition5;
    private Long companyId;
    private Long customerId;

    //@ExcelColumn(zh = "备注", en = "description")
    //List<OrderDetailInfo> orderDetailInfoList;


//    public List<OrderDetailInfo> getOrderDetailInfoList() {
//        return orderDetailInfoList;
//    }
//
//    public void setOrderDetailInfoList(List<OrderDetailInfo> orderDetailInfoList) {
//        this.orderDetailInfoList = orderDetailInfoList;
//    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    public void setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getUnitSellingPrice() {
        return unitSellingPrice;
    }

    public void setUnitSellingPrice(BigDecimal unitSellingPrice) {
        this.unitSellingPrice = unitSellingPrice;
    }

    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
