一：tnaot项目模块划分：

8：redis-service:缓存服务

启动之前需要启动redis服务器，目前配置是本地服务，如果换用liunx上的，注意修改环境配置

windows环境配置，在./bin/redis.windows-service.conf 文件中
    1：检查日志配置目录，是否有密码：requirepass ljtest，并检查项目配置文件中是否有配置
       redis-server.exe redis.windows-service.conf &
    2：启动项目
liunx？

功能点：
    1：可以使用缓存
    2：可以使用redis的发布订阅模式，给其他业务模块发送消息

二：开发前准备

1：jdk1.8
2：代码规范检查使用阿里插件
   安装使用文档https://www.cnblogs.com/jiangbei/p/7668654.html


