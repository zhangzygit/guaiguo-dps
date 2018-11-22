package top.guaiguo.springdps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-11-21 17:03
 */
@Controller
@RequestMapping("/base")
public class BaseController {

    private ThreadLocal<Object> ss = new ThreadLocal<>();

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        ss.set("wwqe");
        ss.set("123");
        Object o = ss.get();
        return "string";
    }
}
