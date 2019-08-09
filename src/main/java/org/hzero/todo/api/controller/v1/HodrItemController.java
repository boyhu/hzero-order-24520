package org.hzero.todo.api.controller.v1;

import java.util.List;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.todo.app.service.HodrItemService;
import org.hzero.todo.config.SwaggerApiConfig;
import org.hzero.todo.domain.entity.HodrItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hzero-order-24520->HodrItemController
 * @description: 物料接口
 * @author: 胡超男
 * @create: 2019-08-05 15:31
 **/
@Api(tags = SwaggerApiConfig.HODRITEM)
@RestController("hodrItemController.v1")
@RequestMapping("/v1/{organizationId}/items")
public class HodrItemController {

    @Autowired
    private HodrItemService itemService;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "基于物料编码，物料名称查询物料信息")
    @GetMapping
    public ResponseEntity<List<HodrItem>> queryItems(String itemCode, String itemDescription){
        List<HodrItem> items = itemService.queryItems(itemCode, itemDescription);
        return Results.success(items);
    }
}
