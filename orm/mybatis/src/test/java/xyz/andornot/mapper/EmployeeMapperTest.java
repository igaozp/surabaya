package xyz.andornot.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.andornot.domain.Employee;
import xyz.andornot.service.EmployeeService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @Resource
    private EmployeeService employeeService;

    @Test
    void selectAll() {
        List<Employee> employees = employeeMapper.selectAll();
        assertFalse(employees.isEmpty());
        assertNotNull(employees.getFirst().getFirstName());
    }

    @Test
    void getByName() {
        assertTrue(employeeService.twiceQueryCheck());
    }

    @Test
    void getByNameNoCache() {
        assertFalse(employeeService.twiceQueryCheckNoCache());
    }
}