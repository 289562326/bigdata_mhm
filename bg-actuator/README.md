# SpringBoot-actuator

## 一、Actuator作用
Spring Boot2.x中，默认只开放了info、health两个端点，剩余的需要自己通过配置

## 二、功能实现

http://localhost:18081/actuator信息

{"_links":{"self":{"href":"http://localhost:18081/actuator","templated":false},"health-component":{"href":"http://localhost:18081/actuator/health/{component}","templated":true},"health-component-instance":{"href":"http://localhost:18081/actuator/health/{component}/{instance}","templated":true},"health":{"href":"http://localhost:18081/actuator/health","templated":false},"info":{"href":"http://localhost:18081/actuator/info","templated":false}}}

http://localhost:18081/actuator/info信息
{"blog-url":"http://mhm.com","author":"Mhm","version":"0.0.1-SNAPSHOT"}
http://localhost:18081/actuator/health信息
{"status":"UP","details":{"diskSpace":{"status":"UP","details":{"total":104856547328,"free":2407612416,"threshold":10485760}}}}


## 自定义
http://localhost:18081/actuator/mhm




为了安全一般都启用独立的端口来访问后端的监控信息，默认与server.port相同



management.endpoints.web.path-mapping修改路径

### 跨域支持CROS

### 自定义管理服务器地址


### 配置管理特定SSL

### 健康信息


### 禁用/开启和暴露端点

### 修改端点映射路径


### 修改基本路径


