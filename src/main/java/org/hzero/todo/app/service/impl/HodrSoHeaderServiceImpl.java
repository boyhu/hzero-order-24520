package org.hzero.todo.app.service.impl;/**
 * Created by hcn on 2019/8/5.
 */

import java.sql.Date;
import java.util.List;

import org.hzero.todo.app.service.HodrSoHeaderService;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.repository.HodrSoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: hzero-order-24520->HodrSoHeaderServiceImpl
 * @description: 订单头应用服务实现
 * @author: 胡超男
 * @create: 2019-08-05 15:19
 **/
@Service
public class HodrSoHeaderServiceImpl implements HodrSoHeaderService {

    @Autowired
    private HodrSoHeaderRepository soHeaderRepository;

    /**
     * 订单头信息录入
     * @param soHeader 订单头信息
     * @return String 提示信息
     */
    @Override
    public String insertHodrSoHeader(HodrSoHeader soHeader) {
        //判断订单编号是否存在
        List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderNumber", soHeader.getOrderNumber());
        if(soHeaders != null && soHeaders.size() > 0){
            //如果存在，提示订单编号已存在，请重新输入
            return "订单编号已存在，请重新输入！";
        }else {
            //如果不存在，将该订单插入到数据库中
            soHeader.setOrderDate(new Date(new java.util.Date().getTime()));
            soHeaderRepository.insert(soHeader);
            return "订单头录入成功！";
        }
    }

    /**
     * 查看订单头信息
     * @param orderNumber 订单编号
     * @return HodrSoHeader 订单头信息
     */
    @Override
    public HodrSoHeader queryHodrSoHeader(String orderNumber) {
        //创建返回结果
        HodrSoHeader soHeader = new HodrSoHeader();
        List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderNumber", orderNumber);
        if(soHeaders != null && soHeaders.size() > 0){
            soHeader = soHeaders.get(0);
        }
        return soHeader;
    }

    /**
     * 更新订单头
     * @param soHeader
     * @return
     */
    @Override
    public String updateHodrSoHeader(HodrSoHeader soHeader) {
        try {
            //根据soHeader的orderNumber获取数据库中原有的订单头数据
            HodrSoHeader soHeaderDB = soHeaderRepository.selectByPrimaryKey(soHeader.getSoHeaderId());
            soHeaderDB.setOrderNumber(soHeader.getOrderNumber());
            soHeaderDB.setCompanyId(soHeader.getCompanyId());
            soHeaderDB.setOrderStatus(soHeader.getOrderStatus());
            soHeaderDB.setCustomerId(soHeader.getCustomerId());
            //将更新后的soHeaderDB对象同步到数据库中
            soHeaderRepository.updateByPrimaryKey(soHeaderDB);
            return "更新成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "更新失败！";
        }
    }

    /**
     * 根据订单编号查询订单头ID
     * @param orderNumber 订单编号
     * @return 订单头ID
     */
    @Override
    public Long selectSoHeadIdByOrderNumber(String orderNumber) {
        List<HodrSoHeader> soHeads = soHeaderRepository.select("orderNumber", orderNumber);
        if(soHeads != null && soHeads.size() > 0){
            return soHeads.get(0).getSoHeaderId();
        }
        return null;
    }
}
