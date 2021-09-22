package sem3.backend.ongoing.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sem3.backend.ongoing.services.ReservationService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{carId}/{date}")
    boolean isCarfree(@PathVariable int carId, @PathVariable String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate d = LocalDate.parse(date,formatter);
        return reservationService.isCarFree(carId,d);

    }
}
