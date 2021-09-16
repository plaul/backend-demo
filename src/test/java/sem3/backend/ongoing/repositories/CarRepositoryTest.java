package sem3.backend.ongoing.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.testUtils.TestDataMaker;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;

    @BeforeEach
    public void setupCars(){
        TestDataMaker.makeCars(carRepository);
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