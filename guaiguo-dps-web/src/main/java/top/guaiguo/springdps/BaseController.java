package top.guaiguo.springdps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.guaiguo.springdps.model.Student;

/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2018-11-21 17:03
 */
@Controller
@RequestMapping("/base")
@RefreshScope
public class BaseController {

    private ThreadLocal<Object> ss = new ThreadLocal<>();

    @Autowired
    private StudentService studentService;

    //http://localhost:8888/guaiguo-dps-web/pro/master
    @Value("${test-sprig-cloud-config-url}")
    private String url;

    @RequestMapping("/index")
    @ResponseBody
    public String index() {
        studentService.addStudent();
        return "success";
    }

    @RequestMapping("/update/{stuId}")
    @ResponseBody
    public String updateAge(@PathVariable("stuId") Long stuId) {
        studentService.updateAge(stuId);
        return "success";
    }


    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(HttpServletRequest request) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAge(i + 1);
            student.setName("name--->" + (i + 1));
            student.setBirthday(new Date());
            list.add(student);
        }
        request.setAttribute("studentList", list);
        return "hello";
    }

    @RequestMapping("/testCloudConfig")
    @ResponseBody
    public String testCloudConfig() {
        return url;
    }

}
