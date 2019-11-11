package io.bitexpress.topia.commons.idempotence;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.godmonth.status.advancer.intf.NextOperation;
import com.godmonth.status.executor.intf.OrderExecutor;
import io.bitexpress.topia.commons.data.model.IdObject;
import io.bitexpress.topia.commons.rpc.IdentifiableRequest;

public class IdempotenceTemplate<T, RI extends IdObject<Long>> {

	private RequestIdentityRepo<RI> requestIdentityRepo;

	private OrderExecutor<T, Void> orderExecutor;

	private PagingAndSortingRepository<T, Long> crudRepository;

	private TransactionTemplate transactionTemplate;

	private String parentType;

	private NextOperation afterSaveOperation = NextOperation.ADVANCE;

	private class RequestIdentitySupplier<REQ extends IdentifiableRequest> implements Supplier<RI> {
		private REQ request;

		public RequestIdentitySupplier(REQ request) {
			this.request = request;
		}

		@Override
		public RI get() {
			return requestIdentityRepo.save(request.getRequestIdentity(), parentType);
		}
	};

	private <REQ extends IdentifiableRequest> T getOrder(REQ request) {
		RI ri = requestIdentityRepo.findByRi(request.getRequestIdentity());
		if (ri != null) {
			return crudRepository.findById(ri.getId()).orElse(null);
		} else {
			return null;
		}
	}

	public <REQ extends IdentifiableRequest> T execute(final REQ request,
			final Function<IdempotenceParam<REQ, RI>, T> callback) {
		T order = getOrder(request);
		if (order == null) {
			order = transactionTemplate.execute(new TransactionCallback<T>() {

				@Override
				public T doInTransaction(TransactionStatus arg0) {
					return callback
							.apply(new IdempotenceParam<REQ, RI>(new RequestIdentitySupplier<REQ>(request), request));
				}
			});
		}
		switch (afterSaveOperation) {
		case ADVANCE:
			return orderExecutor.execute(order, null, null).getModel();
		case ASYNC_ADVANCE:
			orderExecutor.executeAsync(order, null, null);
			break;
		case PAUSE:
			break;
		default:
		}
		return order;

	}

	public void setRequestIdentityRepo(RequestIdentityRepo<RI> requestIdentityRepo) {
		this.requestIdentityRepo = requestIdentityRepo;
	}

	public void setOrderExecutor(OrderExecutor<T, Void> orderExecutor) {
		this.orderExecutor = orderExecutor;
	}

	public void setCrudRepository(PagingAndSortingRepository<T, Long> crudRepository) {
		this.crudRepository = crudRepository;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public void setAfterSaveOperation(NextOperation afterSaveOperation) {
		this.afterSaveOperation = afterSaveOperation;
	}

}
