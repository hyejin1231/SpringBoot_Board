package myself.practice.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myself.practice.board.domain.board.Board;
import myself.practice.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String boards(Model model) {
        List<Board> boards = boardService.BoardList();
        model.addAttribute("boards", boards);
        return "boards/boards";
    }

    @GetMapping("/{uid}")
    public String board(@PathVariable("uid") String uid, Model model) {
        Optional<Board> result = boardService.BoardOne(uid);
        Board board = result.get();
        model.addAttribute("board", board);
        log.info("board={}", board);
        return "boards/board";
    }
}
