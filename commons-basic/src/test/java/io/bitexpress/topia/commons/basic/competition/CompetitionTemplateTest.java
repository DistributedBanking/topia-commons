package io.bitexpress.topia.commons.basic.competition;

import java.util.Date;

import org.joda.time.Period;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class CompetitionTemplateTest {

	private CompetitionTemplate competitionTemplate;
	private HazelcastInstance hazelcastInstance;

	@BeforeClass
	private void prepare() throws Exception {
		Config config = new Config();
		hazelcastInstance = Hazelcast.newHazelcastInstance(config);
		competitionTemplate = new CompetitionTemplate();
		competitionTemplate.setLastExecutedTimeKey("test1let");
		competitionTemplate.setLockKey("testlockk1");
		competitionTemplate.setHazelcastInstance(hazelcastInstance);
		competitionTemplate.setCoolingPeriod(Period.ZERO);
		competitionTemplate.afterPropertiesSet();

	}

	@AfterClass
	private void destroy() {
		hazelcastInstance.shutdown();
	}

	@Test
	public void execute() {
		CompetitionCallback competitionCallback = new CompetitionCallback() {
			@Override
			public Void won(Date executeTime) {
				System.out.println("win");
				return null;
			}
		};
		competitionTemplate.execute(competitionCallback);
		competitionTemplate.execute(competitionCallback);
	}
}
