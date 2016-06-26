#Fixer
####环境：
Linux / OSX。windows也行，但本文档只说前面两者。  
java-8  
apache-tomcat-8.5.3  
####构建工具：
apache-ant-1.9.7  
####开发环境配置：
1. 下载JDK并设置好**$JAVA_HOME**环境变量。  
2. 安装tomcat, 事实是下载然后解压就行了。  
假设tomcat目录为 $TOMCAT, 启动与停止是在 $TOMCAT/bin 中的startup.sh与shutdown.sh.  
3. 安装ant, linux用户可直接通过镜像源安装，完整包名：apache-ant. 其他系统不知道。  
4. 配置tomcat与ant. 假设项目的目录为 $APP.     
编辑 $APP/build.xml, 找到定义catalina.home的地方，将value设置为你的tomcat目录。  
编辑 $TOMCAT/conf/tomcat_users.xml, 在tomcat-users标签中加入```<user username="the_user" password="the_pwd" roles="standard,manager-gui,manager-script"/>```  
将 $TOMCAT/lib/catalina-ant.jar 拷贝到 ant 安装目录的lib目录下。我(Linux)是```/usr/share/apache-ant```.  
5. 运行tomcat（参考2.）, 在 $APP 运行```ant install```, 浏览器打开```http://localhost:8080/fixer/```即可看到结果。

####数据库配置及说明
1.安装mysql （sudo apt-get install mysql-server mysql-client libapache2-mod-auth-mysql ，牢记root用户密码）
2.设置mysql字符集（sudo vim /etc/mysql/my.cnf）
在[client]下加入：
default-character-set = utf8
在[mysqld]下加入:
character-set-server=utf8
collation-server = utf8_general_ci
skip-character-set-client-handshake
在[mysql]下加入：
default-character-set = utf8
（测试：在mysql命令行下输入show variables like "%char%";查看除character_set_filesystem外，其他字段是否全为utf8）

3.开启mysql服务 service mysql start（关闭是service mysql stop）
4.进入mysql控制台 mysql -u root -p，输入密码
5.创建我们这次用到的数据库，依次输入以下命令：
CREATE DATABASE fixer character SET utf8 COLLATE utf8_general_ci; 
CREATE USER 'taotao'@'localhost' IDENTIFIED BY 'taotao666';
GRANT ALL ON fixer.* TO 'taotao'@'localhost';
然后可以用show databases;验证一下是否创建成功。
6.创建数据表
	1）首先下载jdbc：http://dev.mysql.com/downloads/connector/j/，解压后将jar文件（只有一个）拷入$APP/web/WEB-INF/lib下。（为以后项目中servlet访问数据库做准备）
	2）再将这个文件拷入$JAVA_HOME/jre/lib/ext下(为建表做准备)
	3）将目录切换到$APP/src，输入
		javac CreateTable.java
		java CreateTable create（建表）
		java CreateTable remove（删表）（！！！删表将会将所有表删除，其中的数据也将被删除，请谨慎操作）
		java CreateTable update（改表）（未实现，留作后用）
PS:Servlet_test.java中有测试servlet访问数据库的代码，可自行测试

####PS：数据表字段说明：
1、为相互区别，作为各表主键的id采用了表名缩写+id的形式，看起来很怪异，不要不认识23333
2、未注释枚举类型字段对照：
Customer.property:0 家庭用户，1 单位用户，2 代理商，3 签约用户
CallToRepairRecord.status:0 进行中，1 结束，2 撤销
Device.deviceType:0 台式机，1 笔记本，2 投影仪，3 打印机，4 其他
Device.breakdownType:0 固定性故障,1 间隙性故障
RepairRecord.status:0 未分配，1 分配未检测，2 检测完成维修未完成，3 维修完成
RepairRecord.delayDegree:0 一般，1 比较严重，2 严重
Parts.status:0 正常，1 临界，2 警示，3 缺货
User.characters:0 系统管理员，1 客服，2 任务调度，3 技术工程师，4 财务人员，5 库管，6 运营监督









