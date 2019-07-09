智能药柜服务器端
===

## 项目简介
>智能药柜为简化医院取药流程而设计的系统，医生配完药后可将药放在智能药柜硬件的平台上，随后智能药柜会分配一个空柜子存储药品，并向用户手机发送取药验证码，用户可从安卓APP上随时查看药品的配药进度等信息，并通过在智能药柜处输入取药验证码进行取药。主要分为硬件层、服务器、安卓APP三个部分，[安卓APP点这里](https://github.com/HandsomeDong/SmartBoxAPP)，**本项目是服务器部分。**

本项目是基于Spring+Spring MVC+Mybatis框架构建的系统，提供HTTP接口给智能药柜硬件更新药品订单进度及APP获取订单进度，接口全部都是返回Json数据，无前端界面。

## 实现功能

* APP
	* 注册
	* 登陆
	* 查看当前药品订单
	* 查看历史药品订单
* 硬件
	* 获取所有空柜子
	* 更新订单进度

## 运行环境
* jdk1.8
* tomcat9.0
* mysql5.7
* Spring4.3
* Spring MVC4.3
* Mybatis3.4
* jackson2.8
* qcloundsms1.0
* java-jwt3.1

>上面列举了大部分主要的jar包，详细具体可查看pom.xml。jackson主要用于处理请求参数，qcloundsms是腾讯云短信SDK，用于发送注册验证码及取药验证码短信，jwt用于加密用户信息生成token返回给APP。

克隆或下载该项目后，还需要在webapp/resource/下新建文件夹properties，并且新建三个配置文件，分别是**jdbc.properties**、**machine.properties**和**qcloud.properties**，为了信息安全，我gitignoire了。

### jdbc.properties
数据库的配置

```
#驱动
mysql_driverClassName=com.mysql.jdbc.Driver
#url
mysql_url=jdbc:mysql://XXX:3306/ssm_smartbox
#mysql用户名和密码
mysql_username=XXX
mysql_password=XXX

druid_initialSize=1
druid_minIdle=1
druid_maxActive=10
druid_maxWait=10000
druid_timeBetweenEvictionRunsMillis=60000
druid_minEvictableIdleTimeMillis=300000
```

### machine.properties
智能药柜硬件更新订单前会被拦截器拦截，验证身份。

```
machineId=XXX
password=XXX
```

### qcloud.properties
这个是腾讯云短信的配置，发送短信主要需要三个参数，appid、appkey和短信模版id。该项目需要发送三种短信，注册验证码、取药验证码和取药完成短信。**想要把项目跑起来，没有腾讯云短信配置的话，这里随便填就行了，顶多就不发送短信。**
[腾讯云短信详细使用方式点这里。](https://cloud.tencent.com/document/product/382/13613)

```
appid=XXX
appkey=XXX
register=XXX
take_medicine=XXX
finish=XXX
```

### 数据库
>创建表的sql语句在resource/sql/smartbox.sql里，全部执行一遍再自行插入数据。

建议使用Mysql5.6或Mysql5.6以上数据库版本，低版本的对timeStamp列的创建有限制。

一共有五张表，分别是register、user、box、medicine_order和history_order。
* register 存储手机号和注册验证码
* user 存储手机号、用户名、密码
* box 存储柜子状态
* medicine_oder 存储用户的当前药品订单
* history_order 存储用户的已完成历史订单

表之间的关系如下图。
![表之间的关系](https://i.loli.net/2019/07/08/5d23194ec0e7f73330.png)
