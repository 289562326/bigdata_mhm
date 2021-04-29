package com.mhm.create.factoryMethod.simpleFactory;

/**
 * 简单工厂
 *
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2020-4-15 19:26
 */
public class SimpleFactory {

    /**
     * 违反了开闭原则，每增加一种类型，此处就要修改代码
     *
     * @param type
     * @return
     */
    public AccessMode getMode(String type) {
        switch (type.toUpperCase()) {
            case "IEC104":
                return new IEC104Mode();
            case "MQTT":
                return new MQTTMode();
        }
        return null;
    }

    /**
     * @param className
     * @return
     */
    public AccessMode getModeByReflect(String className) {
        try {
            return (AccessMode) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param clazz
     * @return
     */
    public Object getModyByClass(Class<? extends AccessMode> clazz) {
        try {
            return (AccessMode) Class.forName(clazz.getName()).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        SimpleFactory factory = new SimpleFactory();
        AccessMode iec104Mode = factory.getMode("IEC104");
        iec104Mode.start();
        iec104Mode.stop();

        AccessMode mqttMode = factory.getMode("MQTT");
        mqttMode.start();
        mqttMode.stop();

        mqttMode = factory.getModeByReflect("MQTTMode");
        mqttMode.start();
        mqttMode.stop();
    }

}

/**
 * 接入模式
 */
interface AccessMode {
    boolean start();

    boolean stop();
}

class IEC104Mode implements AccessMode {

    @Override
    public boolean start() {
        System.out.println("IEC104启动");
        return true;
    }

    @Override
    public boolean stop() {
        System.out.println("IEC104停止");
        return true;
    }
}

class MQTTMode implements AccessMode {

    @Override
    public boolean start() {
        System.out.println("MQTT启动");
        return true;
    }

    @Override
    public boolean stop() {
        System.out.println("MQTT停止");
        return true;
    }
}
