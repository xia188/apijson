/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon/APIJSON)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package apijson.demo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import apijson.NotNull;
import apijson.RequestMethod;
import apijson.framework.APIJSONObjectParser;
import apijson.framework.HttpSession;
import apijson.orm.Join;
import apijson.orm.SQLConfig;

/**
 * 简化Parser，getObject和getArray(getArrayConfig)都能用
 * 
 * @author Lemon
 */
public class DemoObjectParser extends APIJSONObjectParser<Long> {

	public DemoObjectParser(HttpSession session, @NotNull JSONObject request, String parentPath,
			SQLConfig<Long> arrayConfig, boolean isSubquery, boolean isTable, boolean isArrayMainTable)
			throws Exception {
		super(session, request, parentPath, arrayConfig, isSubquery, isTable, isArrayMainTable);
	}

	@Override
	public SQLConfig<Long> newSQLConfig(RequestMethod method, String table, String alias, JSONObject request,
			List<Join> joinList, boolean isProcedure) throws Exception {
		return DemoSQLConfig.<Long>newSQLConfig(method, table, alias, request, joinList, isProcedure);
	}

}
