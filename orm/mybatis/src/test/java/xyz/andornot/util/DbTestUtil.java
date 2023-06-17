package xyz.andornot.util;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;
import java.util.function.Consumer;

/**
 * 使用 SqlSession 实现的 MyBatis 测试工具类
 *
 * @author igaozp
 * @since 2022/11/12
 */
public class DbTestUtil {
    public static void runDbTest(List<Class<?>> mappers, Consumer<SqlSession> consumer) {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(mappers);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            consumer.accept(session);
        }
    }

    private static SqlSessionFactory getSqlSessionFactory(List<Class<?>> classes) {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder
                .setType(EmbeddedDatabaseType.H2)
                .setName("testdb")
                .addScripts("classpath:schema.sql", "classpath:data.sql");

        Environment environment = new Environment("development", new JdbcTransactionFactory(), embeddedDatabaseBuilder.build());
        Configuration configuration = new Configuration(environment);
        classes.forEach(configuration::addMapper);
        configuration.setMapUnderscoreToCamelCase(true);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}
