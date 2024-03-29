package io.bitexpress.topia.commons.basic.competition;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VmLockTemplateTest {

    private static final Logger logger = LoggerFactory.getLogger(VmLockTemplateTest.class);
    private VmLockTemplate lockTemplate = new VmLockTemplate();

    @Test
    public void execute() {

        LockCallback<Void> competitionCallback = new LockCallback<Void>() {

            @Override
            public Void locked() {
                logger.info("ff");
                return null;
            }
        };
        lockTemplate.execute("a", "a", competitionCallback);
        lockTemplate.execute("a", "b", competitionCallback);
        lockTemplate.execute("a", "b", competitionCallback);
        Map<String, Pair<DateTime, String>> lockInfoMap = lockTemplate.getLockInfoMap();
        System.out.println(lockInfoMap);

    }

}
