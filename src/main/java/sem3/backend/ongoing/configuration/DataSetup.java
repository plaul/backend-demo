package sem3.backend.ongoing.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.entities.Member;
import sem3.backend.ongoing.repositories.CarRepository;
import sem3.backend.ongoing.repositories.MemberRepository;

@Configuration
public class DataSetup implements CommandLineRunner {

    CarRepository carRepository;
    MemberRepository memberRepository;

    public DataSetup(CarRepository carRepository, MemberRepository memberRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        carRepository.save(new Car("Volvo","C40",560));
        carRepository.save(new Car("Volvo","C40",560));
        carRepository.save(new Car("Volvo","C40",560));
        carRepository.save(new Car("Volvo","XC60",560));
        carRepository.save(new Car("Suzuki","Vitara",500));
        carRepository.save(new Car("Suzuki","Vitara",500));
        carRepository.save(new Car("Suzuki","S-Cross",500));
        carRepository.save(new Car("Peugeot","208",480));

        memberRepository.save(new Member("Kurt","Wonnegut","kw@somewhere.com"));
        memberRepository.save(new Member("Hanne","Wonnegut","hanne@somewhere.com"));
        memberRepository.save(new Member("Jan","Olsen","jan@somewhere.com"));
        memberRepository.save(new Member("Jannie","Peterson","jannie@somewhere.com"));
    }
}
