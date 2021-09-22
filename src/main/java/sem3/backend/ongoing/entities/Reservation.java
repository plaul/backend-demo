package sem3.backend.ongoing.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private LocalDate reservationDate;

    //@JsonFormat(pattern = "dd-MM-YYYY")  //Should have been done in a DTO
    @Column
    private LocalDate rentalDate;

    @ManyToOne
    Car reservedCar;

    @ManyToOne
    Member reservedTo;

    public Reservation(LocalDate rentalDate, Car reservedCar, Member reservedTo) {
        this.rentalDate = rentalDate;
        this.reservedCar = reservedCar;
        this.reservedTo = reservedTo;
    }

    public Reservation() {}

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Car getReservedCar() {
        return reservedCar;
    }

    public void setReservedCar(Car reservedCar) {
        this.reservedCar = reservedCar;
    }

    public Member getReservedTo() {
        return reservedTo;
    }

    public void setReservedTo(Member reservedTo) {
        this.reservedTo = reservedTo;
    }
}
