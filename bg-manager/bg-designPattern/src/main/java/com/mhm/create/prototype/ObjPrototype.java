package com.mhm.create.prototype;

/**
 * 浅拷贝
 * 深拷贝对象实现原型模式
 * （1）类初始化消耗资源较多；
 * （2）使用new 生成一个对象需要非常繁琐的过程（数据准备访问权限等）;
 * （3）构造函数比较复杂；
 * （4）在循环体中产生大量对象；
 *
 * 使用场景：
 * spring  scope="property"
 * json
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2020-4-14 22:00
 */
public class ObjPrototype {



    public static void main(String[] args) {
        EurekaServer server = new EurekaServer();
        server.setSid("1000");
        server.setName("bg-cloud-restclient");
        try {
            EurekaServer newServer = (EurekaServer) server.clone();
            //比较浅拷贝的地址
            System.out.println(newServer.getName() == server.getName());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        //深拷贝
        EurekaServer deepCloneServer = (EurekaServer) CloneUtils.cloneObject(server);
        System.out.println(deepCloneServer.toString());
        System.out.println(deepCloneServer.getName() == server.getName());
    }
}
