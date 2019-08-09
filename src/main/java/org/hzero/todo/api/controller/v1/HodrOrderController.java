package org.hzero.todo.api.controller.v1;/**
 * Created by hcn on 2019/8/6.
 */

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.base.BaseController;
import org.hzero.core.util.Results;
import org.hzero.export.annotation.ExcelExport;
import org.hzero.export.constant.ExportType;
import org.hzero.export.vo.ExportParam;
import org.hzero.todo.api.dto.*;
import org.hzero.todo.app.service.HodrOrderService;
import org.hzero.todo.config.SwaggerApiConfig;
import org.hzero.todo.infra.mapper.HodrSoLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: hzero-order-24520-dd->HodrOrderController
 * @description: 订单接口
 * @author: 胡超男
 * @create: 2019-08-06 16:50
 **/
@Api(tags = SwaggerApiConfig.HODRORDER)
@RestController("hodrOrderController.v1")
@RequestMapping("/v1/{organizationId}/orders")
public class HodrOrderController extends BaseController {

    @Autowired
    private HodrOrderService orderService;
    @Autowired
    private HodrSoLineMapper soLineMapper;


    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "查询订单")
    @GetMapping
    public ResponseEntity<List<OrderInfo>> queryOrderInfos(SearchOrder searchOrder){
        List<OrderInfo> orderInfos = orderService.queryOrderInfos(searchOrder);
        return Results.success(orderInfos);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "根据订单号查询订单明细")
    @GetMapping("/{orderNumber}")
    public ResponseEntity<List<OrderDetailInfo>> queryOrderDetailInfosByOrderNumber(@PathVariable String orderNumber){
        List<OrderDetailInfo> orderDetailInfos = orderService.queryOrderDetailInfosByOrderNumber(orderNumber);
        return Results.success(orderDetailInfos);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单录入")
    @PostMapping
    public ResponseEntity<ResultDTO> craeteOrdeDeatailInfo(@RequestBody SoHeaderAndLineListDTO orderInfo){
        String message = orderService.craeteOrdeDeatailInfo(orderInfo);
        return Results.success(new ResultDTO(message));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "根据订单编号查询最大行号")
    @GetMapping("/selectMaxLineNumber/{orderNumber}")
    public ResponseEntity selectMax(@PathVariable String orderNumber){
        Integer maxLineNumber = orderService.selectMaxLineNumberByOrderNumber(orderNumber);
        return Results.success(maxLineNumber);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单更新")
    @PutMapping
    public ResponseEntity<ResultDTO> updateOrdeDeatailInfo(@RequestBody OrderDetailInfo orderDetailInfo){
        // 简单数据校验
        this.validObject(orderDetailInfo);
        // 数据防篡改校验
        //SecurityTokenHelper.validToken(orderDetailInfo);
        BigDecimal zero = new BigDecimal(0);
        if(orderDetailInfo.getOrderQuantity().compareTo(zero) == 1 && orderDetailInfo.getUnitSellingPrice().compareTo(zero) == 1){
            String message = orderService.updateOrderDeatailInfo(orderDetailInfo);
            return Results.success(new ResultDTO(message));
        }else {
            return Results.success(new ResultDTO("数据输入有误，请检查！"));
        }
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "更新订单状态")
    @GetMapping("/updateOrderStatus/{orderNumber}/{orderStatus}")
    public ResponseEntity<List<OrderDetailInfo>> updateOrderStatus(@PathVariable String orderNumber,
            @PathVariable String orderStatus){
        List<OrderDetailInfo> orderDetailInfos = orderService.updateOrderStatus(orderNumber, orderStatus);
        return Results.success(orderDetailInfos);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "整单删除")
    @DeleteMapping
    public ResponseEntity<ResultDTO> deleteOrder(String orderNumber){
        String message = orderService.deleteOrder(orderNumber);
        return Results.success(new ResultDTO(message));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "导出")
    @GetMapping("/export")
    @ExcelExport(ExportOrderInfoDTO .class)
    public ResponseEntity export(SearchOrder searchOrder, ExportParam exportParam, HttpServletResponse response) {
//        exportParam.setExportType(ExportType.DATA);
        List<ExportOrderInfoDTO > list = orderService.queryOrderDetailInfosBySearchOrder(searchOrder);
        return Results.success(list);
    }
}
