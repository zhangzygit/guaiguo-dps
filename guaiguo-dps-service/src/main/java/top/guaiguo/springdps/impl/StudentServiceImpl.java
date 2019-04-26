package top.guaiguo.springdps.impl;

import com.alibaba.dubbo.config.annotation.Service;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import top.guaiguo.springdps.StudentService;
import top.guaiguo.springdps.dao.StudentMapper;
import top.guaiguo.springdps.model.Student;


/**
 * Created with IntelliJ IDEA Ultimate.
 *
 * @Author zhangzhaoyuan
 * @Description
 * @Datetime 2019-03-04 11:20
 */
@Service(version = "1.0.0")
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addStudent() {
        Student student = new Student();
        student.setAge(28);
        student.setName("zhangzhaoyuan");
        stringRedisTemplate.opsForValue().set("1", "2");
        studentMapper.insertSelective(student);
    }
}
