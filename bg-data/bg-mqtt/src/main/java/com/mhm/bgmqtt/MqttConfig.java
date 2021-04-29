package com.mhm.bgmqtt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MHm
 * @date 2021-4-5 22:15
 */
@Component
@ConfigurationProperties("spring.mqtt")
@Setter
@Getter
public class MqttConfig {
    @Autowired
    private MqttPushClient mqttPushClient;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 连接地址
     */
    private String hostUrl;
    /**
     * 客户Id
     */
    private String clientId;
    /**
     * 默认连接话题
     */
    private String defaultTopic;
    /**
     * 超时时间
     */
    private int timeout;
    /**
     * 保持连接数
     */
    private int keepalive;

//    @Bean
//    public MqttPushClient getMqttPushClient() {
//        mqttPushClient.connect(hostUrl, clientId, username, password, timeout, keepalive);
//        // 以/#结尾表示订阅所有以test开头的主题
//        mqttPushClient.subscribe("test/#", 0);
//        return mqttPushClient;
//    }

}
