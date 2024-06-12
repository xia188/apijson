package apijson.hutool;

import java.io.IOException;

import apijson.demo.DemoController;
import apijson.demo.DemoSession;
import apijson.framework.HttpSession;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
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
			HttpSession session = getSession(request);
			switch (method) {
			case "login":
				body = apijson.login(body, session).toJSONString();
				break;
			case "logout":
				body = apijson.logout(session).toJSONString();
				break;
			default:
				body = apijson.crud(method, body, session);
				break;
			}
			Console.log(body);
			response.write(body);
		} else {
			response.write("必须是post请求");
		}
	}

	/**
	 * 计算jwt令牌，信息尽可能少，只有userId最好
	 * 
	 * @param userId
	 * @return
	 */
	public static String token(Long userId) {
		return userId == null ? StrUtil.EMPTY : String.valueOf(userId);
	}

	/**
	 * 解析jwt令牌，计算userId，再去拿session
	 * 
	 * @param jwt
	 * @return
	 */
	public static Long userId(String jwt) {
		return StrUtil.isBlank(jwt) ? null : Long.valueOf(jwt);
	}

	private HttpSession getSession(HttpServerRequest request) {
		String jwt = request.getHeader(Header.AUTHORIZATION);
		Long userId = userId(jwt);
		return DemoSession.of(userId);
	}

}
