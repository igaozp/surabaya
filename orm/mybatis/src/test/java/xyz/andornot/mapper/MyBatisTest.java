package xyz.andornot.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import xyz.andornot.domain.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
class MyBatisTest {
    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    void selectAll() {
        List<Employee> employees = employeeMapper.selectAll();
        assertFalse(employees.isEmpty());
        assertNotNull(employees.get(0).getFirstName());
    }
}
