package org.hzero.todo.app.service;/**
 * Created by hcn on 2019/8/5.
 */

import java.util.List;

import org.hzero.todo.domain.entity.HodrItem;

/**
 * @program: hzero-order-24520->HodrItemService
 * @description: 物料应用服务
 * @author: 胡超男
 * @create: 2019-08-05 15:15
 **/
public interface HodrItemService {
    List<HodrItem> queryItems(String itemCode, String itemDescription);
}
