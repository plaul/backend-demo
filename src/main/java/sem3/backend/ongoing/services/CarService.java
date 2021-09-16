package sem3.backend.ongoing.services;

import org.springframework.stereotype.Service;
import sem3.backend.ongoing.dtos.CarDTO;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.repositories.CarRepository;

import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> getCars(String brand, String model) {
        if(brand!=null && model!=null ){
            return CarDTO.carDTOSfromCar(carRepository.findCarByBrandAndModel(brand,model));
        }
        if(brand!=null && model==null ){
            return CarDTO.carDTOSfromCar(carRepository.findCarByBrand(brand));
        }
        return CarDTO.carDTOSfromCar(carRepository.findAll());
    }

    public CarDTO getCar(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        return new CarDTO(car);
    }

    public CarDTO addCar(CarDTO newCar){
        Car carToMake = CarDTO.carFromCarDTO(newCar);
        return new CarDTO(carRepository.save(carToMake));
    }

    public CarDTO editCar(CarDTO carToEdit, int id){
        Car carOrg = carRepository.findById(id).orElseThrow();
        carOrg.setBrand(carToEdit.getBrand());
        carOrg.setModel(carToEdit.getModel());
        carOrg.setPricePrDay(carToEdit.getPricePrDay());
        return new CarDTO(carRepository.save(carOrg));
    }

    public void deleteCar(int id) {
        //Make 404 when car was not found
        carRepository.deleteById(id);
    }
}
