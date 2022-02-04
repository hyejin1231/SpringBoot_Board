package myself.practice.board.service;

import lombok.RequiredArgsConstructor;
import myself.practice.board.domain.member.Member;
import myself.practice.board.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId).filter(m -> m.getPassword().equals(password)).orElse(null);
    }

}
