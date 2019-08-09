package org.hzero.todo.api.dto;

import java.util.List;

import org.hzero.todo.domain.entity.HodrSoHeader;
import org.hzero.todo.domain.entity.HodrSoLine;

/**
 * @program: hzero-order-24520-dd->SoHeaderAndLineListDTO
 * @description:
 * @author: 胡超男
 * @create: 2019-08-08 16:03
 **/
public class SoHeaderAndLineListDTO {
    private HodrSoHeader soHeader;
    private List<HodrSoLine> soLines;

    public HodrSoHeader getSoHeader() {
        return soHeader;
    }

    public void setSoHeader(HodrSoHeader soHeader) {
        this.soHeader = soHeader;
    }

    public List<HodrSoLine> getSoLines() {
        return soLines;
    }

    public void setSoLines(List<HodrSoLine> soLines) {
        this.soLines = soLines;
    }
}
