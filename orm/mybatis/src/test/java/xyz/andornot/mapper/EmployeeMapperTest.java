package xyz.andornot.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.andornot.domain.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * EmployeeMapperTest
 *
 * @author igaozp
 * @since 2021/11/10
 */
@SpringBootTest
class EmployeeMapperTest {
    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    void selectAll() {
        List<Employee> employees = employeeMapper.selectAll();
        assertFalse(employees.isEmpty());
        assertNotNull(employees.getFirst().getFirstName());
    }
}