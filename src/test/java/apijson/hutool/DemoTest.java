package apijson.hutool;

import org.junit.ClassRule;
import org.junit.Test;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;

public class DemoTest {
	@ClassRule
	public static TestServer server = TestServer.getInstance();
	public static String host = "http://localhost:8080", json = null;
	public static String get = host + "/get";
	public static String head = host + "/head";

	@Test
	public void testGet() {
		// {"Access":{"id":"1"}}
		json = new JSONObject().set("Access", new JSONObject().set("id", "1")).toString();
		HttpRequest.post(get).body(json).execute().body();
	}

	@Test
	public void testHead() {
		// {"Access":{}}
		json = new JSONObject().set("Access", new JSONObject()).toString();
		HttpRequest.post(head).body(json).execute().body();
	}
}
