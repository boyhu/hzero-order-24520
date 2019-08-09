package org.hzero.todo.infra.repository.impl;/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todo.domain.entity.HodrSoLine;
import org.hzero.todo.domain.repository.HodrSoLineRepository;
import org.springframework.stereotype.Component;

/**
 * @program: hzero-order-24520->HodrSoLineRepositoryImpl
 * @description: 订单行资源库实现
 * @author: 胡超男
 * @create: 2019-08-05 15:03
 **/
@Component
public class HodrSoLineRepositoryImpl extends BaseRepositoryImpl<HodrSoLine> implements HodrSoLineRepository {
}
