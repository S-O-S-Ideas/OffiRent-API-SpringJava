package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationService {

    Reservation getReservationByIdAndAccountId (Long accountId, Long reservationId);
    Page<Reservation> getAllReservationsByAccountId(Long accountId,Pageable pageable);
    Reservation createReservation(Long accountId, Reservation reservation);
    Reservation updateReservation(Long accountId, Long reservationId, Reservation reservationRequest);
    ResponseEntity<?> deleteReservation(Long accountId, Long reservationId);
}
