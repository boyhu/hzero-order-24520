package org.hzero.todo.app.service;

import java.util.List;

import org.hzero.todo.domain.entity.HodrCustomer;

/**
 * Created by hcn on 2019/8/5.
 */
public interface HodrCustomerService {
    List<HodrCustomer> queryCustomer(Long companyId, String customerNumber, String customerName);
    Long selectCustomerIdByCustomerNumber(String customerNumber);
}
