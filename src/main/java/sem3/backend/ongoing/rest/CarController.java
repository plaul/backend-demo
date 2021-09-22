package sem3.backend.ongoing.rest;

import org.springframework.web.bind.annotation.*;
import sem3.backend.ongoing.dtos.CarDTO;
import sem3.backend.ongoing.services.CarService;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    List<CarDTO> getCars(@RequestParam(required = false) String brand,
                         @RequestParam(required = false) String model){
        if(brand == null && model != null){
            //We will eventually handle this better
            throw new IllegalArgumentException("make is required when model is supplied");
        }
       return carService.getCars(brand,model);
    }

    @GetMapping("/{id}")
    CarDTO getCar(@PathVariable int id) {

        return carService.getCar(id);
    }

    @PostMapping
    CarDTO addCar(@RequestBody CarDTO newCar) {
        return carService.addCar(newCar);
    }

    @PutMapping("/{id}")
    CarDTO editCar(@RequestBody CarDTO carToEdit,@PathVariable int id ) throws Exception {
        return carService.editCar(carToEdit,id);
    }

    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable int id){
        carService.deleteCar(id);

    }
}
