package com.acme.offirent.controller;


import com.acme.offirent.domain.model.Reservation;
import com.acme.offirent.domain.service.ReservationService;
import com.acme.offirent.resource.ReservationResource;
import com.acme.offirent.resource.SaveReservationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "accounts")
@RestController
@RequestMapping("/api")
public class ReservationsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ReservationService reservationService;


    @GetMapping("/accounts/{accountId}/reservations")
    public Page<ReservationResource> getAllReservationsByAccountId(
            @PathVariable Long accountId, Pageable pageable){

        Page<Reservation> reservationPage = reservationService.getAllReservationsByAccountId(accountId,pageable);
        List<ReservationResource> resources = reservationPage.getContent().stream().map(
                this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources,pageable,resources.size());
    }
/*
   @GetMapping("/accounts/{accountId}/reservations/{reservationId}")
    public Page<ReservationResource> getReservationByIdAndAccountId(
            @PathVariable(name = "accountId") Long accountId,
            @PathVariable(name = "reservationId") Long reservationId){

        return convertToResource(reservationService.getReservationByIdAndAccountId(accountId,reservationId));
    }
    */


   @PostMapping("/accounts/{accountId}/reservations")
    public ReservationResource createReservation(
            @PathVariable Long accountId,
            @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(reservationService.createReservation(accountId,convertToEntity(resource)));
    }

    @PutMapping("/accounts/{accountId}/reservations/{reservationId}")
    public ReservationResource updateReservation(
            @PathVariable (value = "accountId") Long accountId,
            @PathVariable (value = "reservationId") Long reservationId,
            @Valid @RequestBody SaveReservationResource resource){
        return convertToResource(reservationService.updateReservation(accountId, reservationId,convertToEntity(resource)));
    }

    @DeleteMapping("/accounts/{accountId}/reservations/{reservationId}")
    public ResponseEntity<?> deleteReservation(
            @PathVariable(value = "accountId") Long accountId,
            @PathVariable(value = "reservationId") Long reservationId){
        return reservationService.deleteReservation(accountId,reservationId);
    }

    private Reservation convertToEntity(SaveReservationResource resource){
        return mapper.map(resource,Reservation.class);
    }
    private ReservationResource convertToResource(Reservation entity){
        return mapper.map(entity,ReservationResource.class);
    }


}
