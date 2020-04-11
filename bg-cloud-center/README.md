# SpringCloud Eureka
## 客户端配置参数
EurekaClientConfigBean.java
## 实例配置项
EurekaInstanceConfigBean.java
## 服务端配置参数
EurekaServerConfigBean.java
- 当Eureka Server节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式，Eureka Server就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。
- 自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定

+ eureka.server.renewal-percent-threshold =0.85 默认值等于0.85
+ eureka.server.enable-self-preservation =true(默认true，表示开启)

>>紧急情况！Eureka可能不正确地声称实例在不在的情况下出现。续约没有那么紧急！Eureka可能不正确地声称实例在不在的情况下出现。续订小于阈值，因此不会为了安全而过期实例。

>>EMERGENCY! EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT. RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEING EXPIRED JUST TO BE SAFE.
