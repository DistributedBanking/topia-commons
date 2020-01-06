package io.bitexpress.topia.commons.rpc.i18n;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class I18nMessage {

    private String key;

    private Serializable[] params;
}
