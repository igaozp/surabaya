package xyz.andornot.mapper;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import xyz.andornot.domain.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SqlSessionTest {

    @Test
    void selectAll() {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
            List<Employee> employees = employeeMapper.selectAll();
            assertFalse(employees.isEmpty());
            assertNotNull(employees.get(0).getFirstName());
        }
    }

    private static SqlSessionFactory getSqlSessionFactory() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                .addScripts("classpath:schema.sql", "classpath:data.sql");

        Environment environment = new Environment("development", new JdbcTransactionFactory(), embeddedDatabaseBuilder.build());
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(EmployeeMapper.class);
        configuration.setMapUnderscoreToCamelCase(true);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
