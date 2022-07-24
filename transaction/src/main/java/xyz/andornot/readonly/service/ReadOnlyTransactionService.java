package xyz.andornot.readonly.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 只读事务示例
 *
 * @author igaozp
 * @since 2022/7/9
 */
@Service
public class ReadOnlyTransactionService {
    /**
     * 开启只读事务读取数据
     */
    @Transactional(readOnly = true)
    public void readOnlyTx() {

    }

    /**
     * 开启普通事务读取数据
     */
    @Transactional
    public void commonTx() {

    }

    /**
     * 无事务读取数据
     */
    public void noTx() {

    }
}
