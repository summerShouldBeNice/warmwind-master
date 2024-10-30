package top.warmwind.master.core.redis;

import jakarta.annotation.Resource;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis锁服务
 *
 * @author warmwind
 * @since 2024-10-30 下午5:42
 */
@Service
public class RedisLockService {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取锁
     * @param lockKey   锁键
     * @param waitTime  等待时间
     * @param leaseTime 租约时间
     * @return 是否获取到锁
     */
    public boolean getLock(String lockKey, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            // 当前线程中断
            Thread.currentThread().interrupt();
            // 返回获取锁失败
            return false;
        }
    }

    /**
     * 释放锁
     * @param lockKey 锁键
     */
    public void releaseLock(String lockKey) {
        // 获取指定锁键对应的 Redisson 锁
        RLock lock = redissonClient.getLock(lockKey);
        // 如果当前线程持有该锁
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}
