package org.hzero.todo.api.controller.v1;

import java.util.List;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.todo.app.service.HodrCustomerService;
import org.hzero.todo.config.SwaggerApiConfig;
import org.hzero.todo.domain.entity.HodrCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hzero-order-24520->HodrCustomerController
 * @description: 客户接口
 * @author: 胡超男
 * @create: 2019-08-05 15:30
 **/
@Api(tags = SwaggerApiConfig.HODRCUSTOMER)
@RestController("hodrCustomerController.v1")
@RequestMapping("/v1/{organizationId}/customers")
public class HodrCustomerController {

    @Autowired
    private HodrCustomerService customerService;

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "基于公司Id，客户编码，客户名称查询客户信息")
    @GetMapping
    public ResponseEntity<List<HodrCustomer>> queryCustomers(Long companyId, String customerNumber, String customerName){
        List<HodrCustomer> customers = customerService.queryCustomer(companyId, customerNumber, customerName);
        return Results.success(customers);
    }
}
