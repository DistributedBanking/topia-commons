package io.bitexpress.topia.commons.basic.competition;

import java.util.Properties;

import com.hazelcast.cp.IAtomicReference;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class HazelcastLockTemplate2Test {
    private static final Logger logger = LoggerFactory.getLogger(HazelcastLockTemplate2Test.class);
    private static HazelcastInstance hazelcastInstance;
    private static HazelcastLockTemplate2 lockTemplate = new HazelcastLockTemplate2();

    static {
        Properties props = System.getProperties();
        props.setProperty("hazelcast.logging.type", "slf4j");
        System.setProperties(props);
    }

    @BeforeAll
    private static void prepare() throws Exception {
        Config config = new Config();
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        lockTemplate.setHazelcastInstance(hazelcastInstance);

    }

    @AfterAll
    private static void destroy() {
        hazelcastInstance.shutdown();
    }

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
