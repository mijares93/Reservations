package com.alejandro.reservations.web.application.service;

import com.alejandro.reservations.business.domain.RoomReservation;
import com.alejandro.reservations.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mijares on 25/07/2017.
 */
@RestController
@RequestMapping(value = "/api")
public class ReservationServiceController {

    @Autowired
    private ReservationService reservationService;
    public List<RoomReservation> getAllReservationsForDate(@PathVariable(value = "date")String dateString){
        return this.reservationService.getRoomReservationsForDate( dateString );

    }
}
