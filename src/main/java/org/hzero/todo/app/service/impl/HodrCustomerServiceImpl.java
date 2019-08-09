package org.hzero.todo.app.service.impl;/**
 * Created by hcn on 2019/8/5.
 */

import java.util.ArrayList;
import java.util.List;

import org.hzero.mybatis.domian.Condition;
import org.hzero.todo.app.service.HodrCustomerService;
import org.hzero.todo.domain.entity.HodrCustomer;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.repository.HodrCustomerRepository;
import org.hzero.todo.domain.repository.HodrSoHeaderRepository;
import org.hzero.todo.infra.mapper.HodrSoHeaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: hzero-order-24520->HodrCustomerServiceImpl
 * @description: 客户应用服务实现
 * @author: 胡超男
 * @create: 2019-08-05 15:18
 **/
@Service
public class HodrCustomerServiceImpl implements HodrCustomerService {

    @Autowired
    private HodrCustomerRepository customerRepository;
    @Autowired
    private HodrSoHeaderRepository soHeaderRepository;
    @Autowired
    private HodrSoHeaderMapper soHeaderMapper;

    /**
     * 根据公司Id，客户编码，客户名称进行关联查询
     * @param companyId 公司Id
     * @param customerNumber 客户编码
     * @param customerName 客户名称
     * @return List<HodrCustomer> 客户列表
     */
    @Override
    public List<HodrCustomer> queryCustomer(Long companyId, String customerNumber, String customerName) {
        //创建返回结果对象
        List<HodrCustomer> customers = new ArrayList<HodrCustomer>();
        //创建查询条件对象
        Condition conditioin = new Condition(HodrCustomer.class);
        Condition.Criteria criteria = conditioin.createCriteria();
        //判断公司ID是否为空
        //如果公司ID为空，则根据customerId，customerName查询客户
        if (StringUtils.isEmpty(companyId)) {
            //判断customerId，customerName是否为空
            if (!StringUtils.isEmpty(customerNumber) && !StringUtils.isEmpty(customerName)) {
                //如果都不为空，则根据customerId精准查询，根据customerName模糊查询
                criteria.andEqualTo("customerNumber", customerNumber);
                criteria.andLike("customerName", "%" + customerName + "%");
            } else if (StringUtils.isEmpty(customerNumber) && !StringUtils.isEmpty(customerName)) {
                //如果customerId为空，customerName不为空，则根据customerName模糊查询
                criteria.andLike("customerName", "%" + customerName + "%");
            } else if (!StringUtils.isEmpty(customerNumber) && StringUtils.isEmpty(customerName)) {
                //如果customerId不为空，customerName为空，则根据customerId精准查询
                criteria.andEqualTo("customerNumber", customerNumber);
            } else {
                //如果都为空，则查询所有，即不用添加查询条件
            }
            //执行查询
            customers = customerRepository.selectByCondition(conditioin);
            //返回结果
            return customers;
        } else {
            //如果公司id不为空，则根据该公司ID查询与其有关联的客户信息
            List<HodrCustomer> allCustomers = new ArrayList<HodrCustomer>();
            //根据公司ID来查询与其关联的客户ID
            List<Long> customerIdLs = soHeaderMapper.selectRelationCustomerIdByCompanyId(companyId);
            //遍历客户ID，获取所有与其关联的客户信息
            for (Long customerIdL : customerIdLs) {
                //根据客户ID获取客户对象
                HodrCustomer hodrCustomer = customerRepository.selectByPrimaryKey(customerIdL);
                //将客户对象添加到list中
                //此时得到的是与该公司ID关联的所有客户对象，但还要考虑customerId，customerName是否为空
                allCustomers.add(hodrCustomer);
            }
            //判断customerId，customerName是否为空
            if(StringUtils.isEmpty(customerNumber) && StringUtils.isEmpty(customerName)){
                //返回结果
                return allCustomers;
            }else if (!StringUtils.isEmpty(customerNumber) && !StringUtils.isEmpty(customerName)) {
                //如果都不为空，则根据customerId精准查询，根据customerName模糊查询
                criteria.andEqualTo("customerNumber", customerNumber);
                criteria.andLike("customerName", "%" + customerName + "%");
            } else if (StringUtils.isEmpty(customerNumber) && !StringUtils.isEmpty(customerName)) {
                //如果customerId为空，customerName不为空，则根据customerName模糊查询
                criteria.andLike("customerName", "%" + customerName + "%");
            } else {
                //如果customerId不为空，customerName为空，则根据customerId精准查询
                criteria.andEqualTo("customerNumber", customerNumber);
            }
            //如果都为空，则查询所有与该公司ID关联的客户对象，即不用添加查询条件
            //此时获取的是根据customerId，customerName查询到的客户信息
            List<HodrCustomer> queryCustomers = customerRepository.selectByCondition(conditioin);
            //创建返回结果对象
            List<HodrCustomer> result = new ArrayList<HodrCustomer>();
            //遍历allCustomers，queryCustomers，取其交集
            for (HodrCustomer customer : allCustomers) {
                for(HodrCustomer cus : queryCustomers){
                    if(customer.getCustomerId().longValue() == cus.getCustomerId().longValue()){
                        result.add(customer);
                    }
                }
            }
            //返回结果
            return result;
        }
    }

    /**
     * 根据客户编码查询客户ID
     * @param customerNumber 客户编码
     * @return Long 客户ID
     */
    @Override
    public Long selectCustomerIdByCustomerNumber(String customerNumber) {
        List<HodrCustomer> customers = customerRepository.select("customerNumber", customerNumber);
        if(customers != null && customers.size() > 0){
            HodrCustomer customer = customers.get(0);
            return customer.getCustomerId();
        }
        return null;
    }
}
