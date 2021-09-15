package sem3.backend.ongoing.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sem3.backend.ongoing.entities.Car;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;

    @BeforeEach
    public void setupCars(){
        carRepository.save(new Car("Volvo","V40",560));
        carRepository.save(new Car("Volvo","V40",560));
        carRepository.save(new Car("Volvo","V40",560));
        carRepository.save(new Car("Suzuki","Vitara",500));
        carRepository.save(new Car("Suzuki","Vitara",500));
        carRepository.save(new Car("Suzuki","S-Cross",500));
        carRepository.save(new Car("Peugeot","208",480));
    }

    @Test
    void findCarByBrand() {
        long volvosFound = carRepository.findCarByBrand("Volvo").size();
        assertEquals(3,volvosFound);
    }

    @Test
    void findCarByBrandAndMode() {
        long vitaraCount = carRepository.findCarByBrandAndModel("Suzuki","Vitara").size();
        assertEquals(2,vitaraCount);
    }

    @Test
    void findCarByPricePrDayLessThan() {
        long carsFound = carRepository.findCarByPricePrDayLessThan(500).size();
        assertEquals(1,carsFound);
    }

    @Test
    void findCarByPricePrDayLessThanEqual() {
        long carsFound = carRepository.findCarByPricePrDayLessThanEqual(500).size();
        assertEquals(4,carsFound);
    }
}