package sem3.backend.ongoing.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sem3.backend.ongoing.entities.Car;
import sem3.backend.ongoing.entities.Member;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDTO {
    Integer id;
    String firstName;
    String lastName;
    String street;
    String city;
    String zip;
    String email;

    public MemberDTO(Member m) {
        this.id = m.getId();
        this.firstName = m.getFirstName();
        this.lastName = m.getLastName();
        this.street = m.getStreet();
        this.city = m.getCity();
        this.zip = m.getZip();
        this.email = m.getEmail();
    }

    public static List<MemberDTO> memberDTOSfromMember(Iterable<Member> members){
        List<MemberDTO> dtos = StreamSupport.stream(members.spliterator(), false)
                .map(member -> new MemberDTO(member))
                .collect(Collectors.toList());
        return dtos;
    }

    //This does NOT include ID
    public static Member memberFromMemberDTO(MemberDTO mem){
        Member m = new Member();
        m.setFirstName(mem.getFirstName());
        m.setLastName(mem.getLastName());
        m.setStreet(mem.getStreet());
        m.setCity(mem.getCity());
        m.setZip(mem.getZip());
        m.setEmail(mem.getEmail());
        return m;
    }
}
