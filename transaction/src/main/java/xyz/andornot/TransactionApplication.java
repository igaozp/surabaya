package xyz.andornot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 事务使用示例
 *
 * @author igaozp
 * @since 2022/7/8
 */
@SpringBootApplication
@EnableTransactionManagement
public class TransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }
}
