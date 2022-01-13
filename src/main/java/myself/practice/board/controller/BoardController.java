package myself.practice.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/boards")
public class BoardController {

    @GetMapping
    public String boards(Model model) {
        return "boards/boards";
    }
}
