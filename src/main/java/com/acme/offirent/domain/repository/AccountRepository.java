package com.acme.offirent.domain.repository;

import com.acme.offirent.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Page <Account> findByPaymentMethodId (Long PaymentMethodId, Pageable pageable);
    Optional <Page<Account>> findByReservationId (Long reservationId, Pageable);
    Optional <Page<Account>> findByOfficeId (Long officeId, Pageable);
}