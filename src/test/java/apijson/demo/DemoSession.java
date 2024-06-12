package apijson.demo;

import java.util.HashMap;
import java.util.Map;

import apijson.framework.HttpSession;
import cn.hutool.core.util.IdUtil;

public class DemoSession implements HttpSession {
	private String id;
	private Map<String, Object> attributes = new HashMap<>();

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Object getAttribute(String name) {
		return attributes.get(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	@Override
	public void invalidate() {
		Long userId = DemoVerifier.getVisitorId(this);
		sessions.remove(userId);
	}

	public static Map<Long, DemoSession> sessions = new HashMap<>();

	public static DemoSession of(Long userId) {
		if (userId == null) {
			return null;
		}
		DemoSession session = sessions.get(userId);
		if (session == null) {
			session = new DemoSession();
			session.id = IdUtil.fastSimpleUUID();
			sessions.put(userId, session);
		}
		return session;
	}
}
