package io.bitexpress.topia.commons.rpc.error.i18n;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p></p >
 *
 * @author shenyue
 * @deprecated use {@link io.bitexpress.topia.commons.rpc.i18n.I18nMessage}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class I18nMessage implements Serializable {

    private String key;

    private Serializable[] params;
}
