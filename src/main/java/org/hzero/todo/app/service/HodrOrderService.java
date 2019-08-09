package org.hzero.todo.app.service;/**
 * Created by hcn on 2019/8/5.
 */

import java.util.List;

import org.hzero.todo.api.dto.*;

/**
 * @program: hzero-order-24520->HodrItemService
 * @description: 订单应用服务
 * @author: 胡超男
 * @create: 2019-08-05 15:15
 **/
public interface HodrOrderService {
    List<OrderInfo> queryOrderInfos(SearchOrder searchOrder);
    List<OrderDetailInfo> queryOrderDetailInfosByOrderNumber(String orderNumber);
    String craeteOrdeDeatailInfo(SoHeaderAndLineListDTO orderInfo);
    String updateOrderDeatailInfo(OrderDetailInfo orderDetailInfo);
    List<OrderDetailInfo> updateOrderStatus(String orderNumber, String orderStatus);
    String deleteOrder(String orderNumber);
    Integer selectMaxLineNumberByOrderNumber(String orderNumber);
    List<ExportOrderInfoDTO> queryOrderDetailInfosBySearchOrder(SearchOrder searchOrder);
    void importOrderDetailInfo(ExportOrderInfoDTO exportOrderInfoDTO);
}
