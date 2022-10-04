package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.rpc.BusinessCode;
import io.bitexpress.topia.commons.rpc.SystemCode;
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

    public static <K, V> MapResultResponse<K, V> codeMapResultResponse(Map<K, V> result, SystemCode systemCode,
                                                                       String businessCode, String message) {
        MapResultResponse<K, V> rr = new MapResultResponse<K, V>();
        rr.setSystemCode(systemCode);
        rr.setBusinessCode(businessCode);
        rr.setMessage(message);
        rr.setResult(result);
        return rr;
    }

    public static <K, V> MapResultResponse<K, V> successMapResultResponse(Map<K, V> result) {
        return codeMapResultResponse(result, SystemCode.SUCCESS, BusinessCode.SUCCESS.name(), null);
    }

    public static <K, V> MapResultResponse<K, V> failureMapResultResponse(String message) {
        return codeMapResultResponse(null, SystemCode.FAILURE, null, message);
    }


}
