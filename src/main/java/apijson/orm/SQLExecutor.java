/*Copyright (C) 2020 THL A29 Limited, a Tencent company.  All rights reserved.

This source code is licensed under the Apache License Version 2.0.*/


package apijson.orm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import apijson.NotNull;

/**executor for query(read) or update(write) MySQL database
 * @author Lemon
 */
public interface SQLExecutor<T extends Object> {
	Parser<T> getParser();
	SQLExecutor<T> setParser(Parser<T> parser);

	/**保存缓存
	 * @param sql
	 * @param list
	 * @param config
	 */
	void putCache(String sql, List<JSONObject> list, SQLConfig<T> config);

	/**获取缓存
	 * @param sql
	 * @param config
	 * @return
	 */
	List<JSONObject> getCache(String sql, SQLConfig<T> config);

	/**获取缓存
	 * @param sql
	 * @param position
	 * @param config
	 * @return
	 */
	JSONObject getCacheItem(String sql, int position, SQLConfig<T> config);

	/**移除缓存
	 * @param sql
	 * @param config
	 */
	void removeCache(String sql, SQLConfig<T> config);

	/**执行SQL
	 * @param config
	 * @param unknownType
	 * @return
	 * @throws Exception
	 */
	JSONObject execute(@NotNull SQLConfig<T> config, boolean unknownType) throws Exception;

	//executeQuery和executeUpdate这两个函数因为返回类型不同，所以不好合并
	/**执行查询
	 * @param config
	 * @return
	 * @throws SQLException
	 */
	default ResultSet executeQuery(@NotNull SQLConfig<T> config) throws Exception {
		return executeQuery(config, null);
	}
	ResultSet executeQuery(@NotNull SQLConfig<T> config, String sql) throws Exception;

	/**执行增、删、改
	 * @param config
	 * @return
	 * @throws SQLException
	 */
	default int executeUpdate(@NotNull SQLConfig<T> config) throws Exception {
		return executeUpdate(config, null);
	}
	int executeUpdate(@NotNull SQLConfig<T> config, String sql) throws Exception;


	/**判断是否为JSON类型
	* @param config
	* @param rsmd
	* @param position
	* @param lable
	* @return
	*/
	boolean isJSONType(@NotNull SQLConfig<T> config, ResultSetMetaData rsmd, int position, String lable);


	Connection getConnection(@NotNull SQLConfig<T> config) throws Exception;
	default Statement getStatement(@NotNull SQLConfig<T> config) throws Exception {
		return getStatement(config, null);
	}
	Statement getStatement(@NotNull SQLConfig<T> config, String sql) throws Exception;

	int getTransactionIsolation();
	void setTransactionIsolation(int transactionIsolation);
	/**开始事务
	 * @throws SQLException
	 */
	void begin(int transactionIsolation) throws SQLException;
	/**回滚事务
	 * @throws SQLException
	 */
	void rollback() throws SQLException;
	/**提交事务
	 * @throws SQLException
	 */
	void rollback(Savepoint savepoint) throws SQLException;
	/**提交事务
	 * @throws SQLException
	 */
	void commit() throws SQLException;
	/**关闭连接，释放资源
	 */
	void close();

	ResultSet executeQuery(@NotNull Statement statement, String sql) throws Exception;

	int executeUpdate(@NotNull Statement statement, String sql) throws Exception;

	ResultSet execute(@NotNull Statement statement, String sql) throws Exception;

	int getGeneratedSQLCount();

	int getCachedSQLCount();

	int getExecutedSQLCount();

	long getExecutedSQLDuration();

	long getSqlResultDuration();

}
