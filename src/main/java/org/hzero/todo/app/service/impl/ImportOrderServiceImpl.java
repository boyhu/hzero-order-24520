package org.hzero.todo.app.service.impl;/**
 * Created by hcn on 2019/8/9.
 */

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.hzero.todo.api.dto.ExportOrderInfoDTO;
import org.hzero.todo.app.service.HodrOrderService;
import org.hzero.todo.infra.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: hzero-order-24520->ImportOrderService
 * @description: 导入订单信息应用服务
 * @author: 胡超男
 * @create: 2019-08-09 11:40
 **/
@ImportService(templateCode = "HZERO-ORDER-24520")
class ImportOrderServiceImpl implements IDoImportService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HodrOrderService orderService;


    @Override
    public Boolean doImport(String data) {
        // 获取自定义参数
        ExportOrderInfoDTO orderInfoDTO;
        try {
            orderInfoDTO = objectMapper.readValue(data, ExportOrderInfoDTO.class);
        } catch (IOException e) {
            // 失败
            return false;
        }
        orderService.importOrderDetailInfo(orderInfoDTO);
         //成功
        return true;
    }
}
