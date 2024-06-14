package apijson.hutool;

import java.sql.Connection;

import org.h2.tools.Server;
import org.junit.rules.ExternalResource;

import apijson.Log;
import apijson.demo.DemoCreator;
import apijson.framework.APIJSONApplication;
import apijson.framework.APIJSONFunctionParser;
import apijson.framework.APIJSONVerifier;
import cn.hutool.core.lang.Console;
import cn.hutool.db.Db;
import cn.hutool.db.ds.GlobalDSFactory;
import cn.hutool.http.HttpStatus;
import cn.hutool.http.server.SimpleServer;

public class DemoApp extends ExternalResource {
	static SimpleServer server;

	public static void main(String[] args) {
		init();
	}

	public static void init() {
		Log.DEBUG = false;
		// 控制初始化时的三步检查
		APIJSONVerifier.ENABLE_VERIFY_ROLE = true;
		APIJSONFunctionParser.ENABLE_REMOTE_FUNCTION = true;
		// https://github.com/APIJSON/APIJSON-Demo/blob/master/MySQL/single/Auto/sys_Script.sql
		APIJSONFunctionParser.ENABLE_SCRIPT_FUNCTION = true;
		APIJSONVerifier.ENABLE_VERIFY_CONTENT = true;
		try {
			APIJSONApplication.init(false, new DemoCreator());
		} catch (Exception e) {
			Console.error(e.getMessage());
			return;
		}
		server = new SimpleServer(8080);
		server.addAction("/", new DemoAction());
		server.addAction("/h2/", (request, response) -> {
			try (Connection conn = Db.use().getConnection()) {
				response.sendOk();
				Server.startWebServer(conn);
			} catch (Exception e) {
				response.sendError(HttpStatus.HTTP_INTERNAL_ERROR, e.getMessage());
			}
		});
		server.start();
	}

	public static void stop() {
		GlobalDSFactory.get().destroy();
		Console.log("DemoApp stop");
	}
}
