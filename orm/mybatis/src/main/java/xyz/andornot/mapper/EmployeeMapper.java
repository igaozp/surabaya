package xyz.andornot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import xyz.andornot.domain.Employee;

import java.util.List;

/**
 * Employee mapper
 *
 * @author igaozp
 * @since 2021/11/10
 */
@Mapper
public interface EmployeeMapper {

    @Select("SELECT * FROM employee")
    List<Employee> selectAll();

    @Select("SELECT * FROM employee WHERE first_name = #{firstname}")
    Employee getByFirstName(String firstname);

    @Select("SELECT * FROM employee WHERE first_name = #{firstname}")
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    Employee getByFirstNameNoCache(String firstname);
}
