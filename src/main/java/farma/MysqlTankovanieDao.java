package farma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlTankovanieDao implements TankovanieDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlTankovanieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tankovanie> getAllPodlaIdStroja(int idStroja) {
        String sql = "select tankovanie.id as 'tId', tankovanie.stroj_id as 'tStrojId', tankovanie.mnozstvo as 'tMnozstvo', tankovanie.datum as 'tDatum' from tankovanie where stroj_id=" + idStroja;
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Tankovanie>>() {
            @Override
            public List<Tankovanie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Tankovanie> tankovania = new ArrayList<>();
                Tankovanie tankovanie = null;
                while (rs.next()) {
                    int tankovanieId = rs.getInt("tId");
                    if (tankovanie == null || tankovanieId != tankovanie.getId()) {
                        tankovanie = new Tankovanie();
                        tankovanie.setId(tankovanieId);
                        tankovanie.setStrojId(rs.getInt("tStrojId"));
                        tankovanie.setMnozstvo(rs.getDouble("tMnozstvo"));
                        Timestamp ts = rs.getTimestamp("tDatum");
                        if (ts != null) {
                            tankovanie.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        tankovania.add(tankovanie);
                    }
                }
                return tankovania;
            }
        });
    }

    @Override
    public List<Tankovanie> getAll() {
        String sql = "SELECT tankovanie.id AS 'tId', tankovanie.stroj_id AS 'tStrojId', tankovanie.mnozstvo AS 'tMnozstvo', tankovanie.datum AS 'tDatum' FROM tankovanie;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Tankovanie>>() {
            @Override
            public List<Tankovanie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Tankovanie> tankovania = new ArrayList<>();
                Tankovanie tankovanie = null;
                while (rs.next()) {
                    int tankovanieId = rs.getInt("tId");
                    if (tankovanie == null || tankovanieId != tankovanie.getId()) {
                        tankovanie = new Tankovanie();
                        tankovanie.setId(tankovanieId);
                        tankovanie.setStrojId(rs.getInt("tStrojId"));
                        tankovanie.setMnozstvo(rs.getDouble("tMnozstvo"));
                        Timestamp ts = rs.getTimestamp("tDatum");
                        if (ts != null) {
                            tankovanie.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        tankovania.add(tankovanie);
                    }
                }
                return tankovania;
            }
        });
    }

    @Override
    public void add(Tankovanie tankovanie) {
        if (tankovanie == null) {
            return;
        }
        if (tankovanie.getId() == 0) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("tankovanie");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");

            simpleJdbcInsert.usingColumns("stroj_id", "mnozstvo", "datum");

            Map<String, Object> data = new HashMap<>();
            data.put("stroj_id", tankovanie.getStrojId());
            data.put("mnozstvo", tankovanie.getMnozstvo());
            data.put("datum", tankovanie.getDatum());
            tankovanie.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else {
            // UPDATE
            String sql = "UPDATE tankovanie SET stroj_id = ?, mnozstvo = ?, datum = ? WHERE id = " + tankovanie.getId();
            jdbcTemplate.update(sql,
                    tankovanie.getStrojId(),
                    tankovanie.getMnozstvo(),
                    tankovanie.getDatum());
        }
    }

    @Override
    public void deletePodlaId(int id) {
        String sql = "DELETE FROM tankovanie WHERE tankovanie.id =" + id + ";";
        try {
            int zmazany = jdbcTemplate.update(sql);
        } catch (Exception e) {
        }
    }

    @Override
    public void deleteAllPodlaIdStroja(int idStroja) {
        String sql = "DELETE FROM tankovanie WHERE tankovanie.stroj_id =" + idStroja + ";";
        try {
            int zmazany = jdbcTemplate.update(sql);
        } catch (Exception e) {
        }
    }

}
