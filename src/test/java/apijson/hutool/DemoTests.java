package apijson.hutool;

import static apijson.hutool.DemoTest.host;
import static apijson.hutool.DemoTest.json;
import static apijson.hutool.DemoTest.token;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;

public class DemoTests {
	@ClassRule
	public static TestServer server = TestServer.getInstance();

	@Test
	public void testGets() {
		// Request表配置了{"MUST": "id"}，且id只能是自己的（用别人的会抛异常）
		json = "{\"Privacy\":{\"id\":38710},\"tag\":\"Privacy\"}";
		post(host + "/gets", json);
	}

	@Before
	public void login() {
		json = "{\"phone\":\"13000038710\",\"password\":\"666666\"}";
		json = HttpRequest.post(host + "/login").body(json).execute().body();
		token = (String) JSONUtil.getByPath(JSONUtil.parse(json), "token");
	}

	private String post(String url, String body) {
		return HttpRequest.post(url).header(Header.AUTHORIZATION, token).body(body).execute().body();
	}

	@After
	public void logout() {
		Console.log("token={}", token);
		HttpRequest.post(host + "/logout").header(Header.AUTHORIZATION, token).execute().body();
	}
}
