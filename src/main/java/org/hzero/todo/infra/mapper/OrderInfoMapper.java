package org.hzero.todo.infra.mapper;

import java.util.List;

import io.choerodon.mybatis.common.BaseMapper;
import org.hzero.todo.api.dto.ExportOrderInfoDTO;
import org.hzero.todo.api.dto.OrderDetailInfo;
import org.hzero.todo.api.dto.OrderInfo;
import org.hzero.todo.api.dto.SearchOrder;

/**
 * Created by hcn on 2019/8/6.
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    List<OrderInfo> queryOrderInfo(SearchOrder searchOrder);
    List<OrderDetailInfo> queryOrderDetailInfo(String orderNumber);
    List<ExportOrderInfoDTO> queryOrderDetailInfoBySearchOrder(SearchOrder order);
}
