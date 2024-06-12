### 移除servlet依赖

有些web框架如solon.web、hutool.http、light-4j等并不是servlet架构，apijson提供HttpSession供外部适配。

### 依赖fastjson，其他可选

UnitAutoApp依赖classgraph，需要时再引入依赖
APIJSONSQLExecutor依赖postgresql，调整代码以便缺少相关类时也可以运行

### 初始化检查：第1项必须启用，否则数据可能被删除，见DemoTest.testGet

1，APIJSONVerifier.ENABLE_VERIFY_ROLE启用方法+表+权限检查，读取Access表到ACCESS_MAP，APIJSONParser.isNeedVerifyRole可绕过
2，APIJSONFunctionParser.ENABLE_REMOTE_FUNCTION启用远程函数检查，读取Function表到FUNCTION_MAP，script.sql没走通暂时禁掉
3，APIJSONVerifier.ENABLE_VERIFY_CONTENT启用请求校验，读取Request表到REQUEST_MAP

### 登录、会话、安全操作

1，所有表都需要配置Access表记录控制各种操作权限，get、head允许UNKNOWN访问时即可执行查询操作
2，登录之后会有会话session，gets、heads允许LOGIN访问即可安全查询，post可新增数据
3，put、delete允许OWNER即可安全更新和删除，ADMIN默认拥有所有操作权限（但必须重写AbstractVerifier.verifyAdmin）
4，管理员可通过DemoVerifier.adminUserId指定，-Dapijson.adminUserId=0谁都不是管理员，管理员可执行reload重载操作
