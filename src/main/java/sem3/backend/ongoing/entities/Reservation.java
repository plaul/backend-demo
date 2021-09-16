package sem3.backend.ongoing.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @CreationTimestamp
    LocalDate reservationDate;

    LocalDate rentalDate;

    public Reservation(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Reservation(LocalDate rentalDate, Car car, Member member) {
        this.rentalDate = rentalDate;
        this.member = member;
        this.car = car;
    }

    @ManyToOne
    Member member;

    @ManyToOne
    Car car;




}
