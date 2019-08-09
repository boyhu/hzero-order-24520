package org.hzero.todo.app.service;/**
 * Created by hcn on 2019/8/5.
 */

import java.util.List;

import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.todo.domain.entity.HodrCompany;

/**
 * @program: hzero-order-24520->HodrCompanyService
 * @description: 公司应用服务
 * @author: 胡超男
 * @create: 2019-08-05 15:13
 **/
public interface HodrCompanyService {
    List<HodrCompany> pageCompany(String companyNumber, String companyName);
    Long selectCompanyIdByCompanyNumber(String companyNumber);
}
