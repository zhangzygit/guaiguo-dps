package top.guaiguo.springdps;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private StudentService studentService;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        ss.set("wwqe");
        ss.set("1232131231");
        return ss.get().toString();
    }


    @RequestMapping("/addStudent")
    @ResponseBody
    public String addStudent() {
        studentService.addStudent();
        return "success";
    }
}
