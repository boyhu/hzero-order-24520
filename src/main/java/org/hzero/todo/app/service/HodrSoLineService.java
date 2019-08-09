package org.hzero.todo.app.service;
/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.todo.domain.entity.HodrSoLine;

/**
 * @program: hzero-order-24520->HodrSoLineRepository
 * @description: 订单行应用服务
 * @author: 胡超男
 * @create: 2019-08-05 15:16
 **/
public interface HodrSoLineService {
    String insertHodrSoLine(HodrSoLine soLine);
    HodrSoLine queryHodrSoLine(String soHeaderId, String lineNumber);
    String updateHodrSoLine(HodrSoLine soLine);
}
