package sem3.backend.ongoing.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.FixedWidth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.entities.Member;
import sem3.backend.ongoing.entities.Reservation;
import sem3.backend.ongoing.repositories.CarRepository;
import sem3.backend.ongoing.repositories.MemberRepository;
import sem3.backend.ongoing.repositories.ReservationRepository;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest  //We mock the database with H2
class ReservationServiceTest {
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReservationRepository reservationRepository;

    int carId;
    public void setupTestData(){
        reservationRepository.deleteAll();
        carRepository.deleteAll();
        memberRepository.deleteAll();
        //One car, One Member and one reservation should be enough
        Car car = carRepository.save(new Car("Volvo","V40",560));
        carId = car.getId();
        Member member = memberRepository.save(new Member("aaa","bbb","a@b.dk"));
        reservationRepository.save(new Reservation(LocalDate.of(2021, Month.NOVEMBER,4),car,member));
    }

    //NOTE --> All the code above is Almost identical to what was done in the ReservationRepository test
    //         If you have time, refactor to avoid redundant code

    ReservationService reservationService;

    @BeforeEach
    public void prepareAllForTest(){
        setupTestData();
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    void carIsReserved() {
        boolean carIsFreeOnDate = reservationService.isCarFree(carId,LocalDate.of(2021, Month.NOVEMBER,4));
        assertFalse(carIsFreeOnDate,"Expect false for a car reserved on given date");
    }
    @Test
    void carIsNotReserved() {
        boolean carIsFreeOnDate = reservationService.isCarFree(carId,LocalDate.of(2021, Month.JANUARY,4));
        assertTrue(carIsFreeOnDate,"Expect true for a car NOT reserved on the given date");
    }
}