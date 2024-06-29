package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.MapBodyResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 包含处理数据的响应组件
 *
 * @author shenyue
 */
public class MapBodyResponseUtils {
    private static final Logger logger = LoggerFactory.getLogger(MapBodyResponseUtils.class);

    public static <K, V> MapBodyResponse<K, V> codeMapResponse(Map<K, V> body, SystemCode systemCode, String businessCode, String message) {
        ResponseHeader responseHeader = ResponseHeader.builder().systemCode(systemCode).businessCode(businessCode).message(message).build();
        return MapBodyResponse.<K, V>mapBodyBuilder().header(responseHeader).body(body).build();
    }

    public static <K, V> MapBodyResponse<K, V> successMapResponse(Map<K, V> result) {
        return codeMapResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <K, V> MapBodyResponse<K, V> failureMapResponse(String message) {
        return codeMapResponse(null, SystemCode.FAILURE, null, message);
    }


}
