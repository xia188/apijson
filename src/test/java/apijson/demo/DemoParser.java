package apijson.demo;

import com.alibaba.fastjson.JSONObject;

import apijson.RequestMethod;
import apijson.framework.APIJSONObjectParser;
import apijson.framework.APIJSONParser;
import apijson.orm.SQLConfig;

public class DemoParser extends APIJSONParser<Long> {
	public DemoParser() {
		super();
	}

	public DemoParser(RequestMethod method) {
		super(method);
	}

	public DemoParser(RequestMethod method, boolean needVerify) {
		super(method, needVerify);
	}

	@Override
	public APIJSONObjectParser<Long> createObjectParser(JSONObject request, String parentPath,
			SQLConfig<Long> arrayConfig, boolean isSubquery, boolean isTable, boolean isArrayMainTable)
			throws Exception {
		return new DemoObjectParser(getSession(), request, parentPath, arrayConfig, isSubquery, isTable,
				isArrayMainTable).setMethod(getMethod()).setParser(this);
	}
}
