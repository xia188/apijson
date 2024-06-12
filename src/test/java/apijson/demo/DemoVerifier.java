package apijson.demo;

import apijson.Log;
import apijson.framework.APIJSONVerifier;
import apijson.framework.HttpSession;

public class DemoVerifier extends APIJSONVerifier<Long> {
	public static Long adminUserId = Long.getLong("apijson.adminUserId", 70793L);

	@Override
	public void verifyAdmin() throws Exception {
		// 必须先登录
		super.verifyLogin();
		// 管理员判断逻辑可自定义
		if (!visitorId.equals(adminUserId)) {
			throw new IllegalAccessException("只允许管理员操作，当前用户权限不够！");
		}
	}

	// 参考verifyLogin(session)
	public static void verifyAdmin(HttpSession session) throws Exception {
		Log.d(TAG, "verifyAdmin  session.getId() = " + (session == null ? null : session.getId()));
		APIJSON_CREATOR.createVerifier().setVisitor(getVisitor(session)).verifyAdmin();
	}

}
