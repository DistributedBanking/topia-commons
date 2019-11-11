package io.bitexpress.topia.commons.basic.patroller;

import org.testng.annotations.Test;

import io.bitexpress.topia.commons.basic.competition.VmLockTemplate2;

public class MutexPatrollerTest {

	@Test
	public void patrol() throws Exception {
		VmLockTemplate2 vt = new VmLockTemplate2();
		TestObject to = new TestObject();
		MutexPatroller mp = new MutexPatroller();
		mp.setLockKey("lockkkk");
		mp.setLockTemplate(vt);
		mp.setTarget(to);
		mp.setMethod("hello");
		mp.afterPropertiesSet();
		mp.patrol();
	}

	private static class TestObject {
		public void hello() {
			System.out.println("ok");
		}
	}
}
