package org.hzero.todo.app.service;

import java.util.List;
import java.util.Map;

import org.hzero.boot.scheduler.infra.annotation.JobHandler;
import org.hzero.boot.scheduler.infra.enums.ReturnT;
import org.hzero.boot.scheduler.infra.handler.IJobHandler;
import org.hzero.boot.scheduler.infra.tool.SchedulerTool;
import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.repository.HodrSoHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: hzero-order-24520->JobService
 * @description:
 * @author: 胡超男
 * @create: 2019-08-09 16:57
 **/
@JobHandler("orderHcn")
public class JobService implements IJobHandler{
    @Autowired
    private HodrSoHeaderRepository soHeaderRepository;

    @Override
    public ReturnT execute(Map<String, String> map, SchedulerTool schedulerTool) {
        String status = map.get("status");
        List<HodrSoHeader> soHeaders = soHeaderRepository.select("orderStatus", status);
        if(soHeaders != null && soHeaders.size() > 0){
            for (HodrSoHeader soHeader : soHeaders) {
                soHeader.setOrderStatus("CLOSED");
                soHeaderRepository.updateByPrimaryKey(soHeader);
            }
        }
        return ReturnT.SUCCESS;
    }
}
