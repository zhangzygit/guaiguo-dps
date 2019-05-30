package top.guaiguo.springdps.dubbo.impl;

import org.springframework.stereotype.Service;
import top.guaiguo.springdps.dubbo.DubboService;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-05-30 10:10
 */
//@Service(version = "0.0.1")
@Service
public class DubboServiceImpl implements DubboService {

    //@Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
