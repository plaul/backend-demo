package sem3.backend.ongoing.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.entities.Member;
import sem3.backend.ongoing.entities.Reservation;
import sem3.backend.ongoing.repositories.CarRepository;
import sem3.backend.ongoing.repositories.MemberRepository;
import sem3.backend.ongoing.repositories.ReservationRepository;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;


@ActiveProfiles("test")  //Will prevent the DateSetup CommandlineRunner from running
@AutoConfigureTestDatabase
@EnableAutoConfiguration
@SpringBootTest(classes = {sem3.backend.ongoing.OngoingApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationControllerTest {


    //These are used to setup Testdata
    @Autowired
    CarRepository carRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ReservationRepository resRepository;

    private final String BASE_PATH = "/api/reservations";
    private final HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    int carId;

    @BeforeEach
    public void setupTestData() {
        //One car, One Member and one reservation should be enough
        resRepository.deleteAll();
        carRepository.deleteAll();
        memberRepository.deleteAll();

        Car car = carRepository.save(new Car("Volvo", "V40", 560));
        carId = car.getId();
        Member member = memberRepository.save(new Member("aaa", "bbb", "a@b.dk"));
        resRepository.save(new Reservation(LocalDate.of(2021, Month.NOVEMBER, 4), car, member));
    }

    @Test
    public void carIsNotFree(){
       String result = isCarFree(carId,"4-11-2021");
       assertEquals("false",result);
    }
    @Test
    public void carIsFree(){
       String result = isCarFree(carId,"4-10-2021");
       assertEquals("true",result);
    }


    //Get result as string ("true" or "false")
    String isCarFree(int id, String date) {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String url = makeUrl(BASE_PATH)+"/"+id+"/"+date;
        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET,
                entity,
                String.class);
        return response.getBody();

    }

    private String makeUrl(String path) {
        String pathBuilded = "http://localhost:" + port + path;
        System.out.println(pathBuilded);
        return pathBuilded;
    }

}
