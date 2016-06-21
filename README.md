**环境**：
Linux / OSX。windows也行，但本文档只说前面两者。
java-8
apache-tomcat-8.5.3
**构建工具**：
apache-ant-1.9.7
**开发环境配置**：
1. 下载JDK并设置好**$JAVA_HOME**环境变量。
2. 安装tomcat, 事实是下载然后解压就行了。  
假设tomcat目录为 \$TOMCAT, 启动与停止是在 \$TOMCAT/bin 中的startup.sh与shutdown.sh.
3. 安装ant, linux用户可直接通过镜像源安装，完整包名：apache-ant. 其他系统不知道。
4. 配置tomcat与ant. 假设项目的目录为 \$APP.   
编辑 \$APP/build.xml, 找到定义catalina.home的地方，将value设置为你的tomcat目录。  
编辑 \$TOMCAT/conf/tomcat_users.xml, 在tomcat-users标签中加入```<user username="the_user" password="the_pwd" roles="standard,manager-gui,manager-script"/>```  
将 \$TOMCAT/lib/catalina-ant.jar 拷贝到 ant 安装目录的lib目录下。我(Linux)是```/usr/share/apache-ant```.
5. 运行tomcat（参考2.）, 在 \$APP 运行```ant install```, 浏览器打开```http://localhost:8080/fixer/```即可看到结果。