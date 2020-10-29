package com.acme.offirent.domain.service;

import com.acme.offirent.domain.model.Account;
import com.acme.offirent.domain.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    Page <Reservation> getAllReservations(Pageable pageable);
    Reservation getReservationById (Long reservationId);
    Page<Reservation> getAllReservationsByAccountId(Long accountId,Pageable pageable);
    Reservation createReservation(Reservation reservation);
    Reservation updateReservation(Long reservationId, Reservation reservationRequest);
    ResponseEntity<?> deleteReservation(Long reservationId);
}
