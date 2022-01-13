package myself.practice.board.repository;

import lombok.RequiredArgsConstructor;
import myself.practice.board.domain.board.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTemplateBoardRepository implements BoardRepository{


    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Board> selectAll() {
        return jdbcTemplate.query("select * from test_write", BoardRowMapper());
    }

    @Override
    public Optional<Board> selectOne(String uid) {
        return Optional.empty();
    }

    @Override
    public void updateViewCnt(String uid) {

    }

    @Override
    public void insertOne(Board board) {

    }

    @Override
    public void updateOne(Board board) {

    }

    @Override
    public void deleteOne(String uid) {

    }

    private RowMapper<Board> BoardRowMapper() {
        return (rs,rowNum)->{
            Board board = new Board();
            board.setUid(rs.getInt("wr_uid"));
            board.setSubject(rs.getString("wr_subject"));
            board.setContents(rs.getString("wr_content"));
            board.setName(rs.getString("wr_name"));
            board.setViewcnt(rs.getString("wr_viewcnt"));
            board.setRegdate(rs.getString("wr_regdate"));
            return board;
        };
    }
}
