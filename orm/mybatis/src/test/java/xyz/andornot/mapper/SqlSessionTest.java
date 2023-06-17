package xyz.andornot.mapper;

import org.junit.jupiter.api.Test;
import xyz.andornot.domain.Employee;
import xyz.andornot.util.DbTestUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SqlSessionTest {

    @Test
    void selectAll() {
        DbTestUtil.runDbTest(List.of(EmployeeMapper.class), session -> {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employees = employeeMapper.selectAll();
            assertFalse(employees.isEmpty());
            assertNotNull(employees.get(0).getFirstName());
        });
    }
}
