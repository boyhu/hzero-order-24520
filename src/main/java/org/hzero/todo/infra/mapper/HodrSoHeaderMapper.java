package org.hzero.todo.infra.mapper;

import java.util.List;

import io.choerodon.mybatis.common.BaseMapper;
import org.hzero.todo.domain.entity.HodrSoHeader;

/**
 * Created by hcn on 2019/8/5.
 */
public interface HodrSoHeaderMapper extends BaseMapper<HodrSoHeader> {
    Long selectMaxSoHeaderId();
    List<Long> selectRelationCustomerIdByCompanyId(Long companyId);
}
