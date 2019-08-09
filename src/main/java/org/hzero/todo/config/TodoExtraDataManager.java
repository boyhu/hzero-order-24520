package org.hzero.todo.config;/**
 * Created by hcn on 2019/7/30.
 */

import io.choerodon.core.swagger.ChoerodonRouteData;
import io.choerodon.swagger.annotation.ChoerodonExtraData;
import io.choerodon.swagger.swagger.extra.ExtraData;
import io.choerodon.swagger.swagger.extra.ExtraDataManager;

/**
 * @program: hzero-todo-servcie->TodoExtraDataManager
 * @description: 服务路由配置
 * @author: 胡超男
 * @create: 2019-07-30 18:29
 **/
@ChoerodonExtraData
public class TodoExtraDataManager implements ExtraDataManager {

    @Override
    public ExtraData getData() {
        ChoerodonRouteData choerodonRouteData = new ChoerodonRouteData();
        choerodonRouteData.setName("hodr-24520");
        choerodonRouteData.setPath("/order-24520/**");
        choerodonRouteData.setServiceId("hzero-order-24520");
//        choerodonRouteData.setName("todo");
//        choerodonRouteData.setPath("/todo/**");
//        choerodonRouteData.setServiceId("hzero-todo-service");
        extraData.put(ExtraData.ZUUL_ROUTE_DATA, choerodonRouteData);
        return extraData;
    }
}
