package myself.practice.board.repository;

import lombok.RequiredArgsConstructor;
import myself.practice.board.domain.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Member save(Member member) {

        jdbcTemplate.update("insert into member (loginId, name, password) values(?,?,?)",
                member.getLoginId(), member.getName(), member.getPassword());

        return member;
    }
}
