package apijson.hutool;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;

public class CorsUtil {

	public static void addCorsHeader(HttpServerRequest request, HttpServerResponse response) {
		Map<String, List<String>> headers = request.getHeaders();
		List<String> origins = headers.getOrDefault("Origin", Collections.emptyList());
		List<String> requestMethods = headers.getOrDefault("Access-Control-Request-Method", Collections.emptyList());
		List<String> requestHeaders = headers.getOrDefault("Access-Control-Request-Headers", Collections.emptyList());
		if (CollUtil.isEmpty(origins) && CollUtil.isEmpty(requestMethods) && CollUtil.isEmpty(requestHeaders)) {
			return;// 以上三个请求头不全为空才是跨域请求
		}
		origins.forEach(origin -> response.addHeader("Access-Control-Allow-Origin", origin));// Vary: Origin
		response.addHeader("Access-Control-Allow-Methods", "POST");// apijson只允许post
		if (CollUtil.isNotEmpty(requestHeaders)) {
			requestHeaders.forEach(header -> response.addHeader("Access-Control-Allow-Headers", header));
		} else {
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Allow-Headers", "Authorization");
//			response.addHeader("Access-Control-Allow-Headers", "WWW-Authenticate");
		}
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Max-Age", "3600");// 一小时
	}
}
