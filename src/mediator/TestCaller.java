package mediator;

import junit.framework.TestCase;

public class TestCaller extends TestCase {
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public TestCaller(String name) {
		super(name);
	}

	public void testAdd() {
		Caller co = new Caller();
		co.add(220, 4, 1);
		co.allConnect();

	}
}
