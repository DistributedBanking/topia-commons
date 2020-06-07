package io.bitexpress.topia.commons.concept.scope;

import org.junit.jupiter.api.Test;

import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p></p >
 *
 * @author shenyue
 */
class ScopeTest {
    @Test
    void name() {
        Scope<Serializable> build = Scope.builder().from("s").to("ff").build();
        System.out.println(build);
    }
}