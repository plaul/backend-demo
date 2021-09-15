package sem3.backend.ongoing.repositories;

import org.springframework.data.repository.CrudRepository;
import sem3.backend.ongoing.entities.Car;

import java.util.List;

public interface CarRepository extends CrudRepository<Car,Integer> {
    List<Car> findCarByBrand(String brand);
    List<Car> findCarByBrandAndModel(String brand, String model);
    List<Car> findCarByPricePrDayLessThan(double pricePrDay);
    List<Car> findCarByPricePrDayLessThanEqual(double pricePrDay);
}
