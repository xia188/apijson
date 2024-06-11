package apijson.demo;

import java.sql.Connection;

import javax.sql.DataSource;

import apijson.framework.APIJSONSQLExecutor;
import apijson.orm.SQLConfig;
import cn.hutool.db.ds.DSFactory;

public class DemoSQLExecutor extends APIJSONSQLExecutor<Long> {

	@Override
	public Connection doGetConnection(SQLConfig<Long> config) throws Exception {
		// 获取默认数据源 db.setting
		DataSource ds = DSFactory.get();
		return ds.getConnection();
	}

}
