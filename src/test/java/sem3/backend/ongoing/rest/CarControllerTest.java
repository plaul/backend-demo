package sem3.backend.ongoing.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import sem3.backend.ongoing.dtos.CarDTO;
import sem3.backend.ongoing.repositories.CarRepository;
import sem3.backend.ongoing.testUtils.TestDataMaker;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase
@EnableAutoConfiguration
@SpringBootTest(classes = {sem3.backend.ongoing.OngoingApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    @Autowired
    CarRepository carRepository; //Used to setup testdata
    private final String BASE_PATH = "/api/cars";
    private final HttpHeaders headers = new HttpHeaders();

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    //Will ALWAYS hold the ids of cars inserted for the test
    private static List<Integer> carIds;

    @BeforeEach
    public void setUpCarData(){
        carIds = TestDataMaker.makeCars(carRepository);
    }

    @Test
    void getCars() {
        ResponseEntity<List<CarDTO>> response = getResponseFromAllCars();
        assertEquals(7,response.getBody().size());
    }

    private ResponseEntity<List<CarDTO>> getResponseFromAllCars() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<List<CarDTO>> response = restTemplate.exchange(makeUrl(BASE_PATH),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<CarDTO>>() {});
        return response;
    }

    @Test
    void getCar() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<CarDTO> response = restTemplate.exchange(makeUrl(BASE_PATH+ "/"+carIds.get(3)),
                HttpMethod.GET,
                entity,
                CarDTO.class);
        assertEquals("Suzuki",response.getBody().getBrand());
    }

    @Test
    void addCar() {
        CarDTO newCar = new CarDTO("Nissan","Micra",400);
        HttpEntity<CarDTO> entity = new HttpEntity<CarDTO>(newCar,headers);
        ResponseEntity<CarDTO> response = restTemplate.exchange(makeUrl(BASE_PATH),
                HttpMethod.POST,
                entity,
                CarDTO.class);
        assertTrue(response.getBody().getId()>0);
        assertEquals("Nissan",response.getBody().getBrand());
    }

    @Test
    void editCar() {
        int id = carIds.get(3);//Car number four in the inserts
        CarDTO carToEdit = new CarDTO("Suzuki","Vitara",425);
        carToEdit.setId(id);
        HttpEntity<CarDTO> entity = new HttpEntity<CarDTO>(carToEdit,headers);
        ResponseEntity<CarDTO> response = restTemplate.exchange(makeUrl(BASE_PATH+"/"+id),
                HttpMethod.PUT,
                entity,
                CarDTO.class);
        assertEquals(id,response.getBody().getId());
        assertEquals(425,response.getBody().getPricePrDay());
    }

    @Test
    void deleteCar() {
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<CarDTO> response = restTemplate.exchange(makeUrl(BASE_PATH+ "/"+carIds.get(3)),
                HttpMethod.DELETE,
                entity,
                CarDTO.class);
        ResponseEntity<List<CarDTO>> res = getResponseFromAllCars();
        assertEquals(7-1,res.getBody().size());
        
    }

    private String makeUrl(String path){
        String pathBuilded = "http://localhost:"+port+path;
        System.out.println(pathBuilded);
        return pathBuilded;
    }
}