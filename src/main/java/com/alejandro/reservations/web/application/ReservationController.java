package com.alejandro.reservations.web.application;

import com.alejandro.reservations.business.domain.RoomReservation;
import com.alejandro.reservations.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mijares on 20/07/2017.
 */
@Controller
@RequestMapping(value="/reservations")
public class ReservationController {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd");

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method= RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateString, Model model){
        Date date = null;
        if (null!=dateString){
            try {
                date=DATE_FORMAT.parse( dateString );

            }catch (ParseException pe){
                date= new Date(  );
            }
            }else {
            date= new Date(  );
        }
        List<RoomReservation> roomReservationList = this.reservationService.getRoomReservationsForDate( date );
        model.addAttribute( "roomReservations", roomReservationList );

        return "reservations";
    }
}
