package top.guaiguo.springdps.dao;

import org.springframework.stereotype.Repository;
import top.guaiguo.springdps.model.Student;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
