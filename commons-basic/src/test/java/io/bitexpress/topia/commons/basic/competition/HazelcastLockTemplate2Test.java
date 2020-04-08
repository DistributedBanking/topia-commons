package io.bitexpress.topia.commons.basic.competition;

import java.util.Properties;

import com.hazelcast.cp.IAtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastLockTemplate2Test {
	static {
		Properties props = System.getProperties();
		props.setProperty("hazelcast.logging.type", "slf4j");
		System.setProperties(props);
	}
	private HazelcastLockTemplate2 lockTemplate = new HazelcastLockTemplate2();
	private HazelcastInstance hazelcastInstance;

	@BeforeClass
	private void prepare() throws Exception {
		Config config = new Config();
		hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		lockTemplate.setHazelcastInstance(hazelcastInstance);

	}

	@AfterClass
	private void destroy() {
		hazelcastInstance.shutdown();
	}

	private static final Logger logger = LoggerFactory.getLogger(HazelcastLockTemplate2Test.class);

	@Test
	public void execute() {

		LockCallback<Void> competitionCallback = new LockCallback<Void>() {

			@Override
			public Void locked() {
				logger.info("ff");
				return null;
			}
		};
		lockTemplate.execute("a", competitionCallback);
		lockTemplate.execute("a", competitionCallback);
		lockTemplate.execute("a", competitionCallback);

	}


}
