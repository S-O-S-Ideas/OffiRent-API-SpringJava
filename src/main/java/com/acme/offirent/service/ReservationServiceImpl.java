package com.acme.offirent.service;

import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.repository.AccountRepository;
import com.acme.offirent.domain.repository.ReservationRepository;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Page<Reservation> getAllReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }

    @Override
    public Page<Reservation> getAllReservationsByAccountId(Long accountId, Pageable pageable) {
        return accountRepository.findById(accountId).map(account -> {
            List<Reservation> reservations = account.getReservations();
            int reservationsCount = reservations.size();
            return new PageImpl<>(reservations,pageable,reservationsCount);
        }).orElseThrow(()->new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Long reservationId, Reservation reservationRequest) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(reservationRequest.getStatus());
            reservation.setInitialDate(reservationRequest.getInitialDate());
            reservation.setEndDate(reservationRequest.getEndDate());
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long reservationId) {
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }
}
