package io.bitexpress.topia.commons.jsr303.context;

import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.BaseResponse;
import io.bitexpress.topia.commons.rpc.response.ResponseHeader;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class MethodValidationInterceptor3 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (ConstraintViolationException e) {
            Class<?> returnType = invocation.getMethod().getReturnType();
            if (BaseResponse.class.isAssignableFrom(returnType)) {
                BaseResponse baseResponse = (BaseResponse) returnType.newInstance();
                ResponseHeader responseHeader = ResponseHeader.builder().systemCode(SystemCode.FAILURE).trace(ExceptionUtils.getStackTrace(e)).build();
                if (CollectionUtils.isNotEmpty(e.getConstraintViolations())) {
                    ConstraintViolation<?> next = e.getConstraintViolations().iterator().next();
                    responseHeader.setMessage(next.getPropertyPath().toString() + ":" + next.getMessage());
                    baseResponse.setHeader(responseHeader);
                }
                return baseResponse;
            } else {
                throw e;
            }
        }
    }

}
