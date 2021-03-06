package io.bitexpress.topia.commons.basic.rpc.utils2;

import io.bitexpress.topia.commons.rpc.request.BaseRequest;
import io.bitexpress.topia.commons.rpc.request.BodyRequest;
import io.bitexpress.topia.commons.rpc.request.ListBodyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * 基础响应组件
 *
 * @author shenyue
 */
public class BaseRequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(BaseRequestUtils.class);

    private BaseRequestUtils() {
    }

    public static BaseRequest empty() {
        return BaseRequest.builder().build();
    }

    public static <T extends Serializable> BodyRequest<T> body(T body) {
        return new BodyRequest<>();
    }

    public static <T extends Serializable> ListBodyRequest<T> listBodyRequest(List<T> listBody) {
        ListBodyRequest listBodyRequest = new ListBodyRequest();
        listBodyRequest.setBody(listBody);
        return listBodyRequest;
    }

    public static <T extends Serializable> BodyRequest<T> listBodyRequest(T body) {
        BodyRequest bodyRequest = new BodyRequest();
        bodyRequest.setBody(body);
        return bodyRequest;
    }


}
