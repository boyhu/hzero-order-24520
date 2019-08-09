package org.hzero.todo.api.dto;/**
 * Created by hcn on 2019/8/6.
 */

/**
 * @program: hzero-order-24520-dd->SearchOrder
 * @description: 查询订单的搜索条件
 * @author: 胡超男
 * @create: 2019-08-06 16:38
 **/
public class SearchOrder {
    //公司ID
    private Long companyId;
    //客户ID
    private Long customerId;
    //订单编号
    private String orderNumber;
    //物料ID
    private Long itemId;
    //订单状态
    private String orderStatus;


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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
