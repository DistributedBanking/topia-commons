package io.bitexpress.topia.commons.data.rpc;

import com.godmonth.util.dozer.DozerMapperFunction;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import io.bitexpress.topia.commons.basic.rpc.utils2.BodyResponseUtils;
import io.bitexpress.topia.commons.basic.rpc.utils2.ListBodyResponseUtils;
import io.bitexpress.topia.commons.pagination.Pagination;
import io.bitexpress.topia.commons.rpc.SystemCode;
import io.bitexpress.topia.commons.rpc.response.BodyResponse;
import io.bitexpress.topia.commons.rpc.response.ListBodyResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;

import java.util.List;

public class ResponseHelper2<DOMAIN, MODEL> implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(ResponseHelper2.class);

	protected Class<MODEL> modelClass;

	protected Mapper mapper;

	protected Function<DOMAIN, MODEL> function;

	@Override
	public void afterPropertiesSet() throws Exception {
		function = new DozerMapperFunction<DOMAIN, MODEL>(mapper, modelClass);
	}

	public BodyResponse<MODEL> returnSuccess(DOMAIN domain) {
		if (domain != null) {
			return BodyResponseUtils.successBodyResponse(getFunction().apply(domain));
		} else {
			return BodyResponseUtils.successBodyResponse(null);
		}
	}

	public ListBodyResponse<MODEL> returnListSuccess(List<DOMAIN> domainList) {
		if (CollectionUtils.isNotEmpty(domainList)) {
			List<MODEL> modelList = Lists.transform(domainList, getFunction());
			return ListBodyResponseUtils.successListBodyResponse(modelList);
		} else {
			return ListBodyResponseUtils.successListBodyResponse(null);
		}
	}

	public BodyResponse<Pagination<MODEL>> returnPageSuccess(Page<DOMAIN> domainPage) {
		Pagination<MODEL> transform = PageTransformer.transform(domainPage, getFunction());
		return BodyResponseUtils.successBodyResponse(transform);
	}

	public BodyResponse<Pagination<MODEL>> returnPageSuccess(Pagination<DOMAIN> domainPage) {
		Pagination<MODEL> transform = PageTransformer.transform(domainPage, getFunction());
		return BodyResponseUtils.successBodyResponse(transform);
	}

	public <K> BodyResponse<MODEL> returnError(K key, Exception e) {
		logger.error("", e);
		BodyResponse<MODEL> sor = BodyResponseUtils.codeBodyResponse(null, SystemCode.FAILURE, null,
				e.getMessage());

		sor.getHeader().setTrace(ExceptionUtils.getStackTrace(e));
		if (key != null) {
			DOMAIN domain = findModel(key);
			if (domain != null) {
				sor.setBody(getFunction().apply(domain));
			}
		}
		return sor;
	}

	protected DOMAIN findModel(Object key) {
		return null;
	}

	public void setModelClass(Class<MODEL> modelClass) {
		this.modelClass = modelClass;
	}

	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

	public Function<DOMAIN, MODEL> getFunction() {
		return function;
	}

}
