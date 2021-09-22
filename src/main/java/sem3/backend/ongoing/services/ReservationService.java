package sem3.backend.ongoing.services;

import org.springframework.stereotype.Service;
import sem3.backend.ongoing.entities.Reservation;
import sem3.backend.ongoing.repositories.ReservationRepository;

import java.time.LocalDate;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public boolean isCarFree(int carId, LocalDate date){
        Reservation r = reservationRepository.findReservationByReservedCar_IdAndRentalDate(carId,date);
        return r==null ? true: false;
    }
}
