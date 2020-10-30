
package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.PaymentMethod;
import com.acme.offirent.domain.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


public interface PaymentMethodService {
    //Page<Resource> findByAccountIdAndPaymentMethodId(Long AccountId, long PaymentMethodId);

    PaymentMethod getById(Long paymentMethodId);
    //crud?
    PaymentMethod createPaymentMethod(PaymentMethod paymentMethod);
    PaymentMethod updatePaymentMethod(Long paymentMethodId,PaymentMethod resourceRequest);
    ResponseEntity<?> deletePaymentMethod(Long paymentMethodId);
}
