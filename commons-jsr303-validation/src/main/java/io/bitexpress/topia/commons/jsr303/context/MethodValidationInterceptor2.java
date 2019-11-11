package io.bitexpress.topia.commons.jsr303.context;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import io.bitexpress.topia.commons.rpc.BaseResponse;
import io.bitexpress.topia.commons.rpc.SystemCode;

public class MethodValidationInterceptor2 implements MethodInterceptor {
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			return invocation.proceed();
		} catch (ConstraintViolationException e) {
			Class<?> returnType = invocation.getMethod().getReturnType();
			if (BaseResponse.class.isAssignableFrom(returnType)) {
				BaseResponse baseResponse = (BaseResponse) returnType.newInstance();
				baseResponse.setSystemCode(SystemCode.FAILURE);
				baseResponse.setTrace(ExceptionUtils.getStackTrace(e));
				if (CollectionUtils.isNotEmpty(e.getConstraintViolations())) {
					ConstraintViolation<?> next = e.getConstraintViolations().iterator().next();
					baseResponse.setMessage(next.getPropertyPath().toString() + ":" + next.getMessage());
				}
				return baseResponse;
			} else {
				throw e;
			}
		}
	}

}
