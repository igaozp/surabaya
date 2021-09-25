package xyz.andornot.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeMapperTest {
    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    void selectAll() {
        System.out.println(employeeMapper.selectAll());
    }
}