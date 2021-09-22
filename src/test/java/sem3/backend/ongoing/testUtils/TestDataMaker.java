package sem3.backend.ongoing.testUtils;

import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.repositories.CarRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataMaker {

    public static List<Integer> makeCars(CarRepository carRepository)  {
        carRepository.deleteAll();
        List<Integer> ids = new ArrayList<>();
        ids.add(carRepository.save(new Car("Volvo","V40",560)).getId());
        ids.add(carRepository.save(new Car("Volvo","V40",560)).getId());
        ids.add(carRepository.save(new Car("Volvo","V40",560)).getId());
        ids.add(carRepository.save(new Car("Suzuki","Vitara",500)).getId());
        ids.add(carRepository.save(new Car("Suzuki","Vitara",500)).getId());
        ids.add(carRepository.save(new Car("Suzuki","S-Cross",500)).getId());
        ids.add(carRepository.save(new Car("Peugeot","208",480)).getId());
        return ids;
    }

}
