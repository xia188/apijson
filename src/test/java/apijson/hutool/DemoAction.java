package apijson.hutool;

import java.io.File;
import java.io.IOException;

import apijson.demo.DemoController;
import apijson.demo.DemoSession;
import apijson.framework.HttpSession;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.Method;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;
import cn.hutool.http.server.action.RootAction;

public class DemoAction implements Action {
	private static DemoController apijson = new DemoController();
	private static RootAction root = new RootAction(new File("apijson/APIAuto"));

	@Override
	public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
		if (request.isPostMethod()) {
			Console.log("{} {}", request.getMethod(), request.getPath());
			String method = StrUtil.trimToEmpty(request.getPath()), tag = null;
			method = method.startsWith("/") ? method.substring(1) : method;
			int slash = method.indexOf('/');
			if (slash > 0) {
				tag = method.substring(slash + 1);
				method = method.substring(0, slash);
			}
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
			case "reload":
				body = apijson.reload(body, session).toJSONString();
				break;
			default:
				if (StrUtil.isBlank(tag)) {
					body = apijson.crud(method, body, session);
				} else {
					body = apijson.crudByTag(method, tag, null, body, session);
				}
				break;
			}
			Console.log(body);
			CorsUtil.addCorsHeader(request, response);
			response.addHeader("Content-Type", "application/json");
			response.write(body);
		}else if(Method.GET.name().equalsIgnoreCase(request.getMethod())) {
			root.doAction(request, response);
		} else if (Method.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
			CorsUtil.addCorsHeader(request, response);
			response.sendOk();
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
