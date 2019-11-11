package io.bitexpress.topia.commons.basic.curator;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.io.IOException;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.TestingServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ZookeeperSingletonRepoTest {

	private TestingServer zkTestServer;

	private CuratorFramework cli;

	private ZookeeperSingletonRepo<TestObject> zookeeperSingletonRepo;

	@BeforeClass
	public void startZookeeper() throws Exception {
		zkTestServer = new TestingServer();
		zkTestServer.start();
		cli = CuratorFrameworkFactory.newClient(zkTestServer.getConnectString(), new RetryOneTime(2000));
		cli.start();
		zookeeperSingletonRepo = new ZookeeperSingletonRepo<>();
		zookeeperSingletonRepo.setClazz(TestObject.class);
		zookeeperSingletonRepo.setCuratorFramework(cli);
		zookeeperSingletonRepo.setPath("/ffff");
		zookeeperSingletonRepo.setObjectMapper(new ObjectMapper());
		zookeeperSingletonRepo.init();
	}

	public static class TestObject {
		private String name;
		private int age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		/**
		 * @see java.lang.Object#equals(Object)
		 */
		public boolean equals(Object object) {
			if (!(object instanceof TestObject)) {
				return false;
			}
			TestObject rhs = (TestObject) object;
			return new EqualsBuilder().append(this.name, rhs.name).append(this.age, rhs.age).isEquals();
		}

	}

	@Test
	public void putAndGet() throws Exception {
		TestObject testObject = zookeeperSingletonRepo.get();
		assertNull(testObject);

		testObject = new TestObject();
		testObject.setAge(1);
		testObject.setName("ffff");
		zookeeperSingletonRepo.put(testObject);
		zookeeperSingletonRepo.put(testObject);
		TestObject testObject2 = zookeeperSingletonRepo.get();
		assertEquals(testObject2, testObject);
		zookeeperSingletonRepo.delete();
		testObject2 = zookeeperSingletonRepo.get();
		assertNull(testObject2);

	}

	@AfterClass
	public void stopZookeeper() throws IOException {
		zookeeperSingletonRepo.close();
		cli.close();
		zkTestServer.stop();
	}
}
