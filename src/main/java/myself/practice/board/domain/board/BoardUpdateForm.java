package myself.practice.board.domain.board;

import javax.validation.constraints.NotBlank;

public class BoardUpdateForm {

    // subject, contents

    @NotBlank(message = "제목은 반드시 입력해야합니다.")
    private String subject;

    private String contents;
}
