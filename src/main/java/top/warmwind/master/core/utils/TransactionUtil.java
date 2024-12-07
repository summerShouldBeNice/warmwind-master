package top.warmwind.master.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class TransactionUtil {

    private final PlatformTransactionManager transactionManager;
    private static final ThreadLocal<TransactionStatus> currentTransactionStatus = new ThreadLocal<>();
    private static final Lock lock = new ReentrantLock();

    @Autowired
    public TransactionUtil(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 开启事务（使用默认配置）
     */
    public TransactionStatus begin() {
        return begin(TransactionDefinition.ISOLATION_REPEATABLE_READ,
                TransactionDefinition.PROPAGATION_REQUIRED);
    }

    /**
     * 开启事务（指定隔离级别）
     * @param isolationLevel 隔离级别
     */
    public TransactionStatus begin(int isolationLevel) {
        return begin(isolationLevel, TransactionDefinition.PROPAGATION_REQUIRED);
    }

    /**
     * 开启事务（指定传播行为）
     * @param propagationBehavior 传播行为
     */
    public TransactionStatus beginWithPropagation(int propagationBehavior) {
        return begin(TransactionDefinition.ISOLATION_REPEATABLE_READ, propagationBehavior);
    }

    /**
     * 开启事务（完全自定义）
     * @param isolationLevel 隔离级别
     * @param propagationBehavior 传播行为
     */
    public TransactionStatus begin(int isolationLevel, int propagationBehavior) {
        try {
            lock.lock();
            if (currentTransactionStatus.get() != null) {
                throw new IllegalStateException("当前线程已经存在事务");
            }

            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setIsolationLevel(isolationLevel);
            def.setPropagationBehavior(propagationBehavior);

            TransactionStatus status = transactionManager.getTransaction(def);
            currentTransactionStatus.set(status);
            return status;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 提交事务
     */
    public void commit() {
        TransactionStatus status = currentTransactionStatus.get();
        if (status == null) {
            throw new IllegalStateException("当前线程不存在事务");
        }

        try {
            lock.lock();
            transactionManager.commit(status);
        } finally {
            currentTransactionStatus.remove();
            lock.unlock();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback() {
        TransactionStatus status = currentTransactionStatus.get();
        if (status == null) {
            throw new IllegalStateException("当前线程不存在事务");
        }

        try {
            lock.lock();
            transactionManager.rollback(status);
        } finally {
            currentTransactionStatus.remove();
            lock.unlock();
        }
    }

    /**
     * 获取当前事务状态
     */
    public TransactionStatus getCurrentTransactionStatus() {
        return currentTransactionStatus.get();
    }
}