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
    public Reservation getReservationByIdAndAccountId(Long accountId, Long reservationId) {
        return reservationRepository.findByIdAndAccountId(reservationId, accountId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reservation not found with Id " + reservationId +
                                " and AccountId " + accountId));
    }

    @Override
    public Page<Reservation> getAllReservationsByAccountId(Long accountId, Pageable pageable) {
      return reservationRepository.findByAccountId(accountId,pageable);
    }

    @Override
    public Reservation createReservation(Long accountId, Reservation reservation) {
        return accountRepository.findById(accountId).map(account->{
            reservation.setAccount(account);
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Account","Id",accountId));
    }

    @Override
    public Reservation updateReservation(Long accountId, Long reservationId, Reservation reservationRequest) {
        if(!accountRepository.existsById(accountId))
            throw new ResourceNotFoundException("Account","Id", accountId);
        return reservationRepository.findById(reservationId).map(reservation -> {
            reservation.setStatus(reservationRequest.getStatus());
            reservation.setInitialDate(reservationRequest.getInitialDate());
            reservation.setEndDate(reservationRequest.getEndDate());
            return reservationRepository.save(reservation);
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }

    @Override
    public ResponseEntity<?> deleteReservation(Long accountId, Long reservationId) {
        if(!accountRepository.existsById(accountId))
            throw new ResourceNotFoundException("Account","Id", accountId);

        return reservationRepository.findById(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",reservationId));
    }
}