package org.hzero.todo.infra.repository.impl;/**
 * Created by hcn on 2019/8/5.
 */

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.repository.HodrSoHeaderRepository;
import org.springframework.stereotype.Component;

/**
 * @program: hzero-order-24520->HodrSoHeaderRepositoryImpl
 * @description: 订单头资源库实现
 * @author: 胡超男
 * @create: 2019-08-05 15:02
 **/
@Component
public class HodrSoHeaderRepositoryImpl extends BaseRepositoryImpl<HodrSoHeader> implements HodrSoHeaderRepository {
}
