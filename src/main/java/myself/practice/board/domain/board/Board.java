package myself.practice.board.domain.board;

import lombok.Data;

@Data
public class Board {

    private int uid;
    private String subject;
    private String contents;
    private String name;
    private String viewcnt;
    private String regdate;

    public Board() {
    }

    public Board(int uid, String subject, String contents, String name, String viewcnt, String regdate) {
        this.uid = uid;
        this.subject = subject;
        this.contents = contents;
        this.name = name;
        this.viewcnt = viewcnt;
        this.regdate = regdate;
    }
}
