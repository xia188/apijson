package apijson.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import apijson.RequestMethod;
import apijson.framework.APIJSONObjectParser;
import apijson.framework.APIJSONParser;
import apijson.orm.SQLConfig;

public class DemoParser extends APIJSONParser<Long> {
	public static boolean correctJson = Boolean.getBoolean("apijson.correctJson");

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
	public JSONObject parseResponse(JSONObject request) {
		JSONObject response = super.parseResponse(request);
		if (correctJson) {
			getCorrectJson(response);
		}
		return response;
	}

	@Override
	public APIJSONObjectParser<Long> createObjectParser(JSONObject request, String parentPath,
			SQLConfig<Long> arrayConfig, boolean isSubquery, boolean isTable, boolean isArrayMainTable)
			throws Exception {
		return new DemoObjectParser(getSession(), request, parentPath, arrayConfig, isSubquery, isTable,
				isArrayMainTable).setMethod(getMethod()).setParser(this);
	}

	/**
	 * MySQL老版本不支持json类型，因此特殊处理String=\"[1,2,3]\"为JSONArray=[1,2,3]
	 */
	public static com.alibaba.fastjson.JSON getCorrectJson(com.alibaba.fastjson.JSON json) {
		if (json instanceof JSONArray) {
			((JSONArray) json).stream().forEach(obj -> {
				if (obj instanceof com.alibaba.fastjson.JSON) {
					getCorrectJson((com.alibaba.fastjson.JSON) obj);
				}
			});
		} else if (json instanceof JSONObject) {
			((JSONObject) json).entrySet().forEach(entry -> {
				Object value = entry.getValue();
				if (value instanceof String) {
					String str = (String) value;
					if (str != null && str.length() >= 2) {
						if(str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') {
							entry.setValue(com.alibaba.fastjson.JSON.parseArray(str));
						}else if(str.charAt(0) == '{' && str.charAt(str.length() - 1) == '}') {
							entry.setValue(com.alibaba.fastjson.JSON.parseObject(str));
						}
					}
				} else if (value instanceof JSONObject) {
					getCorrectJson((JSONObject) value);
				} else if (value instanceof JSONArray) {
					((JSONArray) value).stream().forEach(obj -> {
						if (obj instanceof com.alibaba.fastjson.JSON) {
							getCorrectJson((com.alibaba.fastjson.JSON) obj);
						}
					});
				}
			});
		}
		return json;
	}
}
