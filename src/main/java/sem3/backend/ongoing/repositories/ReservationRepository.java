package sem3.backend.ongoing.repositories;

import org.springframework.data.repository.CrudRepository;
import sem3.backend.ongoing.entities.Reservation;

import java.time.LocalDate;

public interface ReservationRepository extends CrudRepository<Reservation,Integer> {
    Reservation findReservationByCar_IdAndRentalDate(int id, LocalDate date);
}
