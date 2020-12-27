package io.bitexpress.topia.commons.rpc.error;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class ErrorCodeImpl implements ErrorCode {

    private String code;
    private String Template;

}
