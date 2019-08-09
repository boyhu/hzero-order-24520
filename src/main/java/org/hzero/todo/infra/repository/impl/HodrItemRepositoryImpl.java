package org.hzero.todo.infra.repository.impl;/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todo.domain.entity.HodrItem;
import org.hzero.todo.domain.repository.HodrItemRepository;
import org.springframework.stereotype.Component;

/**
 * @program: hzero-order-24520->HodrItemRepositoryImpl
 * @description: 物料资源库实现
 * @author: 胡超男
 * @create: 2019-08-05 15:01
 **/
@Component
public class HodrItemRepositoryImpl extends BaseRepositoryImpl<HodrItem> implements HodrItemRepository {
}
