package sem3.backend.ongoing.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 60,nullable = false)
    String brand;

    @Column(length = 60,nullable = false)
    String model;

    @Column(name="price_pr_day")
    double pricePrDay;

    @CreationTimestamp
    LocalDateTime dateCreated;

    @UpdateTimestamp
    LocalDateTime dateUpdated;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<Reservation> reservations = new ArrayList<>();

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }
    public Car(String brand, String model, double pricePrDay) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        reservation.setCar(this);
    }

    public Car() {}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPricePrDay() {
        return pricePrDay;
    }

    public void setPricePrDay(double pricePrDay) {
        this.pricePrDay = pricePrDay;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Objects.equals(dateCreated, car.dateCreated) && Objects.equals(dateUpdated, car.dateUpdated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCreated, dateUpdated);
    }
}
