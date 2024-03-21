package xyz.andornot.service;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.andornot.mapper.EmployeeMapper;

@Service
public class EmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;

    @Transactional
    public boolean twiceQueryCheck() {
        var first = employeeMapper.getByFirstName("Lokesh");
        var second = employeeMapper.getByFirstName("Lokesh");
        // the query results are the same object, so modifying the first variable
        // also updates the second variable
        first.setId(0);
        return first.equals(second);
    }

    @Transactional
    public boolean twiceQueryCheckNoCache() {
        var first = employeeMapper.getByFirstNameNoCache("Lokesh");
        var second = employeeMapper.getByFirstNameNoCache("Lokesh");
        // the query isn't using mybatis first-level cache, so the results return different variables
        first.setId(0);
        return first.equals(second);
    }
}
