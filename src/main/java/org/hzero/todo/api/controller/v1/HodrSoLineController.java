package org.hzero.todo.api.controller.v1;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.todo.api.dto.ResultDTO;
import org.hzero.todo.app.service.HodrSoLineService;
import org.hzero.todo.config.SwaggerApiConfig;
import org.hzero.todo.domain.entity.HodrSoLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: hzero-order-24520->HodrSoLineController
 * @description: 订单行接口
 * @author: 胡超男
 * @create: 2019-08-05 15:34
 **/
@Api(tags = SwaggerApiConfig.HODRSOLINE)
@RestController("hodrSoLineController.v1")
@RequestMapping("/v1/{organizationId}/soLines")
public class HodrSoLineController {

    @Autowired
    private HodrSoLineService soLineService;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单行录入")
    @PostMapping
    public ResponseEntity<ResultDTO> insertHodrSoLine(@RequestBody HodrSoLine soLine){
        String message = soLineService.insertHodrSoLine(soLine);
        return Results.success(new ResultDTO(message));
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单行查询")
    @GetMapping("/{soHeaderId}/{lineNumber}")
    public ResponseEntity<HodrSoLine> queryHodrSoLine(@PathVariable String soHeaderId,
                                                      @PathVariable String lineNumber){
        HodrSoLine soLine = soLineService.queryHodrSoLine(soHeaderId, lineNumber);
        return Results.success(soLine);
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "订单行更新")
    @PutMapping
    public ResponseEntity<ResultDTO> updateHodrSoLine(@RequestBody HodrSoLine soLine){
        String message = soLineService.updateHodrSoLine(soLine);
        return Results.success(new ResultDTO(message));
    }
}
