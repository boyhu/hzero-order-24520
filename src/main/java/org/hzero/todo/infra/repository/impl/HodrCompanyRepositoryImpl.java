package org.hzero.todo.infra.repository.impl;
/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todo.domain.entity.HodrCompany;
import org.hzero.todo.domain.repository.HodrCompanyRepository;
import org.springframework.stereotype.Component;

/**
 * @program: hzero-order-24520->HodrCompanyRepositoryImpl
 * @description: 公司资源库实现
 * @author: 胡超男
 * @create: 2019-08-05 14:58
 **/
@Component
public class HodrCompanyRepositoryImpl extends BaseRepositoryImpl<HodrCompany> implements HodrCompanyRepository {
}
