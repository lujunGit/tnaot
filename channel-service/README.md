一：tnaot项目模块划分：

1：channel-service:频道服务

每个服务对应该的接口放在各自服务模块中

熔断仪表盘功能【添加@EnableHystrixDashboard注解】，访问：
http://localhost:8081/hystrix.stream 查看数据
访问：locahost:8762/hystrix 通过配置查看熔断图形

二：开发前准备

1：jdk1.8
2：代码规范检查使用阿里插件
   安装使用文档https://www.cnblogs.com/jiangbei/p/7668654.html


