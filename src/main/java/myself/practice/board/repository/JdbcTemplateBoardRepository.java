package myself.practice.board.repository;

import lombok.RequiredArgsConstructor;
import myself.practice.board.domain.board.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Board> result = jdbcTemplate.query("select * from test_write where wr_uid=?", BoardRowMapper(), uid);
        return result.stream().findAny();
    }

    @Override
    public void updateViewCnt(String uid) {
        jdbcTemplate.update("update test_write set wr_viewcnt = wr_viewcnt + 1 where wr_uid = ?", uid);
    }

    @Override
    public void insertOne(Board board) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("test_write").usingGeneratedKeyColumns("wr_uid");
//
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("wr_name", board.getName());
//        parameters.put("wr_subject", board.getSubject());
//        parameters.put("wr_content", board.getContents());
//        jdbcInsert.execute(new MapSqlParameterSource(parameters));

        jdbcTemplate.update("insert into test_write (wr_subject, wr_content, wr_name ) values (?,?,?)",
                board.getSubject(), board.getContents(), board.getName());
    }

    @Override
    public void updateOne(Board board) {
        jdbcTemplate.update("update test_write set wr_subject=?, wr_content=? where wr_uid =?",
                board.getSubject(), board.getContents(), board.getUid());
    }

    @Override
    public void deleteOne(String uid) {
        jdbcTemplate.update("delete from test_write where wr_uid = ?", uid);
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
