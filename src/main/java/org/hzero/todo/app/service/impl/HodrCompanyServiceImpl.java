package org.hzero.todo.app.service.impl;
/**
 * Created by hcn on 2019/8/5.
 */

import java.util.ArrayList;
import java.util.List;

import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import org.hzero.mybatis.domian.Condition;
import org.hzero.todo.app.service.HodrCompanyService;
import org.hzero.todo.domain.entity.HodrCompany;
import org.hzero.todo.domain.repository.HodrCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: hzero-order-24520->HodrCompanyServiceImpl
 * @description: 公司应用服务实现
 * @author: 胡超男
 * @create: 2019-08-05 15:17
 **/
@Service
public class HodrCompanyServiceImpl implements HodrCompanyService {

    @Autowired
    private HodrCompanyRepository companyRepository;

    /**
     * 基于公司ID，公司全称进行关联查询
     * @param companyNumber 公司ID
     * @param companyName 公司全称
     * @return List<HodrCompany> 查询得到的公司列表
     */
    @Override
    public List<HodrCompany> pageCompany(String companyNumber, String companyName) {
       //创建返回结果对象
        List<HodrCompany> hodrCompanies = new ArrayList<HodrCompany>();
        //创建查询条件对象
        Condition conditioin = new Condition(HodrCompany.class);
        Condition.Criteria criteria = conditioin.createCriteria();
        //判断companyNumber、companyName是否为空的情况
        //如果都不为空,根据companyNumber精准查询，根据companyName模糊查询
        if(!StringUtils.isEmpty(companyNumber) && !StringUtils.isEmpty(companyName)){
            criteria.andLike("companyName","%"+companyName+"%");
            criteria.andEqualTo("companyNumber",companyNumber);
        }else if (StringUtils.isEmpty(companyNumber) && !StringUtils.isEmpty(companyName)){
            //companyNumber为空，companyName不为空,只根据companyName模糊查询
            criteria.andLike("companyName","%"+companyName+"%");
        }else if(!StringUtils.isEmpty(companyNumber) && StringUtils.isEmpty(companyName)){
            //companyNumber不为空，companyName为空,只根据companyNumber精准查询
            criteria.andEqualTo("companyNumber",companyNumber);
        }else{
            //如果都为空，查询所有，即不加查询条件
        }
        //执行查询
        hodrCompanies = companyRepository.selectByCondition(conditioin);
        //返回结果
        return hodrCompanies;
    }

    /**
     * 根据公司编码查询公司ID
     * @param companyNumber 公司编码
     * @return Long 公司ID
     */
    @Override
    public Long selectCompanyIdByCompanyNumber(String companyNumber) {
        List<HodrCompany> companies = companyRepository.select("companyNumber", companyNumber);
        if(companies != null && companies.size() > 0){
            HodrCompany company = companies.get(0);
            return company.getCompanyId();
        }
        return null;
    }
}
