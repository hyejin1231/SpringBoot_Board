package myself.practice.board.repository;

import myself.practice.board.domain.member.Member;

public interface MemberRepository {

    // 회원 가입만 구현
     Member save(Member member);

}
