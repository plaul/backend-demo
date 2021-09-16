package sem3.backend.ongoing.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.entities.Member;
import sem3.backend.ongoing.entities.Reservation;
import sem3.backend.ongoing.repositories.CarRepository;
import sem3.backend.ongoing.repositories.MemberRepository;
import sem3.backend.ongoing.repositories.ReservationRepository;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class DataSetup implements CommandLineRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    public DataSetup(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Car car1 = carRepository.save(new Car("Volvo","C40",560));
        carRepository.save(new Car("Volvo","C40",560));
        carRepository.save(new Car("Volvo","C40",560));
        carRepository.save(new Car("Volvo","XC60",560));
        carRepository.save(new Car("Suzuki","Vitara",500));
        carRepository.save(new Car("Suzuki","Vitara",500));
        carRepository.save(new Car("Suzuki","S-Cross",500));
        carRepository.save(new Car("Peugeot","208",480));

        Member kurt = new Member("Kurt","Wonnegut","kw@somewhere.com");
        kurt.setStreet("Lyngbyvej 27");
        kurt.setZip("2800");
        kurt.setCity("Lyngby");
        Member mKurt = memberRepository.save(kurt);
        memberRepository.save(new Member("Hanne","Wonnegut","hanne@somewhere.com"));
        memberRepository.save(new Member("Jan","Olsen","jan@somewhere.com"));
        memberRepository.save(new Member("Jannie","Peterson","jannie@somewhere.com"));

        Reservation r1 = new Reservation(LocalDate.of(2021, Month.NOVEMBER,4),car1,mKurt);
        Reservation r2 = new Reservation(LocalDate.of(2021, Month.NOVEMBER,5),car1,mKurt);
        Reservation r3 = new Reservation(LocalDate.of(2021, Month.NOVEMBER,6),car1,mKurt);
        Reservation r4 = new Reservation(LocalDate.of(2021, Month.NOVEMBER,7),car1,mKurt);
        Reservation r5 = new Reservation(LocalDate.of(2021, Month.NOVEMBER,8),car1,mKurt);
        reservationRepository.save(r1);
        reservationRepository.save(r2);
        reservationRepository.save(r3);
        reservationRepository.save(r4);
        reservationRepository.save(r5);
    }
}
