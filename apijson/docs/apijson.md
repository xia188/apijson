### 移除servlet依赖

有些web框架如solon.web、hutool.http、light-4j等并不是servlet架构，apijson提供HttpSession供外部适配。

### 依赖fastjson，其他可选

UnitAutoApp依赖classgraph，需要时再引入依赖
APIJSONSQLExecutor依赖postgresql，调整代码以便缺少相关类时也可以运行
