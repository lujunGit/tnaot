一：tnaot项目模块划分：

1：kafka-service:kafka消息服务

运行前准备：
    1：启动zookeeper服务
        配置：
            host、port、log目录等
        windows直接双击D:\zookeeper-3.4.10\bin\zkServer.cmd
        liunx?
    2：启动kafka服务
        配置：
           host、port、log目录等
           配置zookeeper.connect=kafka.host:port
        windows .\bin\windows\kafka-server-start.bat .\config\server.properties
        liunx?
    3：启动项目
    4：liunx中搭建kafka集群环境，可以参考
    https://www.cnblogs.com/luotianshuai/p/5206662.html

二：开发前准备

1：jdk1.8
2：代码规范检查使用阿里插件
   安装使用文档https://www.cnblogs.com/jiangbei/p/7668654.html


