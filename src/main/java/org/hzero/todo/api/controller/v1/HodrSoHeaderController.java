package org.hzero.todo.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.todo.api.dto.ResultDTO;
import org.hzero.todo.app.service.HodrSoHeaderService;
import org.hzero.todo.config.SwaggerApiConfig;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: hzero-order-24520->HodrSoHeaderController
 * @description: 订单头接口
 * @author: 胡超男
 * @create: 2019-08-05 15:32
 **/
@Api(tags = SwaggerApiConfig.HODRSOHEADER)
@RestController("hodrSoHeaderController.v1")
@RequestMapping("/v1/{organizationId}/soHeaders")
public class HodrSoHeaderController {

    @Autowired
    private HodrSoHeaderService soHeaderService;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单头录入")
    @PostMapping
    public ResponseEntity<ResultDTO> insertHodrSoHeader(@RequestBody HodrSoHeader soHeader){
        String message = soHeaderService.insertHodrSoHeader(soHeader);
        return Results.success(new ResultDTO(message));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单头查询")
    @GetMapping("/{orderNumber}")
    public ResponseEntity<HodrSoHeader> queryHodrSoHeader(@PathVariable String orderNumber){
        HodrSoHeader soHeader = soHeaderService.queryHodrSoHeader(orderNumber);
        return Results.success(soHeader);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单头更新")
    @PutMapping
    public ResponseEntity<ResultDTO> updateHodrSoHeader(@RequestBody HodrSoHeader soHeader){
        String message = soHeaderService.updateHodrSoHeader(soHeader);
        return Results.success(new ResultDTO(message));
    }

}
