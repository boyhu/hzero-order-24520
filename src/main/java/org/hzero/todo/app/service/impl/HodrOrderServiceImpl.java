package org.hzero.todo.app.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hzero.mybatis.domian.Condition;
import org.hzero.todo.api.dto.*;
import org.hzero.todo.app.service.HodrCompanyService;
import org.hzero.todo.app.service.HodrCustomerService;
import org.hzero.todo.app.service.HodrOrderService;
import org.hzero.todo.app.service.HodrSoHeaderService;
import org.hzero.todo.domain.entity.HodrItem;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.entity.HodrSoLine;
import org.hzero.todo.domain.repository.HodrItemRepository;
import org.hzero.todo.domain.repository.HodrSoHeaderRepository;
import org.hzero.todo.domain.repository.HodrSoLineRepository;
import org.hzero.todo.infra.mapper.HodrSoHeaderMapper;
import org.hzero.todo.infra.mapper.HodrSoLineMapper;
import org.hzero.todo.infra.mapper.OrderInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: hzero-order-24520->HodrItemServiceImpl
 * @description: 订单应用服务实现
 * @author: 胡超男
 * @create: 2019-08-05 15:18
 **/
@Service
public class HodrOrderServiceImpl implements HodrOrderService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private HodrSoHeaderRepository soHeaderRepository;
    @Autowired
    private HodrSoHeaderMapper soHeaderMapper;
    @Autowired
    private HodrSoLineMapper soLineMapper;
    @Autowired
    private HodrItemRepository itemRepository;
    @Autowired
    private HodrSoLineRepository soLineRepository;
    @Autowired
    private HodrCompanyService companyService;
    @Autowired
    private HodrCustomerService customerService;
    @Autowired
    private HodrSoHeaderService soHeaderService;

    /**
     * 根据搜索条件查询订单信息
     * @param searchOrder 搜索条件
     * @return List<OrderInfo> 订单信息列表
     */
    @Override
    public List<OrderInfo> queryOrderInfos(SearchOrder searchOrder) {
        List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
        orderInfoList = orderInfoMapper.queryOrderInfo(searchOrder);
        return orderInfoList;
    }

    /**
     * 根据订单号查询订单明细
     * @param orderNumber 订单号
     * @return List<OrderDetailInfo> 订单明细列表
     */
    @Override
    public List<OrderDetailInfo> queryOrderDetailInfosByOrderNumber(String orderNumber) {
        List<OrderDetailInfo> orderDetailInfos = new ArrayList<OrderDetailInfo>();
        orderDetailInfos = orderInfoMapper.queryOrderDetailInfo(orderNumber);
        return orderDetailInfos;
    }

    /**
     * 更新订单明细
     * @param orderDetailInfo 订单明细
     * @return String 提示信息
     */
    @Override
    public String updateOrderDeatailInfo(OrderDetailInfo orderDetailInfo) {
        try{
            //根据订单编号查询到对应的HodrSoHear对象
            List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderNumber", orderDetailInfo.getOrderNumber());
            if(soHeaders != null && soHeaders.size() > 0){
                HodrSoHeader soHeader = soHeaders.get(0);
                //对查询到的HodrSoHear对象更新属性值
                soHeader.setCompanyId(orderDetailInfo.getCompanyId());
                soHeader.setOrderStatus(orderDetailInfo.getOrderStatus());
                soHeader.setCustomerId(orderDetailInfo.getCustomerId());
                //将更新后的HodrSoHear对象更新到数据库中
                soHeaderMapper.updateByPrimaryKey(soHeader);
                //根据soHeaderId和lineNumber查询到对应的HodrSoLine对象
                Long soHeaderId = soHeader.getSoHeaderId();
                Integer lineNumber = orderDetailInfo.getLineNumber();
                //创建并设置查询条件
                Condition condition = new Condition(HodrSoLine.class);
                Condition.Criteria criteria = condition.createCriteria();
                criteria.andEqualTo("soHeaderId",soHeaderId);
                criteria.andEqualTo("lineNumber",lineNumber);
                List<HodrSoLine> hodrSoLines = soLineMapper.selectByCondition(condition);
                if(hodrSoLines != null && hodrSoLines.size() > 0){
                    HodrSoLine soLine = hodrSoLines.get(0);
                    //根据传到后台的物料编码去查询物料ID的值
                    List<HodrItem> items = itemRepository.select("itemCode", orderDetailInfo.getItemCode());
                    if(items != null && items.size() > 0){
                        Long itemId = items.get(0).getItemId();
                        soLine.setItemId(itemId);
                    }
                    soLine.setOrderQuantity(orderDetailInfo.getOrderQuantity());
                    soLine.setOrderQuantityUom(orderDetailInfo.getOrderQuantityUom());
                    soLine.setUnitSellingPrice(orderDetailInfo.getUnitSellingPrice());
                    soLine.setDescription(orderDetailInfo.getDescription());
                    soLine.setAddition1(orderDetailInfo.getAddition1());
                    soLine.setAddition2(orderDetailInfo.getAddition2());
                    soLine.setAddition3(orderDetailInfo.getAddition3());
                    soLine.setAddition4(orderDetailInfo.getAddition4());
                    soLine.setAddition5(orderDetailInfo.getAddition5());
                    //将构建好的HodrSoLine对象更新到数据库中
                    soLineMapper.updateByPrimaryKey(soLine);
                    return "更新成功！";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return "更新失败！";
        }
        return "更新失败！";
    }

    /**
     * 更新订单状态
     * @param orderNumber 订单编号
     * @param orderStatus 订单状态
     * @return List<OrderDetailInfo> 订单明细
     */
    @Override
    public List<OrderDetailInfo> updateOrderStatus(String orderNumber, String orderStatus) {
        //根据订单编号查询对应的HodrSoHeader对象
        List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderNumber", orderNumber);
        if (soHeaders != null && soHeaders.size() > 0){
            HodrSoHeader soHeader = soHeaders.get(0);
            //更新订单状态
            soHeader.setOrderStatus(orderStatus);
            //将HodrSoHeader对象同步到数据库
            soHeaderMapper.updateByPrimaryKey(soHeader);
            //根据订单编号重新查询订单明细
            List<OrderDetailInfo> orderDetailInfos = queryOrderDetailInfosByOrderNumber(orderNumber);
            return orderDetailInfos;
        }
        return null;
    }

    /**
     * 整单删除
     * @param orderNumber 订单编号
     * @return String 提示信息
     */
    @Override
    public String deleteOrder(String orderNumber) {
        //根据orderNumber获取soHeaderId
        List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderNumber", orderNumber);
        if(soHeaders != null && soHeaders.size() > 0){
            HodrSoHeader soHeader = soHeaders.get(0);
            Long soHeaderId = soHeader.getSoHeaderId();
            //先删除订单行信息
            List<HodrSoLine> soLines = soLineRepository.select("soHeaderId", soHeaderId);
            if(soLines != null && soLines.size() > 0){
                soLineRepository.batchDelete(soLines);
            }
            //再删除订单头信息
            soHeaderRepository.delete(soHeader);
            return "删除成功！";
        }
        return "删除失败！";
    }

    /**
     * 根据订单编码查询最大行号
     * @param orderNumber 订单编号
     * @return Integer 最大行号
     */
    @Override
    public Integer selectMaxLineNumberByOrderNumber(String orderNumber) {
        List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderNumber", orderNumber);
        if (soHeaders != null && soHeaders.size() > 0){
            Long soHeaderId = soHeaders.get(0).getSoHeaderId();
            return soLineMapper.selectMaxLineNumberBySoHeadId(soHeaderId);
        }
        return 0;
    }

    /**
     * 根据搜索条件查询订单明细
     * @param searchOrder 搜索条件
     * @return List<OrderDetailInfo>
     */
    @Override
    public List<ExportOrderInfoDTO > queryOrderDetailInfosBySearchOrder(SearchOrder searchOrder) {
        List<ExportOrderInfoDTO > orderDetailInfos = new ArrayList<>();
        orderDetailInfos = orderInfoMapper.queryOrderDetailInfoBySearchOrder(searchOrder);
        return orderDetailInfos;
    }

    /**
     * 订单导入
     * @param importInfo
     */
    @Override
    public void importOrderDetailInfo(ExportOrderInfoDTO importInfo) {
        //根据订单编码去判断订单是否已存在
        Condition condition = new Condition(HodrSoHeader.class);
        Condition.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("orderNumber",importInfo.getOrderNumber());
        List<HodrSoHeader> hodrSoHeaders = soHeaderRepository.selectByCondition(condition);
        if(hodrSoHeaders != null && hodrSoHeaders.size() > 0){
            //如果订单已存在，无须向数据库中添加HodrSoHeader对象，只需添加HodrSoLine即可
            //获取soHeader的soHeaderId的值
            Long soHeaderId = hodrSoHeaders.get(0).getSoHeaderId();
            //构建HodrSoLine对象并对其赋值
            HodrSoLine soLine = new HodrSoLine();
            soLine.setSoHeaderId(soHeaderId);
            soLine.setLineNumber(importInfo.getLineNumber());
            //根据ItemCode去查询ItemId
            List<HodrItem> items = itemRepository.select("itemCode", importInfo.getItemCode());
            if(items != null && items.size() > 0){
                soLine.setItemId(items.get(0).getItemId());
            }
            soLine.setOrderQuantity(importInfo.getOrderQuantity());
            soLine.setOrderQuantityUom(importInfo.getOrderQuantityUom());
            soLine.setUnitSellingPrice(importInfo.getUnitSellingPrice());
            soLine.setDescription(importInfo.getDescription());
            soLine.setAddition1(importInfo.getAddition1());
            soLine.setAddition2(importInfo.getAddition2());
            soLine.setAddition3(importInfo.getAddition3());
            soLine.setAddition4(importInfo.getAddition4());
            soLine.setAddition5(importInfo.getAddition5());
            //将soLine插入到数据库中去
            soLineRepository.insert(soLine);
        }else{
            //如果订单不存在，则须向数据库中添加HodrSoHeader和HodrSoLine对象
            HodrSoHeader soHeader = new HodrSoHeader();
            soHeader.setOrderNumber(importInfo.getOrderNumber());
            soHeader.setCompanyId(companyService.selectCompanyIdByCompanyNumber(importInfo.getCompanyNumber()));
            soHeader.setOrderDate(importInfo.getOrderDate());
            soHeader.setOrderStatus(importInfo.getOrderStatus() == "新建"? "NEW":"");
            soHeader.setCustomerId(customerService.selectCustomerIdByCustomerNumber(importInfo.getCustomerNumber()));
            //将数据插入到数据库中
            soHeaderMapper.insert(soHeader);
            //构建soLine对象并对其赋值
            HodrSoLine soLine = new HodrSoLine();
            Long soHeaderId = soHeaderService.selectSoHeadIdByOrderNumber(importInfo.getOrderNumber());
            soLine.setSoHeaderId(soHeaderId);
            soLine.setLineNumber(importInfo.getLineNumber());
            //根据ItemCode去查询ItemId
            List<HodrItem> items = itemRepository.select("itemCode", importInfo.getItemCode());
            if(items != null && items.size() > 0){
                soLine.setItemId(items.get(0).getItemId());
            }
            soLine.setOrderQuantity(importInfo.getOrderQuantity());
            soLine.setOrderQuantityUom(importInfo.getOrderQuantityUom());
            soLine.setUnitSellingPrice(importInfo.getUnitSellingPrice());
            soLine.setDescription(importInfo.getDescription());
            soLine.setAddition1(importInfo.getAddition1());
            soLine.setAddition2(importInfo.getAddition2());
            soLine.setAddition3(importInfo.getAddition3());
            soLine.setAddition4(importInfo.getAddition4());
            soLine.setAddition5(importInfo.getAddition5());
            //将soLine插入到数据库中去
            soLineRepository.insert(soLine);
        }
    }

    /**
     * 订单明细录入
     * @param orderInfo 订单头与行列表组合信息
     * @return String 提示信息
     */
    @Override
    public String craeteOrdeDeatailInfo(SoHeaderAndLineListDTO orderInfo) {
        try {
            HodrSoHeader soHeader = orderInfo.getSoHeader();
            List<HodrSoLine> soLines = orderInfo.getSoLines();
            //根据传入的订单编号去查询订单，判断这个订单是否已存在
            Condition condition = new Condition(HodrSoHeader.class);
            Condition.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("orderNumber",soHeader.getOrderNumber());
            List<HodrSoHeader> hodrSoHeaders = soHeaderRepository.selectByCondition(condition);
            if(hodrSoHeaders != null && hodrSoHeaders.size() > 0){
                //如果订单已存在，无须向数据库中添加HodrSoHeader对象，只需添加HodrSoLine即可
                //获取soHeader的soHeaderId的值
                Long soHeaderId = hodrSoHeaders.get(0).getSoHeaderId();
                //遍历soLines，将每一个soLine对象插入到数据库中
                for (HodrSoLine soLine : soLines) {
                    soLine.setSoHeaderId(soHeaderId);
                    soLineMapper.insert(soLine);
                }
                return "录入成功！";
            }else{
                //订单不存在，则须向数据库中添加HodrSoHeader和HodrSoLine对象
                //HodrSoHeader对象设置订单日期值
                soHeader.setOrderDate(new Date(new java.util.Date().getTime()));
                //将HodrSoHeader对象插入到数据库中
                soHeaderMapper.insert(soHeader);
                //获取当前数据库中最大的SoHeaderId
                Long maxSoHeaderId = soHeaderMapper.selectMaxSoHeaderId();
                //遍历soLines，将每一个soLine对象插入到数据库中
                for (HodrSoLine soLine : soLines) {
                    soLine.setSoHeaderId(maxSoHeaderId);
                    soLineMapper.insert(soLine);
                }
                return "录入成功！";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "录入失败！";
        }
    }
}
