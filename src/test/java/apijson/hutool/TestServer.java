package apijson.hutool;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.rules.ExternalResource;

public class TestServer extends ExternalResource {
	private static final AtomicInteger refCount = new AtomicInteger(0);

	private static final TestServer instance = new TestServer();

	public static TestServer getInstance() {
		return instance;
	}

	private TestServer() {

	}

	@Override
	protected void before() {
		try {
			if (refCount.get() == 0) {
				DemoApp.init();
			}
		} finally {
			refCount.getAndIncrement();
		}
	}

	@Override
	protected void after() {
		refCount.getAndDecrement();
		if (refCount.get() == 0) {
			DemoApp.stop();
		}
	}
}
