package sem3.backend.ongoing.repositories;

import org.springframework.data.repository.CrudRepository;
import sem3.backend.ongoing.entities.Member;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member,Integer> {
}
