package sem3.backend.ongoing.services;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sem3.backend.ongoing.dtos.MemberDTO;
import sem3.backend.ongoing.entities.Member;
import sem3.backend.ongoing.repositories.MemberRepository;

import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<MemberDTO> getMembers(){
        Iterable<Member> members = memberRepository.findAll();
        return MemberDTO.memberDTOSfromMember(members);
    }

    public MemberDTO addMember(MemberDTO newMember){
        Member memberToAdd = MemberDTO.memberFromMemberDTO(newMember);
        return new MemberDTO(memberRepository.save(memberToAdd));
    }
}
