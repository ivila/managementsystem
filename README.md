# Book Management System
这是一个使用Spring和Vue开发的图书管理系统Demo。
支持用户登录和权限控制，以及书籍的增删改查。

## 1. 可用的测试账户
在开发模式下，为了方便调试，会自动创建4个帐号
分别如下：
|账户名|密码值|权限|说明|
|----|----|----|----|
|test-admin1@xxx.com|test1234|查看，编辑|给后端接口调试用，没有删除权限|
|test-admin2@xxx.com|test1234|查看，编辑，删除|给后端接口调试用|
|test-admin3@xxx.com|16d7a4fca7442dda3ad93c9a726597e4|查看，新增|给前端调试页面用，没有删除权限，原始密码test1234|
|test-admin4@xxx.com|16d7a4fca7442dda3ad93c9a726597e4|查看，新增，编辑，删除|给前端调试页面用，原始密码test1234|
请记住这些账户信息，后面调试时会使用到。

上面提到的权限，对应的所需编码值如下：
|权限|编码值|功能|
|----|----|----|
|查看|book-view|支持查看书籍|
|编辑|book-edit|支持新增和更新书籍|
|删除|book-delete|支持删除书籍|

## 2. 尝试启动
使用makefile来快速启动
对应的功能和命令如下
|功能|命令|说明|
|----|----|----|
|初始化前端环境|make initfe|安装所需要的package|
|开发后端|make debugbe|以dev环境启动后台，使用端口8888|
|开发前端|make debugfe|启动前端dev server，使用端口8081，对应后端连接端口也是8888|
|构建前端|make buildfe|将前端内容编译成html和js文件，并放置到resource/static下|
|测试后端|make testbe|需要先构建前端，因为开发模式下是使用前端做路由管理，而实际部署时是由后端做路由管理，不太一样，所以需要单独测试|
开发时应该就使用make debugbe和make debugfe就好了

## 3. 测试后端接口功能
在这里我们介绍如何测试后端功能。
先在命令行执行make debugbe启动测试进程，对应的网络端口为8888
然后在本地使用Postman进行接口调试，可使用的API集合可以直接通过这个[文件](https://github.com/ivila/public_files/blob/master/managementsystem/json/Book.postman_collection.json)导入
然后我们可以看到一些接口集合。
> 注意到的是，对于下述提到的所有接口，在GET时，我们的参数使用Params，在POST时，我们的参数使用JSON。

#### 3.1) [Auth]Login
请求方式: POST
路径: /api/auth/login
作用：用于请求登录并设置好登录态，保证后续请求其它接口时不会被拦截
|参数|类型|Required|说明|
|----|----|----|----|
|email|string|Y|用户的邮箱|
|password|string|Y|md5加密后的用户密码信息|
在调试这个接口时，可以直接使用上面提到的test-admin1@xxx.com和test-admin2@xxx.com

#### 3.2) [User]Current
请求方式: GET
路径：/api/user/current
作用：获取当前用户信息，主要提供给前端调用

### 3.3) [Book]create
请求方式: POST
路径: /api/book/create
作用：创建一本新的书籍
|参数|类型|Required|说明|
|----|----|----|----|
|isbn|string|Y|ISBN|
|bookName|string|Y|图书名称|
|coverUrl|string|Y|图片链接|
|author|string|Y|图书作者|
需要book-edit权限

### 3.4) [Book]update
请求方式: POST
路径: /api/book/update
作用：创建一本新的书籍
|参数|类型|Required|说明|
|----|----|----|----|
|id|integer|Y|想要修改的书籍ID|
|isbn|string|Y|ISBN|
|bookName|string|Y|图书名称|
|coverUrl|string|Y|图片链接|
|author|string|Y|图书作者|
需要book-edit权限

### 3.5) [Book]list
请求方式: POST
路径: /api/book/list
作用：查询书籍列表，给前端使用
|参数|类型|Required|说明|
|----|----|----|----|
|isbn|string|N|ISBN|
|author|string|N|作者名称|
|operator|string|N|最近一次编辑人|
需要book-view权限

### 3.6) [Book]get
请求方式: GET
路径: /api/book/get
作用：获取书籍信息
|参数|类型|Required|说明|
|----|----|----|----|
|id|integer|Y|书籍ID|
需要book-view权限

### 3.7) [Book]delete
请求方式: DELETE
路径: /api/book/delete
作用：删除书籍
|参数|类型|Required|说明|
|----|----|----|----|
|id|integer|Y|书籍ID|
需要book-edit权限

## 4. 测试前端页面操作
在这里我们介绍如何测试前端功能。
先在命令行执行make debugfe启动测试进程，对应的网络端口为8081

### 4.1) 测试登录
使用浏览器打开 http://localhost:8081，就会看到这个页面：
![图片](https://raw.githubusercontent.com/ivila/public_files/master/managementsystem/snapshot/Login.jpg)
输入账户和密码再点击登录即可。
在这里可以使用上面提到的test-admin3@xxx.com和test-admin4@xxx.com。

### 4.2) 测试查询列表
登录后，打开如下页面。
![图片](https://raw.githubusercontent.com/ivila/public_files/master/managementsystem/snapshot/BookList.jpg)
在此页面可以自由地输入Author，ISBN，以及Operator进行过滤。

### 4.3) 测试新增
点击列表页面的Create按钮，就会打开新增页面。
![图片](https://raw.githubusercontent.com/ivila/public_files/master/managementsystem/snapshot/BookCreate.jpg)
填写对应的信息后，点击Confirm即可。

### 4.4) 测试编辑
点击想要编辑的书籍那行末尾的编辑按钮，就会打开编辑页面
![图片](https://raw.githubusercontent.com/ivila/public_files/master/managementsystem/snapshot/BookEdit.jpg)
修改对应的信息后，点击Confirm即可。

### 4.5) 测试删除
点击想要删除的书籍那行末尾的删除按钮，就会弹出一个确认框，点击确认即可删除
![图片](https://raw.githubusercontent.com/ivila/public_files/master/managementsystem/snapshot/BookDelete.jpg)

## 5. 整合测试
在本地执行make buildfe以将前端代码编译成原生JS内容，并自动放置到resource/static/dist目录下。
然后执行make testfe以test环境启动本地测试，对应的网络端口为8889。
然后打开 http://localhost:8889，重复前端测试流程即可。
