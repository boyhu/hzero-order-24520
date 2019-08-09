package org.hzero.todo.app.service.impl;/**
 * Created by hcn on 2019/8/5.
 */

import java.util.ArrayList;
import java.util.List;

import org.hzero.mybatis.domian.Condition;
import org.hzero.todo.app.service.HodrItemService;
import org.hzero.todo.domain.entity.HodrItem;
import org.hzero.todo.domain.repository.HodrItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @program: hzero-order-24520->HodrItemServiceImpl
 * @description: 物料应用服务实现
 * @author: 胡超男
 * @create: 2019-08-05 15:18
 **/
@Service
public class HodrItemServiceImpl implements HodrItemService {

    @Autowired
    private HodrItemRepository itemRepository;

    /**
     * 基于物料编码，物料描述查询物料信息
     * @param itemCode 物料编码
     * @param itemDescription 物料描述
     * @return List<HodrItem> 物料列表信息
     */
    @Override
    public List<HodrItem> queryItems(String itemCode, String itemDescription) {
        //创建返回结果对象
        List<HodrItem> items = new ArrayList<HodrItem>();
        //创建查询条件对象
        Condition conditioin = new Condition(HodrItem.class);
        Condition.Criteria criteria = conditioin.createCriteria();
        //判断itemCode、itemDescription是否为空的情况
        if(!StringUtils.isEmpty(itemCode) && !StringUtils.isEmpty(itemDescription)){
            //两者都不为空，则根据itemCode精准查询，itemDescription模糊查询
            criteria.andEqualTo("itemCode",itemCode);
            criteria.andLike("itemDescription","%"+itemDescription+"%");
        }else if(StringUtils.isEmpty(itemCode) && !StringUtils.isEmpty(itemDescription)){
            //itemCode为空，itemDescription不为空，则根据itemDescription模糊查询
            criteria.andLike("itemDescription","%"+itemDescription+"%");
        }else if (!StringUtils.isEmpty(itemCode) && StringUtils.isEmpty(itemDescription)){
            //itemCode不为空，itemDescription为空，则根据itemCode精准查询
            criteria.andEqualTo("itemCode",itemCode);
        }else{
            //如果都为空，查询所有，即不加查询条件
        }
        //执行查询
        items = itemRepository.selectByCondition(conditioin);
        return items;
    }
}
