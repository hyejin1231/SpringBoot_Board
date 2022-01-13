package myself.practice.board.repository;


import myself.practice.board.domain.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    public List<Board> selectAll();

    public Optional<Board> selectOne(String uid);

    public void updateViewCnt(String uid);

    public void insertOne(Board board);

    public void updateOne(Board board);

    public void deleteOne(String uid);


}
