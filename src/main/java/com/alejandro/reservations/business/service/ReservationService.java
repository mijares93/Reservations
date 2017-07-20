package com.alejandro.reservations.business.service;

import com.alejandro.reservations.business.domain.RoomReservation;
import com.alejandro.reservations.data.entity.Guest;
import com.alejandro.reservations.data.entity.Reservation;
import com.alejandro.reservations.data.entity.Room;
import com.alejandro.reservations.data.entity.repository.GuestRepository;
import com.alejandro.reservations.data.entity.repository.ReservationRepository;
import com.alejandro.reservations.data.entity.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mijares on 18/07/2017.
 */
@Service
public class ReservationService {

    private RoomRepository roomRepository;
    private GuestRepository guestRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date){
        Iterable<Room> rooms = this.roomRepository.findAll();

        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach( room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId( room.getId() );
            roomReservation.setRoomName( room.getName() );
            roomReservation.setRoomNumber( room.getNumber() );
            roomReservationMap.put( room.getId(), roomReservation );
        } );
        Iterable<Reservation> reservations = this.reservationRepository.findByDate( new java.sql.Date( date.getTime()));
        if(null!=reservations){
            reservations.forEach( reservation -> {
                Guest guest = this.guestRepository.findOne( reservation.getGuestId());
                if(null!=guest){
                    RoomReservation roomReservation = roomReservationMap.get( reservation.getId() );
                    roomReservation.setDate( date );
                    roomReservation.setFirstName( guest.getFirstName() );
                    roomReservation.setLastName( guest.getLastName() );
                    roomReservation.setGuestId( guest.getId() );
                }
            } );
        }
        List<RoomReservation> roomReservations = new ArrayList<>(  );
        for(Long roomId:roomReservationMap.keySet()){
            roomReservations.add( roomReservationMap.get( roomId ) );
        }
        return roomReservations;
    }

}
