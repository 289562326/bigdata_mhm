package com.mhm.bgmqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息监听回调
 * @author MHm
 * @date 2021-4-5 22:18
 */
@Component
public class PushCallback implements MqttCallback {
    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);

    @Autowired
    private MqttConfig mqttConfig;

    private static MqttClient client;

    @Override
    public void connectionLost(Throwable throwable) {
        // 连接丢失后，一般在这里面进行重连
        logger.info("连接断开，可以做重连");
        if (client == null || !client.isConnected()) {
            mqttConfig.getMqttPushClient();
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        // subscribe后得到的消息会执行到这里面
        logger.info("接收消息主题 : " + topic);
        logger.info("接收消息Qos : " + mqttMessage.getQos());
        logger.info("接收消息内容 : " + new String(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info("deliveryComplete---------" + iMqttDeliveryToken.isComplete());
    }

}
