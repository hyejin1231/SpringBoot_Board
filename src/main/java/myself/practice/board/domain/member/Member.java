package myself.practice.board.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

// 테이블 생성도 해야함! 테이블 이름 member
@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;
}
