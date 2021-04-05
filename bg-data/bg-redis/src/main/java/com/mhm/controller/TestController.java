package com.mhm.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by MHm on 2019/6/14.
 */
@org.springframework.web.bind.annotation.RestController
@Slf4j
public class TestController {
    //独占锁,加true实现公平锁
    private final Lock lock = new ReentrantLock();

    //redis分布式锁
    public static final String REDIS_LOCK = "atguiguLock";

    private int m = 0;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * topic操作
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/topic")
    public String topic() {
        int index = m;
        for (int i = m; i < index + 10; i++) {
            redisTemplate.convertAndSend("mytopic", "这是我发第" + i + "条的消息啊");
        }
        redisTemplate.opsForValue().set("mhm", "mhm");
        return redisTemplate.opsForValue().get("mhm");
    }

    /**
     * 性能测试：并发查询
     *
     * @param num
     * @param threadNum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/query")
    public String performanceQuery(@RequestParam("num") Integer num, @RequestParam("threadNum") Integer threadNum) {
        ThreadPoolExecutor localThreadPoolExecutor = new ThreadPoolExecutor(threadNum, threadNum, 30L, TimeUnit.SECONDS,
        new LinkedBlockingQueue());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            localThreadPoolExecutor.execute(new Thread() {
                public void run() {
                    for (int i = 0; i < num; i++) {
                        redisTemplate.opsForValue().get("mhm");
                    }
                }
            });
        }
        localThreadPoolExecutor.shutdown();
        // 等待线程都处理完
        try {
            boolean bool = false;
            while (!(bool)) {
                bool = localThreadPoolExecutor.awaitTermination(-6037246970332971007L, TimeUnit.SECONDS);
            }
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return num + " query used time " + (endTime - startTime);
    }

    /**
     * 性能测试：并发设置
     *
     * @param num
     * @param key
     * @param value
     * @param threadNum
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/set")
    public String performanceSet(@RequestParam("num") Integer num, @RequestParam("key") String key,
    @RequestParam("value") String value, @RequestParam("threadNum") Integer threadNum) {

        ThreadPoolExecutor localThreadPoolExecutor = new ThreadPoolExecutor(threadNum, threadNum, 30L, TimeUnit.SECONDS,
        new LinkedBlockingQueue());
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            localThreadPoolExecutor.execute(new Thread() {
                public void run() {
                    for (int i = 0; i < num; i++) {
                        redisTemplate.opsForValue().set(key, value);
                    }
                }
            });
        }
        localThreadPoolExecutor.shutdown();
        // 等待线程都处理完
        try {
            boolean bool = false;
            while (!(bool)) {
                bool = localThreadPoolExecutor.awaitTermination(-6037246970332971007L, TimeUnit.SECONDS);
            }
        } catch (InterruptedException localInterruptedException) {
            localInterruptedException.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return num + " query used time " + (endTime - startTime);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/batchUpdate")
    public String batchUpdate(@RequestParam("threadNum") Integer threadNum) {
        // 1.查看库存数量
        //        for(int i=0;i<threadNum;i++){
        //            new Thread(new Runnable(){
        //                public void run(){
        String result = redisTemplate.opsForValue().get("goods001");
        int goodsNumber = result == null ? 0 : Integer.parseInt(result);
        // 2.卖商品
        if (goodsNumber > 0) {
            int realNumber = goodsNumber - 1;
            // 3.成功买入，库存减少一件
            redisTemplate.opsForValue().set("goods001", String.valueOf(realNumber));
            System.out.println("成功买入商品，库存还剩下：" + redisTemplate.opsForValue().get("goods001"));
        } else {
            System.out.println("商品卖完");
        }
        //                }
        //            }
        //            ).start();
        //        }

        return redisTemplate.opsForValue().get("goods001");
    }

    /**
     * 测试并发请求出现的超卖异常
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/batchSet")
    public String batchSet() {
        // 1.查看库存数量
        String result = redisTemplate.opsForValue().get("goods001");
        int goodsNumber = result == null ? 0 : Integer.parseInt(result);
        // 2.卖商品
        if (goodsNumber > 0) {
            int realNumber = goodsNumber - 1;
            // 3.成功买入，库存减少一件
            redisTemplate.opsForValue().set("goods001", String.valueOf(realNumber));
            System.out.println("成功买入商品，库存还剩下：" + redisTemplate.opsForValue().get("goods001"));
        } else {
            System.out.println("商品卖完");
        }
        return redisTemplate.opsForValue().get("goods001");
    }

    /**
     * 同步锁
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/batchSyncSet")
    public String batchSyncSet() {
        synchronized (this) {
            // 1.查看库存数量
            String result = redisTemplate.opsForValue().get("goods001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            // 2.卖商品
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                // 3.成功买入，库存减少一件
                redisTemplate.opsForValue().set("goods001", String.valueOf(realNumber));
                System.out.println("成功买入商品，库存还剩下：" + redisTemplate.opsForValue().get("goods001"));
            } else {
                System.out.println("商品卖完");
            }
        }
        return redisTemplate.opsForValue().get("goods001");
    }

    /**
     * 单机锁
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/batchLockSet")
    public String batchLockSet() {
        try {
            if (lock.tryLock(3L, TimeUnit.SECONDS)) {
                // 1.查看库存数量
                String result = redisTemplate.opsForValue().get("goods001");
                int goodsNumber = result == null ? 0 : Integer.parseInt(result);
                // 2.卖商品
                if (goodsNumber > 0) {
                    int realNumber = goodsNumber - 1;
                    // 3.成功买入，库存减少一件
                    redisTemplate.opsForValue().set("goods001", String.valueOf(realNumber));
                    System.out.println("成功买入商品，库存还剩下：" + goodsNumber);
                } else {
                    System.out.println("商品卖完");
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return redisTemplate.opsForValue().get("goods001");
    }

    /**
     * 分布式锁
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/atguiguLock")
    public String atguiguLock() {
        String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
        Boolean flag = redisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value);
        // 加锁不成功
        if (!flag) {
            return "抢锁失败";
        }
        String result = redisTemplate.opsForValue().get("goods001");
        int goodsNumber = result == null ? 0 : Integer.parseInt(result);

        // 2.卖商品
        if (goodsNumber > 0) {
            int realNumber = goodsNumber - 1;
            // 3.成功买入，库存减少一件
            redisTemplate.opsForValue().set("goods001", String.valueOf(realNumber));
            // 4.解锁
            redisTemplate.delete(REDIS_LOCK);

            return "成功买入商品，库存还剩下：" + realNumber;
        } else {
            System.out.println("商品卖完");
        }
        return redisTemplate.opsForValue().get("goods001");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/redissonLock")
    public String redissonLock() {
        String key = "lock001";
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            // 尝试加锁，最多等待20秒，上锁以后5秒自动解锁
            Future<Boolean> LockFuture = lock.tryLockAsync(20, 5, TimeUnit.SECONDS);
            boolean result = LockFuture.get();
            String goods001 = redisTemplate.opsForValue().get("goods001");
            int goodsNumber = goods001 == null ? 0 : Integer.parseInt(goods001);
            //如果库存为空
            if (result) {
                log.info("我获得了锁");
                if (goodsNumber <= 0) {
                    log.info("商品卖完");
                }else{
                    //简单减库存操作 没有重新写其他接口了
                    int realNumber = goodsNumber - 1;
                    // 3.成功买入，库存减少一件
                    redisTemplate.opsForValue().set("goods001", String.valueOf(realNumber));
                    log.info("成功买入商品，库存还剩下：" + goodsNumber);
                }

            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            //解锁
            lock.unlock();
        }
        return redisTemplate.opsForValue().get("goods001");
    }
}
