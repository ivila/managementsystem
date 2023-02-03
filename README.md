## Book Management System
这是一个使用Spring和Vue开发的图书管理系统Demo。
支持数据的增删改查。

### 1，尝试启动
使用makefile来快速启动
对应的功能和命令如下
|功能|命令|说明|
|----|----|----|
|初始化前端环境|make initfe|安装所需要的package|
|开发后端|make debugbe|以dev环境启动后台，使用端口8888|
|开发前端|make debugfe|启动前端dev server，使用端口8081，对应后端连接端口也是8888|
|构建前端|make buildfe|将前端内容编译成html和js文件，并防止到resource/static下|
|测试后端|make testbe|需要先构建前端，因为开发模式下是使用前端做路由管理，而实际部署时是由后端做路由管理，不太一样，所以需要单独测试|


