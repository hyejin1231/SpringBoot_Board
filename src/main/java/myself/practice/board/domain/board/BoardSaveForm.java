package myself.practice.board.domain.board;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardSaveForm {

    // subject, content, name

    @NotBlank(message = "제목은 반드시 입력해야합니다.")
    private String subject;

    private String contents;

    @NotBlank(message = "작성자는 반드시 입력해야합니다.")
    private String name;
}
