package io.bitexpress.topia.commons.basic.competition;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VmLockTemplateTest {

    private VmLockTemplate lockTemplate = new VmLockTemplate();

    @Test
    public void execute() {

        LockCallback<Void> competitionCallback = new LockCallback<Void>() {

            @Override
            public Void locked() {
                log.info("ff");
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
