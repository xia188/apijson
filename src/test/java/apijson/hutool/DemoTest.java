package apijson.hutool;

import org.junit.ClassRule;
import org.junit.Test;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 开放查询测试DemoTest，安全操作测试DemoTests
 */
public class DemoTest {
	@ClassRule
	public static TestServer server = TestServer.getInstance();
	public static String host = "http://localhost:8080", json = null;
	public static String get = host + "/get";
	public static String head = host + "/head";
	public static String token = null;

	@Test
	public void testGet() {
		// DemoApp.init必须启用APIJSONVerifier.ENABLE_VERIFY_ROLE，否则所有表数据都可能被删除
//		json = new JSONObject().set("tag", "Access").set("Access", new JSONObject().set("id", "1")).toString();
//		HttpRequest.post(host + "/delete").body(json).execute().body();
		// {"Access":{"id":"1"}}
		json = new JSONObject().set("Access", new JSONObject().set("id", "1")).toString();
		HttpRequest.post(get).body(json).execute().body();
		// get/Access[]
		json = "{\"Access\":{}}";
		HttpRequest.post(get + "/Access[]").body(json).execute().body();
	}

	@Test
	public void testHead() {
		// {"Access":{}}
		json = new JSONObject().set("Access", new JSONObject()).toString();
		HttpRequest.post(head).body(json).execute().body();
	}

	@Test
	public void testLogin() {
		json = "{\"phone\":\"13000038710\",\"password\":\"666666\"}";
		json = HttpRequest.post(host + "/login").body(json).execute().body();
		token = (String) JSONUtil.getByPath(JSONUtil.parse(json), "token");
		Console.log("token={}", token);
		HttpRequest.post(host + "/logout").header(Header.AUTHORIZATION, token).execute().body();
	}
}
