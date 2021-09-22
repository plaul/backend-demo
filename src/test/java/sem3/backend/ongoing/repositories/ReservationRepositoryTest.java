package sem3.backend.ongoing.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.entities.Member;
import sem3.backend.ongoing.entities.Reservation;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationRepositoryTest {
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReservationRepository reservationRepository;

    int carId;
    @BeforeEach
    public void setupTestData(){
        //One car, One Member and one reservation should be enough
        reservationRepository.deleteAll();
        carRepository.deleteAll();
        memberRepository.deleteAll();
        Car car = carRepository.save(new Car("Volvo","V40",560));
        carId = car.getId();
        Member member = memberRepository.save(new Member("aaa","bbb","a@b.dk"));
        reservationRepository.save(new Reservation(LocalDate.of(2021, Month.NOVEMBER,4),car,member));
    }

    @Test
    void findExistingReservationForCarOnDate() {
        Reservation res = reservationRepository.findReservationByReservedCar_IdAndRentalDate(carId,LocalDate.of(2021, Month.NOVEMBER,4));
        assertNotNull(res,"Expected to find a reservation for this car on this date");
    }

    @Test
    void shouldNotFindNonExistingReservation() {
        Reservation res = reservationRepository.findReservationByReservedCar_IdAndRentalDate(carId,LocalDate.of(2021, Month.JANUARY,4));
        assertNull(res,"Expected to NOT find a reservation for this car on this date");
    }
}