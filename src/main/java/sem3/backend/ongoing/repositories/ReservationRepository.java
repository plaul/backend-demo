package sem3.backend.ongoing.repositories;

import io.swagger.models.auth.In;
import org.springframework.data.repository.CrudRepository;
import sem3.backend.ongoing.entities.Reservation;

import java.time.LocalDate;

public interface ReservationRepository extends CrudRepository<Reservation, Integer>
{
    Reservation findReservationByReservedCar_IdAndRentalDate(int reservedCar, LocalDate date);


}
