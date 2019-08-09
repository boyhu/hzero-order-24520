package org.hzero.todo.api.controller.v1;

import java.util.List;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hzero.core.util.Results;
import org.hzero.todo.app.service.HodrCompanyService;
import org.hzero.todo.config.SwaggerApiConfig;
import org.hzero.todo.domain.entity.HodrCompany;
import org.hzero.todo.domain.repository.HodrCompanyRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hzero-order-24520->HodrCompanyController
 * @description: 公司接口
 * @author: 胡超男
 * @create: 2019-08-05 15:28
 **/
@Api(tags = SwaggerApiConfig.HODRCOMPANY)
@RestController("hodrCompanyController.v1")
@RequestMapping("/v1/{organizationId}/companys")
public class HodrCompanyController {

    private final HodrCompanyRepository companyRepository;
    private final HodrCompanyService companyService;

    public HodrCompanyController(HodrCompanyRepository companyRepository, HodrCompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @Permission(level = ResourceLevel.ORGANIZATION)
    @ApiOperation(value = "基于公司编码，公司名称查询公司信息")
    @GetMapping
    public ResponseEntity<List<HodrCompany>> pageCompanies(String companyNumber,
                                                           String companyName){
          List<HodrCompany> hodrCompanies = companyService.pageCompany(companyNumber, companyName);
          return Results.success(hodrCompanies);
    }
}
