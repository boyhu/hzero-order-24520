package org.hzero.todo.infra.mapper;

import io.choerodon.mybatis.common.BaseMapper;
import org.hzero.todo.domain.entity.HodrSoLine;

/**
 * Created by hcn on 2019/8/5.
 */
public interface HodrSoLineMapper extends BaseMapper<HodrSoLine> {
    Integer selectMaxLineNumberBySoHeadId(Long soHeadId);
}
