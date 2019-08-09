package org.hzero.todo.config;
/**
 * Created by hcn on 2019/7/30.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @program: hzero-todo-servcie->SwaggerApiConfig
 * @description: Swapper Api 描述配置
 * @author: 胡超男
 * @create: 2019-07-30 18:10
 **/
@Configuration
public class SwaggerApiConfig {
    public static final String HODRCOMPANY = "HodrCompany";
    public static final String HODRCUSTOMER = "HodrCustomer";
    public static final String HODRITEM = "HodrItem";
    public static final String HODRSOHEADER = "HodrSoHeader";
    public static final String HODRSOLINE = "HodrSoLine";
    public static final String HODRORDER = "HodrOrder";

    @Autowired
    public SwaggerApiConfig(Docket docket) {
        docket.tags(
                new Tag(HODRCOMPANY, "公司信息"),
                new Tag(HODRCUSTOMER, "客户信息"),
                new Tag(HODRITEM, "物料信息"),
                new Tag(HODRSOHEADER, "订单头信息"),
                new Tag(HODRSOLINE, "订单行信息"),
                new Tag(HODRORDER, "订单信息")
        );
    }
}
