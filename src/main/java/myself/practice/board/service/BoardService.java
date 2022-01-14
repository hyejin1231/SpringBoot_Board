package myself.practice.board.service;

import lombok.RequiredArgsConstructor;
import myself.practice.board.domain.board.Board;
import myself.practice.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> BoardList() {
        return boardRepository.selectAll();
    }

    public Optional<Board> BoardOne(String uid) {
        return boardRepository.selectOne(uid);
    }

    public void BoardAdd(Board board) {
        boardRepository.insertOne(board);
    }

    public void BoardDelete(String uid) {
        boardRepository.deleteOne(uid);
    }
}
