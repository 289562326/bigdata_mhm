package com.mhm.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MHm
 * @date 2021-3-14 19:57
 */
@Configuration
public class RedissonConfig {
//    @Bean(destroyMethod="shutdown")
//    public RedissonClient redisson() throws IOException {
//        RedissonClient redisson = Redisson.create(
//        Config.fromYAML(new ClassPathResource("redisson-single.yml").getInputStream()));
//        return redisson;
//    }
@Bean
public RedissonClient getRedisson()
{
    Config config = new Config();
    config.useSingleServer()
    .setAddress("redis://10.45.156.192:6379").setPassword("zxm10@@@")
    .setRetryInterval(5000)
    .setTimeout(10000)
    .setConnectTimeout(10000);
    return Redisson.create(config);
}

}
