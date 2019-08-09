package org.hzero.todo.infra.repository.impl;/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todo.domain.entity.HodrCustomer;
import org.hzero.todo.domain.repository.HodrCustomerRepository;
import org.springframework.stereotype.Component;

/**
 * @program: hzero-order-24520->HodrCustomerRepositoryImpl
 * @description: 客户资源库实现
 * @author: 胡超男
 * @create: 2019-08-05 14:59
 **/
@Component
public class HodrCustomerRepositoryImpl extends BaseRepositoryImpl<HodrCustomer> implements HodrCustomerRepository {
}
