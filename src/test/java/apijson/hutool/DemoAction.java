package apijson.hutool;

import java.io.IOException;

import apijson.demo.DemoController;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;

public class DemoAction implements Action {
	private static DemoController apijson = new DemoController();

	@Override
	public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
		Console.log("{} {}", request.getMethod(), request.getPath());
		if (request.isPostMethod()) {
			String method = StrUtil.trimToEmpty(request.getPath());
			method = method.startsWith("/") ? method.substring(1) : method;
			String body = request.getBody();
			Console.log(body);
			String resp = apijson.crud(method, body, null);
			Console.log(resp);
			response.write(resp);
		} else {
			response.write("必须是post请求");
		}
	}

}
