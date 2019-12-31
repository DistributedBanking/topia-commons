package io.bitexpress.topia.commons.rpc.error.i18n;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p></p >
 *
 * @author shenyue
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class I18nMessage {

    private String key;

    private Object[] params;
}
