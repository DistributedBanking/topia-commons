package io.bitexpress.topia.commons.data.keygenerator;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p></p >
 *
 * @author shenyue
 */
class TimeLongTableGeneratorTest {
    @Id
    @GenericGenerator(name = "receiverItemGenerator", strategy = "com.wexmarket.topia.commons.data.keygenerator.LongSequenceGenerator", parameters = {
            @Parameter(name = "table_name", value = "t_sequence_table"),
            @Parameter(name = "allocationSize", value = "1"),
           })
    @GeneratedValue(generator = "receiverItemGenerator")
    private Long id;

}