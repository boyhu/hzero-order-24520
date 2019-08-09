package org.hzero.todo.app.service.impl;/**
 * Created by hcn on 2019/8/5.
 */

import java.util.List;

import org.hzero.mybatis.domian.Condition;
import org.hzero.todo.app.service.HodrSoLineService;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.entity.HodrSoLine;
import org.hzero.todo.domain.repository.HodrSoHeaderRepository;
import org.hzero.todo.domain.repository.HodrSoLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: hzero-order-24520->HodrSoLineServiceImpl
 * @description: 订单行应用服务实现
 * @author: 胡超男
 * @create: 2019-08-05 15:20
 **/
@Service
public class HodrSoLineServiceImpl implements HodrSoLineService {

    @Autowired
    private HodrSoHeaderRepository soHeaderRepository;
    @Autowired
    private HodrSoLineRepository soLineRepository;

    /**
     * 订单行录入
     * @param soLine 订单行信息
     * @return String 提示信息
     */
    @Override
    public String insertHodrSoLine(HodrSoLine soLine) {
        //根据soHeaderId查询订单头是否存在
        HodrSoHeader soHeader = soHeaderRepository.selectByPrimaryKey(soLine.getSoHeaderId());
        if(soHeader != null){
            //如果存在，则将该订单行信息插入到数据库中
            soLineRepository.insert(soLine);
            return "订单行录入成功！";
        }else {
            //如果不存在，则提示订单头不存在，请先录入订单头
            return "订单头不存在，请先录入订单头！";
        }
    }

    /**
     * 订单行查询
     * @param soHeaderId 订单头ID
     * @param lineNumber 行号
     * @return HodrSoLine 订单行信息
     */
    @Override
    public HodrSoLine queryHodrSoLine(String soHeaderId, String lineNumber) {
        //创建返回结果
        HodrSoLine soLine = new HodrSoLine();
        //创建并设置查询条件
        Condition condition = new Condition(HodrSoLine.class);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("soHeaderId", soHeaderId);
        criteria.andEqualTo("lineNumber", lineNumber);
        //执行查询
        List<HodrSoLine> hodrSoLines = soLineRepository.selectByCondition(condition);
        if(hodrSoLines != null && hodrSoLines.size() > 0){
            soLine = hodrSoLines.get(0);
            //返回结果
            return soLine;
        }
        return soLine;
    }

    /**
     * 订单行更新
     * @param soLine 订单行信息
     * @return String 提示信息
     */
    @Override
    public String updateHodrSoLine(HodrSoLine soLine) {
        try {
            //根据soLine的soLineId获取数据库中原有的订单行数据
            HodrSoLine soLineDB = soLineRepository.selectByPrimaryKey(soLine.getSoLineId());
            //更新值
            soLineDB.setItemId(soLine.getItemId());
            soLineDB.setOrderQuantity(soLine.getOrderQuantity());
            soLine.setOrderQuantityUom(soLine.getOrderQuantityUom());
            soLineDB.setUnitSellingPrice(soLine.getUnitSellingPrice());
            //将数据同步到数据库中
            soLineRepository.updateByPrimaryKey(soLineDB);
            return "订单行更新成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "订单行更新失败！";
        }
    }
}
