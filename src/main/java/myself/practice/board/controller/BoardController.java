package myself.practice.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myself.practice.board.domain.board.Board;
import myself.practice.board.domain.board.BoardSaveForm;
import myself.practice.board.domain.board.BoardUpdateForm;
import myself.practice.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        boardService.ViewcntUpdate(uid);
        Optional<Board> result = boardService.BoardOne(uid);
        Board board = result.get();
        model.addAttribute("board", board);
        log.info("board={}", board);
        return "boards/board";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("board", new Board());
        return "boards/addForm";
    }

    // 검증 로직 추가하기
    @PostMapping("/add")
    public String addBoard(@Validated @ModelAttribute("board") BoardSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "boards/addForm";
        }

        // 성공 로직
        Board board = new Board();
        board.setSubject(form.getSubject());
        board.setContents(form.getContents());
        board.setName(form.getName());

        boardService.BoardAdd(board);
        return "redirect:/boards";
    }

    @RequestMapping("/{uid}/delete")
    public String deleteBoard(@PathVariable String uid) {
        boardService.BoardDelete(uid);
        return "redirect:/boards";
    }

    @GetMapping("/{uid}/edit")
    public String editForm(@PathVariable("uid") String uid, Model model) {
        Optional<Board> result = boardService.BoardOne(uid);
        Board board = result.get();
        model.addAttribute("board", board);
        log.info("board={}", board);

        return "boards/editForm";
    }

    // 검증 로직 추가하기
    @PostMapping("/{uid}/edit")
    public String edit(@PathVariable String uid, @Validated @ModelAttribute("board") BoardUpdateForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "boards/editForm";
        }

        Board board = new Board();
        board.setSubject(form.getSubject());
        board.setContents(form.getContents());
        board.setUid(Integer.parseInt(uid));

        boardService.BoardUpdate(board);
        return "redirect:/boards/{uid}";
    }
}
