package sem3.backend.ongoing.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false,length = 50)
    String firstName;

    @Column(nullable = false,length = 70)
    String lastName;

    @Column(length = 70)
    String street;

    @Column(length = 50)
    String city;

    @Column(length = 4)
    String zip;

    @Column(nullable = false,length = 120,unique = true)
    String email;

    /**
     * We assume that once we have a credit card (NOT PART OF THIS EXERCISE) a Member is approved
     */
    boolean approved;

    /**
     * Ranking of Members for internal use.
     * Valid values -1,0,1,2,3,4,5  Where 5 is a perfect customer and 0 is NOT a perfect customer ;-)
     * -1 indicates NOT SET
     */
    int ranking = -1; //-1 = NOT SET

    @OneToMany(mappedBy = "reservedTo", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    List<Reservation> reservations = new ArrayList<>();

    public Member(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addReservation(Reservation reservation){
        reservations.add(reservation);
        reservation.setReservedTo(this);
    }

    public Member() {}
}
