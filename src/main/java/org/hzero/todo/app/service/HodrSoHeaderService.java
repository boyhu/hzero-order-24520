package org.hzero.todo.app.service;
/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.todo.domain.entity.HodrSoHeader;

/**
 * @program: hzero-order-24520->HodrSoHeaderService
 * @description: 订单头应用服务
 * @author: 胡超男
 * @create: 2019-08-05 15:16
 **/
public interface HodrSoHeaderService {
    String insertHodrSoHeader(HodrSoHeader soHeader);
    HodrSoHeader queryHodrSoHeader(String orderNumber);
    String updateHodrSoHeader(HodrSoHeader soHeader);
    Long selectSoHeadIdByOrderNumber(String orderNumber);
}
