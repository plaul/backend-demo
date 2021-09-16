package sem3.backend.ongoing.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sem3.backend.ongoing.entities.Car;

import javax.persistence.Column;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    int id;
    String brand;
    String model;
    double pricePrDay;

    //Use this for incoming JSON (Create)
    public CarDTO(String brand, String model, double pricePrDay) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
    }

    public CarDTO(Car car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.pricePrDay = car.getPricePrDay();
        this.id = car.getId();
    }

    public static List<CarDTO> carDTOSfromCar(Iterable<Car> cars){
        List<CarDTO> dtos = StreamSupport.stream(cars.spliterator(), false)
                .map(car -> new CarDTO(car))
                .collect(Collectors.toList());
        return dtos;
    }

    public static Car carFromCarDTO(CarDTO car){
        return new Car(car.getBrand(),car.getModel(),car.getPricePrDay());
    }
}
