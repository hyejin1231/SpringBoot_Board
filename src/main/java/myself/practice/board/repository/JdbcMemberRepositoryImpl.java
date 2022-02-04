package myself.practice.board.repository;

import lombok.RequiredArgsConstructor;
import myself.practice.board.domain.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMemberRepositoryImpl implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    // 회원 가입
    @Override
    public Member save(Member member) {

        jdbcTemplate.update("insert into member (loginId, name, password) values(?,?,?)",
                member.getLoginId(), member.getName(), member.getPassword());

        return member;
    }

    // 로그인 위해 회원 아이디 검색
    @Override
    public Optional<Member> findByLoginId(String loginId) {
        List<Member> result = jdbcTemplate.query("select * from member where loginId = ?", MemberRowMapper(), loginId);
        return result.stream().findAny();
    }

    private RowMapper<Member> MemberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setLoginId(rs.getString("loginId"));
            member.setName(rs.getString("name"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
