package apijson.demo;

import apijson.framework.APIJSONSQLConfig;

public class DemoSQLConfig extends APIJSONSQLConfig<Long> {

	static {
		// DemoSQLExecutor通过数据源获取连接，因此不必配置url等参数
		DEFAULT_SCHEMA = "apijson";// 数据库名
		ENABLE_COLUMN_CONFIG = true;// 支持!key反选字段
	}

}
