package io.bitexpress.topia.commons.rpc;

/**
 * 可标识请求
 * 
 * @author godmonth
 *
 */
public interface IdentifiableRequest {
	RequestIdentity getRequestIdentity();
}
