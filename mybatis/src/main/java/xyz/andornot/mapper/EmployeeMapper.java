package xyz.andornot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xyz.andornot.domain.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("""
            SELECT * FROM employee
            """)
    List<Employee> selectAll();
}
